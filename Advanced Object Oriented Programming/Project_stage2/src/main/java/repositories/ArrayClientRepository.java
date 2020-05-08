package repositories;

import model.Client;

import java.util.ArrayList;

public class ArrayClientRepository {
    private ArrayList<Client> clienti = new ArrayList<>();

    public void addClient(Client client) {
        clienti.add(client);
    }

    public Client findClientByUsername(String username) {
        for (int i = 0; i < clienti.size(); i++) {
            if (clienti.get(i).getNume().equals(username)) {
                return (clienti.get(i));
            }
        }
        return null;
    }

    public Client findClientById(int id) {
        for (int i = 0; i < clienti.size(); i++) {
            if (clienti.get(i).getId() == id) {
                return (clienti.get(i));
            }
        }
        return null;
    }

    public void afisClienti() {
        for(Client c: clienti){
            System.out.println(c);
        }
    }

}
