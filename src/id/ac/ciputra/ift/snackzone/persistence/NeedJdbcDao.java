/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.Need;
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
public class NeedJdbcDao implements NeedDao {

    private Connection conn;

    public NeedJdbcDao() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public void insertNeed(Need need) {
        final String insert = "insert into need values "
                + "(?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1, need.getProductId());
            pstmt.setInt(2, need.getRawMaterialId());
            pstmt.setFloat(3, need.getNeed());
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

    public Vector readAllNeed() {
        Vector result = new Vector();
        String readAllProduct = "select * from need";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(readAllProduct);
            rs = pstmt.executeQuery();
            Need n = null;
            while (rs.next()) {
                n = new Need();
                n.setProductId(rs.getInt("product_id"));
                n.setRawMaterialId(rs.getInt("raw_material_id"));
                n.setNeed(rs.getFloat("need"));
                result.addElement(n);
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

    public void updateNeedRawMaterial(int productId, int newRawMaterialId) {
        final String update = "update need set raw_material_id = ? "
                + "where product_id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(update);
            pstmt.setInt(1, newRawMaterialId);
            pstmt.setInt(2, productId);
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

    public void updateNeed(int productId, int newRawMaterialId, float newNeed) {
        final String update = "update need set raw_material_id = ?, need = ? "
                + "where product_id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(update);
            pstmt.setInt(1, newRawMaterialId);
            pstmt.setFloat(2, newNeed);
            pstmt.setInt(3, productId);
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
