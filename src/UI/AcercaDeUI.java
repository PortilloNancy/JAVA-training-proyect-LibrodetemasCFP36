package UI;

import java.awt.Dimension;
import javax.swing.JDialog;

public class AcercaDeUI extends JDialog {

    AcercaDeUI(Menu aThis, boolean b) {
        super(aThis, b);
        initComponents();
        setLocationRelativeTo(null);
        this.setSize(new Dimension(700, 500));
        this.setMinimumSize(new Dimension(700, 500));
        textoPrinc.setFocusable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoPrinc = new javax.swing.JEditorPane();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LIBRO DE TEMAS2.png"))); // NOI18N
        jLabel2.setText("   LIBRO DE TEMAS ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 520, 120));

        jLabel3.setText("Version 11.2019. Se reservan los derechos a CFP36  Mataderos.");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 620, 20));

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 400, -1, -1));

        textoPrinc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        textoPrinc.setText("    \n      Este progama fue diseñado y desarrollado por estudiantes y\n      profesores del CFP36 Mataderos\n\n      Direccion: Diego, Sergio Rodriguez y Reinaldo \n     \n      Proyect Manager: Nelson Esteban Figueroa, \n\n      Developers:Sergio D' Andrea, Hugo Zarate, Adriano Da Silva Barbosa,\n\t Josè Luis y Portillo Nancy\n\n      Todos los estudiantes del CFP36 sede Mataderos agradecemos\n      la dedicacion,  el apoyo y la motivacion recibida por los profesores\n      que durante casi un año nos acompañaron a transitar el comienzo \n      de esta carrera!\n\n\t\tGRACIAS!!!! ");
        textoPrinc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(textoPrinc);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 450, 240));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cfp36Imagen.jpg"))); // NOI18N
        jLabelFondo.setMaximumSize(new java.awt.Dimension(700, 500));
        jLabelFondo.setMinimumSize(new java.awt.Dimension(700, 500));
        jLabelFondo.setName(""); // NOI18N
        jLabelFondo.setOpaque(true);
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JEditorPane textoPrinc;
    // End of variables declaration//GEN-END:variables
}
