/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kevin
 */
public class ConnectionManager {

    private static ConnectionManager ourInstance = new ConnectionManager();
    private Connection conn = null;

    public static ConnectionManager getInstance() {
        return ourInstance;
    }

    //~ Constructors ..........................................................
    private ConnectionManager() {
        try {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "snackzone", "snackzone");
            conn.setAutoCommit(false);
            System.out.println("must only print once");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }
}
