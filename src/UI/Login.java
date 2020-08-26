package UI;

import Dao.UsuarioDAO;
import Entidades.Usuario;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    Usuario user;

    public Login() {
        initComponents();
        inisializartxt();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void atajo() {
        user = new Usuario();
        //user.setTipo("Auditor");
        //user.setTipo("Profesor");
        user.setTipo("Director");
        Menu menu = new Menu(user);
        dispose();
    }

    private void inisializartxt() {
        TextPrompt txtusuario = new TextPrompt("Ingrese el DNI sin puntos", txtUsuario);
        txtusuario.setForeground(Color.GRAY);
        txtusuario.changeAlpha(0.5f);
        txtusuario.changeStyle(Font.BOLD + Font.ROMAN_BASELINE + Font.ITALIC);
        TextPrompt txtcontrasenia = new TextPrompt("Ingrese la contraseña", txtContrasenia);
        txtcontrasenia.setForeground(Color.GRAY);
        txtcontrasenia.changeAlpha(0.5f);
        txtcontrasenia.changeStyle(Font.BOLD + Font.ROMAN_BASELINE + Font.ITALIC);
    }

    private void procederLogin() {
        UsuarioDAO uDAO = new UsuarioDAO();
        user = uDAO.getUsuarioByDNI(Integer.parseInt(txtUsuario.getText().trim()));
        if (!(user.getIdUsuario() == 0)) {
            if (user.isDeshabilitado()) {
                JOptionPane.showMessageDialog(null, "     se encuentra deshabilitiado "
                        + "\n              para usar el sistema"
                        + "\ncomuniquese con el administrador   ");
            } else {
                if (user.getPassword().equals(txtContrasenia.getText().trim())) {
                    dispose();
                    new Menu(user);
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro el usuario");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtUsuario = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        txtContrasenia = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(238, 112, 82));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(238, 112, 82));
        jLabel2.setText("Password:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, -1, -1));

        jLabel3.setBackground(new java.awt.Color(238, 112, 82));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(238, 112, 82));
        jLabel3.setText("Usuario:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_User_96px_2.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 110, 130));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, 210, 10));

        txtUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtUsuario.setBorder(null);
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 160, 40));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 210, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_customer_32px_1.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 50, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Key_32px.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 40, 40));

        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_OFF.png"))); // NOI18N
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnIngresar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_ON.png"))); // NOI18N
        btnIngresar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Enter_ON.png"))); // NOI18N
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 110, 40));

        txtContrasenia.setBorder(null);
        txtContrasenia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseniaKeyPressed(evt);
            }
        });
        jPanel1.add(txtContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 160, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        switch (evt.getKeyCode()) {
            case java.awt.event.KeyEvent.VK_ENTER:
                txtContrasenia.requestFocus();
                txtContrasenia.selectAll();
                break;
            case java.awt.event.KeyEvent.VK_TAB:
                txtContrasenia.selectAll();
                break;
            case java.awt.event.KeyEvent.VK_F5:
                atajo();
                break;

            default:
                break;
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtContraseniaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseniaKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            btnIngresar.doClick();
        }
    }//GEN-LAST:event_txtContraseniaKeyPressed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        procederLogin();
    }//GEN-LAST:event_btnIngresarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JPasswordField txtContrasenia;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
