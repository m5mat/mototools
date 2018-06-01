package cafe.deadbeef.data;

import org.junit.Ignore;
import org.junit.Test;

import cafe.deadbeef.data.Point;

public class ConstrainedClusterTest {
	
	@Test
	@Ignore
	public void MatrixTest() throws Exception {
		PointDistanceMatrix matrix = new PointDistanceMatrix();
		matrix.addOrigin(new Point("Origin 1", 1.0, 1.0));
		matrix.addOrigin(new Point("Origin 2", 1.0, 2.0));
		matrix.addOrigin(new Point("Origin 3", 2.0, 1.0));
		matrix.addOrigin(new Point("Origin 4", 2.0, 2.0));
		
		matrix.addPoint(new Point("Point 1", 1.1, 1.1));
		matrix.addPoint(new Point("Point 2", 1.2, 1.2));
		matrix.addPoint(new Point("Point 3", 1.3, 1.3));		
		matrix.addPoint(new Point("Point 4", 1.4, 1.4));
		
		Point[] closestPair = matrix.findClosestPair();
		System.out.println(String.format("Closest Pair are %s and %s, which are %f apart", closestPair[0].id, closestPair[1].id, closestPair[0].distanceTo(closestPair[1])));
		
		System.out.println(matrix.toString());
	}

    @Test
    @Ignore
    public void SimpleTest() {
        ConstrainedCluster tester = new ConstrainedCluster(); // MyClass is tested

        // Class initialisation
        tester.addPoint(new Point("1", 1.0, 1.0));
        tester.addPoint(new Point("2", 1.0, 1.1));
        tester.addPoint(new Point("3", 1.0, 1.2));
        tester.addPoint(new Point("4", 1.5, 1.0));
        tester.addPoint(new Point("5", 1.5, 1.1));
        tester.addPoint(new Point("6", 1.5, 1.2));
        tester.addPoint(new Point("7", 2.0, 1.0));
        tester.addPoint(new Point("8", 2.0, 1.1));
        tester.addPoint(new Point("9", 2.0, 1.2));
        
        tester.run(3, 3, 2);
        
        // assert statements
        //assertEquals(0, tester.multiply(10, 0), "10 x 0 must be 0");
        //assertEquals(0, tester.multiply(0, 10), "0 x 10 must be 0");
        //assertEquals(0, tester.multiply(0, 0), "0 x 0 must be 0");
    }

    @Test
    @Ignore
    public void ComplexTest() {
        ConstrainedCluster tester = new ConstrainedCluster(); // MyClass is tested

        // Class initialisation
        tester.addPoint(new Point("1", 1.5, 1.0));
        tester.addPoint(new Point("2", 1.5, 1.5));
        tester.addPoint(new Point("3", 1.5, 2.0));
        tester.addPoint(new Point("4", 1.6, 1.0));
        tester.addPoint(new Point("5", 1.6, 1.5));
        tester.addPoint(new Point("6", 1.6, 2.0));
        
        tester.run(2, 3, 2);
        
        // assert statements
        //assertEquals(0, tester.multiply(10, 0), "10 x 0 must be 0");
        //assertEquals(0, tester.multiply(0, 10), "0 x 10 must be 0");
        //assertEquals(0, tester.multiply(0, 0), "0 x 0 must be 0");
    }

    @Test
    public void LongSimpleTest() {
        ConstrainedCluster tester = new ConstrainedCluster(); // MyClass is tested

        // Class initialisation
        tester.addPoint(new Point("1", 1.0, 1.0));
        tester.addPoint(new Point("2", 1.0, 1.1));
        tester.addPoint(new Point("3", 1.0, 1.2));
        tester.addPoint(new Point("4", 1.5, 1.0));
        tester.addPoint(new Point("5", 1.5, 1.1));
        tester.addPoint(new Point("6", 1.5, 1.2));
        tester.addPoint(new Point("7", 2.0, 1.0));
        tester.addPoint(new Point("8", 2.0, 1.1));
        tester.addPoint(new Point("9", 2.0, 1.2));
        
        tester.run(3, 3, 20);
        
        try {
        	System.out.println("Point 1 is a member of cluster " + tester.getCluster("1"));
        	System.out.println("Point 2 is a member of cluster " + tester.getCluster("2"));
        	System.out.println("Point 3 is a member of cluster " + tester.getCluster("3"));
        	System.out.println("Point 4 is a member of cluster " + tester.getCluster("4"));
        	System.out.println("Point 5 is a member of cluster " + tester.getCluster("5"));
        	System.out.println("Point 6 is a member of cluster " + tester.getCluster("6"));
        	System.out.println("Point 7 is a member of cluster " + tester.getCluster("7"));
        	System.out.println("Point 8 is a member of cluster " + tester.getCluster("8"));
        	System.out.println("Point 9 is a member of cluster " + tester.getCluster("9"));
    	} catch (Exception e) {
			e.printStackTrace();
		}
        
        // assert statements
        //assertEquals(0, tester.multiply(10, 0), "10 x 0 must be 0");
        //assertEquals(0, tester.multiply(0, 10), "0 x 10 must be 0");
        //assertEquals(0, tester.multiply(0, 0), "0 x 0 must be 0");
    }
}