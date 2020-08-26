package UI;

import Dao.CaracterDAO;
import Entidades.Caracter;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class CaracterUI extends javax.swing.JDialog {

    public CaracterUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Caracter");
        mostrarCaracters();
        limpiareInisializartxt();
        AItxt(false);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(false);

    }

    private void mostrarCaracters() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Descripción");
        CaracterDAO caracterdao = new CaracterDAO();
        LinkedList<Caracter> lista;
        lista = caracterdao.getAllCaracters();
        Object[] data = new Object[2];
        for (Caracter caracter : lista) {
            data[0] = caracter.getIdCaracter();
            data[1] = caracter.getDescripcion();
            modelo.addRow(data);
        }
        tblCaracter.setModel(modelo);
        TableColumn col = tblCaracter.getColumnModel().getColumn(0);
        TableColumn col1 = tblCaracter.getColumnModel().getColumn(1);
        col.setResizable(false); // para Ocultar una columna, en este caso, la de id.
        col.setMinWidth(0);
        col.setMaxWidth(0);
    }

    private void AItxt(boolean b) {
        txtDescripcion.setEnabled(b);
    }

    private void limpiareInisializartxt() {
        TextPrompt txtdescripcion = new TextPrompt("Ingresa el Nombre del Caracter...", txtDescripcion);
        txtdescripcion.setForeground(Color.GRAY);
        txtdescripcion.changeAlpha(0.5f);
        txtdescripcion.changeStyle(Font.BOLD + Font.ROMAN_BASELINE + Font.ITALIC);
    }

    private void limpiartxts() {
        txtDescripcion.setText("");
    }

    private void mostrarCaracterSel(int id) {
        CaracterDAO caracterdao = new CaracterDAO();
        Caracter ca = caracterdao.getCaracterByID(id);
        txtDescripcion.setText(String.valueOf(ca.getDescripcion()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCaracter = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblCaracter.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCaracter.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblCaracterFocusLost(evt);
            }
        });
        tblCaracter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCaracterMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCaracter);

        btnAgregar.setMnemonic('a');
        btnAgregar.setText("Agregar");
        btnAgregar.setToolTipText("");
        btnAgregar.setPreferredSize(new java.awt.Dimension(77, 28));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setMnemonic('m');
        btnModificar.setText("Modificar");
        btnModificar.setToolTipText("");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSalir.setMnemonic('s');
        btnSalir.setText("Salir");
        btnSalir.setToolTipText("");
        btnSalir.setPreferredSize(new java.awt.Dimension(77, 28));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel1.setText("Descripción: ");

        btnGuardar.setMnemonic('g');
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        btnAgregar.setEnabled(false);
        btnGuardar.setEnabled(true);
        limpiareInisializartxt();
        AItxt(true);
        txtDescripcion.requestFocus();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String Descripcion = txtDescripcion.getText();
        Caracter caracter = new Caracter();
        if (Descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe Completar este campo!");
            txtDescripcion.requestFocus();
            return;
        } else {
            // btnAgregar.setText("Guardar");
            btnAgregar.requestFocus();
        }
        caracter.setDescripcion(Descripcion);
        CaracterDAO sdc = new CaracterDAO();
        if (btnModificar.isEnabled()) {
            int idcaracter = Integer.parseInt(tblCaracter.getValueAt(tblCaracter.getSelectedRow(), 0).toString());
            caracter.setIdCaracter(idcaracter);
            sdc.update(caracter);
            mostrarCaracters();
            btnGuardar.setEnabled(false);
            btnAgregar.setEnabled(true);
            limpiartxts();
            AItxt(false);
        } else {
            try {
                if (sdc.insert(caracter)) {
                    mostrarCaracters();
                    btnGuardar.setEnabled(false);
                    btnAgregar.setEnabled(true);
                    limpiartxts();
                    AItxt(false);
                }
            } catch (Exception e) {
                System.out.println("fallo algo");
            }

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        AItxt(true);
        //  btnModificar.setEnabled(false);
        btnGuardar.setEnabled(true);

    }//GEN-LAST:event_btnModificarActionPerformed

    private void tblCaracterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCaracterMouseClicked
        int idcol, idcaracter;

        idcol = tblCaracter.getSelectedRow();
        if (idcol < 0) {
            System.out.println("Salio por la tabla");
            return;
        } else {
            idcaracter = Integer.parseInt(tblCaracter.getValueAt(idcol, 0).toString());
        }
        mostrarCaracterSel(idcaracter);
        btnModificar.setEnabled(true);
        btnAgregar.setEnabled(false);
    }//GEN-LAST:event_tblCaracterMouseClicked

    private void tblCaracterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblCaracterFocusLost
        //btnModificar.setEnabled(false);
        // btnAgregar.setEnabled(true);

    }//GEN-LAST:event_tblCaracterFocusLost

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCaracter;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
