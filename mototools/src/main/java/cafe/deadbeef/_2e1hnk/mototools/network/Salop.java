package cafe.deadbeef._2e1hnk.mototools.network;

import cafe.deadbeef._2e1hnk.mototools.radioprofiles.RadioProfile;

public class Salop extends AbstractNetwork {
	public Salop(RadioProfile radioProfile) {
		super(radioProfile);

		this.roamName.put(950, "Salop Cluster");

		// 'All' is a special case here - all repeaters will be included in the roaming
		// zone/list
		this.roamRepeaters.put(950, new String[] { "All" });

		this.addTalkgroup(1, 9, "Local", false, true);
		this.addTalkgroup(2, 950, "Cluster", false, true, true);
		
		// Add Monitor Talkgroups
		this.addMonitorTalkgroup(1, 9, "Local", false, true);
	}
}
