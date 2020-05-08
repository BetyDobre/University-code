package repositories;

import model.Client;

import java.util.ArrayList;


public interface ClientRepository {

    void addClient(Client client);
    Client findClientByUsername(String username);
    Client findClientById(int id);
    ArrayList<Client> afisClienti();
    void deleteClientByUsername(String username);
    static ClientRepository build(Type type) {
        switch (type) {
            case DB: return new DBClientRepository();
            case FILE: return new FileClientRepository();
        }
        throw new RuntimeException("No such type");
    }

    enum Type {
        DB, FILE
    }
}
