package model;

import abstractDAO.AbstractDAO;
import reflection.Reflective;

import java.util.ArrayList;

public class Client {
    int id;
    String name;
    String email;
    public Client(){

    }

    /**
     * A method that returns a Client if the ID is valid
     * @return
     */
    public Client getClientById(int idul){
        AbstractDAO<Client> abstractDAO = new AbstractDAO<Client>();
        Reflective.reflectiveProperties(abstractDAO);
        return (Client) abstractDAO.findById(idul);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString(){
        return "Id "+id+" name: "+name+" email: "+email;
    }
}
