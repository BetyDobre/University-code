package repositories;

import exceptions.InexistentOpereFileException;
import jdk.swing.interop.SwingInterOpUtils;
import model.Client;
import service.AuditService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class FileClientRepository implements ClientRepository {
    private final String file = "CLIENTS.csv";

    @Override
    public void addClient(Client client) {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(file,true))){
            printWriter.print(client.getId() + "," + client.getNume()+"," + client.getParola() + '\n');
        }
        catch (Exception e){
            e.printStackTrace();
        }
        AuditService c = (new AuditService("Adaugare client"));
        Thread t = new Thread(c);
        t.start();

    }

    @Override
    public Client findClientByUsername(String username) {
        Path path = Paths.get(file);
        Client client = null;

        try {
            if (!Files.exists(path)) {
                throw new InexistentOpereFileException();
            }
            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (attr[1].equals(username)) {
                    client = new Client();
                    client.setId(Integer.parseInt(attr[0]));
                    client.setNume(attr[1]);
                    client.setParola(attr[2]);
                    break;
                }
            }
            AuditService c = (new AuditService("Cautare client dupa nume"));
            Thread t = new Thread(c);
            t.start();

            return client;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client findClientById(int id) {
        Path path = Paths.get(file);
        Client client = null;

        try {
            if (!Files.exists(path)) {
                throw new InexistentOpereFileException();
            }
            var list = Files.readAllLines(path);
            for (String u : list) {
                String [] attr = u.split(",");
                if (Integer.parseInt(attr[0]) == id) {
                    client = new Client();
                    client.setId(Integer.parseInt(attr[0]));
                    client.setNume(attr[1]);
                    client.setParola(attr[2]);
                    break;
                }
            }
            AuditService c = (new AuditService("Cautare client dupa ID"));
            Thread t = new Thread(c);
            t.start();

            return client;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Client> afisClienti() {
        Path path = Paths.get(file);
        ArrayList<Client> result = new ArrayList<>();

        try {
            if (!Files.exists(path)) {
                throw new InexistentOpereFileException();
            }

            var list = Files.readAllLines(path);
//            list.remove(0);
            for (String u : list) {
                String [] attr = u.split(",");
                    Client client = new Client();
                    client.setId(Integer.parseInt(attr[0]));
                    client.setNume(attr[1]);
                    client.setParola(attr[2]);
                    result.add(client);
            }
            AuditService c = (new AuditService("Afisarea tuturor clientilor"));
            Thread t = new Thread(c);
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteClientByUsername(String nume) {
        try {
            File inFile = new File("CLIENTS.csv");
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            Path path = Paths.get(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            var list = Files.readAllLines(path);
            for (String u : list) {
                String[] attr = u.split(",");
                if (!attr[1].equals(nume)) {
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

            AuditService c = (new AuditService("Stergere client dupa username"));
            Thread t = new Thread(c);
            t.start();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
