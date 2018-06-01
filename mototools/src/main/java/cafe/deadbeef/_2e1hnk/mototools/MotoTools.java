package cafe.deadbeef._2e1hnk.mototools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import cafe.deadbeef._2e1hnk.mototools.exceptions.ContactListFullException;
import cafe.deadbeef._2e1hnk.mototools.exceptions.KMeansException;
import cafe.deadbeef._2e1hnk.mototools.exceptions.RadioIdOutOfRangeException;
import cafe.deadbeef._2e1hnk.mototools.network.*;
import cafe.deadbeef._2e1hnk.mototools.radioprofiles.*;
import cafe.deadbeef.data.ConstrainedCluster;
import cafe.deadbeef.data.Point;
import cafe.deadbeef.data.ScatterChart;

public class MotoTools {

	final static Logger logger = Logger.getLogger(MotoTools.class);
	private static RadioProfile radioProfile;
	private static Map<Integer, RadioProfile> profiles = new HashMap<Integer, RadioProfile>();

	private static List<MotoToolsJob> jobs = new ArrayList<MotoToolsJob>();

	private static String ukLastHeardUrl = "http://www.opendmr.net/uk_lastusers_nodate.php?limit=1000";

	public static void main(String[] args) throws Exception {

		// Create region filters

		// Dummy region
		Filter dummyFilter = new Filter("Dummy");
		dummyFilter.addToFilterKey("region", "dummy");

		// Exclude southern regions to create a codeplug for use in the north
		Filter northFilter = new Filter("North Region");
		northFilter.addToFilterKey("region", "SW");
		northFilter.addToFilterKey("region", "SE");
		northFilter.addToFilterKey("region", "MID");
		northFilter.addToFilterKey("region", "WM");
		
		// Exclude extreme southern and northern regions to create a codeplug for use in
		// central UK
		Filter centralFilter = new Filter("Central Region");
		centralFilter.addToFilterKey("region", "SCO");
		centralFilter.addToFilterKey("region", "NI");
		centralFilter.addToFilterKey("region", "SW");
		centralFilter.addToFilterKey("region", "SE");
		centralFilter.addToFilterKey("region", "NOR");
		
		// Exclude northern regions to create a codeplug for use in the south
		Filter southFilter = new Filter("South Region");
		southFilter.addToFilterKey("region", "SCO");
		southFilter.addToFilterKey("region", "NI");
		southFilter.addToFilterKey("region", "NOR");
		
		// Exclude northern and south-eastern regions to create a codeplug for use in
		// the south west
		Filter southWestFilter = new Filter("South-West Region");
		southWestFilter.addToFilterKey("region", "SCO");
		southWestFilter.addToFilterKey("region", "NI");
		southWestFilter.addToFilterKey("region", "NOR");
		southWestFilter.addToFilterKey("region", "SE");
		southWestFilter.addToFilterKey("region", "MID");
		
		// Exclude northern and welsh regions to create a codeplug for use in the
		// south-east
		Filter southEastFilter = new Filter("South-East Region");
		southEastFilter.addToFilterKey("region", "SCO");
		southEastFilter.addToFilterKey("region", "NI");
		southEastFilter.addToFilterKey("region", "NOR");
		southEastFilter.addToFilterKey("region", "WM");
		
		// Load up radio profiles
		profiles.put(2344327, new DP4800UHF());
		profiles.put(2341514, new DP4800UHF());
		// profiles.put(2, new DM4400VHF());
		// profiles.put(3, new DM4600UHF1());
		// profiles.put(4, new DM4600VHF());
		// profiles.put(5, new DP4400UHF());
		// profiles.put(6, new DP4400VHF());
		// profiles.put(7, new DP4800UHF());
		// profiles.put(8, new DP4800VHF());
		// profiles.put(9, new SL4000UHF1());

		// Load up job list
		jobs.add(new MotoToolsJob(2344327, new DP4800UHF(), southWestFilter));
		jobs.add(new MotoToolsJob(2344327, new DP4800UHF(), southEastFilter));
		jobs.add(new MotoToolsJob(2344327, new DP4800UHF(), centralFilter));
		jobs.add(new MotoToolsJob(2344327, new DP4800UHF(), southFilter));
		jobs.add(new MotoToolsJob(2344327, new DP4800UHF(), northFilter));
		// jobs.add(new MotoToolsJob(2341514, new DP4800UHF(), centralFilter));

		for (MotoToolsJob job : jobs) {
			Integer radioId = job.getRadioId();

			RadioProfile candidateRadioProfile = profiles.get(radioId);

			logger.info("Using radio profile " + candidateRadioProfile.getClass().getSimpleName());
			radioProfile = candidateRadioProfile;

			logger.info("Loading codeplug template");
			Codeplug codeplug = new Codeplug("utils/codeplugs/DP4800-template.xml", radioProfile);

			logger.info("Generating radio boot image");
			codeplug.setBootImage("", "DMR ID: " + radioId.toString(), job.getFilter().getFilterName());

			// Set radio ID
			logger.info("Setting Radio ID " + radioId);
			codeplug.setRadioId(radioId);

			Filter filter = job.getFilter();

			codeplug = addSimplexChannels(codeplug); // Checked - OK

			codeplug = populateCodeplugFromUKRepeaters(codeplug, filter); // Checked - OK
			// codeplug = addReflectors(codeplug); // Checked - OK (but uses a lot of
			// contact slots!)
			codeplug = addContactsFromLastHeard(codeplug); // Checked - OK

			String outputFileName = String.format("utils/codeplugs/MotoTools-%s-ID%d-%s.xml",
					candidateRadioProfile.getClass().getSimpleName(), radioId, filter.getFilterName());
			logger.info("Writing out codeplug XML to " + outputFileName);
			codeplug.toXml(outputFileName);
		}

		logger.info("Signing codeplugs");

		Runtime.getRuntime().exec("cmd /c start \"Signing Codeplugs...\" sign-codeplug.bat", null,
				new File("utils\\codeplugs"));

		logger.info("Done");
	}

