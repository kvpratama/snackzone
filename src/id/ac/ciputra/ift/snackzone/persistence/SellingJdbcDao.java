/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.Selling;
import java.sql.Connection;
import java.sql.Date;
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
public class SellingJdbcDao implements SellingDao {

    private Connection conn;

    public SellingJdbcDao() {
        conn = ConnectionManager.getInstance().getConnection();
    }

    public Vector getMonth() {
        Vector result = new Vector();
        String getMonth = "select distinct to_char(selling_date, 'Month') as month "
                + "from selling order by month";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(getMonth);
            rs = pstmt.executeQuery();
            String month = null;
            while (rs.next()) {
                month = rs.getString("month");
                result.addElement(month);
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

    public Vector getDistinctDate() {
        Vector result = new Vector();
        String readDateDistinct = "select distinct selling_date from selling "
                + "order by selling_date asc";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(readDateDistinct);
            rs = pstmt.executeQuery();
            Date date = null;
            while (rs.next()) {
                date = rs.getDate("selling_date");
                result.addElement(date);
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

    public Vector readAll() {
        Vector result = new Vector();
        String readAllSelling = "select * from selling order by selling_id asc";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(readAllSelling);
            rs = pstmt.executeQuery();
            Selling s = null;
            while (rs.next()) {
                s = new Selling();
                s.setSellingId(rs.getInt("selling_id"));
                s.setCashier(rs.getString("cashier"));
                s.setSellingDate(rs.getDate("selling_date"));
                s.setTotalQuantity(rs.getInt("total_quantity"));
                s.setTotalPrice(rs.getDouble("total_price"));
                s.setPaymentMethod(rs.getString("payment_method"));
                s.setCardNumber(rs.getString("card_number"));
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

    public int countSelling() {
        int result = 0;
        String countSelling = "select count(selling_id) as count from selling";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(countSelling);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getInt("count");
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

    public void insertSelling(Selling selling) {
        final String insert = "insert into selling values "
                + "(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(insert);
            pstmt.setInt(1, selling.getSellingId());
            pstmt.setString(2, selling.getCashier());
            pstmt.setDate(3, selling.getSellingDate());
            pstmt.setInt(4, selling.getTotalQuantity());
            pstmt.setDouble(5, selling.getTotalPrice());
            pstmt.setString(6, selling.getPaymentMethod());
            pstmt.setString(7, selling.getCardNumber());
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

    public int getSellingSequence() {
        int result = 0;
        String readSequence = "select SELLING_SEQ.nextval as selling_sequence from dual";
//        String readSequence2 = "select SELLING_SEQ.currval as selling_sequence from dual";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(readSequence);
//            pstmt.executeQuery();
//            pstmt = conn.prepareStatement(readSequence2);
            rs = pstmt.executeQuery(readSequence);
            while (rs.next()) {
                result = rs.getInt("selling_sequence");
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
