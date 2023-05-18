package controller;

import bll.ClientBLL;
import bll.ProductBLL;
import view.viewClient;
import view.view;
import view.viewPurchase;
import bll.PurchaseBLL;
import model.Purchase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PurchaseController {
    private viewPurchase viewPurchase;
    private PurchaseBLL purchaseBLL;

    public PurchaseController(viewPurchase view, PurchaseBLL purchaseBLL) {
        this.viewPurchase = view;
        this.purchaseBLL = purchaseBLL;

        // Add action listeners to the buttons
        view.getSearchIdPurchaseBtn().addActionListener(new SearchIdPurchaseListener());
        view.getGiveAllOrdersBtn().addActionListener(new GiveAllOrdersListener());
        view.getGoPurchaseBtn().addActionListener(new GoProductListener());
        view.getGoClientBtn().addActionListener(new GoClientListener());
    }

    // ActionListener for searchIdPurchaseBtn
    class SearchIdPurchaseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(viewPurchase.getTextId().getText());
            try {
                Purchase purchase = purchaseBLL.findPurchaseById(id);
               // view.displayPurchaseDetails(purchase);
            } catch (NoSuchElementException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(viewPurchase, "Purchase not found!");
            }
        }
    }

    // ActionListener for giveAllOrdersBtn
    class GiveAllOrdersListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<Purchase> purchases = purchaseBLL.findAll();
            //view.displayAllPurchases(purchases);
        }
    }

    // ActionListener for goPurchaseBtn
    class GoProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO: Open the purchase view
            // Example code:
            view productView = new view();
            ProductBLL productBLL= new ProductBLL();
            ProductController productController = new ProductController(productBLL, productView);
            productView.setVisible(true);
        }
    }

    // ActionListener for goClientBtn
    class GoClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO: Open the client view
            // Example code:
            viewClient clientView = new viewClient();
            ClientBLL clientBLL =new ClientBLL();
            ClientController clientController = new ClientController(clientView, clientBLL);
            clientView.setVisible(true);
        }
    }
}
