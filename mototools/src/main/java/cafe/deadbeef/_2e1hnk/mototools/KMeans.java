package cafe.deadbeef._2e1hnk.mototools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cafe.deadbeef._2e1hnk.mototools.exceptions.KMeansException;

public class KMeans {
	private Map<String, LatLng> points = new HashMap<String, LatLng>();

	public KMeans() {

	}

	public void add(String identifier, LatLng latLng) {
		points.put(identifier, latLng);
	}

	// Run the K-Means Algorithm to cluster points in to k clusters
	public Map<Integer, ArrayList<KMeansPoint>> run(int k) throws KMeansException {
		Map<Integer, ArrayList<KMeansPoint>> clusters = new HashMap<Integer, ArrayList<KMeansPoint>>();
		Map<Integer, ArrayList<KMeansPoint>> candidateClusters = new HashMap<Integer, ArrayList<KMeansPoint>>();
		Map<Integer, LatLng> origins = new HashMap<Integer, LatLng>();

		Double maxLat = null, minLat = null, maxLng = null, minLng = null;

		for (String id : points.keySet()) {
			MotoTools.logger.debug("Testing point " + id);
			LatLng latLng = points.get(id);
			if (maxLat == null || maxLat < latLng.lat) {
				MotoTools.logger.debug("Setting maxLat to " + latLng.lat);
				maxLat = latLng.lat;
			}
			if (minLat == null || minLat > latLng.lat) {
				MotoTools.logger.debug("Setting minLat to " + latLng.lat);
				minLat = latLng.lat;
			}
			if (maxLng == null || maxLng < latLng.lng) {
				MotoTools.logger.debug("Setting maxLng to " + latLng.lng);
				maxLng = latLng.lng;
			}
			if (minLng == null || minLng > latLng.lng) {
				MotoTools.logger.debug("Setting minLng to " + latLng.lng);
				minLng = latLng.lng;
			}
		}
		MotoTools.logger.debug(String.format("KMeans bounds: maxLat: %f, minLat: %f, maxLng: %f, minLng: %f", maxLat,
				minLat, maxLng, minLng));

		Random r = new Random();

		for (int i = 1; i <= k; i++) {
			LatLng origin = new LatLng(minLat + (maxLat - minLat) * r.nextDouble(),
					minLng + (maxLng - minLng) * r.nextDouble());
			origins.put(i, origin);
			MotoTools.logger.debug(String.format("KMeans origin: %f, %f", origin.lat, origin.lng));
		}

		// Just run 10 iterations for now
		for (int iteration = 1; iteration <= 10; iteration++) {
			for (String pointName : points.keySet()) {
				LatLng point = points.get(pointName);
				double minDist = 10000;
				int closestPoint = 0;
				for (Integer originId : origins.keySet()) {
					LatLng origin = origins.get(originId);
					if (point.getDistance(origin) < minDist) {
						minDist = point.getDistance(origin);
						closestPoint = originId;
					}
				}
				// Check we've found a suitable nearest origin
				if (minDist == 10000 && closestPoint == 0) {
					throw new KMeansException();
				} else {
					// Add point to correct cluster
					ArrayList<KMeansPoint> cluster = new ArrayList<KMeansPoint>();
					if ( candidateClusters.get(closestPoint) != null ) {
						cluster = candidateClusters.get(closestPoint);
					}
					cluster.add(new KMeansPoint(pointName, point.lat, point.lng));
					candidateClusters.put(closestPoint, cluster);
				}
			}

			MotoTools.logger.debug(String.format("KMeans status: iteration %d", iteration));
			for (Integer clusterId : clusters.keySet()) {
				MotoTools.logger.debug(this.clusterStatus(clusters.get(clusterId)));
			}

			// Re-calculate origins
			double latSum = 0, lngSum = 0;
			int members = 0;
			for (Integer clusterId : candidateClusters.keySet()) {
				for (KMeansPoint point : candidateClusters.get(clusterId)) {
					latSum += point.lat;
					lngSum += point.lng;
					members++;
				}
				double meanLat = latSum / members;
				double meanLng = lngSum / members;
				origins.put(clusterId, new LatLng(meanLat, meanLng));
			}
			
			// todo: Check if candidateClusters and clusters are the same, if so exit, otherwise iterate again
			clusters = candidateClusters;
		}
		return clusters;
	}

	private String clusterStatus(ArrayList<KMeansPoint> cluster) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("size:" + cluster.size());
		for (KMeansPoint point : cluster) {
			sb.append(", " + point.getId() + ": { lat:" + point.lat + ", lng:" + point.lng + "}");
		}
		sb.append("}");
		return sb.toString();
	}
}
