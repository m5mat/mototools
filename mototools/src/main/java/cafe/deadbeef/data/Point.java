package cafe.deadbeef.data;

public class Point {
	double latitude;
	double longitude;
	String id;
	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
	
	public Point() {
		
	}
	
	public Point(Point another) {
		this.latitude = another.latitude;
		this.longitude = another.longitude;
		this.id = another.id;
	}
	
	public Point(String id, double latitude, double longitude) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double distanceTo(Point that) {
        double dLat  = Math.toRadians((that.latitude - this.latitude));
        double dLong = Math.toRadians((that.longitude - this.longitude));

        double startLat = Math.toRadians(this.latitude);
        double endLat   = Math.toRadians(that.latitude);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
	}
	
    public double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    public boolean equals(Point that) {
    	if ( this.id.equals(that.id) && this.latitude == that.latitude && this.longitude == that.longitude ) {
    		return true;
    	}
    	return false;
    }
}
