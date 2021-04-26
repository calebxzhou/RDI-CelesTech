package cn.davickk.rdi.essentials.general.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class SQLUtils {
    /*public static final String DB_URL = "jdbc:mysql://cdb-p243thok.cd.tencentcdb.com:10083/rdi?useSSL=true";
    public static final String USR= "root";
    public static final String PWD = "dmts_avia";
    private static Connection SQL_CONN;*/
    private Reader CONF_RES;
    private SqlSession SQL_SESSION;
    /*private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;*/

    public SQLUtils(){
        try {
            CONF_RES=Resources.getResourceAsReader("SqlMapConfig.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(CONF_RES);
            SQL_SESSION = sessionFactory.openSession();
            //Create a new student object
            /*Student student = new Student("Mohammad","It", 80, 984803322, "Mohammad@gmail.com" );

            //Insert student data
            session.insert("Student.insert", student);
            System.out.println("record inserted successfully");
            session.commit();
            session.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }



        // 以上是样板代码
        // 以下是「业务逻辑」

        /*try {
            IHomeDaoMapper userMapper = sqlSession.getMapper(IHomeDaoMapper.class);
            Home user = userMapper.findById(1L);
            log.info("{}", user);
        } finally {
            sqlSession.close();
        }*/
        /*config.setJdbcUrl(DB_URL);
        config.setUsername(USR);
        config.setPassword(PWD);
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        config.addDataSourceProperty("keepaliveTime","30000");
        config.setMaximumPoolSize(10);
        config.setAutoCommit(false);
        ds = new HikariDataSource( config );*/
    }
    public SqlSession getSqlSession() {
            return this.SQL_SESSION;
            //return ds.getConnection();


    }
}
