package cafe.deadbeef._2e1hnk.mototools.radioprofiles;

public class DM4400VHF extends RadioProfile {
	@Override
	public int getMinFrequencyMhz() {
		return 136;
	}

	@Override
	public int getMaxFrequencyMhz() {
		return 174;
	}

	@Override
	public int getMaxZoneNameLength() {
		// TODO Auto-generated method stub
		return 16;
	}

	@Override
	public int getMaxRadioId() {
		// TODO Auto-generated method stub
		return 16776415;
	}

	@Override
	public int getMaxDigitalContacts() {
		// TODO Auto-generated method stub
		return 500;
	}

	@Override
	public int getMaxAnalogueContacts() {
		// TODO Auto-generated method stub
		return 500;
	}

	@Override
	public int getMaxContactNameLength() {
		// TODO Auto-generated method stub
		return 16;
	}

	@Override
	public int getMaxChannels() {
		// TODO Auto-generated method stub
		return 99;
	}

	@Override
	public int getMaxChannelsPerScanList() {
		// TODO Auto-generated method stub
		return 16;
	}

	@Override
	public int[] getBootLogoSize() {
		// TODO Auto-generated method stub
		return null;
	}
}
