package cafe.deadbeef._2e1hnk.mototools;

import java.util.Comparator;

public class LocationComparator implements Comparator<Repeater> {
	
	  @Override
	  public int compare(Repeater r1, Repeater r2) {
	    return r1.latlng.compareTo(r2.latlng);
	  }
}
