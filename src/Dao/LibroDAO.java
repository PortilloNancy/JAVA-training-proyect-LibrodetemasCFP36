package Dao;

import Entidades.Libro;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class LibroDAO extends Conexion {

    private String SQL;
    private Connection cn;

    public LinkedList<Libro> getAllLibrosByComisionId(int idComi) {
        LinkedList<Libro> lista = new LinkedList<>();
        Libro libro = null;
        SQL = "SELECT * FROM tb_libro WHERE id_comision = ?";
        cn = conectar();
        ComisionDAO comision = new ComisionDAO();
        CaracterDAO caracter = new CaracterDAO();
        try {
            PreparedStatement st = cn.prepareStatement(SQL);
            st.setInt(1, idComi);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                libro = new Libro();
                libro.setIdLibro(rs.getInt(1));
                libro.setComision(comision.getComisionxID(rs.getInt(2)));
                libro.setFecha(rs.getDate(3));
                libro.setCaracter(caracter.getCaracterByID(rs.getInt(4)));
                libro.setContenido(rs.getString(5));
                libro.setActividad(rs.getString(6));
                libro.setObservacion(rs.getString(7));
                libro.setFeriado(rs.getBoolean((8)));
                lista.add(libro);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return lista;
    }

    public boolean insert(Libro libro) {
        boolean insert = false;
        SQL = "INSERT INTO tb_libro VALUES (NULL,?,?,?,?,?,?,?)";
        cn = conectar();
        PreparedStatement ps;
        String fecha;

        //  Formateo la Fecha para poder insertarla correctamente en la base
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fecha = formato.format(libro.getFecha());
        } catch (Exception ex) {
            System.out.println("error de Fecha" + ex);
            fecha = null;
        }
        Date date = Date.valueOf(fecha);

        //  Fin de formateo         
        try {
            ps = cn.prepareStatement(SQL);
            ps.setInt(1, libro.getComision().getIdComision());
            ps.setDate(2, date);
            ps.setInt(3, libro.getCaracter().getIdCaracter());
            ps.setString(4, libro.getContenido());
            ps.setString(5, libro.getActividad());
            ps.setString(6, libro.getObservacion());
            ps.setBoolean(7, libro.isFeriado());
            if (ps.executeUpdate() > 0) {
                insert = true;
            }
            ps.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("Fallo inserción de Libro.");
            insert = false;
        }
        return insert;
    }

    //--------------------------------------------------------------------------
    public boolean update(Libro libro) {
        boolean update = false;
        SQL = "UPDATE tb_libro SET id_comision = ?, fecha = ?, id_caracter= ?,"
                + "contenido = ?, actividad = ?, observacion = ?, "
                + "es_feriado = ? WHERE idLibro = ? ";
        cn = conectar();
        PreparedStatement ps;
        String fecha;

        //  Formateo la Fecha para poder insertarla correctamente en la base
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fecha = formato.format(libro.getFecha());
        } catch (Exception ex) {
            System.out.println("error de Fecha" + ex);
            fecha = null;
        }
        Date date = Date.valueOf(fecha);

        //  Fin de formateo          
        try {
            ps = cn.prepareStatement(SQL);
            ps.setInt(1, libro.getComision().getIdComision());
            ps.setDate(2, date);
            ps.setInt(3, libro.getCaracter().getIdCaracter());
            ps.setString(4, libro.getContenido());
            ps.setString(5, libro.getActividad());
            ps.setString(6, libro.getObservacion());
            ps.setBoolean(7, libro.isFeriado());
            ps.setInt(8, libro.getIdLibro());
            if (ps.executeUpdate() > 0) {
                update = true;
            }
            ps.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Fallo el Update de Libro.");
        }
        return update;
    }

    //--------------------------------------------------------------------------
    public Libro getLibroxID(int idL) {
        Libro libro = new Libro();
        SQL = "SELECT * FROM tb_libro WHERE idLibro = ?";
        cn = conectar();
        PreparedStatement ps;
        ResultSet rs;
        ComisionDAO comision = new ComisionDAO();
        CaracterDAO caracter = new CaracterDAO();
        try {
            ps = cn.prepareStatement(SQL);
            ps.setInt(1, idL);
            rs = ps.executeQuery();
            if (rs.next()) {
                libro.setIdLibro(rs.getInt(1));
                libro.setComision(comision.getComisionxID(rs.getInt(2)));
                libro.setFecha(rs.getDate(3));
                libro.setCaracter(caracter.getCaracterByID(rs.getInt(4)));
                libro.setContenido(rs.getString(5));
                libro.setActividad(rs.getString(6));
                libro.setObservacion(rs.getString(7));
                libro.setFeriado(rs.getBoolean((8)));

            } else {
                System.out.println("Fallo la lectura del Libro");
            }
            rs.close();
            ps.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("Fallo la lectura del Libro" + e);
        }
        return libro;
    }
}
