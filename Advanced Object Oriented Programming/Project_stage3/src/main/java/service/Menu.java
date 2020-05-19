package service;

import comparators.HourComparator;
import model.*;
import repositories.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    SpectacolRepository repositoryDBO = SpectacolRepository.build(SpectacolRepository.Type.DBOpera);
    SpectacolRepository repositoryDBT = SpectacolRepository.build(SpectacolRepository.Type.DBTeatru);
    SpectacolRepository repositoryDBS = SpectacolRepository.build(SpectacolRepository.Type.DBStandUp);
    ClientRepository repositoryDBC = ClientRepository.build(ClientRepository.Type.DB);

    SpectacolRepository repositoryFT = SpectacolRepository.build(SpectacolRepository.Type.FileTeatru);
    SpectacolRepository repositoryFO = SpectacolRepository.build(SpectacolRepository.Type.FileOpera);
    SpectacolRepository repositoryFS = SpectacolRepository.build(SpectacolRepository.Type.FileStandUp);
    ClientRepository repositoryFC =  ClientRepository.build(ClientRepository.Type.FILE);

    public void printMenu(){
        System.out.println("------>  Menu  <------");
        System.out.println();
        System.out.println("1. Get all the Opera performances");
        System.out.println("2. Get all the Theater plays");
        System.out.println("3. Get all the Stand-UP shows");
        System.out.println("4. Get all the clients");
        System.out.println("5. Find a show by name");
        System.out.println("6. Find a show by date");
        System.out.println("7. Find a show by hour");
        System.out.println("8. Find a show by name, date and hour");
        System.out.println("9. Add a show");
        System.out.println("10. Add a client");
        System.out.println("11. Find a client by his username");
        System.out.println("12. Find a client by his ID");
        System.out.println("13. Delete a client by his username");
        System.out.println("14. Delete a show by name");
        System.out.println("15. Update the hour of the show");
        System.out.println("16. Client Login");
        System.out.println("17. Make a reservation for a theater play");
        System.out.println("18. Make a reservation for an opera performance");
        System.out.println("19. Make a reservation for a stand-up show");
        System.out.println("20. Sort shows by start hour");
        System.out.println("0. Exit menu ");
    }

    public void menu(){
        ArrayList<LocTeatruOpera> locuriPiesa = new ArrayList<>();
        //alocarea locurilor
        int i;
        for (i = 1; i <= 50; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.SectorA);
            locuriPiesa.add(loc);
        }
        for (i = 51; i <= 100; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.SectorB);
            locuriPiesa.add(loc);
        }
        for (i = 101; i <= 110; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.Loja);
            locuriPiesa.add(loc);
        }
        for (i = 111; i <= 150; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.Balcon);
            locuriPiesa.add(loc);
        }

        ArrayList<LocTeatruOpera> locuriOpera = new ArrayList<>();
        //alocarea locurilor
        for (i = 1; i <= 50; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.SectorA);
            locuriOpera.add(loc);
        }
        for (i = 51; i <= 100; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.SectorB);
            locuriOpera.add(loc);
        }
        for (i = 101; i <= 110; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.Loja);
            locuriOpera.add(loc);
        }
        for (i = 111; i <= 150; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.Balcon);
            locuriOpera.add(loc);
        }

        ArrayList<Loc> locuri = new ArrayList<>();
        for (i = 1; i <= 100; i++){
            Loc loc = new Loc(i);
            locuri.add(loc);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Process data from database(1) or from file(2)?");
        int ans = scanner.nextInt();
        printMenu();
        int command = scanner.nextInt();
        if(ans == 1){
            while(command != 0) {
                switch (command) {
                    case 1: {
                        ArrayList<Opera> resultOpera = repositoryDBO.afisSpectacole();
                        for (Opera s : resultOpera) {
                            System.out.println(s);
                        }
                        break;
                    }
                    case 2: {
                        ArrayList<Teatru> resultTeatru = repositoryDBT.afisSpectacole();
                        for (Teatru s : resultTeatru) {
                            System.out.println(s);
                        }
                        break;
                    }
                    case 3: {
                        ArrayList<StandUp> resultStandUp = repositoryDBS.afisSpectacole();
                        for (StandUp s : resultStandUp) {
                            System.out.println(s);
                        }
                        break;
                    }
                    case 4: {
                        ArrayList<Client> resultClient = repositoryDBC.afisClienti();
                        System.out.println(resultClient);
                        break;
                    }
                    case 5: {
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        switch (command2) {
                            case 1: {
                                ArrayList<Teatru> rez = repositoryDBT.findSpectacolByName(name);
                                for (Teatru s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 2: {
                                ArrayList<Opera> rez = repositoryDBO.findSpectacolByName(name);
                                for (Opera s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 3: {
                                ArrayList<StandUp> rez = repositoryDBS.findSpectacolByName(name);
                                for (StandUp s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 6: {
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the date: ");
                        String data = scanner.next();
                        switch (command2) {
                            case 1: {
                                ArrayList<Teatru> rez = repositoryDBT.findSpectacolByData(data);
                                for (Teatru s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 2: {
                                ArrayList<Opera> rez = repositoryDBO.findSpectacolByData(data);
                                for (Opera s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 3: {
                                ArrayList<StandUp> rez = repositoryDBS.findSpectacolByData(data);
                                for (StandUp s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 7: {
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the hour: ");
                        int ora = scanner.nextInt();
                        switch (command2) {
                            case 1: {
                                ArrayList<Teatru> rez = repositoryDBT.findSpectacolByOra(ora);
                                for (Teatru s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 2: {
                                ArrayList<Opera> rez = repositoryDBO.findSpectacolByOra(ora);
                                for (Opera s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 3: {
                                ArrayList<StandUp> rez = repositoryDBS.findSpectacolByOra(ora);
                                for (StandUp s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 8:{
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the hour: ");
                        int ora = scanner.nextInt();
                        System.out.print("Give the date: ");
                        String date = scanner.next();
                        System.out.print("Give the name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        switch (command2) {
                            case 1: {
                                Teatru rez = (Teatru) repositoryDBT.findSpectacol(ora, date, name);
                                System.out.println(rez);
                                break;
                            }
                            case 2: {
                                Opera rez = (Opera) repositoryDBO.findSpectacol(ora, date, name);
                                System.out.println(rez);
                                break;
                            }
                            case 3: {
                                StandUp rez = (StandUp) repositoryDBS.findSpectacol(ora, date, name);
                                System.out.println(rez);
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 9:{
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        System.out.print("Give the city: ");
                        String oras = scanner.next();
                        System.out.print("Give the hour: ");
                        int ora = scanner.nextInt();
                        System.out.print("Give the duration: ");
                        int durata = scanner.nextInt();
                        System.out.print("Give the price:");
                        int price = scanner.nextInt();
                        System.out.print("Give the date: ");
                        String date = scanner.next();
                        switch (command2) {
                            case 1: {
                                System.out.print("Give the name of the theater: ");
                                scanner.nextLine();
                                String numeTeatru = scanner.nextLine();
                                System.out.print("Give the actors of the play: ");
                                scanner.nextLine();
                                String actori = scanner.nextLine();
                                Teatru rez = new Teatru(name, oras, ora, durata, price ,date, numeTeatru, actori);
                                repositoryDBT.addSpectacol(rez);
                                break;
                            }
                            case 2: {
                                System.out.print("Give the name of the opera: ");
                                scanner.nextLine();
                                String numeOpera = scanner.nextLine();
                                System.out.print("Give the language the opera: ");
                                String limba = scanner.next();
                                Opera rez = new Opera(name, oras, ora, durata, price ,date, numeOpera, limba);
                                repositoryDBO.addSpectacol(rez);
                                break;
                            }
                            case 3: {
                                System.out.print("Give the name of the stand-upers: ");
                                scanner.nextLine();
                                String comedianti = scanner.nextLine();
                                StandUp rez = new StandUp(name, oras, ora, durata, price ,date, comedianti);
                                repositoryDBS.addSpectacol(rez);
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 10:{
                        System.out.print("Give the username: ");
                        String username = scanner.next();
                        System.out.print("Give the password: ");
                        String password = scanner.next();
                        Client c = new Client();
                        c.setNume(username);
                        c.setParola(password);
                        repositoryDBC.addClient(c);
                        break;
                    }
                    case 11:{
                        System.out.print("Give the username: ");
                        String username = scanner.next();
                        Client c = repositoryDBC.findClientByUsername(username);
                        System.out.println(c);
                        break;
                    }
                    case 12:{
                        System.out.print("Give the ID: ");
                        int id = scanner.nextInt();
                        Client c = repositoryDBC.findClientById(id);
                        System.out.println(c);
                        break;
                    }
                    case 13:{
                        System.out.print("Give the username: ");
                        String username = scanner.next();
                        repositoryDBC.deleteClientByUsername(username);
                        break;
                    }
                    case 14:{
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        switch (command2) {
                            case 1: {
                                repositoryDBT.deleteSpectacolByNume(name);
                                break;
                            }
                            case 2: {
                                repositoryDBO.deleteSpectacolByNume(name);
                                break;
                            }
                            case 3: {
                                repositoryDBS.deleteSpectacolByNume(name);
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 15:{
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();

                        System.out.print("Give the old hour: ");
                        int old = scanner.nextInt();
                        System.out.print("Give the new hour: ");
                        int neww = scanner.nextInt();

                        switch (command2) {
                            case 1: {
                                repositoryDBT.updateOraSpectacol(old, neww);
                                break;
                            }
                            case 2: {
                                repositoryDBO.updateOraSpectacol(old, neww);
                                break;
                            }
                            case 3: {
                                repositoryDBS.updateOraSpectacol(old, neww);
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 16:{
                        DBLoginService service = DBLoginService.getInstance();
                        System.out.print("Give the username: ");
                        String username = scanner.next();
                        System.out.print("Give the password: ");
                        String password = scanner.next();

                        Client c  = new Client();
                        c.setNume(username);
                        c.setParola(password);

                        boolean rez = service.login(c);
                        if(rez){
                            System.out.println("Succes login !");
                        }
                        else{
                            System.out.println("Incorrect username or password");
                        }
                        break;
                    }
                    case 17:{
                        System.out.println("Locuri disponibile in SectorA, SectorB, Balcon si Loja");
                        DBRezTeatruService rezerv = DBRezTeatruService.getInstance();
                        DBLoginService service = DBLoginService.getInstance();
                        System.out.print("Client by username: ");
                        String username = scanner.next();
                        System.out.print("Choose by name,hour and date: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        int ora = scanner.nextInt();
                        String date = scanner.next();
                        System.out.print("Choose the sit: ");
                        int loc = scanner.nextInt();

                        Client c = repositoryDBC.findClientByUsername(username);
                        Teatru t = (Teatru)repositoryDBT.findSpectacol(ora,date,name);

                        rezerv.rezervare(c, service, t, locuriPiesa.get(loc - 1));
                        break;
                    }
                    case 18:{
                        //alocarea locurilor

                        System.out.println("Locuri disponibile in SectorA, SectorB, Balcon si Loja");
                        DBRezOperaService rezerv = DBRezOperaService.getInstance();
                        DBLoginService service = DBLoginService.getInstance();
                        System.out.print("Client by username: ");
                        String username = scanner.next();
                        System.out.print("Choose Opera by name, hour and date: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        int ora = scanner.nextInt();
                        String date = scanner.next();
                        System.out.print("Choose the sit: ");
                        int loc = scanner.nextInt();

                        Client c = repositoryDBC.findClientByUsername(username);
                        Opera t = (Opera)repositoryDBO.findSpectacol(ora,date,name);

                        rezerv.rezervare(c, service, t, locuriOpera.get(loc - 1));
                        break;
                    }
                    case 19:{
                        System.out.println("Locuri disponibile de la 1 la 100");
                        DBRezStandUpService rezerv = DBRezStandUpService.getInstance();
                        DBLoginService service = DBLoginService.getInstance();
                        System.out.print("Client by username: ");
                        String username = scanner.next();
                        System.out.print("Choose stand-up show by name, hour and date: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        int ora = scanner.nextInt();
                        String date = scanner.next();
                        System.out.print("Choose the sit: ");
                        int loc = scanner.nextInt();

                        Client c = repositoryDBC.findClientByUsername(username);
                        StandUp t = (StandUp) repositoryDBS.findSpectacol(ora,date,name);

                        rezerv.rezervare(c, service, t, locuri.get(loc - 1));
                        break;
                    }
                    case 20: {
                        ArrayList<Spectacol> s = new ArrayList<>();
                        ArrayList<Teatru> teatru = repositoryDBT.afisSpectacole();
                        ArrayList<Opera> opera = repositoryDBO.afisSpectacole();
                        ArrayList<StandUp> stand = repositoryDBS.afisSpectacole();
                        s.addAll(teatru);
                        s.addAll(opera);
                        s.addAll(stand);
                        s.sort(new HourComparator());
                        System.out.println(s);
                        break;
                    }
                    default: {
                        System.out.println("The-command-doesn't-exist");
                        break;
                    }

                }

                printMenu();
                System.out.println();
                System.out.println("The next command is: ");
                command = scanner.nextInt();
            }

        }


        else if(ans == 2){
            while(command != 0) {
                switch (command) {
                    case 1: {
                        ArrayList<Opera> resultOpera = repositoryFO.afisSpectacole();
                        for (Opera s : resultOpera) {
                            System.out.println(s);
                        }
                        break;
                    }
                    case 2: {
                        ArrayList<Teatru> resultTeatru = repositoryFT.afisSpectacole();
                        for (Teatru s : resultTeatru) {
                            System.out.println(s);
                        }
                        break;
                    }
                    case 3: {
                        ArrayList<StandUp> resultStandUp = repositoryFS.afisSpectacole();
                        for (StandUp s : resultStandUp) {
                            System.out.println(s);
                        }
                        break;
                    }
                    case 4: {
                        ArrayList<Client> resultClient = repositoryFC.afisClienti();
                        System.out.println(resultClient);
                        break;
                    }
                    case 5: {
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        switch (command2) {
                            case 1: {
                                ArrayList<Teatru> rez = repositoryFT.findSpectacolByName(name);
                                for (Teatru s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 2: {
                                ArrayList<Opera> rez = repositoryFO.findSpectacolByName(name);
                                for (Opera s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 3: {
                                ArrayList<StandUp> rez = repositoryFS.findSpectacolByName(name);
                                for (StandUp s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 6: {
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the date: ");
                        String data = scanner.next();
                        switch (command2) {
                            case 1: {
                                ArrayList<Teatru> rez = repositoryFT.findSpectacolByData(data);
                                for (Teatru s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 2: {
                                ArrayList<Opera> rez = repositoryFO.findSpectacolByData(data);
                                for (Opera s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 3: {
                                ArrayList<StandUp> rez = repositoryFS.findSpectacolByData(data);
                                for (StandUp s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 7: {
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the hour: ");
                        int ora = scanner.nextInt();
                        switch (command2) {
                            case 1: {
                                ArrayList<Teatru> rez = repositoryFT.findSpectacolByOra(ora);
                                for (Teatru s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 2: {
                                ArrayList<Opera> rez = repositoryFO.findSpectacolByOra(ora);
                                for (Opera s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            case 3: {
                                ArrayList<StandUp> rez = repositoryFS.findSpectacolByOra(ora);
                                for (StandUp s : rez) {
                                    System.out.println(s);
                                }
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 8:{
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the hour: ");
                        int ora = scanner.nextInt();
                        System.out.print("Give the date: ");
                        String date = scanner.next();
                        System.out.print("Give the name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        switch (command2) {
                            case 1: {
                                Teatru rez = (Teatru) repositoryFT.findSpectacol(ora, date, name);
                                System.out.println(rez);
                                break;
                            }
                            case 2: {
                                Opera rez = (Opera) repositoryFO.findSpectacol(ora, date, name);
                                System.out.println(rez);
                                break;
                            }
                            case 3: {
                                StandUp rez = (StandUp) repositoryFS.findSpectacol(ora, date, name);
                                System.out.println(rez);
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 9:{
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        System.out.print("Give the city: ");
                        String oras = scanner.next();
                        System.out.print("Give the hour: ");
                        int ora = scanner.nextInt();
                        System.out.print("Give the duration: ");
                        int durata = scanner.nextInt();
                        System.out.print("Give the price:");
                        int price = scanner.nextInt();
                        System.out.print("Give the date: ");
                        String date = scanner.next();
                        switch (command2) {
                            case 1: {
                                System.out.print("Give the name of the theater: ");
                                scanner.nextLine();
                                String numeTeatru = scanner.nextLine();
                                System.out.print("Give the actors of the play: ");
//                                scanner.nextLine();
                                String actori = scanner.nextLine();
                                Teatru rez = new Teatru(name, oras, ora, durata, price ,date, numeTeatru, actori);
                                repositoryFT.addSpectacol(rez);
                                break;
                            }
                            case 2: {
                                System.out.print("Give the name of the opera: ");
                                scanner.nextLine();
                                String numeOpera = scanner.nextLine();
                                System.out.print("Give the language the opera: ");
                                String limba = scanner.next();
                                Opera rez = new Opera(name, oras, ora, durata, price ,date, numeOpera, limba);
                                repositoryFO.addSpectacol(rez);
                                break;
                            }
                            case 3: {
                                System.out.print("Give the name of the stand-upers: ");
                                scanner.nextLine();
                                String comedianti = scanner.nextLine();
                                StandUp rez = new StandUp(name, oras, ora, durata, price ,date, comedianti);
                                repositoryFS.addSpectacol(rez);
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 10:{
                        System.out.print("Give the username: ");
                        String username = scanner.next();
                        System.out.print("Give the password: ");
                        String password = scanner.next();
                        Client c = new Client();
                        c.setNume(username);
                        c.setParola(password);
                        repositoryFC.addClient(c);
                        break;
                    }
                    case 11:{
                        System.out.print("Give the username: ");
                        String username = scanner.next();
                        Client c = repositoryFC.findClientByUsername(username);
                        System.out.println(c);
                        break;
                    }
                    case 12:{
                        System.out.print("Give the ID: ");
                        int id = scanner.nextInt();
                        Client c = repositoryFC.findClientById(id);
                        System.out.println(c);
                        break;
                    }
                    case 13:{
                        System.out.print("Give the username: ");
                        String username = scanner.next();
                        repositoryFC.deleteClientByUsername(username);
                        break;
                    }
                    case 14:{
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();
                        System.out.print("Give the name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        switch (command2) {
                            case 1: {
                                repositoryFT.deleteSpectacolByNume(name);
                                break;
                            }
                            case 2: {
                                repositoryFO.deleteSpectacolByNume(name);
                                break;
                            }
                            case 3: {
                                repositoryFS.deleteSpectacolByNume(name);
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 15:{
                        System.out.println("1.Teather 2.Opera 3.Stand-Up");
                        int command2 = scanner.nextInt();

                        System.out.print("Give the old hour: ");
                        int old = scanner.nextInt();
                        System.out.println("Give the new hour: ");
                        int neww = scanner.nextInt();

                        switch (command2) {
                            case 1: {
                                repositoryFT.updateOraSpectacol(old, neww);
                                break;
                            }
                            case 2: {
                                repositoryFO.updateOraSpectacol(old, neww);
                                break;
                            }
                            case 3: {
                                repositoryFS.updateOraSpectacol(old, neww);
                                break;
                            }
                            default: {
                                System.out.println("The-command-doesn't-exist");
                                break;
                            }
                        }
                        break;
                    }
                    case 16:{
                        FileLoginService service = FileLoginService.getInstance();
                        System.out.print("Give the username: ");
                        String username = scanner.next();
                        System.out.print("Give the password: ");
                        String password = scanner.next();

                        Client c  = new Client();
                        c.setNume(username);
                        c.setParola(password);

                        boolean rez = service.login(c);
                        if(rez){
                            System.out.println("Succes login !");
                        }
                        else{
                            System.out.println("Incorrect username or password");
                        }
                        break;
                    }
                    case 17:{

                        //alocarea locurilor

                        System.out.println("Locuri disponibile in SectorA(1-50), SectorB(51-100), Balcon(101-110) si Loja(111-120)");
                        FileRezTeatruService rezerv = FileRezTeatruService.getInstance();
                        FileLoginService service =  FileLoginService.getInstance();
                        System.out.print("Client by username: ");
                        String username = scanner.next();
                        System.out.print("Choose by name,hour and date: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        int ora = scanner.nextInt();
                        String date = scanner.next();
                        System.out.print("Choose the sit: ");
                        int loc = scanner.nextInt();

                        Client c = repositoryFC.findClientByUsername(username);
                        Teatru t = (Teatru)repositoryFT.findSpectacol(ora,date,name);

                        rezerv.rezervare(c, service, t, locuriPiesa.get(loc - 1));
                        break;
                    }
                    case 18:{

                        System.out.println("Locuri disponibile in SectorA(1-50), SectorB(51-100), Balcon(101-110) si Loja(111-120)");

                        FileRezOperaService rezerv = FileRezOperaService.getInstance();
                        FileLoginService service = FileLoginService.getInstance();

                        System.out.print("Client by username: ");
                        String username = scanner.next();
                        System.out.print("Choose Opera by name, hour and date: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        int ora = scanner.nextInt();
                        String date = scanner.next();
                        System.out.print("Choose the sit: ");
                        int loc = scanner.nextInt();

                        Client c = repositoryFC.findClientByUsername(username);
                        Opera t = (Opera)repositoryFO.findSpectacol(ora,date,name);

                        rezerv.rezervare(c, service, t, locuriOpera.get(loc - 1));
                        break;
                    }
                    case 19:{

                        FileRezStandUpService rezerv = FileRezStandUpService.getInstance();
                        FileLoginService service =  FileLoginService.getInstance();

                        System.out.print("Client by username: ");
                        String username = scanner.next();
                        System.out.print("Choose stand-up show by name, hour and date: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        int ora = scanner.nextInt();
                        String date = scanner.next();
                        System.out.println("Locuri disponibile de la 1 la 100");
                        System.out.print("Choose the sit: ");
                        int loc = scanner.nextInt();

                        Client c = repositoryFC.findClientByUsername(username);
                        StandUp t = (StandUp) repositoryFT.findSpectacol(ora,date,name);

                        rezerv.rezervare(c, service, t, locuri.get(loc - 1));
                        break;
                    }
                    case 20:{
                        ArrayList<Spectacol> s = new ArrayList<>();
                        ArrayList<Teatru> teatru = repositoryFT.afisSpectacole();
                        ArrayList<Opera> opera = repositoryFO.afisSpectacole();
                        ArrayList<StandUp> stand = repositoryFS.afisSpectacole();
                        s.addAll(teatru);
                        s.addAll(opera);
                        s.addAll(stand);
                        s.sort(new HourComparator());
                        System.out.println(s);
                        break;
                    }
                    default: {
                        System.out.println("The-command-doesn't-exist");
                        break;
                    }

                }

                printMenu();
                System.out.println();
                System.out.println("The next command is: ");
                command = scanner.nextInt();
            }
        }
    }
}
