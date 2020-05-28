package service;


import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditService implements Runnable {

    private final String file = "AUDIT.csv";
    private String actiune;

    public AuditService(String actiune) {
        this.actiune = actiune;
    }

    public String getActiune() {
        return actiune;
    }

    public void addActiune() {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file,true))){
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            printWriter.print(actiune + ',' + timeStamp + "," +Thread.currentThread().getName() + '\n');
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        addActiune();
    }

}
