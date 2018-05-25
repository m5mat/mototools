package cafe.deadbeef._2e1hnk.mototools;

public class Repeater {
	public String zone;
	public String name;
	public Double output;
	public Double input;
	public Tone tone;
	public boolean addToScanList;
	public LatLng latlng;
	
	public Repeater(String zone, String name, Double output, Double input, Tone tone, boolean addToScanList, LatLng latlng) {
		this.zone = zone;
		this.name = name;
		this.output = output;
		this.input = input;
		this.tone = tone;
		this.addToScanList = addToScanList;
		this.latlng = latlng;
	}
}
