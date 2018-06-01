package cafe.deadbeef._2e1hnk.mototools.network;

import cafe.deadbeef._2e1hnk.mototools.radioprofiles.RadioProfile;

public class SWCluster extends AbstractNetwork {
	public SWCluster(RadioProfile radioProfile) {
		super(radioProfile);
		
		this.roamName.put(950, "SW Cluster");
		this.roamRepeaters.put(950, new String[] {"All"});
		
		this.addTalkgroup(1, 9, "Local", false, true);
		this.addTalkgroup(2, 950, "Cluster", false, true, true);
		
		// Add Monitor Talkgroups
		this.addMonitorTalkgroup(1, 9, "Local", false, true);
	}
}
