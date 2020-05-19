package comparators;

import model.Spectacol;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<Spectacol> {
    @Override
    public int compare(Spectacol t1, Spectacol t2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy");
        try {
            Date date1 = sdf.parse(t1.getData());
            Date date2 = sdf.parse(t2.getData());

            return date1.compareTo(date2);
        }
        catch (Exception e){};

        return 0;

    }
}