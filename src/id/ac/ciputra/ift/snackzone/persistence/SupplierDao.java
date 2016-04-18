/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.Supplier;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public interface SupplierDao {

    public void insertSupplier(Supplier supplier);

    public void updateSupplier(Supplier supplier, String oldName);

    public Vector readAll();
}
