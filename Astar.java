import java.util.LinkedList;

import javax.lang.model.util.ElementScanner14;

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
        while (curr.getI() != 11 && curr.getJ() != 11){
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
        } while (curr.getI() != 0 && curr.getJ() != 0);
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
            if(place.isPrev(curr))
                return place;
        }
        return null;
    }

    private LinkedList<Place> expandPending(Place curr){
        LinkedList<Place> morePlaces = generatePlaces(curr);
        LinkedList<Place> realPlaces = new LinkedList<>();
        for (Place place : morePlaces) {
            if(!(place.getI() < 0 || place.getJ() < 0) &&
               !(place.getI() > 11 || place.getJ() > 11) &&
               !(maze[place.getI()][place.getJ()] != 0) &&
               !(visited.contains(place) || pending.contains(place)))
                realPlaces.add(place);
        }
        return realPlaces;
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