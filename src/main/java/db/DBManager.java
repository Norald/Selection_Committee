package db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 * Database manager class. Works with Mysql.
 * @author Prokopenko Vladislav
 * @since 25.09.2020
 */
public class DBManager {
    private static DBManager instance;


    private DBManager() {
    }

    /**
     * Connection is singleton;
     */
    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }


    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {

            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");

            // iinspection_board - the name of data source
            DataSource ds = (DataSource)envContext.lookup("jdbc/board");
            con = ds.getConnection();
        } catch (NamingException ex) {
           ex.printStackTrace();
        }
        return con;
    }


    /**
     * Commits and close the given connection.
     *
     * @param con
     *            Connection to be committed and closed.
     */
    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con
     *            Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
