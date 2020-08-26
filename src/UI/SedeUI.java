package UI;

import Dao.SedeDAO;
import Entidades.Sede;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class SedeUI extends javax.swing.JDialog {

    private int metodo;
    private final int INSERT = 1;
    private final int UPDATE = 2;

    public SedeUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mostrarSedes();
        inisializartxt();
        Alltxt(false);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void mostrarSedes() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Descripción");
        modelo.addColumn("Dirección");
        modelo.addColumn("Teléfono");
        SedeDAO sededao = new SedeDAO();
        LinkedList<Sede> lista;
        lista = sededao.getAllSedes();
        Object[] data = new Object[4];
        for (Sede sede : lista) {
            data[0] = sede.getIdSede();
            data[1] = sede.getDescripcion();
            data[2] = sede.getDireccion();
            data[3] = sede.getTelefono();
            modelo.addRow(data);
        }
        tblSedes.setModel(modelo);
        TableColumn col = tblSedes.getColumnModel().getColumn(0);
        TableColumn col1 = tblSedes.getColumnModel().getColumn(1);
        TableColumn col2 = tblSedes.getColumnModel().getColumn(2);
        TableColumn col3 = tblSedes.getColumnModel().getColumn(3);

        col.setResizable(false);
        col.setMinWidth(0);
        col.setMaxWidth(0);
    }

    private void Alltxt(boolean b) {
        txtDescripcion.setEnabled(b);
        txtDireccion.setEnabled(b);
        txtTelefono.setEnabled(b);
    }

    private void inisializartxt() {
        TextPrompt txtdescripcion = new TextPrompt("Ingresa el Nombre del Establecimiento...", txtDescripcion);
        txtdescripcion.setForeground(Color.GRAY);
        txtdescripcion.changeAlpha(0.5f);
        txtdescripcion.changeStyle(Font.BOLD + Font.ROMAN_BASELINE + Font.ITALIC);
        TextPrompt txtdireccion = new TextPrompt("Igresa la Dirección del Establecimiento...", txtDireccion);
        txtdireccion.setForeground(Color.GRAY);
        txtdireccion.changeAlpha(0.5f);
        txtdireccion.changeStyle(Font.BOLD + Font.ROMAN_BASELINE + Font.ITALIC);
        TextPrompt txttelefono = new TextPrompt("Igresa el número de Telefono del Establecimiento", txtTelefono);
        txttelefono.setForeground(Color.GRAY);
        txttelefono.changeAlpha(0.5f);
        txttelefono.changeStyle(Font.BOLD + Font.ROMAN_BASELINE + Font.ITALIC);
    }

    private void limpiartxts() {
        txtDescripcion.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }

    private void mostrarSedeSel(int id) {
        SedeDAO sededao = new SedeDAO();
        Sede se = sededao.getSedeByID(id);
        txtDescripcion.setText(String.valueOf(se.getDescripcion()));
        txtDireccion.setText(String.valueOf(se.getDireccion()));
        txtTelefono.setText(String.valueOf(se.getTelefono()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSedes = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        tblSedes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSedes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSedesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSedes);

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

        lblDescripcion.setText("Descripción: ");

        lblDireccion.setText("Dirección: ");

        lblTelefono.setText("Teléfono:");

        btnGuardar.setMnemonic('g');
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setMnemonic('e');
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDescripcion)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTelefono)
                                .addComponent(lblDireccion)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 217, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        limpiartxts();
        Alltxt(true);
        txtDescripcion.requestFocus();
        metodo = INSERT;
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String Descripcion = txtDescripcion.getText();
        String Direccion = txtDireccion.getText();
        String Telefono = txtTelefono.getText();
        Sede sede = new Sede();
        if (Descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe Completar este campo!");
            txtDescripcion.requestFocus();
            return;
        }
        if (Direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe Completar este campo!");
            txtDireccion.requestFocus();
            return;
        }
        if (Telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe Completar este campo!");
            txtTelefono.requestFocus();
        } else {
            btnAgregar.requestFocus();
        }
        sede.setDescripcion(Descripcion);
        sede.setDireccion(Direccion);
        sede.setTelefono(Telefono);
        SedeDAO sd = new SedeDAO();
        if (metodo == UPDATE) {
            int idsede = Integer.parseInt(tblSedes.getValueAt(tblSedes.getSelectedRow(), 0).toString());
            sede.setIdSede(idsede);
            System.out.println(sede.toString());
            sd.update(sede);
            mostrarSedes();
            btnGuardar.setEnabled(false);
            btnAgregar.setEnabled(true);
            limpiartxts();
            Alltxt(false);
        } else if (metodo == INSERT) {
            try {
                if (sd.insert(sede)) {
                    mostrarSedes();
                    btnGuardar.setEnabled(false);
                    btnAgregar.setEnabled(true);
                    limpiartxts();
                    Alltxt(false);
                }
            } catch (Exception e) {
                System.out.println("fallo algo");
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Alltxt(true);
        btnGuardar.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        metodo = UPDATE;
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tblSedesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSedesMouseClicked
        int idcol;
        idcol = tblSedes.getSelectedRow();
        if (idcol < 0) {
            System.out.println("Salio por la tabla");
            return;
        }
        mostrarSedeSel((int) tblSedes.getValueAt(idcol, 0));
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnAgregar.setEnabled(false);
        btnGuardar.setEnabled(false);
        Alltxt(false);
    }//GEN-LAST:event_tblSedesMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnAgregar.setEnabled(true);
    }//GEN-LAST:event_formMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Sede sede = new Sede();
        SedeDAO sd = new SedeDAO();
        sede.setIdSede((int) tblSedes.getValueAt(tblSedes.getSelectedRow(), 0));
        sede.setDescripcion((String) tblSedes.getValueAt(tblSedes.getSelectedRow(), 1));
        sede.setDireccion((String) tblSedes.getValueAt(tblSedes.getSelectedRow(), 2));
        sede.setTelefono((String) tblSedes.getValueAt(tblSedes.getSelectedRow(), 3));
        sede.setBorrada(true);
        sd.update(sede);
        mostrarSedes();
        btnGuardar.setEnabled(false);
        btnAgregar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        limpiartxts();
        Alltxt(false);
    }//GEN-LAST:event_btnEliminarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTable tblSedes;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
