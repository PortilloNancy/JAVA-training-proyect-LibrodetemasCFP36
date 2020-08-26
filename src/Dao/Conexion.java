package Dao;

import ConfigManager.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private final String url = Config.Mysql + Config.host
            + Config.port + Config.db;

    public Connection conectar() {
        Connection link = null;
        try {
            Class.forName(Config.driverMysql);
            link = DriverManager.getConnection(url, Config.usr, Config.pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return link;
    }
}
