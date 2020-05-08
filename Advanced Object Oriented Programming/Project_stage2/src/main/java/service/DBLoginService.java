package service;

import model.Client;
import repositories.DBClientRepository;

public class DBLoginService {
    private DBClientRepository clientRepository;

    public DBLoginService() {
        clientRepository = new DBClientRepository();
    }

    public boolean login(Client client) {
        Client result = clientRepository.findClientByUsername(client.getNume());

        if (result != null) {
            if (result.getParola().equals(client.getParola())) {
                return true;
            }
        }

        return false;
    }
}
