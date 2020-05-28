package comparators;

import model.Spectacol;

import java.util.Comparator;

// compararea spectacolelor dupa pret
public class PriceComparator implements Comparator<Spectacol> {
    @Override
    public int compare(Spectacol t1, Spectacol t2){
        return t1.getPret() - t2.getPret();
    }
}
