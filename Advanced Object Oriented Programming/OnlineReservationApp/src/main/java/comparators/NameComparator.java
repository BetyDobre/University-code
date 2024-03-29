package comparators;

import model.Spectacol;

import java.util.Comparator;

// compararea spectacolelor dupa nume
public class NameComparator implements Comparator<Spectacol> {
    @Override
    public int compare(Spectacol t1, Spectacol t2){
        return t1.getNume().compareToIgnoreCase(t2.getNume());
    }
}
