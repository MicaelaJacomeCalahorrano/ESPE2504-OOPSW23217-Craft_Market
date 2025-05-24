package ec.espe.edu.utils;

import ec.espe.edu.model.SalesReport;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class SalesUtils {

    private static final String SALES_FILE = "sales.csv";

    public static void saveSale(SalesReport sale) {
        try (FileWriter fw = new FileWriter(SALES_FILE, true)) {
            fw.write(sale.toCSV() + "\n");
            System.out.println("Venta guardada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la venta: " + e.getMessage());
        }
    }

    public static List<SalesReport> loadSales() {
        List<SalesReport> sales = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(SALES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    String product = fields[0];
                    float price = Float.parseFloat(fields[1]);
                    int quantity = Integer.parseInt(fields[2]);
                    float total = Float.parseFloat(fields[3]);
                    String artisan = fields[4];
                    sales.add(new SalesReport(product, price, quantity, total, artisan ));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de ventas no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de ventas: " + e.getMessage());
        }

        return sales;
    }
}
