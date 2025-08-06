package ec.espe.edu.controller;

import ec.espe.edu.model.Product;

/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */
public class StockManager {
    /**
     * Actualiza el stock de un producto restando la cantidad vendida.
     * @param productId ID del producto
     * @param quantityVendida cantidad vendida
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public static boolean updateStockAfterSale(int productId, int quantityVendida) {
        Product product = Product.findById(productId);
        if (product == null) {
            return false;
        }
        int newStock = product.getStock() - quantityVendida;
        if (newStock < 0) {
            return false;
        }
        Product.updateProductStock(productId, newStock);
        return true;
    }
}
