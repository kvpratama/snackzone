/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.InsertNewEmployee;

import id.ac.ciputra.ift.snackzone.domain.Employees;
import id.ac.ciputra.ift.snackzone.service.EmployeesService;

/**
 *
 * @author Kevin
 */
public class InsertNewEmployeeModel {

    protected void insertNewEmployee(String employeeName, String address, String email,
            String phoneNumber, String position, String status) {

        Employees e = new Employees();
        EmployeesService eService = new EmployeesService();

        e.setEmployeeName(employeeName);
        e.setAddress(address);
        e.setEmail(email);
        e.setPhoneNumber(phoneNumber);
        e.setPosition(position);
        e.setStatus(status);

        eService.insertNewEmployee(e);
    }
}