	public static Codeplug populateCodeplugFromUKRepeaters(Codeplug codeplug, Filter filter)
			throws Exception {

		// Create a constrained cluster with 10 clusters and 16 members per cluster
		ConstrainedCluster clusters = new ConstrainedCluster();

		Map<String, AnalogueRepeater> analogueRepeaters = new HashMap<String, AnalogueRepeater>();
		Map<String, DMRRepeater> dmrRepeaters = new HashMap<String, DMRRepeater>();

		Document doc = Jsoup.connect("https://www.ukrepeater.net/repeaterlist1.htm").get();

		Elements repeaterLines = doc.select("table.mix tr");

		int i = 0;

		for (Element repeaterElement : repeaterLines) {

			if (i > 0) {
				try {
					Elements repeaterDetails = repeaterElement.children();

					// TODO: Add this to the filter
					// skip any no-operational repeaters
					// if ( repeaterDetails.get(16).text().equals("NOT.OP") ||
					// repeaterDetails.get(16).text().equals("LICENSED") ) {
					if (repeaterDetails.get(16).text().equals("NOT.OP")) {
						logger.debug("Skipping " + repeaterDetails.get(0).text() + " " + repeaterDetails.get(8).text()
								+ " (" + repeaterDetails.get(16).text() + ")");
						continue;
					}

					// Check the region name against the filter
					if (!filter.permits("region", repeaterDetails.get(10).text())) {
						logger.info("Skipping " + repeaterDetails.get(0).text() + " " + repeaterDetails.get(8).text()
								+ " due to region filter (" + repeaterDetails.get(10).text() + ")");
						continue;
					}

					String[] rawModes = repeaterDetails.get(4).text().split("/");
					Set<String> modes = new HashSet<String>();

					for (String rawMode : rawModes) {
						modes.add(rawMode);
					}

					// Get Extended Info
					for (int attempt = 1; attempt <= 5; attempt++) {
						String url = repeaterDetails.get(0).selectFirst("a").attr("href");
						try {
							Connection.Response response = Jsoup.connect(url).timeout(10000).execute();
							if (response.statusCode() != 200) {
								logger.error(String.format("Error %d fetching %s", response.statusCode(), url));
							} else {
								Document repeaterDoc = response.parse();

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
											logger.error(
													"Repeater network not identified, using Standalone mode. Network was '"
															+ dmr_matcher.group(2) + "'");
											break;
										}

										// Add digital Repeater

										// This is so that we can add the repeater to the regional zone later (once the
										// zones have been identified)
										dmrRepeaters.put(repeaterCallsignLocation,
												new DMRRepeater(repeaterCallsignLocation,
														Double.parseDouble(repeaterDetails.get(2).text()),
														Double.parseDouble(repeaterDetails.get(3).text()),
														Integer.parseInt(dmr_matcher.group(1)), network, true));

										// This is the individual Repeater's zone
										codeplug.addDigitalRepeater(repeaterCallsignLocation, // Repeater Name (Used for
																								// zone name)
												Double.parseDouble(repeaterDetails.get(2).text()), // Repeater output
																									// frequency
																									// (device
																									// receive
																									// frequency)
												Double.parseDouble(repeaterDetails.get(3).text()), // Repeater input
																									// frequency
																									// (device
																									// transmit
																									// frequency)
												Integer.parseInt(dmr_matcher.group(1)), // DMR Colour code
												network, // Connectivity
												true // Add channels to scan list?
										);
										clusters.addPoint(new Point(repeaterCallsignLocation,
												Double.parseDouble(repeaterDetails.get(14).text()),
												Double.parseDouble(repeaterDetails.get(15).text())));
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
										logger.error(
												"tone couldn't be parsed, defaulting to 123Hz but this is probably wrong!",
												e);
										toneFreq = 123.0;
									}

									AnalogueRepeater repeater = new AnalogueRepeater(repeaterCallsignLocation,
											Double.parseDouble(repeaterDetails.get(2).text()),
											Double.parseDouble(repeaterDetails.get(3).text()), new Tone(toneFreq), true,
											new LatLng(Double.parseDouble(repeaterDetails.get(14).text()),
													Double.parseDouble(repeaterDetails.get(15).text())));

									analogueRepeaters.put(repeaterCallsignLocation, repeater);

									clusters.addPoint(new Point(repeaterCallsignLocation,
											Double.parseDouble(repeaterDetails.get(14).text()),
											Double.parseDouble(repeaterDetails.get(15).text())));
								}
								break;
							}
						} catch (SocketTimeoutException e) {
							logger.error(String.format("Connection to %s timed out on attempt %d", url, attempt));
						}

						/*
						 * codeplug.addAnalogueRepeater(repeaterDetails.get(10).text(), // Zone Name
						 * repeaterCallsignLocation, // Repeater name (used for channel name)
						 * Double.parseDouble(repeaterDetails.get(2).text()), // Repeater output
						 * frequency (device // receive frequency)
						 * Double.parseDouble(repeaterDetails.get(3).text()), // Repeater input
						 * frequency (device // transmit frequency) new Tone(toneFreq), // CTCSS Tone
						 * Frequency true // Add to scan list? );
						 */
					}
				} catch (IllegalStateException e) {
					logger.error("Error adding repeater", e);
				}
			}
			i++;
		}

		int k = Math.floorDiv( (analogueRepeaters.size() + dmrRepeaters.size()), 10 );
		clusters.run(k, 16, 10);

		ScatterChart chart = new ScatterChart();
		chart.chart(clusters.getClusters(), String.format("utils/map-%s.png", filter.getFilterName()));

		Map<String, Nominatim_Address> originAddresses = new HashMap<String, Nominatim_Address>();

		for (String clusterId : clusters.getClusters().keySet()) {
			Point origin;
			try {
				origin = clusters.getOrigin(clusterId);
				originAddresses.put(clusterId, getRegionName(origin.getLatitude(), origin.getLongitude()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (String clusterId : originAddresses.keySet()) {
			logger.debug(String.format("%s: %s", clusterId, originAddresses.get(clusterId).toJson()));
		}

		// We want to look for the largest unique are in the address field to represent
		// the cluster
		Map<String, String> clusterName = new HashMap<String, String>();
		String[] addressFields = { "country", "state", "state_district", "county", "city" };
		for (String clusterId : originAddresses.keySet()) {
			Nominatim_Address clusterAddress = originAddresses.get(clusterId);
			int biggestArea = 0;
			logger.debug(String.format("Processing %s: %s", clusterId, clusterAddress.toJson()));
			for (String otherClusterId : originAddresses.keySet()) {
				if (otherClusterId.equals(clusterId)) {
					logger.debug("Skipping " + otherClusterId);
					continue;
				}
				Nominatim_Address otherClusterAddress = originAddresses.get(otherClusterId);
				logger.debug(String.format("Checking against %s: %s", otherClusterId, otherClusterAddress.toJson()));
				for (int j = biggestArea; j < addressFields.length; j++) {
					if (clusterAddress.getFieldValue(addressFields[j]) == null) {
						continue;
					}
					if (otherClusterAddress.getFieldValue(addressFields[j]) != null) {
						if (otherClusterAddress.getFieldValue(addressFields[j])
								.equals(clusterAddress.getFieldValue(addressFields[j]))) {
							biggestArea++;
						}
					}
				}
				logger.debug("biggestArea is " + biggestArea);
			}

			while (clusterAddress.getFieldValue(addressFields[biggestArea]) == null
					&& biggestArea < addressFields.length) {
				logger.debug("increasing biggestArea value to " + (biggestArea + 1));
				biggestArea++;
				if ( biggestArea >= addressFields.length ) {
					biggestArea--;
				}
			}

			logger.debug(String.format("Biggest unique area for %s is %s (%d)", clusterId, addressFields[biggestArea],
					biggestArea));

			clusterName.put(clusterId, clusterAddress.getFieldValue(addressFields[biggestArea]));

			logger.debug(String.format("Cluster %s is at %s", clusterId, clusterName.get(clusterId)));
		}

		// Finally, add the repeaters to the appropriate zone
		for ( String repeaterKey : analogueRepeaters.keySet() ) {
			AnalogueRepeater repeater = analogueRepeaters.get(repeaterKey);
			logger.debug(String.format("%s: %s", repeaterKey, repeater.name));
		}
		for (String clusterId : clusters.getClusters().keySet()) {

			String zoneName = reduceTitle(clusterName.get(clusterId), 16);

			for (Point point : clusters.getClusters().get(clusterId)) {
				logger.debug("Finding " + point.getId());
				if ( analogueRepeaters.keySet().contains(point.getId()) ) {
					AnalogueRepeater repeater = analogueRepeaters.get(point.getId());
					logger.debug(repeater.toJson());
					codeplug.addAnalogueRepeater(zoneName, // Zone Name
							repeater.getName(), // Repeater name (used for channel name)
							repeater.getOutput(), // Repeater output frequency (device receive frequency)
							repeater.getInput(), // Repeater input frequency (device transmit frequency)
							repeater.getTone(), // CTCSS Tone Frequency
							repeater.isAddToScanList() // Add to scan list?
					);
				} else if ( dmrRepeaters.keySet().contains(point.getId()) ) {
					DMRRepeater repeater = dmrRepeaters.get(point.getId());
					logger.debug(repeater.toJson());
					codeplug.addBasicDigitalRepeaterToZone(zoneName, // Zone Name
							repeater.getName(), // Repeater name (used for channel name)
							repeater.getOutput(), // Repeater output frequency (device receive frequency)
							repeater.getInput(), // Repeater input frequency (device transmit frequency)
							repeater.getColourCode(), // CTCSS Tone Frequency
							repeater.getNetwork(), // DMR Network
							repeater.isAddToScanList() // Add to scan list?
					);
				} else {
					// ERROR - we couldn't find a record of the repeater details. This shouldn't happen...
					// TODO: Better exception here
					throw new Exception();
				}
			}
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

	/*
	 * Sample JSON
	 * "boundingbox":["-34.44159","-34.4370994","-58.7086067","-58.7044712"]}
	 */
	class Nominatim {
		int place_id;
		String license;
		String osm_type;
		String osm_id;
		double lat;
		double lon;
		String display_name;
		Nominatim_Address address;
	}

	class Nominatim_Address {
		String city;
		String county;
		String state_district;
		String state;
		String postcode;
		String country;
		String country_code;

		public String getFieldValue(String fieldName) {
			Field field;
			try {
				field = Nominatim_Address.class.getDeclaredField(fieldName);
				return (String) field.get(this);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		}

		public String toJson() {
			return String.format(
					"{'city':'%s', 'county':'%s', 'state_district':'%s', 'state':'%s', 'postcode':'%s', 'country':'%s', 'country_code':'%s'}",
					city, county, state_district, state, postcode, country, country_code);
		}
	}

	public static Nominatim_Address getRegionName(double latitude, double longitude) {
		String urlString = String.format(
				"https://nominatim.openstreetmap.org/reverse?format=json&lat=%f&lon=%f&zoom=10", latitude, longitude);

		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1) {
				buffer.append(chars, 0, read);
			}

			Gson gson = new Gson();
			Nominatim area = gson.fromJson(buffer.toString(), Nominatim.class);

			return area.address;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	/*
	 * Reduce a string to a defined maximum length, making some intelligent
	 * decisions along the way.
	 */
	public static String reduceTitle(String str, int maxLength) {
		if ( str.length() <= maxLength ) {
			return str;
		}
		
		// Reduce something like 'North Devon' to 'N Devon'
		str.replaceAll("\\bNorth East\\b", "NE");
		str.replaceAll("\\bNorth West\\b", "NW");
		str.replaceAll("\\bSouth East\\b", "SE");
		str.replaceAll("\\bSouth West\\b", "SW");
		str.replaceAll("\\bNorth\\b", "N");
		str.replaceAll("\\bSouth\\b", "S");
		str.replaceAll("\\bEast\\b", "E");
		str.replaceAll("\\bWest\\b", "E");
		
		if ( str.length() <= maxLength ) {
			return str;
		}
		
		// Reduce something like 'Bristol and Bath' to 'Bristol Bath'
		str.replaceAll("\\band\\b", " ");
		
		if ( str.length() <= maxLength ) {
			return str;
		}
		
		// Reduce something like 'Sidmouth' to 'Sidm'th'
		str.replaceAll("mouth\\b", "m'th");
		
		if ( str.length() <= maxLength ) {
			return str;
		}
		
		// Nuclear option...
		str = str.substring(0, (maxLength - 1));
						
		return str;
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
