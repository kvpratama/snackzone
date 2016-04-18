/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.Supplier;
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
public class SupplierJdbcDao implements SupplierDao {

    private Connection conn;

    public SupplierJdbcDao() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public void insertSupplier(Supplier supplier) {
        final String insert = "insert into supplier values "
                + "(sid_seq.nextval, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(insert);
            pstmt.setString(1, supplier.getSupplierName());
            pstmt.setString(2, supplier.getAddress());
            pstmt.setString(3, supplier.getPhoneNumber());
            pstmt.setString(4, supplier.getEmail());
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

    public void updateSupplier(Supplier supplier, String oldName) {
        final String update = "update supplier set supplier_name = ?, address = ?, "
                + "phone_number = ?, email = ? "
                + "where supplier_name = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(update);
            pstmt.setString(1, supplier.getSupplierName());
            pstmt.setString(2, supplier.getAddress());
            pstmt.setString(3, supplier.getPhoneNumber());
            pstmt.setString(4, supplier.getEmail());
            pstmt.setString(5, oldName);
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
        String readAllProduct = "select * from supplier";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(readAllProduct);
            rs = pstmt.executeQuery();
            Supplier s = null;
            while (rs.next()) {
                s = new Supplier();
                s.setSupplierId(rs.getInt("supplier_id"));
                s.setSupplierName(rs.getString("supplier_name"));
                s.setAddress(rs.getString("address"));
                s.setPhoneNumber(rs.getString("phone_number"));
                s.setEmail(rs.getString("email"));
                result.addElement(s);
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
