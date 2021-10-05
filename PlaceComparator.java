import java.util.Comparator;

public class PlaceComparator implements Comparator<Place> {

    @Override
    public int compare(Place place1, Place place2){
        return Integer.compare(place1.getF(), place2.getF());
    }
    
}