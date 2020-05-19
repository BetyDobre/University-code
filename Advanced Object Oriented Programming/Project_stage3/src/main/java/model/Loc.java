package model;

public class Loc {
    private int nrLoc;
    private boolean liber;

    public int getNrLoc() {
        return nrLoc;
    }

    public void setNrLoc(int nrLoc) {
        this.nrLoc = nrLoc;
    }

    public boolean isLiber() {
        return liber;
    }

    public void setLiber(boolean liber) {
        this.liber = liber;
    }

    public Loc(int nr){
        nrLoc = nr;
        liber = true;
    }

    public Loc(){};

}
