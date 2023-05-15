package bll;

import abstractDAO.ClientDAO;
import bll.validator.EmailValidator;
import bll.validator.Validator;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Petrica scrie clase ca si in model
 * Si nici el nu stie ce face
 */
public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());

        clientDAO = new ClientDAO();
    }

    public Client findClientById(int id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }
}

