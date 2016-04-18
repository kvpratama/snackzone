/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package id.ac.ciputra.ift.snackzone.service;

import id.ac.ciputra.ift.snackzone.domain.Employees;
import id.ac.ciputra.ift.snackzone.persistence.EmployeesDAO;
import id.ac.ciputra.ift.snackzone.persistence.EmployeesJdbcDao;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class EmployeesService {
private EmployeesDAO employeeDao;

    public EmployeesService() {
        employeeDao = new EmployeesJdbcDao();
    }

    public void insertNewEmployee(Employees employee){
        employeeDao.insertNewEmployee(employee);
    }

    public Vector readAll(){
        return employeeDao.readAll();
    }

    public Vector readAllEmployee(){
        return employeeDao.readAllEmployee();
    }

    public void updateEmployee(Employees e, String oldName){
        employeeDao.updateEmployee(e, oldName);
    }
}
