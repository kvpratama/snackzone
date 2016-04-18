/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.ReportEmployee;

import id.ac.ciputra.ift.snackzone.service.EmployeesService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class EmployeeReportModel {

    public Vector readEmployee() {
        EmployeesService eService = new EmployeesService();
        return eService.readAll();
    }
}
