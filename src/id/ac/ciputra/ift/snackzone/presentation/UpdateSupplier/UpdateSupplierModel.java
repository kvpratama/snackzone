/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.UpdateSupplier;

import id.ac.ciputra.ift.snackzone.domain.Supplier;
import id.ac.ciputra.ift.snackzone.service.SupplierService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class UpdateSupplierModel {

    protected Vector getName() {
        Vector readAll = new Vector();
        Vector<String> name = new Vector<String>();
        SupplierService sService = new SupplierService();
        readAll = sService.readAll();
        for (int i = 0; i < readAll.size(); i++) {
            Supplier s = (Supplier) readAll.elementAt(i);
            name.add(s.getSupplierName());
        }
        return name;
    }

    protected Vector getAllData() {
        SupplierService sService = new SupplierService();
        return sService.readAll();
    }

    protected void updateSupplier(Supplier supplier, String oldName) {
        SupplierService sService = new SupplierService();
        sService.updateSupplier(supplier, oldName);
    }
}
