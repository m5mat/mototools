package cafe.deadbeef.data;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

public class ScatterChart {
	
	Color[] colours = new Color[10];
	
	public ScatterChart() {
		colours[0] = Color.RED;
		colours[1] = Color.BLUE;
		colours[2] = Color.GREEN;
		colours[3] = Color.YELLOW;
		colours[4] = Color.MAGENTA;
		colours[5] = Color.CYAN;
		colours[6] = Color.BLACK;
		colours[7] = Color.DARK_GRAY;
		colours[8] = Color.ORANGE;
		colours[9] = Color.PINK;
	}
	
	public void chart(Map<String, ArrayList<Point>> clusters, String filename) {
		
		Double maxLat = 57.0, minLat = 50.0, maxLng = 2.0, minLng = -6.0;
		/*
		for ( String clusterId : clusters.keySet() ) {
			for ( KMeansPoint point : clusters.get(clusterId) ) {
				if ( maxLat == null || maxLat < point.lat ) {
					maxLat = point.lat;
				}
				if ( minLat == null || minLat > point.lat ) {
					minLat = point.lat;
				}
				if ( maxLng == null || maxLng < point.lng ) {
					maxLng = point.lng;
				}
				if ( minLng == null || minLng > point.lng ) {
					minLng = point.lng;
				}
			}
		}
		*/
		// BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		BufferedImage img = null;
        try {
			img = ImageIO.read(new File(getClass().getClassLoader().getResource("base-map-greyscale.png").getFile()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		Graphics2D g2d = img.createGraphics();
		
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        int index = 0;
		for ( String clusterId : clusters.keySet() ) {
			g2d.setColor(this.colours[(index%10)]);
			for ( Point point : clusters.get(clusterId) ) {
		        Ellipse2D.Double marker = new Ellipse2D.Double();
		        marker.width = 6;
		        marker.height = 6;
		        marker.x = this.lngToX(img.getWidth(), point.longitude, minLng, maxLng);
		        marker.y = this.latToY(img.getHeight(), point.latitude, minLat, maxLat);
		        g2d.fill(marker);
		        g2d.draw(marker);
		        System.out.println(String.format("Plotted point %s (%f, %f) at (%f, %f)", point.id, point.longitude, point.latitude, marker.x, marker.y));
			}
			index++;
		}		
		
		g2d.dispose();
		
        try {
            ImageIO.write(img, "PNG", new File(filename));
            System.out.println("Generated map image");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	// Note that the origin is at the upper left corner of the image, so the heights are inverted.
	private Double latToY(int imageHeight, Double latitude, Double minLat, Double maxLat) {
		double latRad = latitude * (Math.PI/180);
		double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
		//return (imageHeight/2)-(imageHeight * mercN/(2*Math.PI));
		// the 0.95 here is a fudge factor to correct for map projections
		return (imageHeight - ( ( (latitude - minLat) / (maxLat-minLat) ) * imageHeight * 0.95 ));
	}
	
	private Double lngToX(int imageWidth, Double longitude, Double minLng, Double maxLng) {
		return ( (longitude - minLng) / (maxLng-minLng) ) * imageWidth;
	}
}
