/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.UpdateEmployee;

import id.ac.ciputra.ift.snackzone.domain.Employees;
import id.ac.ciputra.ift.snackzone.service.EmployeesService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class UpdateEmployeeModel {

    protected Vector getName() {
        Vector readAll = new Vector();
        Vector<String> name = new Vector<String>();
        EmployeesService eService = new EmployeesService();
        readAll = eService.readAll();
        for (int i = 0; i < readAll.size(); i++) {
            Employees e = (Employees) readAll.elementAt(i);
            name.add(e.getEmployeeName());
        }
        return name;
    }

    protected Vector getAllData() {
        EmployeesService eService = new EmployeesService();
        return eService.readAllEmployee();
    }

    protected void updateEmployee(Employees e, String oldName){
        EmployeesService eService = new EmployeesService();
        eService.updateEmployee(e, oldName);
    }
}
