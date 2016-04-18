/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.Employees;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class EmployeesJdbcDao implements EmployeesDAO {

    private Connection conn;

    public EmployeesJdbcDao() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public void insertNewEmployee(Employees employees) {
        final String insert = "insert into employees values "
                + "(eid_seq.nextval, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(insert);
            pstmt.setString(1, employees.getEmployeeName());
            pstmt.setString(2, employees.getAddress());
            pstmt.setString(3, employees.getEmail());
            pstmt.setString(4, employees.getPhoneNumber());
            pstmt.setString(5, employees.getPosition());
            pstmt.setString(6, employees.getStatus());
            pstmt.executeUpdate();
            conn.commit();
            System.out.println("commit");
        } catch (SQLException ex) {
            Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateEmployee(Employees employees, String oldName) {
        final String update = "update employees set employee_name = ?, address = ?, "
                + "email = ?, phone_number = ?, position = ?, status = ? "
                + "where employee_name = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(update);
            pstmt.setString(1, employees.getEmployeeName());
            pstmt.setString(2, employees.getAddress());
            pstmt.setString(3, employees.getEmail());
            pstmt.setString(4, employees.getPhoneNumber());
            pstmt.setString(5, employees.getPosition());
            pstmt.setString(6, employees.getStatus());
            pstmt.setString(7, oldName);
            pstmt.executeUpdate();
            conn.commit();
            System.out.println("commit");
        } catch (SQLException ex) {
            Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Vector readAll() {
        Vector result = new Vector();
        String readAllProduct = "select * from employees where status = 'Active'";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(readAllProduct);
            rs = pstmt.executeQuery();
            Employees e = null;
            while (rs.next()) {
                e = new Employees();
                e.setEmployeeName(rs.getString("employee_name"));
                e.setAddress(rs.getString("address"));
                e.setEmail(rs.getString("email"));
                e.setPhoneNumber(rs.getString("phone_number"));
                e.setPosition(rs.getString("position"));
                result.addElement(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    public Vector readAllEmployee() {
        Vector result = new Vector();
        String readAllProduct = "select * from employees";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(readAllProduct);
            rs = pstmt.executeQuery();
            Employees e = null;
            while (rs.next()) {
                e = new Employees();
                e.setEmployeeName(rs.getString("employee_name"));
                e.setAddress(rs.getString("address"));
                e.setEmail(rs.getString("email"));
                e.setPhoneNumber(rs.getString("phone_number"));
                e.setPosition(rs.getString("position"));
                e.setStatus(rs.getString("status"));
                result.addElement(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserJdbcDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }
}
