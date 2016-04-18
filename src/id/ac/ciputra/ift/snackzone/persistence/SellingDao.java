/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.Selling;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public interface SellingDao {

    public Vector readAll();

    public int countSelling();

    public Vector getMonth();

    public Vector getDistinctDate();

    public int getSellingSequence();

    public void insertSelling(Selling selling);
}
