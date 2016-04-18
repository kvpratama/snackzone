/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.service;

import id.ac.ciputra.ift.snackzone.domain.Users;
import id.ac.ciputra.ift.snackzone.persistence.UserDAO;
import id.ac.ciputra.ift.snackzone.persistence.UserJdbcDao;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class UserService {

    private UserDAO userDao;

    public UserService() {
        this.userDao = new UserJdbcDao();
    }

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void createUser(Users user) {
        userDao.createUser(user);
    }

    public void updateUser(String role, String status, String username) {
        userDao.updateUser(role, status, username);
    }

    public void updateUserStatus(String userStatus, String userName) {
        userDao.updateUserStatus(userStatus, userName);
    }

    public void updateUserRole(String role, String userName) {
        userDao.updateRole(role, userName);
    }

    public Vector readAll() {
        return userDao.readAll();
    }
}
