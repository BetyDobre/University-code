package comaprators;

import model.Spectacol;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class HourComparator implements Comparator<Spectacol> {
    @Override
    public int compare(Spectacol t1, Spectacol t2){
        return t1.getOra() - t2.getOra();
    }
}