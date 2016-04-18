/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.UpdateUser;

import id.ac.ciputra.ift.snackzone.domain.Users;
import id.ac.ciputra.ift.snackzone.service.UserService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class UpdateUserModel {

    protected Vector getUsername() {
        Vector readAllUser = new Vector();
        Vector<String> username = new Vector<String>();
        UserService uService = new UserService();
        readAllUser = uService.readAll();
        for (int i = 0; i < readAllUser.size(); i++) {
            Users user = (Users) readAllUser.elementAt(i);
            username.add(user.getUsername());
        }
        return username;
    }

    protected Vector getAllData() {
        UserService uService = new UserService();
        return uService.readAll();
    }

    protected void updateUserRole(String role, String username) {
        UserService uService = new UserService();
        uService.updateUserRole(role, username);
        System.out.println("success");
    }

    protected void updateUserStatus(String status, String username) {
        UserService uService = new UserService();
        uService.updateUserStatus(status, username);
        System.out.println("success");
    }

    protected void updateUser(String role, String status, String username) {
        UserService uService = new UserService();
        uService.updateUser(role, status, username);
    }
}
