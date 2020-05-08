package service;

import model.Client;
import repositories.FileClientRepository;

public class FileLoginService {
    private FileClientRepository clientRepository;

    public FileLoginService() {
        clientRepository = new FileClientRepository();
    }

    public boolean login(Client client) {
        Client result = clientRepository.findClientByUsername(client.getNume());

        if (result != null) {
            if (result.getParola().equals(client.getParola())) {
                return true;
            }
        }
        AuditService c = (new AuditService("Login client"));
        Thread t = new Thread(c);
        t.start();
        return false;
    }
}
