package repositories;

import exceptions.InexistentStandUpsFileException;
import model.StandUp;
import service.AuditService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileStandUpRepository implements SpectacolRepository<StandUp>{
    private final String file = "STAND-UPS.csv";

    @Override
    public void addSpectacol(StandUp standup) {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file,true))){
            printWriter.print(standup.getNume() + "," + standup.getOras()+"," + standup.getOra() + ',' + standup.getDurata() + ',' + standup.getPret() + ',' + standup.getData() +',' + standup.getComedianti() +'\n');
        }
        catch (Exception e){
            e.printStackTrace();
        }
        AuditService c = (new AuditService("Adaugare spectacol de stand-up"));
        Thread t = new Thread(c);
        t.start();
    }

    @Override
    public ArrayList<StandUp> findSpectacolByName(String nume) {
        Path path = Paths.get(file);
        ArrayList<StandUp> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentStandUpsFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[0].equals(nume)) {
                    StandUp standup = new StandUp();
                    standup.setNume(attr[0]);
                    standup.setOras(attr[1]);
                    standup.setOra(Integer.parseInt(attr[2]));
                    standup.setDurata(Integer.parseInt(attr[3]));
                    standup.setPret(Integer.parseInt(attr[4]));
                    standup.setData(attr[5]);
                    standup.setComedianti(attr[6]);
                    result.add(standup);
                }
            }

            AuditService c = (new AuditService("Cautare spectacol de stand-up dupa nume"));
            Thread t = new Thread(c);
            t.start();

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<StandUp> findSpectacolByData(String data) {
        Path path = Paths.get(file);
        ArrayList<StandUp> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentStandUpsFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[5].equals(data)) {
                    StandUp standup = new StandUp();
                    standup.setNume(attr[0]);
                    standup.setOras(attr[1]);
                    standup.setOra(Integer.parseInt(attr[2]));
                    standup.setDurata(Integer.parseInt(attr[3]));
                    standup.setPret(Integer.parseInt(attr[4]));
                    standup.setData(attr[5]);
                    standup.setComedianti(attr[6]);
                    result.add(standup);
                }
            }
            AuditService c = (new AuditService("Cautare spectacol de stand-up dupa data"));
            Thread t = new Thread(c);
            t.start();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<StandUp> findSpectacolByOra(int ora) {
        Path path = Paths.get(file);
        ArrayList<StandUp> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentStandUpsFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (Integer.parseInt(attr[2]) == ora) {
                    StandUp standup = new StandUp();
                    standup.setNume(attr[0]);
                    standup.setOras(attr[1]);
                    standup.setOra(Integer.parseInt(attr[2]));
                    standup.setDurata(Integer.parseInt(attr[3]));
                    standup.setPret(Integer.parseInt(attr[4]));
                    standup.setData(attr[5]);
                    standup.setComedianti(attr[6]);
                    result.add(standup);
                }
            }

            AuditService c = (new AuditService("Cautare spectacol de stand-up dupa ora"));
            Thread t = new Thread(c);
            t.start();

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<StandUp> afisSpectacole() {
        Path path = Paths.get(file);
        ArrayList<StandUp> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentStandUpsFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                StandUp standup = new StandUp();
                standup.setNume(attr[0]);
                standup.setOras(attr[1]);
                standup.setOra(Integer.parseInt(attr[2]));
                standup.setDurata(Integer.parseInt(attr[3]));
                standup.setPret(Integer.parseInt(attr[4]));
                standup.setData(attr[5]);
                standup.setComedianti(attr[6]);
                result.add(standup);
            }

            AuditService c = (new AuditService("Afisarea tuturor spectacolelor de stand up"));
            Thread t = new Thread(c);
            t.start();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public StandUp findSpectacol(int ora, String data, String nume) {
        Path path = Paths.get(file);
        StandUp standup = new StandUp();
        try {
            if (!Files.exists(path)) {
                throw new InexistentStandUpsFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[0].equals(nume) && Integer.parseInt(attr[2]) == ora && attr[5].equals(data)) {
                    standup.setNume(attr[0]);
                    standup.setOras(attr[1]);
                    standup.setOra(Integer.parseInt(attr[2]));
                    standup.setDurata(Integer.parseInt(attr[3]));
                    standup.setPret(Integer.parseInt(attr[4]));
                    standup.setData(attr[5]);
                    standup.setComedianti(attr[6]);
                    break;
                }
            }
            AuditService c = (new AuditService("Cautare spectacole de stand up dupa nume, data si ora"));
            Thread t = new Thread(c);
            t.start();
            return standup;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteSpectacolByNume(String nume){
        try {
            File inFile = new File("STAND-UPS.csv");
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            Path path = Paths.get(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            var list = Files.readAllLines(path);
            for (String u : list) {
                String[] attr = u.split(",");
                if (!attr[0].equals(nume)) {
                    pw.println(u);
                    pw.flush();
                }
            }

            pw.close();
            br.close();

            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

            AuditService c = (new AuditService("Stergere spectacol de stand-up"));
            Thread t = new Thread(c);
            t.start();

        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void updateOraSpectacol(int ora_veche, int ora){
        try {
            File inFile = new File("STAND-UPS.csv");
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            Path path = Paths.get(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            var list = Files.readAllLines(path);
            for (String u : list) {
                String[] attr = u.split(",");
                if (Integer.parseInt(attr[2]) != ora_veche) {
                    pw.println(u);
                    pw.flush();
                }
                else{
                    pw.println(attr[0] + "," + attr[1] +"," + ora + ',' + attr[3] + ',' + attr[4]  + ',' + attr[5]  +',' +attr[6]  +','+attr[7]  +'\n');
                    pw.flush();
                }
            }

            pw.close();
            br.close();

            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

            AuditService c = (new AuditService("Schimbare ora spectacol de stand-up"));
            Thread t = new Thread(c);
            t.start();

        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    };
}
