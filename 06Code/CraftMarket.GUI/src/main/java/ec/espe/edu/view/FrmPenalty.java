/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ec.espe.edu.view;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.espe.edu.model.utils.MongoConnection;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Calendar;
import java.util.Collections; 
import java.util.Date;
import java.util.HashSet;
import java.util.List; 
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.print.PrinterException;
import javax.swing.JTable.PrintMode;

/**
 *
 * @author Michael Chicaiza SOFTCRAF DCCO ESPE
 */
public class FrmPenalty extends javax.swing.JFrame {
     private String loggedInUsername;
    private DefaultTableModel tableModel;
    private final double PENALTY_PER_DAY = 5.00;
    /**
     * Creates new form FrmPenalty
     */
    public FrmPenalty(String username) {
        this.loggedInUsername = username;
        initComponents();
        setLocationRelativeTo(null);
          Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

        txtArtesanoPenalty.setText(loggedInUsername);
        txtArtesanoPenalty.setEditable(false);

        setupTableModel();
        calculateAndDisplayPenalty();
        
        btnReturnPenalty.addActionListener(e -> {
            this.dispose();
           
        });
    }
     private void setupTableModel() {
        tableModel = new DefaultTableModel(new Object[]{"Nro.", "Día Ausente"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(tableModel); 
    }
      private void calculateAndDisplayPenalty() {
        tableModel.setRowCount(0); 
        int absentDaysCount = 0;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<Date> absentDates = new ArrayList<>(); 

        try {
            MongoDatabase db = MongoConnection.connect();
            MongoCollection<Document> attendanceCollection = db.getCollection("Attendance");

            // Obtener el mes y año actual
            Calendar cal = Calendar.getInstance();
            int currentMonth = cal.get(Calendar.MONTH);
            int currentYear = cal.get(Calendar.YEAR);

            
            Set<String> businessDaysInMonthFormatted = new HashSet<>(); 
            List<Date> businessDaysInMonth = new ArrayList<>(); 
            
            Calendar monthCalendar = Calendar.getInstance();
            monthCalendar.set(currentYear, currentMonth, 1); 
            monthCalendar.set(Calendar.HOUR_OF_DAY, 0);
            monthCalendar.set(Calendar.MINUTE, 0);
            monthCalendar.set(Calendar.SECOND, 0);
            monthCalendar.set(Calendar.MILLISECOND, 0);

            while (monthCalendar.get(Calendar.MONTH) == currentMonth) {
                int dayOfWeek = monthCalendar.get(Calendar.DAY_OF_WEEK);
               
                if (dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY) {
                    businessDaysInMonthFormatted.add(dateFormat.format(monthCalendar.getTime()));
                    businessDaysInMonth.add(monthCalendar.getTime()); 
                }
                monthCalendar.add(Calendar.DAY_OF_MONTH, 1); 
            }
            
            
            Collections.sort(businessDaysInMonth);

            
            Set<String> confirmedAttendanceDatesFormatted = new HashSet<>();
            Document query = new Document("artisanName", loggedInUsername)
                                .append("confirmed", true);
            
            FindIterable<Document> documents = attendanceCollection.find(query);
            for (Document doc : documents) {
                Date attendanceDate = doc.getDate("date");
                if (attendanceDate != null) {
                    Calendar attCal = Calendar.getInstance();
                    attCal.setTime(attendanceDate);
                    // Solo considerar asistencias del mes y año actual
                    if (attCal.get(Calendar.MONTH) == currentMonth && attCal.get(Calendar.YEAR) == currentYear) {
                         confirmedAttendanceDatesFormatted.add(dateFormat.format(attendanceDate));
                    }
                }
            }

          
            for (Date businessDayDate : businessDaysInMonth) {
                String formattedBusinessDay = dateFormat.format(businessDayDate);
                if (!confirmedAttendanceDatesFormatted.contains(formattedBusinessDay)) {
                  
                    absentDates.add(businessDayDate); 
                }
            }
            absentDaysCount = absentDates.size();
            for (int i = 0; i < absentDates.size(); i++) {
                tableModel.addRow(new Object[]{ (i + 1), dateFormat.format(absentDates.get(i)) });
            }
            
            txtAbsentDay.setText(String.valueOf(absentDaysCount));
            double totalPenalty = absentDaysCount * PENALTY_PER_DAY;
            txtPenaltyTotal.setText(String.format("%.2f", totalPenalty));

            if (absentDaysCount == 0) {
                JOptionPane.showMessageDialog(this, "¡Felicidades! No tienes días ausentes este mes.", "Sin Penalidad", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Se calcularon " + absentDaysCount + " días ausentes con una penalidad total de $" + String.format("%.2f", totalPenalty), "Penalidad Calculada", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al calcular la penalidad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtArtesanoPenalty = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAbsentDay = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPenaltyTotal = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnReturnPenalty = new javax.swing.JButton();
        btnPrintPenalty = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(284, 284, 284))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Artesano:");

        txtArtesanoPenalty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtArtesanoPenaltyActionPerformed(evt);
            }
        });

        jLabel2.setText("Total dis ausentes");

        jLabel3.setText("Total Penalizacion:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel1)
                        .addGap(78, 78, 78)
                        .addComponent(txtArtesanoPenalty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAbsentDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPenaltyTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtArtesanoPenalty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAbsentDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPenaltyTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );

        btnReturnPenalty.setText("Regresar");
        btnReturnPenalty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnPenaltyActionPerformed(evt);
            }
        });

        btnPrintPenalty.setText("Imprimir");
        btnPrintPenalty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintPenaltyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(btnReturnPenalty)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrintPenalty)
                .addGap(150, 150, 150))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReturnPenalty)
                    .addComponent(btnPrintPenalty))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtArtesanoPenaltyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtArtesanoPenaltyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtArtesanoPenaltyActionPerformed

    private void btnPrintPenaltyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintPenaltyActionPerformed
          try {
            boolean complete = jTable1.print(PrintMode.FIT_WIDTH, 
                                            new java.text.MessageFormat("Penalidad por Ausencias - Artesano: " + loggedInUsername),
                                            new java.text.MessageFormat("Página -{0}"));
            if (complete) {
                JOptionPane.showMessageDialog(this, "Impresión completada exitosamente.", "Impresión", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "La impresión fue cancelada o no pudo completarse.", "Impresión", JOptionPane.WARNING_MESSAGE);
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(this, "Error al imprimir: " + pe.getMessage(), "Error de Impresión", JOptionPane.ERROR_MESSAGE);
            pe.printStackTrace();
        }
    }//GEN-LAST:event_btnPrintPenaltyActionPerformed

    private void btnReturnPenaltyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnPenaltyActionPerformed
        String artisanUsername = loggedInUsername;
        FrmPrincipalMenu frmPrincipalMenu  =new FrmPrincipalMenu(artisanUsername);
        frmPrincipalMenu.setVisible(true); 
        frmPrincipalMenu.setLocationRelativeTo(null);
         this.dispose(); 
    }//GEN-LAST:event_btnReturnPenaltyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPenalty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPenalty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPenalty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPenalty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPenalty("artesano de prueba").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrintPenalty;
    private javax.swing.JButton btnReturnPenalty;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtAbsentDay;
    private javax.swing.JTextField txtArtesanoPenalty;
    private javax.swing.JTextField txtPenaltyTotal;
    // End of variables declaration//GEN-END:variables
}
