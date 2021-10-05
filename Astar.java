import java.util.LinkedList;

public class Astar {

    private String solution;
    private int[][] maze;  //always 12 x 12, walls are 1s paths are 0s
    private LinkedList<Place> visited;
    private LinkedList<Place> pending;

    public Astar(int[][] maze){
        this.setSolution("");
        this.maze = maze;
        this.visited = new LinkedList<>();
        this.pending = new LinkedList<>();
        pending.add(new Place());
    }

    public void solveMaze(){
        Place curr = pending.getFirst();
        while (!(curr.getI() == 11 && curr.getJ() == 11)){
            curr = pending.getFirst();
            pending.addAll(expandPending(curr));
            pending.sort(new PlaceComparator());
            visited.add(curr);
            pending.remove(curr);
        }
        curr = getPrevEnd();
        do {
            visited.remove(curr);
            this.solution = curr.toString() + this.solution;
            curr = getPrev(curr);
        } while (!(curr.getI() == 0 && curr.getJ() == 0));
        System.out.println(this.solution);
    }

    private Place getPrevEnd(){
        Place prevEnd1 = null, prevEnd2 = null;
        for (Place place : this.visited) {
            if (place.getI() == 10 && place.getJ() == 11)
                prevEnd1 = place;
            if (place.getI() == 11 && place.getJ() == 10)
                prevEnd2 = place;
        }
        if (prevEnd1 != null && prevEnd2 != null){
            if (prevEnd1.compareTo(prevEnd2) < 0){
                return prevEnd2;
            } else {
                return prevEnd1;
            }

        } else if(prevEnd1 == null) {
            return prevEnd2;
        } else {
            return prevEnd1;
        }

    }

    private Place getPrev(Place curr){;
        for (Place place : visited) {
            if(isPrev(place, curr))
                return place;
        }
        return null;
    }

    public boolean isPrev(Place pPrev, Place curr){
        if((pPrev.getG() == curr.getG() - 1))
            if((pPrev.getI() - 1 == curr.getI()) ^ (pPrev.getJ() - 1 == curr.getJ()) ^
               (pPrev.getI() + 1 == curr.getI()) ^ (pPrev.getJ() + 1 == curr.getJ()))
                return true;
        return false;
    }

    private LinkedList<Place> expandPending(Place curr){
        LinkedList<Place> morePlaces = generatePlaces(curr);
        LinkedList<Place> realPlaces = new LinkedList<>();
        for (Place place : morePlaces) {
            if(!(place.getI() < 0 || place.getJ() < 0) &&
               !(place.getI() > 11 || place.getJ() > 11) &&
               !(maze[place.getI()][place.getJ()] != 0) &&
               !(contains(visited,place) || contains(pending,place)))
                realPlaces.add(place);
        }
        return realPlaces;
    }

    private boolean contains(LinkedList<Place> places, Place place){
        for (Place p : places) {
            if(p.getI() == place.getI() && p.getJ() == place.getJ())
                return true;
        }
        return false;
    }

    private LinkedList<Place> generatePlaces(Place curr){
        LinkedList<Place> morePlaces = new LinkedList<>();
        morePlaces.add(new Place(curr.getI() - 1, curr.getJ(), curr));
        morePlaces.add(new Place(curr.getI() + 1, curr.getJ(), curr));
        morePlaces.add(new Place(curr.getI(), curr.getJ() - 1, curr));
        morePlaces.add(new Place(curr.getI(), curr.getJ() + 1, curr));
        return morePlaces;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}