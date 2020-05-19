package repositories;

import exceptions.InexistentClientFileException;
import model.Opera;
import service.AuditService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileOperaRepository implements SpectacolRepository<Opera>{
    private final String file = "OPERE.csv";

    @Override
    public void addSpectacol(Opera opera) {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file,true))){
            printWriter.print(opera.getNume() + "," + opera.getOras()+"," + opera.getOra() + ',' + opera.getDurata() + ',' + opera.getPret() + ',' + opera.getData() +',' + opera.getNumeOpera() +','+opera.getLimba() +'\n');
        }
        catch (Exception e){
            e.printStackTrace();
        }

        AuditService c = (new AuditService("Adaugare spectacol de opera"));
        Thread t = new Thread(c);
        t.start();
    }

    @Override
    public ArrayList<Opera> findSpectacolByName(String nume) {
        Path path = Paths.get(file);
        ArrayList<Opera> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentClientFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[0].equals(nume)) {
                    Opera opera = new Opera();
                    opera.setNume(attr[0]);
                    opera.setOras(attr[1]);
                    opera.setOra(Integer.parseInt(attr[2]));
                    opera.setDurata(Integer.parseInt(attr[3]));
                    opera.setPret(Integer.parseInt(attr[4]));
                    opera.setData(attr[5]);
                    opera.setNumeOpera(attr[6]);
                    opera.setLimba(attr[7]);
                    result.add(opera);
                }
            }
            AuditService c = (new AuditService("Cautare opera dupa nume"));
            Thread t = new Thread(c);
            t.start();

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Opera> findSpectacolByData(String data) {
        Path path = Paths.get(file);
        ArrayList<Opera> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentClientFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[5].equals(data)) {
                    Opera opera = new Opera();
                    opera.setNume(attr[0]);
                    opera.setOras(attr[1]);
                    opera.setOra(Integer.parseInt(attr[2]));
                    opera.setDurata(Integer.parseInt(attr[3]));
                    opera.setPret(Integer.parseInt(attr[4]));
                    opera.setData(attr[5]);
                    opera.setNumeOpera(attr[6]);
                    opera.setLimba(attr[7]);
                    result.add(opera);
                }
            }
            AuditService c = (new AuditService("Cautare opera dupa data" ));
            Thread t = new Thread(c);
            t.start();

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Opera> findSpectacolByNumeOpera(String numeOpera) {
        Path path = Paths.get(file);
        ArrayList<Opera> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentClientFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[6].equals(numeOpera)) {
                    Opera opera = new Opera();
                    opera.setNume(attr[0]);
                    opera.setOras(attr[1]);
                    opera.setOra(Integer.parseInt(attr[2]));
                    opera.setDurata(Integer.parseInt(attr[3]));
                    opera.setPret(Integer.parseInt(attr[4]));
                    opera.setData(attr[5]);
                    opera.setNumeOpera(attr[6]);
                    opera.setLimba(attr[7]);
                    result.add(opera);
                }
            }

            AuditService c = (new AuditService("Cautare opera dupa nume opera" ));
            Thread t = new Thread(c);
            t.start();

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Opera> findSpectacolByOra(int ora) {
        Path path = Paths.get(file);
        ArrayList<Opera> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentClientFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (Integer.parseInt(attr[2]) == ora) {
                    Opera opera = new Opera();
                    opera.setNume(attr[0]);
                    opera.setOras(attr[1]);
                    opera.setOra(Integer.parseInt(attr[2]));
                    opera.setDurata(Integer.parseInt(attr[3]));
                    opera.setPret(Integer.parseInt(attr[4]));
                    opera.setData(attr[5]);
                    opera.setNumeOpera(attr[6]);
                    opera.setLimba(attr[7]);
                    result.add(opera);
                }
            }
            AuditService c = (new AuditService("Cautare opera dupa ora" ));
            Thread t = new Thread(c);
            t.start();

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Opera> afisSpectacole() {
        Path path = Paths.get(file);
        ArrayList<Opera> result= new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentClientFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                Opera opera = new Opera();
                opera.setNume(attr[0]);
                opera.setOras(attr[1]);
                opera.setOra(Integer.parseInt(attr[2]));
                opera.setDurata(Integer.parseInt(attr[3]));
                opera.setPret(Integer.parseInt(attr[4]));
                opera.setData(attr[5]);
                opera.setNumeOpera(attr[6]);
                opera.setLimba(attr[7]);
                result.add(opera);
            }

            AuditService c = (new AuditService("Afisarea tuturor operelor"));
            Thread t = new Thread(c);
            t.start();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Opera findSpectacol(int ora, String data, String nume) {
        Path path = Paths.get(file);
        Opera opera = new Opera();
        try {
            if (!Files.exists(path)) {
                throw new InexistentClientFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[0].equals(nume) && Integer.parseInt(attr[2]) == ora && attr[5].equals(data)) {
                    opera.setNume(attr[0]);
                    opera.setOras(attr[1]);
                    opera.setOra(Integer.parseInt(attr[2]));
                    opera.setDurata(Integer.parseInt(attr[3]));
                    opera.setPret(Integer.parseInt(attr[4]));
                    opera.setData(attr[5]);
                    opera.setNumeOpera(attr[6]);
                    opera.setLimba(attr[7]);
                    break;
                }
            }
            AuditService c = (new AuditService("Cautare opera dupa nume,data,ora"));
            Thread t = new Thread(c);
            t.start();
            return opera;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteSpectacolByNume(String nume){
        try {
            File inFile = new File("OPERE.csv");
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

            AuditService c = (new AuditService("Stergere spectacol"));
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
            File inFile = new File("OPERE.csv");
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

            AuditService c = (new AuditService("Schimbare ora spectacol"));
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
