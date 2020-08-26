package UI;

import Dao.UsuarioDAO;
import Entidades.Usuario;
import javax.swing.JOptionPane;

public class ContraseniaUI extends javax.swing.JDialog{

    Usuario user;
    

    public ContraseniaUI(java.awt.Frame parent, boolean modal, Usuario user) {
        super(parent, modal);
        this.user = user;
        initComponents();
        setTitle("Cambiar contraseña");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblApellido1 = new javax.swing.JLabel();
        txt_nueva_sena = new javax.swing.JTextField();
        txt_sena_atual = new javax.swing.JTextField();
        lblNombre1 = new javax.swing.JLabel();
        lblTelefono1 = new javax.swing.JLabel();
        txt_verif_nueva = new javax.swing.JTextField();
        btnCambiar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblApellido1.setText("nueva contraseña");

        lblNombre1.setText("contraseña actual");

        lblTelefono1.setText("verificar nueva contraseña");

        btnCambiar.setMnemonic('m');
        btnCambiar.setText("Cambiar Contraseña");
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });

        btn_salir.setMnemonic('s');
        btn_salir.setText("Salir");
        btn_salir.setToolTipText("");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTelefono1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblApellido1)
                        .addComponent(lblNombre1)))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nueva_sena, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_verif_nueva, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sena_atual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCambiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(408, 408, 408))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_sena_atual, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre1))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCambiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nueva_sena, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblApellido1))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelefono1)
                            .addComponent(txt_verif_nueva, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
 
          if (user.getPassword().equals(txt_sena_atual.getText())) {
              
              if(!txt_nueva_sena.getText().equals(txt_verif_nueva.getText())) {
                  JOptionPane.showMessageDialog(null, "Las constraseñas tiene que ser iguales");
                  txt_nueva_sena.setText("");
                  txt_verif_nueva.setText("");
              } else {
                  UsuarioDAO Dao = new UsuarioDAO();
                  user.setPassword(txt_nueva_sena.getText());
                  if(Dao.updateUsuario(user)){
                      
    
                      JOptionPane.showMessageDialog(null, "Atualizado con éxito");
                      dispose();
                  }else{
                      
                      JOptionPane.showMessageDialog(null, "Fallo en la actualización de la contraseña");
                  }
              }  
          }
          
          System.out.println("anda a logica");
    }//GEN-LAST:event_btnCambiarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
       dispose();
    }//GEN-LAST:event_btn_salirActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel lblApellido1;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JTextField txt_nueva_sena;
    private javax.swing.JTextField txt_sena_atual;
    private javax.swing.JTextField txt_verif_nueva;
    // End of variables declaration//GEN-END:variables
}