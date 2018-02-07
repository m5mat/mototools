package cafe.deadbeef._2e1hnk.mototools.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cafe.deadbeef._2e1hnk.mototools.Codeplug;
import cafe.deadbeef._2e1hnk.mototools.radioprofiles.RadioProfile;

public abstract class AbstractNetwork implements NetworkInterface {

	public List<Talkgroup> talkgroups = new ArrayList<Talkgroup>();
	public Map<Integer, String> reflectors = new HashMap<Integer, String>();
	public Map<Integer, String> roamName = new HashMap<Integer, String>();
	public Map<Integer, String[]> roamRepeaters = new HashMap<Integer, String[]>();
	
	
	private RadioProfile radioProfile;

	public AbstractNetwork(RadioProfile radioProfile) {
		this.radioProfile = radioProfile;
	}

	protected void addTalkgroup(int timeslot, int talkgroupNumber, String talkgroupName,
			boolean callingChannel, boolean addToScanList) {
		this.addTalkgroup(timeslot, talkgroupNumber, talkgroupName, callingChannel, addToScanList, false);
	}
	
	protected void addTalkgroup(int timeslot, int talkgroupNumber, String talkgroupName,
				boolean callingChannel, boolean addToScanList, boolean roamingTalkgroup) {
		/*
		 * Calculate the best name for the talkgroup, options (in descending length
		 * order) are: - Name TGx Sy - Name TGx - Name x/y - Name x - Name
		 */

		String calling = "";

		if (callingChannel) {
			calling = "*";
		}

		String tgName1 = String.format("%s%s TG%s S%s", calling, talkgroupName, talkgroupNumber, timeslot);
		String tgName2 = String.format("%s%s TG%s", calling, talkgroupName, talkgroupNumber);
		String tgName3 = String.format("%s%s %s/%s", calling, talkgroupName, talkgroupNumber, timeslot);
		String tgName4 = String.format("%s%s %s", calling, talkgroupName, talkgroupNumber);
		String tgName5 = String.format("%s%s", calling, talkgroupName);

		if (tgName1.length() <= radioProfile.MAX_CHANNEL_NAME_LENGTH) {
			talkgroups.add(new Talkgroup(timeslot, talkgroupNumber, tgName1, addToScanList, roamingTalkgroup));
		} else if (tgName2.length() <= radioProfile.MAX_CHANNEL_NAME_LENGTH) {
			talkgroups.add(new Talkgroup(timeslot, talkgroupNumber, tgName2, addToScanList, roamingTalkgroup));
		} else if (tgName3.length() <= radioProfile.MAX_CHANNEL_NAME_LENGTH) {
			talkgroups.add(new Talkgroup(timeslot, talkgroupNumber, tgName3, addToScanList, roamingTalkgroup));
		} else if (tgName4.length() <= radioProfile.MAX_CHANNEL_NAME_LENGTH) {
			talkgroups.add(new Talkgroup(timeslot, talkgroupNumber, tgName4, addToScanList, roamingTalkgroup));
		} else if (tgName5.length() <= radioProfile.MAX_CHANNEL_NAME_LENGTH) {
			talkgroups.add(new Talkgroup(timeslot, talkgroupNumber, tgName5, addToScanList, roamingTalkgroup));
		} else {
			talkgroups.add(new Talkgroup(timeslot, talkgroupNumber,
					tgName5.substring(0, (radioProfile.MAX_CHANNEL_NAME_LENGTH - 1)), addToScanList, roamingTalkgroup));
		}
	}

	protected void addReflector(int number, String name) {
		/*
		 * Calculate the best name for the reflector, options (in descending length
		 * order) are: - Rxxxx FullName - Rxxxx Trunc.Name
		 */

		String refName = String.format("R%s %s", number, name);

		if (refName.length() <= radioProfile.MAX_CHANNEL_NAME_LENGTH) {
			reflectors.put(number, refName);
		} else {
			reflectors.put(number, refName.substring(0, (radioProfile.MAX_CHANNEL_NAME_LENGTH - 1)));
		}
	}

	public String getTalkgroupList() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Talkgroup tg : talkgroups) {
			sb.append(String.format("%s, ", tg.getTalkGroupId()));
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}
}
