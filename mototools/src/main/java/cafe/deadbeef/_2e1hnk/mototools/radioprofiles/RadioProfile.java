package cafe.deadbeef._2e1hnk.mototools.radioprofiles;

public abstract class RadioProfile {
	public static int MAX_RADIO_ID = 16776415;
	public static int MAX_DIGITAL_CONTACTS = 500;
	public static int MAX_ANALOGUE_CONTACTS = 500;
	public static int MAX_CONTACT_NAME_LENGTH = 16;
	public static int MAX_CHANNEL_NAME_LENGTH = 16;
	public static int MAX_ZONE_NAME_LENGTH = 16;
	
	public abstract int getMinFrequencyMhz();
	public abstract int getMaxFrequencyMhz();
	public abstract int getMaxRadioId();
	public abstract int getMaxDigitalContacts();
	public abstract int getMaxAnalogueContacts();
	public abstract int getMaxContactNameLength();
	public abstract int getMaxZoneNameLength();
	public abstract int getMaxChannels();
	
}
