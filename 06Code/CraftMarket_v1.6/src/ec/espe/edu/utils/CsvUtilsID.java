package ec.espe.edu.utils;

/**
 *
 * @author Micaela Jacome DESKTOP-46VMNHU ESPE
 */

import java.io.FileWriter;
import java.io.IOException;

public class CsvUtilsID {
    public static void save(String oldId, String newId){
        try(FileWriter writer = new FileWriter("product_id.csv", true)) {
                writer.append(oldId)
                       .append(",")
                       .append(newId)
                       .append("\n");
        }catch (IOException e){
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }
    
}
