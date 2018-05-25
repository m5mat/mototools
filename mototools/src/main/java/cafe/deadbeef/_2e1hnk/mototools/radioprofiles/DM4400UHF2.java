package cafe.deadbeef._2e1hnk.mototools.radioprofiles;

public class DM4400UHF2 extends RadioProfile {
	@Override
	public int getMinFrequencyMhz() {
		return 450;
	}

	@Override
	public int getMaxFrequencyMhz() {
		return 527;
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
