package cafe.deadbeef._2e1hnk.mototools.network;

import cafe.deadbeef._2e1hnk.mototools.radioprofiles.RadioProfile;

public class DPMR extends AbstractNetwork {

	public DPMR(RadioProfile radioProfile) {
		super(radioProfile);
		this.addTalkgroup(1, 1, "", false, false, true);
	}

}
