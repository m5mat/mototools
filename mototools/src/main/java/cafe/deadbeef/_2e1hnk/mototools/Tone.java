package cafe.deadbeef._2e1hnk.mototools;

import java.util.HashMap;
import java.util.Map;

public class Tone {
	private double freq;
	private Map<Double, String> lookup;
	
	public Tone() {
		this.lookup = new HashMap<Double, String>();
		this.lookup.put(67.0, "STR_XZ");
		this.lookup.put(69.3, "STR_WZ");
		this.lookup.put(71.9, "STR_XA");
		this.lookup.put(74.4, "STR_WA");
		this.lookup.put(77.0, "STR_XB");
		this.lookup.put(79.7, "STR_WB");
		this.lookup.put(82.5, "STR_YZ");
		this.lookup.put(85.4, "STR_YA");
		this.lookup.put(88.5, "STR_YB");
		this.lookup.put(91.5, "STR_ZZ");
		this.lookup.put(94.8, "STR_ZA");
		this.lookup.put(97.4, "STR_ZB");
		this.lookup.put(100.0, "STR_1Z");
		this.lookup.put(103.5, "STR_1A");
		this.lookup.put(107.2, "STR_1B");
		this.lookup.put(110.9, "STR_2Z");
		this.lookup.put(114.8, "STR_2A");
		this.lookup.put(118.8, "STR_2B");
		this.lookup.put(123.0, "STR_3Z");
		this.lookup.put(127.3, "STR_3A");
		this.lookup.put(131.8, "STR_3B");
		this.lookup.put(136.5, "STR_4Z");
		this.lookup.put(141.3, "STR_4A");
		this.lookup.put(146.2, "STR_4B");
		this.lookup.put(151.4, "STR_5Z");
		this.lookup.put(156.7, "STR_5A");
		this.lookup.put(162.2, "STR_5B");
		this.lookup.put(167.9, "STR_6Z");
		this.lookup.put(173.8, "STR_6A");
		this.lookup.put(179.9, "STR_6B");
	}
	
	public Tone(double freq) {
		this();
		this.setFreq(freq);
	}
	
	public Tone(String code) {
		this();
		for ( Double freq : lookup.keySet() ) {
			if ( lookup.get(freq).equals(code) ) {
				this.setFreq(freq);
			}
		}
	}

	public double getFreq() {
		return freq;
	}

	public void setFreq(double freq) {
		this.freq = freq;
	}

	public String getCode() {
		return lookup.get(this.freq);
	}
	
}
