package cafe.deadbeef.data;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class provides a k-Means-esque clustering solution with ethe ability to
 * constrain clusters. It is designed for geo (lat/long) clustering but may be
 * useful for other data types.
 * 
 * @author mattc
 *
 */
public class ConstrainedCluster {
	ArrayList<Point> points = new ArrayList<Point>();
	ArrayList<Point> origins = new ArrayList<Point>();
	Map<String, ArrayList<Point>> clusters = new HashMap<String, ArrayList<Point>>();
	
	/*
	 * k is the number of clusters
	 * m is the maximum number of points in a cluster
	 */
	public ConstrainedCluster() {
	}

	public ConstrainedCluster(ArrayList<Point> points) {
		this.points = points;
	}

	public void addPoint(Point point) {
		this.points.add(point);
	}
	
	private Point findCentrePoint(String pointName, ArrayList<Point> points) {
		double latitudeSum = 0.0, longitudeSum = 0.0;
		for ( Point point : points ) {
			latitudeSum += point.latitude;
			longitudeSum += point.longitude;
		}
		return new Point(pointName, (latitudeSum/points.size()), (longitudeSum/points.size()));
	}

	private void populateOrigins(int k) {
		// If clusters is populated then calculate new origins based on the cluster
		// contents, otherwise find some random origins
		this.origins.clear();
		if ( clusters.size() > 0 ) {
			for ( String clusterId : clusters.keySet() ) {
				System.out.println("Calculating centre point for new origin");
				this.origins.add(this.findCentrePoint(clusterId, clusters.get(clusterId)));
			}
		} else {
			System.out.println("Finding random point for new origin");
			double minLat = points.get(0).latitude, minLng = points.get(0).longitude, maxLat = points.get(0).latitude, maxLng = points.get(0).longitude;
			for ( Point point : points ) {
				if ( point.latitude < minLat ) {
					minLat = point.latitude;
				}
				if ( point.latitude > maxLat ) {
					maxLat = point.latitude;
				}
				if ( point.longitude < minLng ) {
					minLng = point.longitude;
				}
				if ( point.longitude > maxLng ) {
					maxLng = point.longitude;
				}
			}
			Random r = new Random();
			for ( int i = 0; i < k; i++ ) {
				this.origins.add(new Point("origin-" + i, minLat + (maxLat - minLat) * r.nextDouble(), minLng + (maxLng - minLng) * r.nextDouble()));
			}
		}
		for ( Point origin : origins ) {
			System.out.println(String.format("Origin %s (%f, %f)", origin.id, origin.latitude, origin.longitude));
		}
	}
	
	private void buildClusters(int k, int m, int iterations) throws Exception {
		
		this.clusters.clear();
		
		// find the closest origin/point pair
		for ( int iteration = 0; iteration < iterations; iteration++ ) {
			this.populateOrigins(k);
			this.clusters.clear();
			ArrayList<Point> tmpPoints = (ArrayList<Point>) this.points.clone();
			ArrayList<Point> tmpOrigins = (ArrayList<Point>) this.origins.clone();
			
			PointDistanceMatrix distanceMatrix = new PointDistanceMatrix(tmpOrigins, tmpPoints);
			
			System.out.println(distanceMatrix.toString());
			
			while ( distanceMatrix.size() > 0 ) {
				System.out.println(distanceMatrix.size() + " points remaining");
				
				Point closestOrigin = distanceMatrix.findClosestPair()[0];
				Point closestPoint = distanceMatrix.findClosestPair()[1];
				
				System.out.println("Processing origin " + closestOrigin.id + ", point " + closestPoint.id + " (distance " + closestOrigin.distanceTo(closestPoint) + ")");
				
				ArrayList<Point> cluster = new ArrayList<Point>();
				if ( this.clusters.get(closestOrigin.id) != null ) {
					cluster = this.clusters.get(closestOrigin.id);
				}
				cluster.add(closestPoint);
				this.clusters.put(closestOrigin.id, cluster);
				
				distanceMatrix.removePoint(closestPoint);
				
				for ( String originId : this.clusters.keySet() ) {
					System.out.println("Cluster " + originId + " has " + this.clusters.get(originId).size() + " members.");
				}
				
				System.out.println("Cluster size: " + this.clusters.get(closestOrigin.id).size() + ", max permissible: " + m);
				
				if ( this.clusters.get(closestOrigin.id).size() >= m ) {
					System.out.println("Removing origin " + closestOrigin.id);
					distanceMatrix.removeOrigin(closestOrigin);
				}
			}
			
			// Print status
			System.out.println("Iteration " + iteration + ":");
			for ( String originId : this.clusters.keySet() ) {
				System.out.println("\tCluster " + originId + ":");
				for ( Point point : this.clusters.get(originId) ) {
					System.out.println("\t\t" + point.id);
				}
			}
		}
	}
	
	public Point getOrigin(String originId) throws Exception {
		for ( Point origin : origins ) {
			if ( origin.id.equals(originId) ) {
				return origin;
			}
		}
		throw new Exception();
	}
	
	public Map<String, ArrayList<Point>> getClusters() {
		return this.clusters;
	}
	
	public String getCluster(Point point) throws Exception {
		return getCluster(point.id);
	}
	
	public String getCluster(String pointId) throws Exception {
		for ( String cluster : clusters.keySet() ) {
			for ( Point point : clusters.get(cluster) ) {
				if (point.id.equals(pointId) ) {
					return cluster;
				}
			}
		}
		throw new Exception();
	}
	
	public void run(int k, int m, int iterations) {
		try {
			this.populateOrigins(k);
			this.buildClusters(k, m, iterations);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}