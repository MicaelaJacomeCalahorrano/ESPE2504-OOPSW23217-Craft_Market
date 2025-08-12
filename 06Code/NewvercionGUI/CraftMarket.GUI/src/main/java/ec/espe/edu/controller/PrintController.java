package ec.espe.edu.controller;

import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Isaac Maisincho Crafters_Market DCCO ESPE
 */
public class PrintController {

    public static void imprimirTabla(JTable table, String tituloReporte, String totalVentas) {
        try {
           
            MessageFormat header = new MessageFormat(tituloReporte + "   |   Total: " + totalVentas);
            
            MessageFormat footer = new MessageFormat("Página {0}");

            boolean complete = table.print(JTable.PrintMode.FIT_WIDTH, header, footer);

            if (complete) {
                JOptionPane.showMessageDialog(null, "Impresión completada");
            } else {
                JOptionPane.showMessageDialog(null, "Impresión cancelada");
            }

        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Error de impresión: " + e.getMessage());
        }
    }
}
