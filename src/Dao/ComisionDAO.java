package Dao;

import Entidades.Comision;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ComisionDAO extends Conexion {

    private String SQL;
    private Connection cn;

    public LinkedList<Comision> getAllComisiones() {
        LinkedList<Comision> lista = new LinkedList<>();
        Comision comision = null;
        SQL = "SELECT * FROM tb_comision ORDER BY descripcion";
        cn = conectar();
        UsuarioDAO usuario = new UsuarioDAO();
        ModuloDAO modulo = new ModuloDAO();
        SedeDAO sede = new SedeDAO();
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                comision = new Comision();
                comision.setIdComision(rs.getInt(1));
                comision.setDescripcion(rs.getString(2));
                comision.setFecha_ini(rs.getDate(3));
                comision.setFecha_fin(rs.getDate(4));
                comision.setUsuario(usuario.getUsuarioById(rs.getInt(5)));
                comision.setModulo(modulo.getModuloByID(rs.getInt(6)));
                comision.setSede(sede.getSedeByID(rs.getInt(7)));
                lista.add(comision);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    public LinkedList<Comision> getComisionesBySedeId(int idSede) {
        LinkedList<Comision> lista = new LinkedList<>();
        Comision comision = null;
        SQL = "SELECT idComision, descripcion "
                + "FROM tb_comision WHERE id_sede = ?";
        cn = conectar();
        try {
            PreparedStatement st = cn.prepareStatement(SQL);
            st.setInt(1, idSede);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                comision = new Comision();
                comision.setIdComision(rs.getInt(1));
                comision.setDescripcion(rs.getString(2));
                lista.add(comision);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return lista;
    }

    public LinkedList<Comision> getComisioneBySedeByProfe(int idSede, int idProfe) {
        LinkedList<Comision> lista = new LinkedList<>();
        Comision comision = null;
        SQL = "SELECT idComision, descripcion "
                + "FROM tb_comision WHERE id_sede = ? AND id_usuario = ?";
        cn = conectar();
        try {
            PreparedStatement st = cn.prepareStatement(SQL);
            st.setInt(1, idSede);
            st.setInt(2, idProfe);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                comision = new Comision();
                comision.setIdComision(rs.getInt(1));
                comision.setDescripcion(rs.getString(2));
                lista.add(comision);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return lista;
    }

    public boolean insert(Comision comision) {
        boolean insert = false;
        SQL = "INSERT INTO tb_comision VALUES (NULL,?,?,?,?,?,?)";
        cn = conectar();
        PreparedStatement ps;
        String fechaini;
        String fechafin;

        //  Formateo la Fecha para poder insertarla correctamente en la base
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechaini = formato.format(comision.getFecha_ini());
        } catch (Exception ex) {
            System.out.println("error de Fecha" + ex);
            fechaini = null;
        }
        Date deini = Date.valueOf(fechaini);

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechafin = formato.format(comision.getFecha_fin());
        } catch (Exception ex) {
            System.out.println("error de Fecha" + ex);
            fechafin = null;
        }
        Date defin = Date.valueOf(fechafin);

        //  Fin de formateo         
        try {
            ps = cn.prepareStatement(SQL);
            ps.setString(1, comision.getDescripcion());
            ps.setDate(2, deini);
            ps.setDate(3, defin);
            ps.setInt(4, comision.getUsuario().getIdUsuario());
            ps.setInt(5, comision.getModulo().getIdModulo());
            ps.setInt(6, comision.getSede().getIdSede());
            if (ps.executeUpdate() > 0) {
                insert = true;
            }
            ps.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("Fallo inserción de Comision.");
            insert = false;
        }
        return insert;
    }

    //--------------------------------------------------------------------------
    public boolean update(Comision comision) {
        boolean update = false;
        SQL = "UPDATE tb_comision SET descripcion = ?, fecha_ini = ?, "
                + "fecha_fin = ?, id_usuario = ?, id_modulo = ?,"
                + " id_sede= ? WHERE idComision = ? ";
        cn = conectar();
        PreparedStatement ps;
        String fechaini;
        String fechafin;

        //  Formateo la Fecha para poder insertarla correctamente en la base
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechaini = formato.format(comision.getFecha_ini());
        } catch (Exception ex) {
            System.out.println("error de Fecha" + ex);
            fechaini = null;
        }
        Date deini = Date.valueOf(fechaini);

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechafin = formato.format(comision.getFecha_fin());
        } catch (Exception ex) {
            System.out.println("error de Fecha" + ex);
            fechafin = null;
        }
        Date defin = Date.valueOf(fechafin);
        //  Fin de formateo         
        try {
            ps = cn.prepareStatement(SQL);
            ps.setString(1, comision.getDescripcion());
            ps.setDate(2, deini);
            ps.setDate(3, defin);
            ps.setInt(4, comision.getUsuario().getIdUsuario());
            ps.setInt(5, comision.getModulo().getIdModulo());
            ps.setInt(6, comision.getSede().getIdSede());
            ps.setInt(7, comision.getIdComision());
            if (ps.executeUpdate() > 0) {
                update = true;
            }
            ps.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Fallo el Update de Comision.");
        }
        return update;
    }

    //--------------------------------------------------------------------------
    public Comision getComisionxID(int idc) {
        Comision comision = new Comision();
        SQL = "SELECT * FROM tb_comision WHERE idComision = ?";
        cn = conectar();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = cn.prepareStatement(SQL);
            ps.setInt(1, idc);
            rs = ps.executeQuery();
            if (rs.next()) {
                comision.setIdComision(rs.getInt(1));
                comision.setDescripcion(rs.getString(2));
                comision.setFecha_fin(rs.getDate(3));
                comision.setFecha_ini(rs.getDate(4));
                UsuarioDAO usudao = new UsuarioDAO();
                comision.setUsuario(usudao.getUsuarioById(rs.getInt(5)));
                ModuloDAO mod = new ModuloDAO();
                comision.setModulo(mod.getModuloByID(rs.getInt(6)));
                SedeDAO sededao = new SedeDAO();
                comision.setSede(sededao.getSedeByID(rs.getInt(7)));
            } else {
                System.out.println("Fallo la busqueda del Usuario");;
            }
            rs.close();
            ps.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("Fallo la lectura del Usuario");
        }
        return comision;
    }

}
