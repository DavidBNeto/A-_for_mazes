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
        while (curr.getI() != 11 && curr.getJ() != 11) {
            pending.addAll(expandPending(curr));
            pending.sort(new PlaceComparator());
            curr = pending.getFirst();
            visited.add(curr); 
        }
        do {
            visited.remove(curr);
            this.solution = curr.toString() + this.solution;
            curr = getPrev(curr);
        } while (curr.getI() != 0 && curr.getJ() != 0);
        System.out.println(this.solution);
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
        for (Place place : morePlaces) {
            if(!place.equals(new Place(11, 11)))
                if(place.getI() < 0 || place.getJ() < 0)
                    morePlaces.remove(place);
                else if(place.getI() > 11 || place.getJ() > 11)
                    morePlaces.remove(place);
                else if(maze[place.getI()][place.getJ()] != 0)
                    morePlaces.remove(place);
                else if(visited.contains(place))
                    morePlaces.remove(place);
        }
        return morePlaces;
    }

    private LinkedList<Place> generatePlaces(Place curr){
        LinkedList<Place> morePlaces = new LinkedList<>();
        morePlaces.add(new Place(curr.getI() - 1, curr.getJ() - 1, curr));
        morePlaces.add(new Place(curr.getI() + 1, curr.getJ() - 1, curr));
        morePlaces.add(new Place(curr.getI() - 1, curr.getJ() + 1, curr));
        morePlaces.add(new Place(curr.getI() + 1, curr.getJ() + 1, curr));
        return morePlaces;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}