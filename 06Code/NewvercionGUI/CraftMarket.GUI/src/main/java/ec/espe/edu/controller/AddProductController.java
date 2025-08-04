package ec.espe.edu.controller;

import ec.espe.edu.model.Product;
import javax.swing.JOptionPane;

/**
 *
 * @author Micaela Jácome
 */
public class AddProductController {

    public static boolean addProduct(String idText, String name, String priceText,
        String stockText, String owner, java.awt.Component parent) {
    try {
        // Validar campos vacíos (mensajes en español)
        if (idText.isEmpty() || name.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
            showError("Todos los campos son obligatorios", parent);
            return false;
        }

        // Validar formato numérico
        int id = Integer.parseInt(idText);
        double price = Double.parseDouble(priceText);
        int stock = Integer.parseInt(stockText);

        // Validar valores positivos
        if (price <= 0 || stock <= 0) {
            showError("Precio y Stock deben ser valores positivos", parent);
            return false;
        }

        // Verificar si el ID ya existe
        if (Product.findById(id) != null) {
            showError("El ID del producto ya existe", parent);
            return false;
        }

        // Crear y guardar producto (el orden ya lo maneja la clase Product)
        Product product = new Product(id, name, price, stock, owner);
        Product.addProduct(product);

        JOptionPane.showMessageDialog(parent, "Producto guardado exitosamente",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        return true;

    } catch (NumberFormatException e) {
        showError("ID, Precio y Stock deben ser números válidos", parent);
        return false;
    } catch (Exception e) {
        showError("Error al guardar: " + e.getMessage(), parent);
        return false;
    }
    }

    private static void showError(String message, java.awt.Component parent) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
