package ec.espe.edu.controller;

import ec.espe.edu.model.Product;
import ec.espe.edu.model.SalesReport;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class SaleController {

    public static void handleSale(
            JTextField txtProductId,
            JSpinner spnQuantity,
            JTable tblProducts
    ) {
        String productIdText = txtProductId.getText().trim();
        if (productIdText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese el ID del producto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int productId;
        try {
            productId = Integer.parseInt(productIdText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int quantity = (int) spnQuantity.getValue();
        Product product = Product.findById(productId);
        if (product == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ProductValidator.isValidStock(quantity) || quantity == 0) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (quantity > product.getStock()) {
            JOptionPane.showMessageDialog(null, "Stock insuficiente. Solo hay " + product.getStock() + " unidades disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double unitPrice = product.getUnitPrice();
        double total = SaleCalculator.calculateTotal(unitPrice, quantity);
        String artisanName = product.getOwner();

        int option = JOptionPane.showConfirmDialog(
                null,
                "¿Desea confirmar la compra?\n\nProducto: " + product.getName()
                + "\nCantidad: " + quantity
                + "\nTotal: $" + String.format("%.2f", total),
                "Confirmar Venta",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (option != JOptionPane.OK_OPTION) {
            return;
        }

        SalesReport sale = new SalesReport(product.getName(), unitPrice, quantity, total, artisanName);
        SalesReport.registerSale(sale);

        boolean stockUpdated = StockManager.updateStockAfterSale(product.getId(), quantity);
        if (!stockUpdated) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el stock del producto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TableController.loadProductsToTable(tblProducts);

        JOptionPane.showMessageDialog(null,
                "Venta registrada exitosamente.\nProducto: " + product.getName()
                + "\nCantidad: " + quantity
                + "\nTotal: $" + String.format("%.2f", total),
                "Venta Exitosa",
                JOptionPane.INFORMATION_MESSAGE);

        txtProductId.setText("");
        spnQuantity.setValue(1);
        txtProductId.requestFocus();
    }

}
