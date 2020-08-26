package Dao;

import Entidades.Provincia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class ProvinciaDAO extends Conexion {

    private Connection cn;
    private String SQL;

    public LinkedList<Provincia> getProvincias() {
        LinkedList<Provincia> lista = new LinkedList<>();
        Provincia provincia = null;
        SQL = "SELECT * FROM tb_provincia ORDER BY nombre";
        cn = conectar();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                provincia = new Provincia();
                provincia.setIdProvincia(rs.getInt(1));
                provincia.setNombre(rs.getString(2));
                lista.add(provincia);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return lista;
    }

    public Provincia getProvinciaById(int id_provincia) {
        Provincia provicia = new Provincia();
        SQL = "SELECT * FROM tb_provincia WHERE idProvincia = ?";
        cn = conectar();
        try {
            PreparedStatement st = cn.prepareStatement(SQL);
            st.setInt(1, id_provincia);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                provicia.setIdProvincia(rs.getInt(1));
                provicia.setNombre(rs.getNString(2));
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return provicia;
    }

}
