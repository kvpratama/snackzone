/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.service;

import id.ac.ciputra.ift.snackzone.domain.Supplier;
import id.ac.ciputra.ift.snackzone.persistence.SupplierDao;
import id.ac.ciputra.ift.snackzone.persistence.SupplierJdbcDao;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class SupplierService {

    private SupplierDao suplierDao;

    public SupplierService() {
        suplierDao = new SupplierJdbcDao();
    }

    public void insertSupplier(Supplier supplier) {
        suplierDao.insertSupplier(supplier);
    }

    public void updateSupplier(Supplier supplier, String oldName){
        suplierDao.updateSupplier(supplier, oldName);
    }

    public Vector readAll() {
        return suplierDao.readAll();
    }
}
