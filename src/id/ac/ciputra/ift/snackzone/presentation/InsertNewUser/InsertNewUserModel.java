/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.InsertNewUser;

import id.ac.ciputra.ift.snackzone.domain.Users;
import id.ac.ciputra.ift.snackzone.service.UserService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class InsertNewUserModel {

    protected void insertNewUser(String username, String role, String status, String password) {
        Users user = new Users();
        UserService uService = new UserService();

        user.setUsername(username);
        user.setRole(role);
        user.setStatus(status);
        user.setPassword(password);

        uService.createUser(user);
    }

    protected Vector readAll(){
        UserService uService = new UserService();
        return uService.readAll();
    }
}