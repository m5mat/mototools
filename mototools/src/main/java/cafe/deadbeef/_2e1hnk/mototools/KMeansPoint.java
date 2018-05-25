package cafe.deadbeef._2e1hnk.mototools;

public class KMeansPoint extends LatLng {
	
	private String id;
	
	public KMeansPoint(String id, Double lat, Double lng) {
		super(lat, lng);
		this.setId(id);
	}

	public KMeansPoint(Double lat, Double lng) {
		super(lat, lng);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
