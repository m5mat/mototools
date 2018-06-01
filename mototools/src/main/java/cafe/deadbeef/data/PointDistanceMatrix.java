package cafe.deadbeef.data;

import java.util.ArrayList;

public class PointDistanceMatrix {
	private ArrayList<Point> origins = new ArrayList<Point>();
	private ArrayList<Point> points = new ArrayList<Point>();
	
	public PointDistanceMatrix() {
		
	}
	
	public PointDistanceMatrix(ArrayList<Point> origins, ArrayList<Point> points) {
		this.setOrigins(origins);
		this.setPoints(points);
	}

	public ArrayList<Point> getOrigins() {
		return origins;
	}

	public void setOrigins(ArrayList<Point> origins) {
		this.origins = origins;
	}
	
	public void addOrigin(Point origin) {
		this.origins.add(origin);
	}

	public void removeOrigin(Point origin) throws Exception {
		this.removeOriginById(origin.id);
	}
	
	public void removeOriginById(String id) throws Exception {
		Point originToRemove = null;

		for ( Point origin : origins ) {
			if ( origin.id.equals(id) ) {
				originToRemove = origin;
			}
		}
		
		if ( originToRemove != null ) {
			origins.remove(originToRemove);
		} else {
			throw new Exception();
		}

	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	
	public void addPoint(Point point) {
		this.points.add(point);
	}

	public void removePoint(Point point) throws Exception {
		this.removePointById(point.id);
	}

	public void removePointById(String id) throws Exception {
		Point pointToRemove = null;

		for ( Point point : points ) {
			if ( point.id.equals(id) ) {
				pointToRemove = point;
			}
		}
		
		if ( pointToRemove != null ) {
			points.remove(pointToRemove);
		} else {
			throw new Exception();
		}
	}

	public Point findClosestOrigin(Point point) throws Exception {
		double distance = 10000.0;
		Point closest = null;
		for ( Point origin : origins ) {
			if ( point.distanceTo(origin) < distance ) {
				distance = point.distanceTo(origin);
				closest = new Point(origin);
			}
		}
		
		if ( closest != null ) {
			return closest;
		}
		
		throw new Exception();
	}
	
	public Point findClosestPoint(Point origin) throws Exception {
		double distance = 10000.0;
		Point closest = null;
		for ( Point point : points ) {
			if ( origin.distanceTo(point) < distance ) {
				distance = point.distanceTo(point);
				closest = new Point(point);
			}
		}
		
		if ( closest != null ) {
			return closest;
		}
		
		throw new Exception();
	}
	
	/*
	 * Returns a point array containing two points. Point[0] is the origin and point[1] is the point.
	 */
	public Point[] findClosestPair () throws Exception {
		System.out.println("Finding closest pair");
		double distance = 10000.0;
		Point closestOrigin = null;
		Point closestPoint = null;
		
		for ( Point origin : origins ) {
			for ( Point point : points ) {
				System.out.println("Origin " + origin.id + " point " + point.id);
				if ( origin.distanceTo(point) < distance ) {
					System.out.println("Best one so far...");
					distance = origin.distanceTo(point);
					closestOrigin = new Point(origin);
					closestPoint = new Point(point);
				}
			}
		}
		
		System.out.println("Closest points are origin " + closestOrigin.id + " point " + closestPoint.id);
		
		if ( closestOrigin != null && closestPoint != null ) {
			Point[] retPoints = new Point[2];
			retPoints[0] = closestOrigin;
			retPoints[1] = closestPoint;
			return retPoints;
		}
		
		throw new Exception();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for ( Point origin : origins) {
			sb.append(String.format("%1$10s: | ", origin.id));
			for ( Point point : points ) {
				sb.append(String.format("%1$5s: %2$9.4f | ", point.id, point.distanceTo(origin)));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public int size() {
		return points.size();
	}
	
}
