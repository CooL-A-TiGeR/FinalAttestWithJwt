package my.app.finalAtt.service;

import my.app.finalAtt.model.Client;
import my.app.finalAtt.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public void create(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> readAll() {
        return clientRepository.findAll();
    }

    @Nullable
    @Override
    public Client read(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Client client, long id) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            clientRepository.save(client);
            return true;
        }
        return false;
    }

    @Override
    public Client getByLoginAndPassword(String login, String password) {
        return clientRepository.getByLoginAndPassword(login, password);
    }

    @Override
    public boolean delete(long id) {
        return clientRepository.deleteById(id);
    }
}
