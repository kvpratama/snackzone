/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.ReportRawMaterial;

import id.ac.ciputra.ift.snackzone.service.RawMaterialService;
import id.ac.ciputra.ift.snackzone.service.SupplierService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class RawMaterialReportModel {

    protected Vector readAllProduct() {
        RawMaterialService rService = new RawMaterialService();
        return rService.readAll();
    }

    protected Vector readAllSupplier(){
        SupplierService sService = new SupplierService();
        return sService.readAll();
    }
}
