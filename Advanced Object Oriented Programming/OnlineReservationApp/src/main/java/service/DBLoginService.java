package service;

import model.Client;
import repositories.ClientRepository;
import repositories.client.DBClientRepository;

public class DBLoginService {

    private ClientRepository clientRepository = ClientRepository.build(ClientRepository.Type.DB);

    public DBLoginService() {}

    public boolean login(Client client) {
        Client result = clientRepository.findClientByUsername(client.getNume());

        if (result != null) {
            if (result.getParola().equals(client.getParola())) {
                return true;
            }
        }

        return false;
    }

    public static DBLoginService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final  DBLoginService INSTANCE = new  DBLoginService();
    }
}
