package bll;

import abstractDAO.ProductDAO;

public class ProductBLL {
    private ProductDAO productDAO;

    public ProductBLL(){
        productDAO=new ProductDAO();
    }
}
