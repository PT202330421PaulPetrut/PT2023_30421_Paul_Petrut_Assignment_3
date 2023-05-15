package model;

import java.util.ArrayList;

public class Purchase {
    public int id;
    public int clientID;
    public ArrayList<Integer> productId;
    public void addProduct(int id){
        productId.add(id);
    }
    public int removeProduct(){
        return productId.remove(0);
    }
    public int removeProduct(int index){
        return productId.remove(index);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public ArrayList<Integer> getProductId() {
        return productId;
    }

    public void setProductId(ArrayList<Integer> productId) {
        this.productId = productId;
    }
}
