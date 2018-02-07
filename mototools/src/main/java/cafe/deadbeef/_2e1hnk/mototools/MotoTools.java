package cafe.deadbeef._2e1hnk.mototools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cafe.deadbeef._2e1hnk.mototools.exceptions.ContactListFullException;
import cafe.deadbeef._2e1hnk.mototools.exceptions.RadioIdOutOfRangeException;
import cafe.deadbeef._2e1hnk.mototools.network.*;
import cafe.deadbeef._2e1hnk.mototools.radioprofiles.*;

public class MotoTools {

	final static Logger logger = Logger.getLogger(MotoTools.class);
	private static RadioProfile radioProfile;
	private static Map<Integer, RadioProfile> profiles = new HashMap<Integer, RadioProfile>();

	private static String ukLastHeardUrl = "http://www.opendmr.net/uk_lastusers_nodate.php?limit=1000";

	public static void main(String[] args) throws Exception {

		// Load up radio profiles
		profiles.put(2344327, new DP4800UHF());
		// profiles.put(1, new DM4400UHF1());
		// profiles.put(2, new DM4400VHF());
		// profiles.put(3, new DM4600UHF1());
		// profiles.put(4, new DM4600VHF());
		// profiles.put(5, new DP4400UHF());
		// profiles.put(6, new DP4400VHF());
		// profiles.put(7, new DP4800UHF());
		// profiles.put(8, new DP4800VHF());
		// profiles.put(9, new SL4000UHF1());

		for (Integer radioId : profiles.keySet()) {

			RadioProfile candidateRadioProfile = profiles.get(radioId);

			logger.info("Using radio profile " + candidateRadioProfile.getClass().getSimpleName());
			radioProfile = candidateRadioProfile;

			logger.info("Loading codeplug template");
			Codeplug codeplug = new Codeplug("utils/codeplugs/DP4800-template.xml", radioProfile);

			// Set radio ID
			logger.info("Setting Radio ID " + radioId);
			codeplug.setRadioId(radioId);

			// codeplug = populateTestConfig(codeplug);
			codeplug = addSimplexChannels(codeplug);
			codeplug = populateCodeplugFromUKRepeaters(codeplug);
			codeplug = addReflectors(codeplug);
			codeplug = addContactsFromLastHeard(codeplug);

			logger.info("Writing out codeplug XML");
			codeplug.toXml(String.format("utils/codeplugs/MotoTools-%s-ID%d.xml",
					candidateRadioProfile.getClass().getSimpleName(), radioId));

		}

		logger.info("Signing codeplugs");

		Runtime.getRuntime().exec("cmd /c start \"Signing Codeplugs...\" cd utils\\codeplugs && sign-codeplugs.bat");
		// Runtime.getRuntime().exec("cmd /k start \"Signing Codeplugs...\" cd");

		logger.info("Done");
	}

	public static Codeplug populateTestConfig(Codeplug codeplug) throws Exception {
		// Add repeaters before contacts as some group contacts will need to be created
		// and we don't want to risk having no contact slots available

		logger.info("Adding digital repeaters");
		// Add a digital Repeater
		codeplug.addDigitalRepeater("GB7CC", // Repeater Name (Used for zone name)
				430.2750, // Repeater output frequency (device receive frequency)
				439.2750, // Repeater input frequency (device transmit frequency)
				2, // DMR Colour code
				new Salop(radioProfile), // Connectivity
				true // Add channels to scan list?
		);

		codeplug.addDigitalRepeater("GB7KM", // Repeater Name (Used for zone name)
				439.6625, // Repeater output frequency (device receive frequency)
				430.6625, // Repeater input frequency (device transmit frequency)
				3, // DMR Colour code
				new PhoenixUK(radioProfile), // Connectivity
				true // Add channels to scan list?
		);

		logger.info("Adding analogue repeaters");
		// Add an analogue Repeater
		codeplug.addAnalogueRepeater("SW", // Zone Name
				"GB3UK", // Repeater name (used for zone name)
				430.8625, // Repeater output frequency (device receive frequency)
				438.4625, // Repeater input frequency (device transmit frequency)
				new Tone(103.5), // CTCSS Tone Frequency
				true // Add to scan list?
		);

		codeplug.addAnalogueRepeater("SW", // Zone Name
				"GB3GH", // Repeater name (used for zone name)
				433.1250, // Repeater output frequency (device receive frequency)
				434.7250, // Repeater input frequency (device transmit frequency)
				new Tone(103.5), // CTCSS Tone Frequency
				true // Add to scan list?
		);

		logger.info("Adding contacts");
		codeplug.addContact(123456, "2E1HNK - Matt", true);
		codeplug.addContact(12345, "G3MXH - Terry", true);

		return codeplug;
	}

