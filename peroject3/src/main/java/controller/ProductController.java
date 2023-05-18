package controller;

import bll.ProductBLL;
import model.Product;
import view.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductController {
    private ProductBLL productBLL;
    private view viewProduct;

    public ProductController(ProductBLL productBLL, view viewProduct) {
        this.productBLL = productBLL;
        this.viewProduct = viewProduct;

        viewProduct.getProdSearchIdBtn().addActionListener(new SearchByIdListener());
        viewProduct.getProdCreateBtn().addActionListener(new CreateProductListener());
        viewProduct.getProdSearchAllBtn().addActionListener(new SearchAllListener());
        viewProduct.getProdDeleteBtn().addActionListener(new DeleteProductListener());
        viewProduct.getProdUpdateBtn().addActionListener(new UpdateProductListener());
    }

    /**
     * Interal method to not have duplicate code, it's a method that gets the text from the view textFields and makes a new Product obj with it
     * @return Product made from the texted in textFields
     */
    public Product initProduct(){
        String name = viewProduct.getTextName().getText();
        int quantity = Integer.parseInt(viewProduct.getTextQuantity().getText());
        int price =  Integer.parseInt(viewProduct.getTextPrice().getText());
        int id =  Integer.parseInt(viewProduct.getTextId().getText());

        // Create a new Product object
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
        return  product;
    }

    /**
     * ActionListener for searching a product by ID
     */
    class SearchByIdListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(viewProduct.getTextId().getText());
            Product product = productBLL.findProductById(id);

            viewProduct.getTextName().setText(product.getName());
            viewProduct.getTextQuantity().setText(String.valueOf(product.getQuantity()));
            viewProduct.getTextPrice().setText(String.valueOf(product.getPrice()));
            viewProduct.getTextId().setText(String.valueOf(product.getId()));
        }
    }

    /**
     * ActionListener for creating a product
     */
    class CreateProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Product product= initProduct();
            boolean success = productBLL.insert(product);

            if (success) {
                JOptionPane.showMessageDialog(viewProduct, "Product created successfully!");
            } else {
                JOptionPane.showMessageDialog(viewProduct, "Error creating product!");
            }
        }
    }

    /**
     * ActionListener for showing all products
     */
    class SearchAllListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get all products using the BLL
            ArrayList<Product> products = productBLL.getAllProducts();

            // Display the products in the view (e.g., in a table)
            // ...
        }
    }

    /**
     *  ActionListener for deleting a product
     */
    class DeleteProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Product product= initProduct();
            boolean success = productBLL.delete(product);

            if (success) {
                JOptionPane.showMessageDialog(viewProduct, "Product deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(viewProduct, "Error deleting product!");
            }
        }
    }

    /**
     * ActionListener for updating a product
     */
    class UpdateProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Product product= initProduct();
            boolean success = productBLL.updateProduct(product);

            if (success) {
                JOptionPane.showMessageDialog(viewProduct, "Product updated successfully!");
            } else {
                JOptionPane.showMessageDialog(viewProduct, "Error updating product!");
            }
        }
    }
}
