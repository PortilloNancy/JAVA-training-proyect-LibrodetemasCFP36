package Dao;

import Entidades.Caracter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class CaracterDAO extends Conexion {

    private Connection cn;
    private String SQL;

    public boolean insert(Caracter caracter) {
        boolean insert = false;
        SQL = "INSERT INTO tb_caracter VALUES (NULL,?)";
        cn = conectar();
        try {
            PreparedStatement ps = cn.prepareStatement(SQL);
            ps.setString(1, caracter.getDescripcion());
            if (ps.executeUpdate() > 0) {
                insert = true;
            }
            ps.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Fallo inserción de Caracter..." + e);
        }
        return insert;
    }

    public boolean update(Caracter caracter) {
        boolean update = false;
        SQL = "UPDATE tb_caracter SET descripcion = ? WHERE idCaracter = ?";
        cn = conectar();
        try {
            PreparedStatement ps = cn.prepareStatement(SQL);
            ps.setString(1, caracter.getDescripcion());
            ps.setInt(2, caracter.getIdCaracter());
            if (ps.executeUpdate() > 0) {
                update = true;
            }
            ps.close();
            cn.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar caracter " + ex);
        }
        return update;
    }

    public LinkedList<Caracter> getAllCaracters() {
        LinkedList<Caracter> lista = new LinkedList<>();
        SQL = "SELECT * FROM tb_caracter order by descripcion";
        cn = conectar();
        try {
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(SQL);
            while (rs.next()) {
                Caracter caracter = new Caracter();
                caracter.setIdCaracter(rs.getInt(1));
                caracter.setDescripcion(rs.getString(2));
                lista.add(caracter);
            }
            cn.close();
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error al listar caracteres " + ex);
        }
        return lista;
    }

    public Caracter getCaracterByID(int id) {
        Caracter caracter = new Caracter();
        SQL = "SELECT * FROM tb_caracter WHERE idCaracter = " + id;
        cn = conectar();
        try {
            PreparedStatement ps = cn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                caracter.setIdCaracter(id);
                caracter.setDescripcion(rs.getString(2));
            }
            ps.close();
            rs.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Fallo la busqueda del Caracter" + e);
        }
        return caracter;
    }

}
