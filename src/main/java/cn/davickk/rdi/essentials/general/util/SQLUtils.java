package cn.davickk.rdi.essentials.general.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtils {
    @Deprecated
    public static final String homeurl = "jdbc:mysql://cdb-p243thok.cd.tencentcdb.com:10083/rdihome";
    public static final String DB_URL = "jdbc:mysql://cdb-p243thok.cd.tencentcdb.com:10083/rdi?useSSL=true";
    public static final String invdburl = "jdbc:mysql://cdb-p243thok.cd.tencentcdb.com:10083/rdirepo?useSSL=true";
    public static final String USR= "root";
    public static final String PWD = "dmts_avia";
    public static final String HOME_TABLE="home";
    public static final String HOME_REAL_TABLE = "home"+ServerUtils.REAL_PORT;
    public static final String HOME_MAIN_TABLE = "home"+ServerUtils.MAIN_PORT;
    public static final String HOME_CRET_TABLE = "home"+ServerUtils.CRET_PORT;

    public static String getHomeTable(int serverPort)
    {
        if(serverPort==ServerUtils.CRET_PORT)
            return HOME_CRET_TABLE;
        else if(serverPort==ServerUtils.MAIN_PORT)
            return HOME_MAIN_TABLE;
        else if(serverPort==ServerUtils.REAL_PORT)
            return HOME_REAL_TABLE;
        else return (HOME_TABLE+serverPort).replace("-","_");
    }
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USR, PWD);
    }
}
