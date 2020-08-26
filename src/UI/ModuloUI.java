package UI;

import Dao.ModuloDAO;
import Entidades.Modulo;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ModuloUI extends javax.swing.JDialog {

    private int metodo;
    private final int INSERT = 1;
    private final int UPDATE = 2;

    public ModuloUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mostrarModulos();
        inisializarTxt();
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        txtDescripcion.setEnabled(false);
    }

    private void mostrarModuloSel() {
        if (tblModulos.getSelectedRow() < 0) {
            System.out.println("Salio por la tabla");
            return;
        }
        ModuloDAO moduDAO = new ModuloDAO();
        Modulo se = moduDAO.getModuloByID((int) tblModulos.getValueAt(tblModulos.getSelectedRow(), 0));
        txtDescripcion.setText(se.getDescripcion());
    }

    private void mostrarModulos() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Descripción");
        ModuloDAO modulodao = new ModuloDAO();
        LinkedList<Modulo> lista;
        lista = modulodao.getAllModulos("");
        Object[] data = new Object[2];
        for (Modulo modulo : lista) {
            data[0] = modulo.getIdModulo();
            data[1] = modulo.getDescripcion();
            modelo.addRow(data);
        }
        tblModulos.setModel(modelo);
        TableColumn c0 = tblModulos.getColumnModel().getColumn(0);
        TableColumn c1 = tblModulos.getColumnModel().getColumn(1);
        c0.setResizable(false);
        c0.setMinWidth(0);
        c0.setMaxWidth(0);
    }

    private void inisializarTxt() {
        TextPrompt txtdescripcion = new TextPrompt("Ingresa el Nombre del Módulo...", txtDescripcion);
        txtdescripcion.setForeground(Color.GRAY);
        txtdescripcion.changeAlpha(0.5f);
        txtdescripcion.changeStyle(Font.BOLD + Font.ROMAN_BASELINE + Font.ITALIC);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblModulos = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtDescripcion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        tblModulos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblModulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblModulosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblModulos);

        btnAgregar.setMnemonic('a');
        btnAgregar.setText("Agregar");
        btnAgregar.setToolTipText("");
        btnAgregar.setPreferredSize(new java.awt.Dimension(77, 28));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnGuardar.setMnemonic('g');
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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

        btnEliminar.setMnemonic('e');
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(355, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        btnAgregar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        txtDescripcion.setText("");
        txtDescripcion.setEnabled(true);
        txtDescripcion.requestFocus();
        metodo = INSERT;
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String Descripcion = txtDescripcion.getText();
        Modulo modulo = new Modulo();
        if (Descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe Completar este campo!");
            txtDescripcion.requestFocus();
            return;
        }
        modulo.setDescripcion(Descripcion);
        ModuloDAO md = new ModuloDAO();
        if (metodo == UPDATE) {
            modulo.setIdModulo((int) tblModulos.getValueAt(tblModulos.getSelectedRow(), 0));
            if (!md.update(modulo)) {
                JOptionPane.showMessageDialog(this, "fallo al actualizar modulo");
                return;
            }
        } else if (metodo == INSERT) {
            if (!md.insert(modulo)) {
                JOptionPane.showMessageDialog(this, "Fallo al agregar modulo");
                return;
            }
        }
        mostrarModulos();
        btnGuardar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnAgregar.setEnabled(true);
        txtDescripcion.setEnabled(false);
        txtDescripcion.setText("");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        btnGuardar.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        txtDescripcion.setEnabled(true);
        metodo = UPDATE;
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Modulo modulo = new Modulo();
        ModuloDAO md = new ModuloDAO();
        modulo.setIdModulo((int) tblModulos.getValueAt(tblModulos.getSelectedRow(), 0));
        modulo.setDescripcion((String) tblModulos.getValueAt(tblModulos.getSelectedRow(), 1));
        modulo.setBorrado(true);
        md.update(modulo);
        mostrarModulos();
        btnGuardar.setEnabled(false);
        btnAgregar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        txtDescripcion.setText("");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblModulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblModulosMouseClicked
        btnGuardar.setEnabled(false);
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        mostrarModuloSel();
    }//GEN-LAST:event_tblModulosMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnAgregar.setEnabled(true);
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblModulos;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
