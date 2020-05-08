package model;
//enum sector {SectorA, SectorB, Loja, Balcon}

public class LocTeatruOpera extends Loc {
    private sector zona;


    public String getZona() {
        return zona.toString();
    }

    public void setZona(sector zona) {
        this.zona = zona;
    }

    public LocTeatruOpera(int loc, sector zona){
        super(loc);
        this.zona = zona;
    }
    public enum sector {SectorA, SectorB, Loja, Balcon};
}
