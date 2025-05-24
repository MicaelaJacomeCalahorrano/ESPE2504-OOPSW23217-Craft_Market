package ec.espe.edu.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jorge Fuentes CraftMarket DCCO ESPE
 */
public class SearchReport {
    private static final String CSV_DIRECTORY = "ventas/";

    public static void InsertDate() throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserte la fecha en el formato: AAAA-MM-DD: ");
        String date = scanner.nextLine();
        
        File foundReportFile = findReportFile(date);
        if (foundReportFile == null) {
            System.out.println("Búsqueda finalizada.");
        }
    }

    public static File findReportFile(String date) throws FileNotFoundException, IOException{
        String fileName = CSV_DIRECTORY + "ventade_" + date + ".csv"; 
        File file = new File(fileName);

        if(!file.exists()){
            System.out.println("No se encontró el reporte para la fecha: " + date + " en el directorio: " + CSV_DIRECTORY);
            return null;
        }
        printReport(file,date);
        return file;
    }

    public static void printReport(File file, String date){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
           String line; 
           System.out.println("----- Reporte de ventas de " + date + " -----");
           
           while((line=br.readLine()) !=null){
               String[] fields = line.split(",");
               for(int index =0;index < fields.length; index++){
                   System.out.print(fields[index].trim());
                   if(index < fields.length -1){
                       System.out.print(", ");
                   }
               }
               System.out.println("");
           }
        }catch(IOException e){
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
    }
}
