/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.InsertNewRawMaterial;

import id.ac.ciputra.ift.snackzone.domain.RawMaterial;
import id.ac.ciputra.ift.snackzone.domain.Supplier;
import id.ac.ciputra.ift.snackzone.service.RawMaterialService;
import id.ac.ciputra.ift.snackzone.service.SupplierService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class InsertNewRawMaterialModel {

    protected Vector getAllSupplierName() {
        Vector readAllUser = new Vector();
        Vector<String> username = new Vector<String>();
        SupplierService sService = new SupplierService();
        readAllUser = sService.readAll();
        for (int i = 0; i < readAllUser.size(); i++) {
            Supplier s = (Supplier) readAllUser.elementAt(i);
            username.add(s.getSupplierName());
        }
        return username;
    }

    protected Vector readAll() {
        RawMaterialService rService = new RawMaterialService();
        return rService.readAll();
    }

    protected void insertRawMaterial(String rawMaterialName,
            String unit, Double rawMaterialPrice, String supplierName) {

        Vector<Supplier> supplier = new Vector<Supplier>();
        SupplierService sService = new SupplierService();
        supplier = sService.readAll();
        int supplierId = 0;

        for (int i= 0; i < supplier.size(); i++) {
            if (supplier.get(i).getSupplierName().equals(supplierName)) {
                supplierId = supplier.get(i).getSupplierId();
                break;
            }
        }

        RawMaterial rawMaterial = new RawMaterial();
        
        rawMaterial.setRawMaterialName(rawMaterialName);
        rawMaterial.setUnit(unit);
        rawMaterial.setPrice(rawMaterialPrice);
        rawMaterial.setSupplier_id(supplierId);

        RawMaterialService rService = new RawMaterialService();
        rService.insertRawMaterial(rawMaterial);
    }
}
