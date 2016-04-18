/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.ReportUser;

import id.ac.ciputra.ift.snackzone.service.UserService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class UserReportModel {

    protected Vector readAll() {
        UserService uService = new UserService();
        return uService.readAll();
    }
}