	public static Codeplug populateCodeplugFromUKRepeaters(Codeplug codeplug)
			throws IOException, ParseException, NumberFormatException, JAXBException {

		Document doc = Jsoup.connect("https://www.ukrepeater.net/repeaterlist1.htm").get();

		Elements repeaterLines = doc.select("table.mix tr");

		int i = 0;

		for (Element repeaterElement : repeaterLines) {
			if (i > 0) {
				try {
					Elements repeaterDetails = repeaterElement.children();

					// skip any no-operational repeaters
					// if ( repeaterDetails.get(16).text().equals("NOT.OP") ||
					// repeaterDetails.get(16).text().equals("LICENSED") ) {
					if (repeaterDetails.get(16).text().equals("NOT.OP")) {
						logger.debug("Skipping " + repeaterDetails.get(0).text() + " " + repeaterDetails.get(8).text()
								+ "(" + repeaterDetails.get(16).text() + ")");
						continue;
					}

					String[] rawModes = repeaterDetails.get(4).text().split("/");
					Set<String> modes = new HashSet<String>();

					for (String rawMode : rawModes) {
						modes.add(rawMode);
					}

					// Get Extended Info
					Document repeaterDoc = Jsoup.connect(repeaterDetails.get(0).selectFirst("a").attr("href")).get();
					String textfield = repeaterDoc.selectFirst("div#body table tbody tr td").text();

					String regex = "NoV holder: ([a-zA-Z\\ \\-].*?) \\[.*Band: (\\w*) .* Database Entry: ([0-9a-zA-Z\\ ].*?) Date of NoV Issue: ([0-9a-zA-Z\\ ].*?) Renewal Date: ([0-9a-zA-Z\\ ].*)";

					Pattern pattern = Pattern.compile(regex);

					Matcher matcher = pattern.matcher(textfield);

					String repeaterCallsignLocation = repeaterDetails.get(0).text() + " "
							+ MotoTools.titleCase(repeaterDetails.get(8).text());

					// Get DMR-specific fields
					if (modes.contains("DMR")) {
						logger.debug("Processing DMR");
						String dmr_regex = "cc:(\\d+) Connectivity: ([0-9A-Z\\ ].+?)\\s{1,}(?:ETCC|This|DMR)";

						Pattern dmr_pattern = Pattern.compile(dmr_regex);

						Matcher dmr_matcher = dmr_pattern.matcher(textfield);

						logger.debug("DMR, found " + dmr_matcher.groupCount() + " matches");

						if (dmr_matcher.find()) {
							AbstractNetwork network = new Standalone(radioProfile);

							switch (dmr_matcher.group(2)) {
							case "BRANDMEISTER":
								network = new Brandmeister(radioProfile);
								break;
							case "PHOENIX UK":
								network = new PhoenixUK(radioProfile);
								break;
							case "SW CLUSTER":
								network = new SWCluster(radioProfile);
								break;
							case "SALOP":
								network = new Salop(radioProfile);
								break;
							case "NORTHERN":
								network = new Northern(radioProfile);
								break;
							default:
								logger.error("Repeater network not identified, using Standalone mode. Network was '"
										+ dmr_matcher.group(2) + "'");
								break;
							}

							// Add digital Repeater
							codeplug.addDigitalRepeater(repeaterCallsignLocation, // Repeater Name (Used for zone name)
									Double.parseDouble(repeaterDetails.get(2).text()), // Repeater output frequency
																						// (device
																						// receive frequency)
									Double.parseDouble(repeaterDetails.get(3).text()), // Repeater input frequency
																						// (device
																						// transmit frequency)
									Integer.parseInt(dmr_matcher.group(1)), // DMR Colour code
									network, // Connectivity
									true // Add channels to scan list?
							);
						}
					} else if (modes.contains("AV") || modes.contains("ANL")) {
						// Add analogue Repeater
						String tone = null;
						Double toneFreq = null;

						if (repeaterDetails.get(11).text().length() > 2) {
							tone = repeaterDetails.get(11).text().substring(0,
									(repeaterDetails.get(11).text().length() - 2));
						} else {
							tone = repeaterDetails.get(11).text();
						}

						try {
							toneFreq = Double.parseDouble(tone);
						} catch (Exception e) {
							logger.error("tone couldn't be parsed, defaulting to 123Hz but this is probably wrong!", e);
							toneFreq = 123.0;
						}

						codeplug.addAnalogueRepeater(repeaterDetails.get(10).text(), // Zone Name
								repeaterCallsignLocation, // Repeater name (used for channel name)
								Double.parseDouble(repeaterDetails.get(2).text()), // Repeater output frequency (device
																					// receive frequency)
								Double.parseDouble(repeaterDetails.get(3).text()), // Repeater input frequency (device
																					// transmit frequency)
								new Tone(toneFreq), // CTCSS Tone Frequency
								true // Add to scan list?
						);
					}
				} catch (IllegalStateException e) {
					logger.error("Error adding repeater", e);
				}
			}
			i++;
		}
		return codeplug;
	}

