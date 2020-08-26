package Dao;

import Entidades.Localidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LocalidadDAO extends Conexion {

    private Connection cn;
    private String SQL;

    public LinkedList<Localidad> getLocalidadesByProvinciaId(int idProvincia) {
        LinkedList<Localidad> lista = new LinkedList<>();
        Localidad localidad = null;
        SQL = "SELECT idLocalidad, nombre, nom_departamento "
                + "FROM tb_localidad WHERE id_provincia = ?";
        cn = conectar();
        try {
            PreparedStatement st = cn.prepareStatement(SQL);
            st.setInt(1, idProvincia);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                localidad = new Localidad();
                localidad.setIdLocalidad(rs.getLong(1));
                localidad.setNombre(rs.getString(2));
                localidad.setNon_departamento(rs.getString(3));
                lista.add(localidad);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return lista;
    }

    public Localidad getLocalidadById(long id_localidad) {
        Localidad localidad = new Localidad();
        ProvinciaDAO provincia = new ProvinciaDAO();
        SQL = "SELECT * FROM tb_localidad WHERE idLocalidad = ?";
        cn = conectar();
        try {
            PreparedStatement st = cn.prepareStatement(SQL);
            st.setLong(1, id_localidad);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                localidad.setIdLocalidad(rs.getLong(1));
                localidad.setNombre(rs.getString(2));
                localidad.setNon_departamento(rs.getString(3));
                localidad.setProvincia(provincia.getProvinciaById(rs.getInt(4)));
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return localidad;
    }

}
