package UI;

import Dao.ComisionDAO;
import Dao.SedeDAO;
import Entidades.Comision;
import Entidades.Modulo;
import Entidades.Sede;
import Entidades.Usuario;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ComisionUI extends javax.swing.JDialog {

    private static Modulo modulo;
    private static Usuario profesor;
    private int metodo;
    private final int INSERT = 1;
    private final int UPDATE = 2;
    private int idComiSel;

    public ComisionUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mostrarComisiones();
        AutoCompleteDecorator.decorate(cmbSedes);
        cargarSedes();
        ADcomponentes(false);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(false);
        txtModulo.setEnabled(false);
        txtProfesor.setEnabled(false);
    }

    public static Modulo getModulo() {
        return modulo;
    }

    public static void setModulo(Modulo modulo) {
        ComisionUI.modulo = modulo;
    }

    public static Usuario getProfesor() {
        return profesor;
    }

    public static void setProfesor(Usuario profesor) {
        ComisionUI.profesor = profesor;
    }

    private void ADcomponentes(boolean b) {
        txtDescripcion.setEditable(b);
        txtFechaFin.setEnabled(b);
        txtFechaInicio.setEnabled(b);
        txtModulo.setEditable(b);
        txtProfesor.setEditable(b);
        cmbSedes.setEnabled(b);
        btnCargaProfesor.setEnabled(b);
        btnCargarModulo.setEnabled(b);
    }

    private void clean() {
        idComiSel = 0;
        txtDescripcion.setText(null);
        txtFechaFin.setDate(null);
        txtFechaInicio.setDate(null);
        txtModulo.setText(null);
        txtProfesor.setText(null);
        cmbSedes.setSelectedIndex(0);
        modulo = null;
        profesor = null;
    }

    private void mostrarComisiones() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Descripción");
        modelo.addColumn("Fecha de Inicio");
        modelo.addColumn("Fecha de Finalización");
        modelo.addColumn("Profesor");
        modelo.addColumn("Modulo");
        modelo.addColumn("Sede");

        ComisionDAO comisiondao = new ComisionDAO();
        LinkedList<Comision> lista;
        lista = comisiondao.getAllComisiones();

        Object[] data = new Object[7];
        for (Comision comi : lista) {
            data[0] = comi.getIdComision();
            data[1] = comi.getDescripcion();
            data[2] = comi.getFecha_ini();
            data[3] = comi.getFecha_fin();
            data[4] = comi.getUsuario().getNombre() + " " + comi.getUsuario().getApellido();
            data[5] = comi.getModulo().getDescripcion();
            data[6] = comi.getSede().getDescripcion();

            modelo.addRow(data);
        }
        tblComision.setModel(modelo);
        TableColumn col0 = tblComision.getColumnModel().getColumn(0);
        TableColumn col1 = tblComision.getColumnModel().getColumn(1);
        TableColumn col2 = tblComision.getColumnModel().getColumn(2);
        TableColumn col3 = tblComision.getColumnModel().getColumn(3);
        TableColumn col4 = tblComision.getColumnModel().getColumn(4);
        TableColumn col5 = tblComision.getColumnModel().getColumn(5);
        TableColumn col6 = tblComision.getColumnModel().getColumn(6);

        col0.setResizable(false); // para Ocultar una columna, en este caso, la de id.
        col0.setMinWidth(0);
        col0.setMaxWidth(0);
    }

    private void cargarSedes() {
        SedeDAO sd = new SedeDAO();
        LinkedList<Sede> listasedes = sd.getAllSedes();
        cmbSedes.addItem("Seleccione sede");
        for (Sede sede : listasedes) {
            cmbSedes.addItem(sede);
        }
    }

    private boolean validado() {
        if (txtDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe agregar una descripcion");
            txtDescripcion.requestFocus();
            return false;
        }
        if (modulo == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un modulo");
            btnCargarModulo.requestFocus();
            return false;
        }
        if (cmbSedes.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una sede");
            cmbSedes.requestFocus();;
            return false;
        }
        if (profesor == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un profesor");
            btnCargaProfesor.requestFocus();
            return false;
        }
        return true;
    }

    private Comision cargarComision() {
        Comision comision = new Comision();
        comision.setIdComision(idComiSel);
        comision.setFecha_ini(txtFechaInicio.getCalendar().getTime());
        comision.setFecha_fin(txtFechaFin.getCalendar().getTime());
        comision.setDescripcion(txtDescripcion.getText());
        comision.setModulo(modulo);
        comision.setSede((Sede) cmbSedes.getSelectedItem());
        comision.setUsuario(profesor);
        return comision;
    }

    private void mostrarComisionSelectcionada(int idc) {
        ComisionDAO comisiondao = new ComisionDAO();
        Comision comision = comisiondao.getComisionxID(idc);
        ADcomponentes(true);
        txtDescripcion.setText(comision.getDescripcion());
        txtFechaInicio.setDate(comision.getFecha_ini());
        txtFechaFin.setDate(comision.getFecha_fin());
        modulo = comision.getModulo();
        txtModulo.setText(modulo.getDescripcion());
        profesor = comision.getUsuario();
        txtProfesor.setText(profesor.getApellido() + ", " + profesor.getNombre());
        cmbSedes.setSelectedItem(comision.getSede());
        ADcomponentes(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblComision = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblDescripcion = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        txtFechaInicio = new com.toedter.calendar.JDateChooser();
        lblFechaFin = new javax.swing.JLabel();
        txtFechaFin = new com.toedter.calendar.JDateChooser();
        lblModulo = new javax.swing.JLabel();
        txtModulo = new javax.swing.JTextField();
        btnCargarModulo = new javax.swing.JButton();
        lblSede = new javax.swing.JLabel();
        cmbSedes = new javax.swing.JComboBox<>();
        lblProfesor = new javax.swing.JLabel();
        txtProfesor = new javax.swing.JTextField();
        btnCargaProfesor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        tblComision.setModel(new javax.swing.table.DefaultTableModel(
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
        tblComision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblComisionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblComision);

        btnAgregar.setMnemonic('a');
        btnAgregar.setText("Agregar");
        btnAgregar.setPreferredSize(new java.awt.Dimension(77, 28));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnGuardar.setMnemonic('g');
        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(77, 28));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setMnemonic('m');
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSalir.setMnemonic('s');
        btnSalir.setText("Salir");
        btnSalir.setPreferredSize(new java.awt.Dimension(77, 28));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblDescripcion.setText("Descripcion:");

        lblFechaInicio.setText("Fecha de Inicio:");

        lblFechaFin.setText("Fecha de finalización: ");

        lblModulo.setText("Módulo:");

        btnCargarModulo.setText("Seleccionar Módulo");
        btnCargarModulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarModuloActionPerformed(evt);
            }
        });

        lblSede.setText("Sede:");

        lblProfesor.setText("Profesor:");

        btnCargaProfesor.setText("Seleccionar Profesor");
        btnCargaProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargaProfesorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProfesor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSede, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModulo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaInicio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDescripcion, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(62, 62, 62)
                            .addComponent(lblFechaFin)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbSedes, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCargarModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCargaProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 1273, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAgregar, btnGuardar, btnModificar, btnSalir});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDescripcion)
                    .addComponent(lblDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtModulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModulo)
                    .addComponent(btnCargarModulo))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbSedes)
                    .addComponent(lblSede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCargaProfesor)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblProfesor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtProfesor, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAgregar, btnGuardar, btnModificar, btnSalir});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!validado()) {
            return;
        }
        ComisionDAO dao = new ComisionDAO();
        Comision comision = cargarComision();
        if (metodo == INSERT) {
            if (!dao.insert(comision)) {
                JOptionPane.showMessageDialog(null, "Fallo al agregar comision");
            }
        }
        if (metodo == UPDATE) {
            if (!dao.update(comision)) {
                JOptionPane.showMessageDialog(null, "Fallo al actualizar comision");
            }
        }
        clean();
        ADcomponentes(false);
        mostrarComisiones();
        btnAgregar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCargarModuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarModuloActionPerformed
        SelectModu selmod = new SelectModu(this, true);
        selmod.setLocationRelativeTo(null);
        selmod.setVisible(true);
    }//GEN-LAST:event_btnCargarModuloActionPerformed

    private void btnCargaProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargaProfesorActionPerformed
        SelectProfe selpro = new SelectProfe(this, true);
        selpro.setLocationRelativeTo(null);
        selpro.setVisible(true);
    }//GEN-LAST:event_btnCargaProfesorActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        ADcomponentes(true);
        clean();
        metodo = INSERT;
        txtDescripcion.requestFocus();
        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tblComisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComisionMouseClicked
        int idcol;
        idcol = tblComision.getSelectedRow();
        if (idcol < 0) {
            System.out.println("Salio por error en la tabla");
            return;
        } else {
            idComiSel = (int) tblComision.getValueAt(idcol, 0);
        }
        mostrarComisionSelectcionada(idComiSel);
        btnModificar.setEnabled(true);
        btnAgregar.setEnabled(false);
    }//GEN-LAST:event_tblComisionMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ADcomponentes(true);
        txtDescripcion.requestFocus();
        metodo = UPDATE;
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (!btnGuardar.isEnabled()) {
            btnAgregar.setEnabled(true);
        }
    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCargaProfesor;
    private javax.swing.JButton btnCargarModulo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<Object> cmbSedes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblModulo;
    private javax.swing.JLabel lblProfesor;
    private javax.swing.JLabel lblSede;
    private javax.swing.JTable tblComision;
    private javax.swing.JTextField txtDescripcion;
    private com.toedter.calendar.JDateChooser txtFechaFin;
    private com.toedter.calendar.JDateChooser txtFechaInicio;
    public static javax.swing.JTextField txtModulo;
    public static javax.swing.JTextField txtProfesor;
    // End of variables declaration//GEN-END:variables

}
