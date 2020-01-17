package cafe.deadbeef._2e1hnk.mototools.network;

import cafe.deadbeef._2e1hnk.mototools.radioprofiles.RadioProfile;

public class Standalone extends AbstractNetwork {
	public Standalone(RadioProfile radioProfile) {
		super(radioProfile);
		this.addTalkgroup(1, 9, "Local", false, false, true);
		this.addTalkgroup(2, 9, "Local", false, false, true);
		
		// Add Monitor Talkgroups
		this.addMonitorTalkgroup(1, 9, "Local", false, true);
	}
}
