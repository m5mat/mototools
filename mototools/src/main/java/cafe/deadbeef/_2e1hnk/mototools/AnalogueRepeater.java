package cafe.deadbeef._2e1hnk.mototools;

public class AnalogueRepeater extends Repeater {
	public Tone tone;
	
	public AnalogueRepeater(String name, Double output, Double input, Tone tone, boolean addToScanList, LatLng latlng) {
		super();
		this.name = name;
		this.output = output;
		this.input = input;
		this.tone = tone;
		this.addToScanList = addToScanList;
	}

	public Tone getTone() {
		return tone;
	}

	public void setTone(Tone tone) {
		this.tone = tone;
	}
}
