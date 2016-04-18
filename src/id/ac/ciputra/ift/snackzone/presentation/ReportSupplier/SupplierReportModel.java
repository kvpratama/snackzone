/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.ReportSupplier;

import id.ac.ciputra.ift.snackzone.service.SupplierService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class SupplierReportModel {

    protected Vector readAll() {
        SupplierService sService = new SupplierService();
        return sService.readAll();
    }
}
