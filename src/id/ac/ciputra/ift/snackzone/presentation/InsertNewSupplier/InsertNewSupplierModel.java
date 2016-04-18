/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.InsertNewSupplier;

import id.ac.ciputra.ift.snackzone.domain.Supplier;
import id.ac.ciputra.ift.snackzone.service.SupplierService;

/**
 *
 * @author Kevin
 */
public class InsertNewSupplierModel {

    protected void insertSupplier(String supplierName, String supplierAddress,
            String supplierEmail, String supplierPhone) {

        Supplier s = new Supplier();
        SupplierService sService = new SupplierService();

        s.setSupplierName(supplierName);
        s.setAddress(supplierAddress);
        s.setPhoneNumber(supplierPhone);
        s.setEmail(supplierEmail);

        sService.insertSupplier(s);
    }
}
