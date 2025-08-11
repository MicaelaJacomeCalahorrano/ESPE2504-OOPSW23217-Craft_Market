/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.espe.edu.controller;

import ec.espe.edu.model.Product;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class TableController {
      public static void loadProductsToTable(JTable tblProducts) {
        List<Product> products = Product.getAllProducts();
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
        model.setRowCount(0);

        for (Product product : products) {
            Object[] row = {
                product.getId(),
                product.getName(),
                product.getUnitPrice(),
                product.getStock(),
                product.getOwner()
            };
            model.addRow(row);
        }
    }
}
