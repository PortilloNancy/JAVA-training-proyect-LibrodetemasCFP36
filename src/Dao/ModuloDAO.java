package Dao;

import Entidades.Modulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class ModuloDAO extends Conexion {

    private Connection cn;
    private String SQL;

    public boolean insert(Modulo modulo) {
        boolean insert = false;
        SQL = "INSERT INTO tb_modulo VALUES(NULL, ?, FALSE)";
        cn = conectar();
        try {
            PreparedStatement ps = cn.prepareStatement(SQL);
            ps.setString(1, modulo.getDescripcion());
            if (ps.executeUpdate() > 0) {
                insert = true;
            }
            ps.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Fallo inserción de módulo.");
        }
        return insert;
    }

    public boolean update(Modulo modulo) {
        boolean update = false;
        SQL = "UPDATE tb_modulo SET descripcion = ?, borrado = ? WHERE idModulo = ?";
        cn = conectar();
        try {
            PreparedStatement ps = cn.prepareStatement(SQL);
            ps.setString(1, modulo.getDescripcion());
            ps.setBoolean(2, modulo.isBorrado());
            ps.setInt(3, modulo.getIdModulo());
            if (ps.executeUpdate() > 0) {
                update = true;
            }
            ps.close();
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Fallo la actualizar " + ex);
        }
        return update;
    }

    public LinkedList<Modulo> getAllModulos(String busqueda) {
        LinkedList<Modulo> lista = new LinkedList<>();
        Modulo modulo;
        SQL = "SELECT * FROM tb_modulo WHERE descripcion LIKE '%" + busqueda
                + "%' AND borrado = FALSE ORDER BY descripcion";
        cn = conectar();
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {
                modulo = new Modulo();
                modulo.setIdModulo(rs.getInt(1));
                modulo.setDescripcion(rs.getString(2));
                modulo.setBorrado(rs.getBoolean(3));
                lista.add(modulo);
            }
            stm.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Fallo al listar modulo " + ex);
        }
        return lista;
    }

    public Modulo getModuloByID(int id) {
        SQL = "SELECT * FROM tb_modulo WHERE idModulo = ?";
        Modulo modulo = new Modulo();
        cn = conectar();
        try {
            PreparedStatement ps;
            ps = cn.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                modulo.setIdModulo(rs.getInt(1));
                modulo.setDescripcion(rs.getString(2));
                modulo.setBorrado(rs.getBoolean(3));
            }
        } catch (SQLException e) {
            System.out.println("Fallo la lectura del Modulo");
        }
        return modulo;
    }

}
