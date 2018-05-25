package cafe.deadbeef._2e1hnk.mototools;

public class LatLng implements Comparable<LatLng> {
	public Double lat;
	public Double lng;
	
	public LatLng(Double lat, Double lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
	/**
	 * Default sort is north to south, so just compare latitudes
	 */
	@Override
	public int compareTo(LatLng other) {
		return this.lat.compareTo(other.lat);
	}
	
	public double getDistance(LatLng that) {
		final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
        double dLat  = Math.toRadians((that.lat - this.lat));
        double dLong = Math.toRadians((that.lng - this.lng));

        double startLat = Math.toRadians(this.lat);
        double endLat   = Math.toRadians(that.lat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d		
	}
	
    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
	
}
