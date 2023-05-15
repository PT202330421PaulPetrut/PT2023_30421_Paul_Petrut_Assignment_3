package abstractDAO;

import model.Client;

import java.util.List;

public class ClientDAO extends AbstractDAO<Client>{
    @Override
    public Client findById(int id) {
        return super.findById(id);
    }

    @Override
    public Client insert(Client client) {
        return super.insert(client);
    }

    @Override
    public List<Client> findAll() {
        return super.findAll();
    }

    @Override
    public Client update(Client client) {
        return super.update(client);
    }
}