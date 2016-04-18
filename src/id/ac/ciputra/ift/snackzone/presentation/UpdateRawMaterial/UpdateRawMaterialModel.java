/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.UpdateRawMaterial;

import id.ac.ciputra.ift.snackzone.domain.RawMaterial;
import id.ac.ciputra.ift.snackzone.domain.Supplier;
import id.ac.ciputra.ift.snackzone.service.RawMaterialService;
import id.ac.ciputra.ift.snackzone.service.SupplierService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class UpdateRawMaterialModel {

    protected Vector getAllSupplier() {
        SupplierService sService = new SupplierService();
        return sService.readAll();

    }

    protected Vector getAllRawMaterialName() {
        Vector readAllRowMaterial = new Vector();
        Vector<String> rawMaterialName = new Vector<String>();
        RawMaterialService rService = new RawMaterialService();
        readAllRowMaterial = rService.readAll();
        for (int i = 0; i < readAllRowMaterial.size(); i++) {
            RawMaterial r = (RawMaterial) readAllRowMaterial.elementAt(i);
            rawMaterialName.add(r.getRawMaterialName());
        }
        return rawMaterialName;
    }

    protected Vector getAllData() {
        RawMaterialService rService = new RawMaterialService();
        return rService.readAll();
    }

    protected void updateRawMaterial(RawMaterial rawMaterial, String oldName){
        RawMaterialService rService = new RawMaterialService();
        rService.updateRawMaterial(rawMaterial, oldName);
    }
}
