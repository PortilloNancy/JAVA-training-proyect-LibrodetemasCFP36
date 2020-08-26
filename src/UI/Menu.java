package UI;

import Entidades.Usuario;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Menu extends javax.swing.JFrame {
    
    Usuario user;
    
    public Menu(Usuario user) {
        this.user = user;
        initComponents();
        setTitle("¡Bienvenido " + this.user.getTipo().toLowerCase() + " " + this.user.getApellido() + " " + this.user.getNombre() + "!");
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        escalarFondo();
        administrarRoles();
    }
    
    private void administrarRoles() {
        switch (user.getTipo()) {
            case "Auditor":
                sMnuCargarLibro.setVisible(false);
                sMnuComisiones.setVisible(false);
                sMnuModulos.setVisible(false);
                sMnuSede.setVisible(false);
                break;
            case "Director":
                sMnuCargarLibro.setVisible(false);
                break;
            case "Profesor":
                sMnuComisiones.setVisible(false);
                sMnuContens.setVisible(false);
                sMnuModulos.setVisible(false);
                sMnuSede.setVisible(false);
                sMnuUsuarios.setVisible(false);
                sMnuVerExportarLibro.setVisible(false);
                break;
            default:
                JOptionPane.showMessageDialog(null, "No se encontro el perfil del usuario");
                dispose();
                new Login();
                break;
        }
    }
    
    private void escalarFondo() {
        ImageIcon wallpaper = new ImageIcon("src/Imagenes/escuelapolomataderos2-cropped.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(this.getWidth(),
                this.getHeight(), Image.SCALE_DEFAULT));
        jLabel1.setIcon(icono);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnuSistema = new javax.swing.JMenu();
        sMnuUsuarios = new javax.swing.JMenuItem();
        sMnuSede = new javax.swing.JMenuItem();
        sMnuModulos = new javax.swing.JMenuItem();
        sMnuComisiones = new javax.swing.JMenuItem();
        sMnuContrasenia = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        mnuLibro = new javax.swing.JMenu();
        sMnuCargarLibro = new javax.swing.JMenuItem();
        sMnuVerExportarLibro = new javax.swing.JMenuItem();
        MnuExtras = new javax.swing.JMenu();
        sMnuContens = new javax.swing.JMenuItem();
        sMnuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 400));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new java.awt.FlowLayout());
        getContentPane().add(jLabel1);

        mnuSistema.setMnemonic('s');
        mnuSistema.setText("Sistema");
        mnuSistema.setToolTipText("");

        sMnuUsuarios.setMnemonic('u');
        sMnuUsuarios.setText("Usuarios");
        sMnuUsuarios.setToolTipText("");
        sMnuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuUsuariosActionPerformed(evt);
            }
        });
        mnuSistema.add(sMnuUsuarios);

        sMnuSede.setMnemonic('d');
        sMnuSede.setText("Sedes");
        sMnuSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuSedeActionPerformed(evt);
            }
        });
        mnuSistema.add(sMnuSede);

        sMnuModulos.setText("Modulos");
        sMnuModulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuModulosActionPerformed(evt);
            }
        });
        mnuSistema.add(sMnuModulos);

        sMnuComisiones.setText("Comisiones");
        sMnuComisiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuComisionesActionPerformed(evt);
            }
        });
        mnuSistema.add(sMnuComisiones);

        sMnuContrasenia.setText("Cambiar mi contraseña");
        sMnuContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuContraseniaActionPerformed(evt);
            }
        });
        mnuSistema.add(sMnuContrasenia);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        mnuSistema.add(exitMenuItem);

        menuBar.add(mnuSistema);

        mnuLibro.setText("Libro");

        sMnuCargarLibro.setMnemonic('l');
        sMnuCargarLibro.setText("Ver / Cargar");
        sMnuCargarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuCargarLibroActionPerformed(evt);
            }
        });
        mnuLibro.add(sMnuCargarLibro);

        sMnuVerExportarLibro.setText("Ver / Exportar");
        sMnuVerExportarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuVerExportarLibroActionPerformed(evt);
            }
        });
        mnuLibro.add(sMnuVerExportarLibro);

        menuBar.add(mnuLibro);

        MnuExtras.setMnemonic('e');
        MnuExtras.setText("Extras");
        MnuExtras.setToolTipText("");

        sMnuContens.setMnemonic('c');
        sMnuContens.setText("Contents");
        MnuExtras.add(sMnuContens);

        sMnuAbout.setMnemonic('a');
        sMnuAbout.setText("About");
        sMnuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuAboutActionPerformed(evt);
            }
        });
        MnuExtras.add(sMnuAbout);

        menuBar.add(MnuExtras);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void sMnuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuUsuariosActionPerformed
        UsuarioUI usu = new UsuarioUI(this, true, user);
        usu.setSize(this.getWidth(), this.getHeight() - 50);
        usu.setLocation(-5, 45);
        usu.setVisible(true);

    }//GEN-LAST:event_sMnuUsuariosActionPerformed

    private void sMnuSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuSedeActionPerformed
        SedeUI su = new SedeUI(this, true);
        su.setSize(this.getWidth(), this.getHeight() - 50);
        su.setLocation(-5, 45);
        su.setVisible(true);

    }//GEN-LAST:event_sMnuSedeActionPerformed

    private void sMnuCargarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuCargarLibroActionPerformed
        LibroUI li = new LibroUI(this, true, user);
        li.setSize(this.getWidth(), this.getHeight() - 45);
        li.setLocation(-5, 45);
        li.setVisible(true);

    }//GEN-LAST:event_sMnuCargarLibroActionPerformed

    private void sMnuComisionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuComisionesActionPerformed
        ComisionUI comi = new ComisionUI(this, true);
        comi.setSize(this.getWidth(), this.getHeight() - 50);
        comi.setLocation(-5, 45);
        comi.setVisible(true);

    }//GEN-LAST:event_sMnuComisionesActionPerformed

    private void sMnuModulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuModulosActionPerformed
        ModuloUI am = new ModuloUI(this, true);
        am.setSize(this.getWidth(), this.getHeight() - 50);
        am.setLocation(-5, 45);
        am.setVisible(true);

    }//GEN-LAST:event_sMnuModulosActionPerformed

    private void sMnuContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuContraseniaActionPerformed
        ContraseniaUI cn = new ContraseniaUI(this, true, user);
        cn.setSize(this.getWidth(), this.getHeight() - 50);
        cn.setLocation(-5, 45);
        cn.setVisible(true);
    }//GEN-LAST:event_sMnuContraseniaActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        escalarFondo();
    }//GEN-LAST:event_formComponentResized

    private void sMnuVerExportarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuVerExportarLibroActionPerformed
        LibroExUI exportaLibro = new LibroExUI(this, true);
        exportaLibro.setSize(this.getWidth(), this.getHeight() - 45);
        exportaLibro.setLocation(-5, 45);
        exportaLibro.setVisible(true);
    }//GEN-LAST:event_sMnuVerExportarLibroActionPerformed

    private void sMnuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuAboutActionPerformed
              AcercaDeUI acercaDe = new AcercaDeUI(this, true);
        acercaDe.setVisible(true);
    }//GEN-LAST:event_sMnuAboutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MnuExtras;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnuLibro;
    private javax.swing.JMenu mnuSistema;
    private javax.swing.JMenuItem sMnuAbout;
    private javax.swing.JMenuItem sMnuCargarLibro;
    private javax.swing.JMenuItem sMnuComisiones;
    private javax.swing.JMenuItem sMnuContens;
    private javax.swing.JMenuItem sMnuContrasenia;
    private javax.swing.JMenuItem sMnuModulos;
    private javax.swing.JMenuItem sMnuSede;
    private javax.swing.JMenuItem sMnuUsuarios;
    private javax.swing.JMenuItem sMnuVerExportarLibro;
    // End of variables declaration//GEN-END:variables

}
