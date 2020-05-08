package service;

import exceptions.InexistentClientFileException;
import model.*;
import repositories.FileOperaRepository;
import repositories.FileStandUpRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileRezStandUpService {
    private final String file = "REZERVARI.csv";
    private FileStandUpRepository standupRepository;

    public FileRezStandUpService() {standupRepository = new FileStandUpRepository();}

    public void rezervare(Client client, FileLoginService loginService, StandUp standup, Loc loc){
        if (loginService.login(client)){
            if(loc.isLiber()){
                try(PrintWriter printWriter = new PrintWriter(new FileWriter(file,true))){
                    LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
                    lineNumberReader.skip(Long.MAX_VALUE);
                    int lines = lineNumberReader.getLineNumber();

                    Rezervare rez = new Rezervare(lines + 1, client.getNume(),standup.getNume(),standup.getData(),standup.getOra(),loc.getNrLoc(),"-");

                    printWriter.print(rez.getNr());
                    printWriter.print(','+ rez.getNumeClient()+ ',' + rez.getNumeSpectacol() + ',' + rez.getData() + ',' +rez.getOra()+ ',' +rez.getLoc()+',' + rez.getSector() + '\n');
                    loc.setLiber(false);
                    System.out.println("Rezervarea a fost efectuata cu succes !");

                    AuditService c = (new AuditService("Rezervare la spectacol de stand-up"));
                    Thread t = new Thread(c);
                    t.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Locul este ocupat !");
            }
        }
    }

    public ArrayList<Rezervare> afisRezervari() {
        Path path = Paths.get(file);
        ArrayList<Rezervare> result = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentClientFileException();
            }

            var list = Files.readAllLines(path);
            for (String u : list) {
                String[] attr = u.split(",");
                Rezervare rezervare = new Rezervare();
                rezervare.setNr(Integer.parseInt(attr[0]));
                rezervare.setNumeClient(attr[1]);
                rezervare.setNumeSpectacol(attr[2]);
                rezervare.setData(attr[3]);
                rezervare.setOra(Integer.parseInt(attr[4]));
                rezervare.setLoc(Integer.parseInt(attr[5]));
                rezervare.setSector(attr[6]);
                result.add(rezervare);
            }
            AuditService c = (new AuditService("Afisarea rezervarilor la stand ups"));
            Thread t = new Thread(c);
            t.start();
            return result;

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
