package ec.espe.edu.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jorge
 */
public class MontlyReport {
    private static final String SALES_DIRECTORY = "ventas/";

    public static void generateMonthlyReport() {
        File folder = new File(SALES_DIRECTORY);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null || listOfFiles.length == 0) {
            System.out.println("No se encontraron archivos de ventas en el directorio: " + SALES_DIRECTORY);
            return;
        }

        Map<String, Double> monthlyProfits = new HashMap<>();
        Map<String, List<String>> monthlySalesDetails = new HashMap<>();

        Pattern pattern = Pattern.compile("ventade_(\\d{4}-\\d{2}-\\d{2})\\.csv");

        System.out.println("\n----- Generando Reporte Mensual de Ventas -----");

        for (File file : listOfFiles) {
            if (file.isFile()) {
                Matcher matcher = pattern.matcher(file.getName());
                
                if (matcher.matches()) {
                    String dateString = matcher.group(1);
                    
                    try {
                        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
                        String monthKey = date.format(DateTimeFormatter.ofPattern("yyyy-MM"));

                        List<String> dailySalesLines = new ArrayList<>();
                        double dailyTotal = calculateDailyTotalAndGetLines(file, dailySalesLines);

                        monthlyProfits.put(monthKey, monthlyProfits.getOrDefault(monthKey, 0.0) + dailyTotal);
                        
                        monthlySalesDetails.computeIfAbsent(monthKey, k -> new ArrayList<>()).addAll(dailySalesLines);

                    } catch (DateTimeParseException e) {
                        System.err.println("Advertencia: Nombre de archivo con formato de fecha inválido - " + file.getName());
                    } catch (IOException e) {
                        System.err.println("Error al leer el archivo " + file.getName() + ": " + e.getMessage());
                    }
                }
            }
        }

        if (monthlyProfits.isEmpty()) {
            System.out.println("No se encontraron ventas válidas para generar un reporte mensual.");
        } else {
            monthlyProfits.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    String monthKey = entry.getKey();
                    double totalProfit = entry.getValue();
                    List<String> salesForMonth = monthlySalesDetails.get(monthKey);

                    if (salesForMonth != null) {
                        for (String saleLine : salesForMonth) {
                            System.out.println(saleLine);
                        }
                    }
                    System.out.printf("Mes: %s - Ganancia Total: $%.2f\n", monthKey, totalProfit);
                    System.out.println("==================================");
                });
        }
    }

    private static double calculateDailyTotalAndGetLines(File dailyFile, List<String> salesLines) throws IOException {
        double total = 0.0;
        try (BufferedReader br = new BufferedReader(new FileReader(dailyFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                salesLines.add(line);
                String[] fields = line.split(",");
                if (fields.length > 3) { 
                    try {
                        total += Double.parseDouble(fields[3].trim());
                    } catch (NumberFormatException e) {
                        System.err.println("Advertencia: Formato de número inválido en la línea de " + dailyFile.getName() + ": " + line);
                    }
                }
            }
        }
        return total;
    }
}
