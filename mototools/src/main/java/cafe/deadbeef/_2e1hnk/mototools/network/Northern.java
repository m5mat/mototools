package cafe.deadbeef._2e1hnk.mototools.network;

import cafe.deadbeef._2e1hnk.mototools.radioprofiles.RadioProfile;

public class Northern extends AbstractNetwork {
	public Northern(RadioProfile radioProfile) {
		super(radioProfile);
		
		this.roamName.put(862, "M62");
		this.roamRepeaters.put(862, new String[] {"All"});
		
		this.addTalkgroup(1, 9, "Local (Pri)", false, true);
		this.addTalkgroup(2, 9, "Local (Sec)", false, true);
		this.addTalkgroup(1, 1, "WW", true, true);
		this.addTalkgroup(1, 2, "Europe", true, true);
		this.addTalkgroup(1, 13, "WW Eng", true, true);
		this.addTalkgroup(1, 80, "UK UA1", false, true);
		this.addTalkgroup(1, 81, "UK UA2", false, true);
		this.addTalkgroup(1, 82, "UK UA3", false, false);
		this.addTalkgroup(1, 83, "UK UA4", false, false);
		this.addTalkgroup(1, 113, "WW Eng UA1", false, false);
		this.addTalkgroup(1, 123, "WW Eng UA2", false, false);
		this.addTalkgroup(1, 119, "WW UA1", false, false);
		this.addTalkgroup(1, 129, "WW UA2", false, false);
		this.addTalkgroup(1, 235, "UK", true, true);
		this.addTalkgroup(2, 820, "NW Eng", false, true);
		this.addTalkgroup(2, 860, "NE Eng", false, true);
		this.addTalkgroup(2, 862, "M62 Corridor", false, true, true);
		this.addTalkgroup(2, 870, "Wales/Borders", false, true);
		this.addTalkgroup(1, 2351, "CQUK WiresX", false, false);
		
	}
}