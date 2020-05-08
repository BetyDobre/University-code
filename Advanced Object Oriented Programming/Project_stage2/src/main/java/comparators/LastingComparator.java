package comparators;

import model.Spectacol;

import java.util.Comparator;

public class LastingComparator implements Comparator<Spectacol> {
    @Override
    public int compare(Spectacol t1, Spectacol t2){
        return t1.getDurata() - t2.getDurata();
    }
}
