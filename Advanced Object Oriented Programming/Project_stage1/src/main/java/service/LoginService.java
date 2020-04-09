package service;

import model.Client;
import repositories.ClientRepository;

public class LoginService {
    private ClientRepository clientRepository;

    public LoginService() {
        clientRepository = new ClientRepository();
    }

    public boolean login(Client client) {
        Client result = clientRepository.findUserByUsername(client.getNume());

        if (result != null) {
            if (result.getParola().equals(client.getParola())) {
                return true;
            }
        }

        return false;
    }
}
