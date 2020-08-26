package Dao;

import Entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class UsuarioDAO extends Conexion {

    private Connection cn;
    private String SQL;

    public LinkedList<Usuario> getAllUsuarios(String busqueda) {
        LinkedList<Usuario> lista = new LinkedList<>();
        LocalidadDAO localidad = new LocalidadDAO();
        Usuario usuario = null;
        SQL = "SELECT * FROM tb_usuario WHERE dni LIKE '%" + busqueda + "%' OR "
                + "apellido LIKE '%" + busqueda + "%'OR nombre LIKE '%"
                + busqueda + "%'ORDER BY deshabilitado, apellido, nombre ";
        cn = conectar();
        try {
            PreparedStatement pr = cn.prepareStatement(SQL);

            ResultSet rs = pr.executeQuery(SQL);
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setDni(rs.getInt(2));
                usuario.setNombre(rs.getString(3));
                usuario.setApellido(rs.getString(4));
                usuario.setFecha_nac(rs.getDate(5));
                usuario.setTelefono(rs.getString(6));
                usuario.setMail(rs.getString(7));
                usuario.setDomicilio(rs.getString(8));
                usuario.setLocalidad(localidad.getLocalidadById(rs.getLong(9)));
                usuario.setPassword(rs.getString(10));
                usuario.setTipo(rs.getString(11));
                usuario.setDeshabilitado(rs.getBoolean(12));
                lista.add(usuario);
            }
            rs.close();
            pr.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return lista;
    }

    public LinkedList<Usuario> getAllProfesores(String busqueda) {
        LinkedList<Usuario> lista = new LinkedList<>();
        LocalidadDAO localidad = new LocalidadDAO();
        Usuario usuario = null;
        SQL = "SELECT * FROM tb_usuario WHERE (dni LIKE '%" + busqueda + "%' "
                + "OR apellido LIKE '%" + busqueda + "%' OR nombre LIKE '%"
                + busqueda + "%') AND tipo = 'Profesor' AND deshabilitado = FALSE"
                + " ORDER BY deshabilitado, apellido, nombre ";
        cn = conectar();
        try {
            PreparedStatement pr = cn.prepareStatement(SQL);

            ResultSet rs = pr.executeQuery(SQL);
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setDni(rs.getInt(2));
                usuario.setNombre(rs.getString(3));
                usuario.setApellido(rs.getString(4));
                usuario.setFecha_nac(rs.getDate(5));
                usuario.setTelefono(rs.getString(6));
                usuario.setMail(rs.getString(7));
                usuario.setDomicilio(rs.getString(8));
                usuario.setLocalidad(localidad.getLocalidadById(rs.getLong(9)));
                usuario.setPassword(rs.getString(10));
                usuario.setTipo(rs.getString(11));
                usuario.setDeshabilitado(rs.getBoolean(12));
                lista.add(usuario);
            }
            rs.close();
            pr.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return lista;
    }

    public Usuario getUsuarioById(int idUsuario) {
        Usuario usuario = new Usuario();
        LocalidadDAO localidad = new LocalidadDAO();
        SQL = "SELECT * FROM tb_usuario WHERE idUsuario = ?";
        cn = conectar();
        try {
            PreparedStatement st = cn.prepareStatement(SQL);
            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setDni(rs.getInt(2));
                usuario.setNombre(rs.getString(3));
                usuario.setApellido(rs.getString(4));
                usuario.setFecha_nac(rs.getDate(5));
                usuario.setTelefono(rs.getString(6));
                usuario.setMail(rs.getString(7));
                usuario.setDomicilio(rs.getString(8));
                usuario.setLocalidad(localidad.getLocalidadById(rs.getLong(9)));
                usuario.setPassword(rs.getString(10));
                usuario.setTipo(rs.getString(11));
                usuario.setDeshabilitado(rs.getBoolean(12));
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return usuario;
    }

    public Usuario getUsuarioByDNI(int dni) {
        Usuario usuario = new Usuario();
        LocalidadDAO localidad = new LocalidadDAO();
        SQL = "SELECT * FROM tb_usuario WHERE dni = ? ";
        cn = conectar();
        try {
            PreparedStatement st = cn.prepareStatement(SQL);
            st.setInt(1, dni);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setDni(rs.getInt(2));
                usuario.setNombre(rs.getString(3));
                usuario.setApellido(rs.getString(4));
                usuario.setFecha_nac(rs.getDate(5));
                usuario.setTelefono(rs.getString(6));
                usuario.setMail(rs.getString(7));
                usuario.setDomicilio(rs.getString(8));
                usuario.setLocalidad(localidad.getLocalidadById(rs.getLong(9)));
                usuario.setPassword(rs.getString(10));
                usuario.setTipo(rs.getString(11));
                usuario.setDeshabilitado(rs.getBoolean(12));
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return usuario;
    }

    public boolean newUsuario(Usuario user) {
        boolean insert = false;
        SQL = "INSERT INTO tb_usuario VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        cn = conectar();
        //  Formateo la Fecha para poder insertarla correctamente en la base
        String fechaNa;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechaNa = formato.format(user.getFecha_nac());
        } catch (Exception ex) {
            System.out.println("error de Fecha" + ex);
            fechaNa = null;
        }
        Date date = Date.valueOf(fechaNa);
        //  Fin de formateo    
        try {
            PreparedStatement pr = cn.prepareStatement(SQL);
            pr.setInt(1, user.getDni());
            pr.setString(2, user.getNombre());
            pr.setString(3, user.getApellido());
            pr.setDate(4, date);
            pr.setString(5, user.getTelefono());
            pr.setString(6, user.getMail());
            pr.setString(7, user.getDomicilio());
            pr.setLong(8, user.getLocalidad().getIdLocalidad());
            pr.setString(9, user.getPassword());
            pr.setString(10, user.getTipo());
            pr.setBoolean(11, user.isDeshabilitado());
            if (pr.executeUpdate() > 0) {
                insert = true;
            }
            pr.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return insert;
    }

    public boolean updateUsuario(Usuario user) {
        boolean update = false;
        SQL = "UPDATE tb_usuario SET dni = ?, nombre = ?, apellido = ?,"
                + " fecha_nac = ?, telefono = ?, mail = ?, domicilio = ?,"
                + " id_localidad = ?, password = ?, tipo = ?, "
                + "deshabilitado = ? WHERE idUsuario = ?";
        cn = conectar();
        //  Formateo la Fecha para poder insertarla correctamente en la base
        String fechaNa;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechaNa = formato.format(user.getFecha_nac());
        } catch (Exception ex) {
            System.out.println("error de Fecha" + ex);
            fechaNa = null;
        }
        Date date = Date.valueOf(fechaNa);
        //  Fin de formateo
        try {
            PreparedStatement pr = cn.prepareStatement(SQL);
            pr.setInt(1, user.getDni());
            pr.setString(2, user.getNombre());
            pr.setString(3, user.getApellido());
            pr.setDate(4, date);
            pr.setString(5, user.getTelefono());
            pr.setString(6, user.getMail());
            pr.setString(7, user.getDomicilio());
            pr.setLong(8, user.getLocalidad().getIdLocalidad());
            pr.setString(9, user.getPassword());
            pr.setString(10, user.getTipo());
            pr.setBoolean(11, user.isDeshabilitado());
            pr.setInt(12, user.getIdUsuario());
            if (pr.executeUpdate() > 0) {
                update = true;
            }
            pr.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return update;
    }

}
