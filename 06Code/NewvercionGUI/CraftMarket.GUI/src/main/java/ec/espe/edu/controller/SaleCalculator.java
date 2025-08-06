package ec.espe.edu.controller;

/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */
public class SaleCalculator {
    /**
     * Calcula el total de una venta dado el precio unitario y la cantidad.
     * @param unitPrice Precio unitario del producto
     * @param quantity Cantidad vendida
     * @return Total de la venta
     */
    public static double calculateTotal(double unitPrice, int quantity) {
        return unitPrice * quantity;
    }
}
