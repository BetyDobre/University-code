package repositories.theater;

import exceptions.InexistentTeatreFileException;
import model.Teatru;
import repositories.SpectacolRepository;
import service.AuditService;
import service.FileRezOperaService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileTeatruRepository implements SpectacolRepository<Teatru> {
    private final String file = "TEATRE.csv";

    @Override
    public void addSpectacol(Teatru teatru) {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file,true))){
            printWriter.print(teatru.getNume() + "," + teatru.getOras()+"," + teatru.getOra() + ',' + teatru.getDurata() + ',' + teatru.getPret() + ',' + teatru.getData() +',' + teatru.getNumeTeatru() +','+teatru.getActori() +'\n');
        }
        catch (Exception e){
            e.printStackTrace();
        }
        AuditService c = (new AuditService("Adaugare spectacol de teatru"));
        Thread t = new Thread(c);
        t.start();
    }

    @Override
    public ArrayList<Teatru> findSpectacolByName(String nume) {
        Path path = Paths.get(file);
        ArrayList<Teatru> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentTeatreFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[0].equals(nume)) {
                    Teatru teatru = new Teatru();
                    teatru.setNume(attr[0]);
                    teatru.setOras(attr[1]);
                    teatru.setOra(Integer.parseInt(attr[2]));
                    teatru.setDurata(Integer.parseInt(attr[3]));
                    teatru.setPret(Integer.parseInt(attr[4]));
                    teatru.setData(attr[5]);
                    teatru.setNumeTeatru(attr[6]);
                    teatru.setActori(attr[7]);
                    result.add(teatru);
                }
            }
            AuditService c = (new AuditService("Cautare spectacol de teatru dupa nume("+nume+")"));
            Thread t = new Thread(c);
            t.start();

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Teatru> findSpectacolByData(String data) {
        Path path = Paths.get(file);
        ArrayList<Teatru> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentTeatreFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[5].equals(data)) {
                    Teatru teatru = new Teatru();
                    teatru.setNume(attr[0]);
                    teatru.setOras(attr[1]);
                    teatru.setOra(Integer.parseInt(attr[2]));
                    teatru.setDurata(Integer.parseInt(attr[3]));
                    teatru.setPret(Integer.parseInt(attr[4]));
                    teatru.setData(attr[5]);
                    teatru.setNumeTeatru(attr[6]);
                    teatru.setActori(attr[7]);
                    result.add(teatru);
                }
            }
            AuditService c = (new AuditService("Cautare spectacol de teatru dupa data("+data+")"));
            Thread t = new Thread(c);
            t.start();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Teatru> findSpectacolByNumeTeatru(String numeTeatru) {
        Path path = Paths.get(file);
        ArrayList<Teatru> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new  InexistentTeatreFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[6].equals(numeTeatru)) {
                    Teatru teatru = new Teatru();
                    teatru.setNume(attr[0]);
                    teatru.setOras(attr[1]);
                    teatru.setOra(Integer.parseInt(attr[2]));
                    teatru.setDurata(Integer.parseInt(attr[3]));
                    teatru.setPret(Integer.parseInt(attr[4]));
                    teatru.setData(attr[5]);
                    teatru.setNumeTeatru(attr[6]);
                    teatru.setActori(attr[7]);
                    result.add(teatru);
                }
            }
            AuditService c = (new AuditService("Cautare spectacol de teatru dupa nume teatru("+numeTeatru+")"));
            Thread t = new Thread(c);
            t.start();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Teatru> findSpectacolByOra(int ora) {
        Path path = Paths.get(file);
        ArrayList<Teatru> result = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new  InexistentTeatreFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (Integer.parseInt(attr[2]) == ora) {
                    Teatru teatru = new Teatru();
                    teatru.setNume(attr[0]);
                    teatru.setOras(attr[1]);
                    teatru.setOra(Integer.parseInt(attr[2]));
                    teatru.setDurata(Integer.parseInt(attr[3]));
                    teatru.setPret(Integer.parseInt(attr[4]));
                    teatru.setData(attr[5]);
                    teatru.setNumeTeatru(attr[6]);
                    teatru.setActori(attr[7]);
                    result.add(teatru);
                }
            }
            AuditService c = (new AuditService("Cautare spectacol de teatru dupa ora("+ora+")"));
            Thread t = new Thread(c);
            t.start();

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Teatru> afisSpectacole() {
        Path path = Paths.get(file);
        ArrayList<Teatru> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new  InexistentTeatreFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                Teatru teatru = new Teatru();
                teatru.setNume(attr[0]);
                teatru.setOras(attr[1]);
                teatru.setOra(Integer.parseInt(attr[2]));
                teatru.setDurata(Integer.parseInt(attr[3]));
                teatru.setPret(Integer.parseInt(attr[4]));
                teatru.setData(attr[5]);
                teatru.setNumeTeatru(attr[6]);
                teatru.setActori(attr[7]);
                result.add(teatru);
            }
            AuditService c = (new AuditService("Afisare spectacole de teatru"));
            Thread t = new Thread(c);
            t.start();

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Teatru findSpectacol(int ora, String data, String nume) {
        Path path = Paths.get(file);
        Teatru teatru = new Teatru();
        try {
            if (!Files.exists(path)) {
                throw new InexistentTeatreFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[0].equals(nume) && Integer.parseInt(attr[2]) == ora && attr[5].equals(data)) {
                    teatru.setNume(attr[0]);
                    teatru.setOras(attr[1]);
                    teatru.setOra(Integer.parseInt(attr[2]));
                    teatru.setDurata(Integer.parseInt(attr[3]));
                    teatru.setPret(Integer.parseInt(attr[4]));
                    teatru.setData(attr[5]);
                    teatru.setNumeTeatru(attr[6]);
                    teatru.setActori(attr[7]);
                    break;
                }
            }
            AuditService c = (new AuditService("Cautare spectacol de teatru dupa nume, data si ora"));
            Thread t = new Thread(c);
            t.start();

            return teatru;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteSpectacolByNume(String nume){
        try {
            File inFile = new File("TEATRE.csv");
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
            AuditService c = (new AuditService("Stergere spectacol de teatru "));
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
            File inFile = new File("TEATRE.csv");
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
            AuditService c = (new AuditService("Schimbare ora spectacol de teatru"));
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
