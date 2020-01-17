package cafe.deadbeef._2e1hnk.mototools.network;

import java.util.Arrays;

import cafe.deadbeef._2e1hnk.mototools.radioprofiles.RadioProfile;

public class Salop extends AbstractNetwork {
	public Salop(RadioProfile radioProfile) {
		super(radioProfile);
		
		this.overrideRepeaters = Arrays.asList("GB7GT", "GB7BJ", "GB7CC", "GB7BX", "GB7OZ", "GB7WD", "GB7DY", "GB7GB", "GB7WN", "GB7EH");

		this.roamName.put(75, "Salop Cluster");

		// 'All' is a special case here - all repeaters will be included in the roaming
		// zone/list
		this.roamRepeaters.put(75, new String[] { "All" });

		this.addTalkgroup(2, 9, "Local/Ref", false, false, true);
		this.addTalkgroup(1, 75, "Cluster", false, false, true, true);
		
		// Add Monitor Talkgroups
		this.addMonitorTalkgroup(2, 9, "Local/Ref", false, true);
	}
}
