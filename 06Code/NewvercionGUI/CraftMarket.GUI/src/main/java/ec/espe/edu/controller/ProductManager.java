package ec.espe.edu.controller;

import ec.espe.edu.model.Product;

/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */
public class ProductManager {
    
    public static boolean updateProductName(int productId, String newName) {
        Product product = Product.findById(productId);
        if (product == null) {
            return false;
        }
        product.setName(newName);
        Product.updateProduct(product);
        return true;
    }

    
    public static boolean updateProductPrice(int productId, double newPrice) {
        Product product = Product.findById(productId);
        if (product == null) {
            return false;
        }
        product.setUnitPrice(newPrice);
        Product.updateProduct(product);
        return true;
    }

    
    public static boolean updateProductStock(int productId, int newStock) {
        Product product = Product.findById(productId);
        if (product == null) {
            return false;
        }
        product.setStock(newStock);
        Product.updateProduct(product);
        return true;
    }
}
