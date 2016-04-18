/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.Users;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public interface UserDAO {

    public void createUser(Users user);

    public void updateUser(String role, String status, String username);

    public void updateUserStatus(String userStatus, String UserName);

    public void updateRole(String role, String UserName);

    public Vector readAll();
}
