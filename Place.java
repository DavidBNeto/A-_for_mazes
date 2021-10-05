public class Place implements Comparable<Place> {

    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    
    public Place(int i, int j, Place curr) {
        this.i = i;
        this.j = j;
        this.g = curr.getG() + 1;
        this.h = 22 - (i + j);
        this.f = g + h;
    }
    
    public Place() {
        this.i = 0;
        this.j = 0;
        this.g = 0;
        this.h = 22;
        this.f = 22;
    }

    public boolean isPrev(Place pPrev){
        if((pPrev.getG() == this.getG() - 1))
            if((pPrev.getI() - 1 == this.getI()) ^ (pPrev.getJ() - 1 == this.getJ()) ^
               (pPrev.getI() + 1 == this.getI()) ^ (pPrev.getJ() + 1 == this.getJ()))
                return true;
        return false;
    }

    public int getF(){
        return f;
    }

    public int getG(){
        return g;
    }

    public int getH(){
        return h;
    }

    public int getI(){
        return i;
    }

    public int getJ(){
        return j;
    }

    @Override
    public int compareTo(Place place) {
        return this.getF() - place.getF();
    }

    @Override
    public String toString() {
        return "(" + this.getI() + "," + this.getJ() + ")";
    }

    public boolean equals(Place place)  {
        return ((this.getF() == place.getF()) && 
                (this.getG() == place.getG()) && 
                (this.getH() == place.getH()) && 
                (this.getI() == place.getI()) && 
                (this.getJ() == place.getJ())) ? true : false;
    }
}