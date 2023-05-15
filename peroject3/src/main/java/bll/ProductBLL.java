package bll;

import abstractDAO.ProductDAO;
import model.Client;
import model.Product;

import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductDAO productDAO;

    public ProductBLL(){
        productDAO=new ProductDAO();
    }
    public Product findClientById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

}
