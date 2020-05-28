package service;

import model.Client;
import model.LocTeatruOpera;
import model.Opera;
import model.Rezervare;
import repositories.opera.ArrayOperaRepository;

import java.util.ArrayList;

public class ArrayRezOperaService {

    ArrayList<Rezervare> rezervari = new ArrayList<>();
    private ArrayOperaRepository operaRepository;

    public ArrayRezOperaService() {operaRepository = new ArrayOperaRepository();}

    public void rezervare(Client client, ArrayLoginService loginService, Opera opera, LocTeatruOpera loc){
        if (loginService.login(client)){
            if(loc.isLiber()){

                Rezervare rez = new Rezervare(rezervari.size(), client.getNume(), opera.getNume(), opera.getData(), opera.getOra(), loc.getNrLoc(), loc.getZona());
                rezervari.add(rez);
                loc.setLiber(false);

                System.out.println("Rezervarea a fost efectuata cu succes !");
            }
            else {
                System.out.println("Locul este ocupat !");
            }
        }
    }

    public void afisRezervari() {
        for (Rezervare i: rezervari) {
            System.out.println(i);
        }
    }
}
