package Dao;

import Entidades.Sede;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class SedeDAO extends Conexion {

    private Connection cn;
    private String SQL;

    public Sede getSedeByID(int id) {
        Sede sede = new Sede();
        SQL = "SELECT * FROM tb_sede WHERE idSede = ?";
        cn = conectar();
        try {
            PreparedStatement st = cn.prepareStatement(SQL);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                sede.setIdSede(rs.getInt(1));
                sede.setDescripcion(rs.getString(2));
                sede.setDireccion(rs.getString(3));
                sede.setTelefono(rs.getString(4));
            }
            cn.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Fallo la lectura de sede " + e);
        }
        return sede;
    }

    public LinkedList<Sede> getAllSedes() {
        LinkedList<Sede> list = new LinkedList<>();
        Sede sede = null;
        SQL = "SELECT * FROM tb_sede WHERE borrada = FALSE order by descripcion";
        cn = conectar();
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {
                sede = new Sede();
                sede.setIdSede(rs.getInt(1));
                sede.setDescripcion(rs.getString(2));
                sede.setDireccion(rs.getString(3));
                sede.setTelefono(rs.getString(4));
                list.add(sede);
            }
            cn.close();
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("error al generar lista " + ex);
        }
        return list;
    }

    public boolean insert(Sede sede) {
        boolean insert = false;
        SQL = "INSERT INTO tb_sede VALUES(NULL, ?, ?, ?, ?)";
        cn = conectar();
        try {
            PreparedStatement pst = cn.prepareStatement(SQL);
            pst.setString(1, sede.getDescripcion());
            pst.setString(2, sede.getDireccion());
            pst.setString(3, sede.getTelefono());
            pst.setBoolean(4, sede.isBorrada());
            if (pst.executeUpdate() > 0) {
                insert = true;
            }
            pst.close();
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return insert;
    }

    public boolean update(Sede sede) {
        boolean update = false;
        SQL = "UPDATE tb_sede SET descripcion = ?, direccion = ?, telefono = ?, "
                + "borrada = ? WHERE idSede = ?";
        cn = conectar();
        try {
            PreparedStatement ps = cn.prepareStatement(SQL);
            ps.setString(1, sede.getDescripcion());
            ps.setString(2, sede.getDireccion());
            ps.setString(3, sede.getTelefono());
            ps.setBoolean(4, sede.isBorrada());
            ps.setInt(5, sede.getIdSede());
            if (ps.executeUpdate() > 0) {
                update = true;
            }
            ps.close();
            cn.close();
            return update;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return update;
    }
    
     public LinkedList<Sede> getAllSedeByProfe(int id_usuario) {//
        LinkedList<Sede> list = new LinkedList<>();
        Sede sede = null;
        SQL = "SELECT DISTINCT se.* "
                + "FROM tb_sede se "
                + "JOIN tb_comision co ON se.idSede = co.id_sede "
                + "JOIN tb_usuario us ON co.id_usuario=  " + id_usuario;

        cn = conectar();
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {
                sede = new Sede();
                sede.setIdSede(rs.getInt(1));
                sede.setDescripcion(rs.getString(2));
                sede.setDireccion(rs.getString(3));
                sede.setTelefono(rs.getString(4));
                list.add(sede);
            }
            cn.close();
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("error al generar lista " + ex);
        }
        return list;
    }
}
