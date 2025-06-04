package Vista;

import Controlador.Controles;
import java.awt.AWTException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainRoblox extends javax.swing.JFrame {

    /**
     * Creates new form MainRoblox
     */
    public MainRoblox() throws AWTException {
        initComponents();
        
        double width = 700;
        double height = 600;
        this.setSize((int) width, (int) height); /////Tamaño de la pantalla
        //Creacion de un nuevo universo///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Universo Universe = new Universo(width, height); 
        this.add(Universe.getCanvas());////Añadir el lienzo a el frame
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        this.setLocationRelativeTo(null); /////Centrar el frame
        //////////////////Añadir controles
        Controles Control = new Controles(Universe,this, Universe.getPersonaje(),Universe.getEstructuras()); ////////Controles que permiten la manipulación del universo

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainRoblox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainRoblox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainRoblox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainRoblox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainRoblox().setVisible(true);
                } catch (AWTException ex) {
                    Logger.getLogger(MainRoblox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
