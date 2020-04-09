package comaprators;

import model.Spectacol;

import java.util.Comparator;

public class NameComparator implements Comparator<Spectacol> {
    @Override
    public int compare(Spectacol t1, Spectacol t2){
        return t1.getNume().compareToIgnoreCase(t2.getNume());
    }
}
