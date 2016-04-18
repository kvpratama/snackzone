/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.SellingDetail;
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
public class SellingDetailJdbcDao implements SellingDetailDao {

    private Connection conn;

    public SellingDetailJdbcDao() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public Vector readAll() {
        Vector result = new Vector();
        String readAllSellingDetail = "select * from selling_detail";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(readAllSellingDetail);
            rs = pstmt.executeQuery();
            SellingDetail s = null;
            while (rs.next()) {
                s = new SellingDetail();
                s.setSellingId(rs.getInt("selling_id"));
                s.setProductId(rs.getInt("product_id"));
                s.setQuantity(rs.getInt("Quantity"));
                s.setDiscountProduct(rs.getInt("discount"));
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

    public Vector getSellingDetail(String sellingId){
        Vector result = new Vector();
        String readSellingDetail = "select * from selling_detail where selling_id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(readSellingDetail);
            pstmt.setInt(1, Integer.parseInt(sellingId));
            rs = pstmt.executeQuery();
            SellingDetail s = null;
            while (rs.next()) {
                s = new SellingDetail();
                s.setSellingId(rs.getInt("selling_id"));
                s.setProductId(rs.getInt("product_id"));
                s.setQuantity(rs.getInt("Quantity"));
                s.setDiscountProduct(rs.getInt("discount"));
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

    public void insertSellingDetail(SellingDetail sellingDetail) {
        final String insert = "insert into selling_detail values "
                + "(?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1, sellingDetail.getSellingId());
            pstmt.setInt(2, sellingDetail.getProductId());
            pstmt.setInt(3, sellingDetail.getQuantity());
            pstmt.setInt(4, sellingDetail.getDiscountProduct());
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
}