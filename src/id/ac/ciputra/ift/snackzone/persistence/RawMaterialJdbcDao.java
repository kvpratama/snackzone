/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.RawMaterial;
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
public class RawMaterialJdbcDao implements RawMaterialDao {

    private Connection conn;

    public RawMaterialJdbcDao() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public void insertRawMaterial(RawMaterial rawMaterial) {
        final String insert = "insert into raw_material values "
                + "(raw_material_seq.nextval, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(insert);
            pstmt.setString(1, rawMaterial.getRawMaterialName());
            pstmt.setFloat(2, rawMaterial.getStock());
            pstmt.setString(3, rawMaterial.getUnit());
            pstmt.setDouble(4, rawMaterial.getPrice());
            pstmt.setInt(5, rawMaterial.getSupplier_id());
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

    public void updatePrice(double newPrice, String rawMaterialName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateStock(int newStock, String productName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Vector readAll() {
        Vector result = new Vector();
        String readAllProduct = "select * from raw_material";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(readAllProduct);
            rs = pstmt.executeQuery();
            RawMaterial rawMaterial = null;
            while (rs.next()) {
                rawMaterial = new RawMaterial();
                rawMaterial.setRawMaterialId(rs.getInt("raw_material_id"));
                rawMaterial.setRawMaterialName(rs.getString("raw_material_name"));
                rawMaterial.setStock(rs.getFloat("stock"));
                rawMaterial.setUnit(rs.getString("unit"));
                rawMaterial.setPrice(rs.getDouble("price_per_unit"));
                rawMaterial.setSupplier_id(rs.getInt("supplier_id"));
                result.addElement(rawMaterial);
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


    public void updateRawMaterial(RawMaterial rawMaterial, String oldName) {
        final String update = "update raw_material set raw_material_name = ?, stock = ?, "
                + "unit = ?, price_per_unit = ?, supplier_id = ? "
                + "where raw_material_name = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(update);
            pstmt.setString(1, rawMaterial.getRawMaterialName());
            pstmt.setFloat(2, rawMaterial.getStock());
            pstmt.setString(3, rawMaterial.getUnit());
            pstmt.setDouble(4, rawMaterial.getPrice());
            pstmt.setInt(5, rawMaterial.getSupplier_id());
            pstmt.setString(6, oldName);
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
    public void updateRawMaterial(int product_id, int quantity) {
        final String update = "update raw_material set stock = "
                + "stock - (select need from need where product_id = ?) * ?"
                + "where raw_material_id = (select raw_material_id from need "
                + "where product_id = ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(update);
            pstmt.setInt(1, product_id);
            pstmt.setInt(2, quantity);
            pstmt.setInt(3, product_id);
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
