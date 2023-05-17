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
    public Product findProductById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }
    public boolean insert(Product product){
        if(!productDAO.insert(product)){
            System.out.print("Insert product error, return=false\n");
            return false;
        }
        return true;
    }

}