	public static Codeplug addReflectors(Codeplug codeplug) {
		List<AbstractNetwork> networks = new ArrayList<AbstractNetwork>();
		networks.add(new Brandmeister(radioProfile));
		networks.add(new PhoenixUK(radioProfile));
		networks.add(new SWCluster(radioProfile));
		networks.add(new Salop(radioProfile));
		networks.add(new Standalone(radioProfile));

		for (AbstractNetwork network : networks) {
			for (int number : network.reflectors.keySet()) {
				try {
					codeplug.addContact(number, network.reflectors.get(number), true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return codeplug;
	}

	public static Codeplug addContactsFromLastHeard(Codeplug codeplug) {
		BufferedReader reader = null;
		try {
			URL url = new URL(ukLastHeardUrl);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				String[] lines = inputLine.split("<BR>");
				for (String line : lines) {
					String[] parts = line.split(",");
					String contactName = parts[2].toUpperCase().substring(0, 1) + parts[2].toLowerCase().substring(1);
					logger.info("Adding contact [" + parts[1] + " " + contactName + "]");
					try {
						codeplug.addContact(Integer.parseInt(parts[0]), parts[1] + " " + parts[2], true);
					} catch (ContactListFullException e) {
						continue;
					} catch (NumberFormatException | RadioIdOutOfRangeException e) {
						logger.error("RadioID Error", e);
					}
				}
			}
			in.close();

		} catch (IOException e1) {
			logger.error("Error adding contact", e1);
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("Error", e);
				}
		}

		return codeplug;
	}

	public static Codeplug addSimplexChannels(Codeplug codeplug) {

		// A simplex channel is basically a repeater with 0MHz split
		// This adds ALL the simplex channels - we rely on the radioProfile to filter
		// out the ones that the radio isn't capable of. This is why we can get away
		// with having more than 16 in a scan list here
		codeplug.addAnalogueRepeater("Simplex", "V16/S8", 145.2000, 145.2000, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V17", 145.2125, 145.2125, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V18/S9", 145.2250, 145.2250, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V19", 145.2375, 145.2375, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V20/S10", 145.2500, 145.2500, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V21", 145.2625, 145.2625, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V22/S11", 145.2750, 145.2750, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V23", 145.2875, 145.2875, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V24/S12", 145.3000, 145.3000, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V25", 145.3125, 145.3125, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V26/S13", 145.3250, 145.3250, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V27", 145.3375, 145.3375, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V28/S14", 145.3500, 145.3500, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V29", 145.3625, 145.3625, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V30/S15", 145.3750, 145.3750, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V31", 145.3875, 145.3875, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V32/S16", 145.4000, 145.4000, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V33", 145.4125, 145.4125, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V34/S17", 145.4250, 145.4250, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V35", 145.4375, 145.4375, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V36/S18", 145.4500, 145.4500, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V37", 145.4625, 145.4625, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V38/S19", 145.4750, 145.4750, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V39", 145.4875, 145.4875, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "*V40/S20", 145.5000, 145.5000, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V41", 145.5125, 145.5125, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V42/S21", 145.5250, 145.5250, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V43", 145.5375, 145.5375, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V44/S22", 145.5500, 145.5500, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "V45", 145.5625, 145.5625, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "V46/S23", 145.5750, 145.5750, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "U272/SU16", 433.4000, 433.4000, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "U273", 433.4125, 433.4125, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "U274/SU17", 433.4250, 433.4250, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "U275", 433.4375, 433.4375, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "U276/SU18", 433.4500, 433.4500, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "U277", 433.4625, 433.4625, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "U279/SU19", 433.4750, 433.4750, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "U279", 433.4875, 433.4875, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "*U280/SU20", 433.5000, 433.5000, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "U281", 433.5125, 433.5125, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "U282/SU21", 433.5250, 433.5250, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "U283", 433.5375, 433.5375, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "U284/SU22", 433.5500, 433.5500, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "U285", 433.5625, 433.5625, new Tone(123), false);
		codeplug.addAnalogueRepeater("Simplex", "U286/SU23", 433.5750, 433.5750, new Tone(123), true);
		codeplug.addAnalogueRepeater("Simplex", "U287", 433.5875, 433.5875, new Tone(123), false);

		codeplug.addAnalogueRepeater("DV Hotspots", "VHF 1", 144.8125, 144.8125, new Tone(123), false);
		codeplug.addAnalogueRepeater("DV Hotspots", "VHF 2", 144.8250, 144.8250, new Tone(123), false);
		codeplug.addAnalogueRepeater("DV Hotspots", "VHF 3", 144.8375, 144.8375, new Tone(123), false);
		codeplug.addAnalogueRepeater("DV Hotspots", "VHF 4", 144.8500, 144.8500, new Tone(123), false);
		codeplug.addAnalogueRepeater("DV Hotspots", "VHF 5", 144.8625, 144.8625, new Tone(123), false);

		codeplug.addAnalogueRepeater("DV Hotspots", "UHF 1 (QRP)", 434.0000, 434.0000, new Tone(123), false);
		codeplug.addAnalogueRepeater("DV Hotspots", "UHF 2", 434.4750, 434.4750, new Tone(123), false);
		codeplug.addAnalogueRepeater("DV Hotspots", "UHF 3", 434.4875, 434.4875, new Tone(123), false);
		codeplug.addAnalogueRepeater("DV Hotspots", "UHF 4", 434.5000, 434.5000, new Tone(123), false);
		codeplug.addAnalogueRepeater("DV Hotspots", "UHF 5", 434.5125, 434.5125, new Tone(123), false);
		codeplug.addAnalogueRepeater("DV Hotspots", "UHF 6", 434.5250, 434.5250, new Tone(123), false);

		return codeplug;
	}

	public static String titleCase(String str) {
		String[] words = str.split("\\s");
		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.append(capFirst(word) + " ");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	public static String maxLength(String str, int length) {
		StringBuilder sb = new StringBuilder(str);
		sb.setLength(length);
		return sb.toString();
	}

	public static String capFirst(String str) {
		return str.toUpperCase().substring(0, 1) + str.toLowerCase().substring(1);
	}

}
