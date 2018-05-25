package cafe.deadbeef._2e1hnk.mototools;

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

public class KMeansChart {
	
	Color[] colours = new Color[10];
	
	public KMeansChart() {
		colours[0] = Color.RED;
		colours[1] = Color.BLUE;
		colours[2] = Color.GREEN;
		colours[3] = Color.YELLOW;
		colours[4] = Color.MAGENTA;
		colours[5] = Color.CYAN;
		colours[6] = Color.BLACK;
		colours[7] = Color.GRAY;
		colours[8] = Color.ORANGE;
		colours[9] = Color.PINK;
	}
	
	public void chart(Map<Integer, ArrayList<KMeansPoint>> clusters, String filename) {
		
		Double maxLat = 57.0, minLat = 50.0, maxLng = 2.0, minLng = -6.0;
		/*
		for ( Integer clusterId : clusters.keySet() ) {
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
			img = ImageIO.read(new File(getClass().getClassLoader().getResource("base-map.png").getFile()));
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

		for ( Integer clusterId : clusters.keySet() ) {
			g2d.setColor(this.colours[(clusterId-1)]);
			for ( KMeansPoint point : clusters.get(clusterId) ) {
		        Ellipse2D.Double marker = new Ellipse2D.Double();
		        marker.width = 4;
		        marker.height = 4;
		        marker.x = this.lngToX(img.getWidth(), point.lng, minLng, maxLng);
		        marker.y = this.latToY(img.getHeight(), point.lat, minLat, maxLat);
		        g2d.draw(marker);
		        MotoTools.logger.debug(String.format("Plotted point %s (%f, %f) at (%f, %f)", point.getId(), point.lng, point.lat, marker.x, marker.y));
			}
		}		
		
		g2d.dispose();
		
        try {
            ImageIO.write(img, "PNG", new File(filename));
            MotoTools.logger.info("Generated map image");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	// Note that the origin is at the upper left corner of the image, so the heights are inverted.
	private Double latToY(int imageHeight, Double latitude, Double minLat, Double maxLat) {
		// the 0.95 here is a fudge factor to correct for map projections
		return (imageHeight - ( ( (latitude - minLat) / (maxLat-minLat) ) * imageHeight )) * 0.9;
	}
	
	private Double lngToX(int imageWidth, Double longitude, Double minLng, Double maxLng) {
		return ( (longitude - minLng) / (maxLng-minLng) ) * imageWidth;
	}
}
