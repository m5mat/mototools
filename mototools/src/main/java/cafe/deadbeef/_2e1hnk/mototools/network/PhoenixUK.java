package cafe.deadbeef._2e1hnk.mototools.network;

import cafe.deadbeef._2e1hnk.mototools.radioprofiles.RadioProfile;

public class PhoenixUK extends AbstractNetwork {
	public PhoenixUK(RadioProfile radioProfile) {
		super(radioProfile);

		this.roamName.put(801, "SE Eng Phoenix");
		this.roamName.put(810, "SW Eng Phoenix");
		this.roamName.put(820, "NW Eng Phoenix");
		this.roamName.put(830, "Midl Eng Phoenix");
		this.roamName.put(840, "East Eng Phoenix");
		this.roamName.put(850, "Scotland Phoenix");
		this.roamName.put(860, "NE Eng Phoenix");
		this.roamName.put(870, "Wales Phoenix");
		this.roamName.put(880, "N Irelnd Phoenix");

		// Note: there are more than 16 repeaters in the SE region so need to split them
		// somehow
		this.roamRepeaters.put(801, new String[] { "GB7AK", "GB7AS", "GB7CK", "GB7CF", "GB7CL", "GB7CT", "GB7EK",
				"GB7EP", "GB7EX", "GB7GF", "GB7HA", "GB7HR", "GB7IK", "GB7LO", "GB7NS", "GB7SC" }); // Full!
		// this.roamRepeaters.put(801, new String[] {"GB7SE", "GB7SK", "GB7TH", "GB7WL",
		// "GB7WS"});
		this.roamRepeaters.put(810, new String[] { "GB7AV", "GB7BK", "GB7CT", "GB7EL", "GB7FI", "GB7IT", "GB7KM",
				"GB7SU", "GB7TC", "GB7WL", "GB7WW" });
		this.roamRepeaters.put(820,
				new String[] { "GB7BR", "GB7CA", "GB7FO", "GB7HM", "GB7LP", "GB7MB", "GB7MR", "GB7PN", "GB7UZ" });
		this.roamRepeaters.put(830, new String[] { "GB7CT", "GB7FW", "GB7MO", "GB7SK" });
		this.roamRepeaters.put(840, new String[] { "GB7AL", "GB7CT", "GB7DS", "GB7FU", "GB7HA", "GB7MK", "GB7ND",
				"GB7PE", "GB7PY", "GB7SK", "GB7WS" });
		this.roamRepeaters.put(850, new String[] { "" });
		this.roamRepeaters.put(860, new String[] { "GB7HS", "GB7HX", "GB7LE", "GB7RE", "GB7RV", "GB7TD" });
		this.roamRepeaters.put(870, new String[] { "GB7HM", "GB7BJ" });
		this.roamRepeaters.put(880, new String[] { "GB7HB", "GB7HZ", "GB7LY", "GB7UL" });

		// NOTE: Maximum of 15 in scan list!
		this.addTalkgroup(1, 9, "Local (Pri)", false, true);
		this.addTalkgroup(2, 9, "Local (Sec)", false, true);
		this.addTalkgroup(1, 1, "WW", true, true);
		this.addTalkgroup(1, 2, "Europe", true, true);
		this.addTalkgroup(1, 13, "WW Eng", true, true);
		this.addTalkgroup(1, 80, "UK UA1", false, true);
		this.addTalkgroup(1, 81, "UK UA2", false, false);
		this.addTalkgroup(1, 82, "UK UA3", false, false);
		this.addTalkgroup(1, 83, "UK UA4", false, false);
		this.addTalkgroup(1, 84, "UK UA5", false, false);
		this.addTalkgroup(1, 113, "WW Eng UA1", false, false);
		this.addTalkgroup(1, 123, "WW Eng UA2", false, false);
		this.addTalkgroup(1, 119, "WW UA1", false, false);
		this.addTalkgroup(1, 129, "WW UA2", false, false);
		this.addTalkgroup(1, 235, "UK", true, true);
		this.addTalkgroup(2, 801, "SE Eng", false, true, true);
		this.addTalkgroup(2, 810, "SW Eng", false, true, true);
		this.addTalkgroup(2, 820, "NW Eng", false, true, true);
		this.addTalkgroup(2, 830, "Midl Eng", false, true, true);
		this.addTalkgroup(2, 840, "East Eng", false, true, true);
		this.addTalkgroup(2, 850, "Scotland", false, true, true);
		this.addTalkgroup(2, 860, "NE Eng", false, true, true);
		this.addTalkgroup(2, 870, "Wales/Borders", false, true, true);
		this.addTalkgroup(2, 880, "N Ireland", false, false, true);
		this.addTalkgroup(2, 9990, "Echo Test", false, false);
		// this.addTalkgroup(1, 260, "UK-Poland UA", false, false);
		// this.addTalkgroup(1, 2351, "CQUK WiresX", false, false);

	}
}
