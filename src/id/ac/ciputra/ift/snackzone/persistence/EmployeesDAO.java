/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.Employees;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public interface EmployeesDAO {

    public void insertNewEmployee(Employees employees);

    public void updateEmployee(Employees e, String oldName);

    public Vector readAll();

    public Vector readAllEmployee();
}
