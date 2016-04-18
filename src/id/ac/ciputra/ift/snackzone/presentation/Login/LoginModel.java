/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.Login;

import id.ac.ciputra.ift.snackzone.service.UserService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class LoginModel {

    public Vector readAllUser() {
        UserService us = new UserService();
        return us.readAll();
    }
}
