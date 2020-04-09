package main;

import comaprators.*;
import model.*;
import repositories.*;
import service.LoginService;
import service.RezOperaService;
import service.RezStandUpService;
import service.RezTeatruService;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // ADAUGARE CLIENT
        ClientRepository repository = new ClientRepository();
        Client client = new Client();
        client.setNume("Maria Tudor");
        client.setParola("maria");
//        repository.addClient(client);

        // AFISARE TOTI CLIENTII
        ArrayList<Client> resultClient = repository.afisClienti();
//        System.out.println(resultClient);

        // VERIFICARE LOGIN CLIENT
        LoginService service = new LoginService();
        Client u1 = new Client(0, "Maria Tudor", "maria");
        boolean res1 = service.login(u1);
//        System.out.println(res1);

        Client u2 = new Client(2,"jane", "1234");
        boolean res2 = service.login(u2);
//        System.out.println(res2);


        // ADAUGARE PIESA DE TEATRU
        SpectacolRepository repositoryT = SpectacolRepository.build(SpectacolRepository.Type.Teatru);

        Teatru teatru = new Teatru();
        teatru.setNume("Gaitele");
        teatru.setOras("Ploiesti");
        teatru.setOra(18);
        teatru.setDurata(90);
        teatru.setPret(45);
        teatru.setData("30.09.2020");
        teatru.setNumeTeatru("Teatrul Toma Caragiu ");
        teatru.setActori("Adriana Trandafir, Carmen Tanase");
//        repositoryT.addSpectacol(teatru);

        // GASIRE PIESA DE TEATRU DUPA ORA, DATA SI NUME
//        Teatru t1 = repositoryT.findSpectacol(18, "30.09.2020", "Gaitele");
//        System.out.println(t1.toString());

        // AFISARE TOATE PIESELE DE TEATRU
        ArrayList<Teatru> resultTeatru = repositoryT.afisSpectacole();
//        System.out.println(resultTeatru);

        // REZERVARE LA O PIESA DE TEATRU
        ArrayList<LocTeatruOpera> locuriPiesa = new ArrayList<>();
        //alocarea locurilor
        int i;
        for (i = 1; i <= 100; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.SectorA);
            locuriPiesa.add(loc);
        }
        RezTeatruService rezervT = new RezTeatruService();
        rezervT.rezervare(resultClient.get(1), service, resultTeatru.get(1), locuriPiesa.get(11));

        // CAUTARE PIESE DE TEATRU DUPA DATA/ORA/NUME/NUME TEATRU
        ArrayList<Teatru> resultC1 = repositoryT.findSpectacolByOra(19);
//        System.out.println(resultC1);

        // SORTAREA PIESELOR DE TEATRU DUPA ORA DE INCEPUT
        Collections.sort(resultTeatru, new HourComparator());
//        for (Object x : resultTeatru)
//            System.out.println(x.toString());


        // ADAUGARE OPERA
        SpectacolRepository repositoryO = SpectacolRepository.build(SpectacolRepository.Type.Opera);

        Opera opera = new Opera();
        opera.setNume("Carmen");
        opera.setOras("Bucuresti");
        opera.setOra(19);
        opera.setDurata(180);
        opera.setPret(75);
        opera.setData("1.06.2020");
        opera.setNumeOpera("Opera Nationala");
        opera.setLimba("italiana");
//        repositoryO.addSpectacol(opera);

        //AFISAREA TUTUROR OPERELELOR
        ArrayList<Opera> resultOpera = repositoryO.afisSpectacole();
//        System.out.println(resultOpera);

        // REZERVARE LA UN SPECTACOL DE OPERA
        //setare locuri
        ArrayList<LocTeatruOpera> locuriOpera = new ArrayList<>();
        for (i = 1; i <= 50; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.Loja);
            locuriOpera.add(loc);
        }
        for(i = 51; i <= 100; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.SectorA);
            locuriOpera.add(loc);
        }
        for(i = 101; i <= 120; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.Balcon);
            locuriOpera.add(loc);
        }
        for(i = 121; i <= 150; i++){
            LocTeatruOpera loc = new LocTeatruOpera(i, LocTeatruOpera.sector.SectorB);
            locuriOpera.add(loc);
        }

        RezOperaService rezervO = new RezOperaService();
        rezervO.rezervare(resultClient.get(0), service, resultOpera.get(0), locuriOpera.get(10));

        // CAUTARE OPERA DUPA DATA/ORA/NUME/NUME OPERA
        ArrayList<Opera> resultC2 = repositoryO.findSpectacolByOra(18);
//        System.out.println(resultC2);


        // ADAUGARE SHOW DE STAND-UP
        SpectacolRepository repositoryS = SpectacolRepository.build(SpectacolRepository.Type.StandUp);
        StandUp standup = new StandUp();
        standup.setNume("Haha");
        standup.setOras("Sibiu");
        standup.setOra(21);
        standup.setDurata(60);
        standup.setPret(30);
        standup.setData("04.06.2020");
        standup.setComedianti("Micutzu, Dan Badea");
//        repositoryS.addSpectacol(standup);


        // AFISAREA TUTUROR SHOW-URILOR DE STAND-UP
        ArrayList<StandUp> resultStandUp = repositoryS.afisSpectacole();
//        System.out.println(resultStandUp);

        // CAUTARE STAND-UP DUPA DATA/ORA/NUME
        ArrayList<StandUp> resultC3 = repositoryS.findSpectacolByData("04.06.2020");
//        System.out.println(resultC3);

        //REZERVARE LOC LA STAND-UP
        ArrayList<Loc> locuriStandUp = new ArrayList<>();
        for (i = 1; i <= 90; i++){
            Loc loc = new Loc(i);
            locuriStandUp.add(loc);
        }
        RezStandUpService rezervS = new RezStandUpService();
        rezervS.rezervare(resultClient.get(0), service, resultStandUp.get(1), locuriStandUp.get(3));
        //loc ocupat
        rezervS.rezervare(resultClient.get(0), service, resultStandUp.get(1), locuriStandUp.get(3));


//        AFISAREA TUTUROR SPECTACOLELOR
        ArrayList<Object> tot = new ArrayList<>();
        tot.addAll(resultStandUp);
        tot.add(resultOpera);
        tot.add(resultTeatru);
//        System.out.println(tot);

    }
}
