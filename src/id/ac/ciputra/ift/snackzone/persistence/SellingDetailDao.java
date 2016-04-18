/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.SellingDetail;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public interface SellingDetailDao {

    public Vector readAll();

    public Vector getSellingDetail(String sellingId);

    public void insertSellingDetail(SellingDetail sellingDetail);
}
