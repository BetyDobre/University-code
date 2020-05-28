package service;

import model.Client;
import repositories.client.ArrayClientRepository;

public class ArrayLoginService {

    private ArrayClientRepository clientRepository;

    public ArrayLoginService() {
        clientRepository = new ArrayClientRepository();
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
