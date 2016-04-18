/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.service;

import id.ac.ciputra.ift.snackzone.domain.Selling;
import id.ac.ciputra.ift.snackzone.persistence.SellingDao;
import id.ac.ciputra.ift.snackzone.persistence.SellingJdbcDao;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class SellingService {

    private SellingDao sellingDao;

    public SellingService() {
        sellingDao = new SellingJdbcDao();
    }

    public int getSellingSequence(){
        return sellingDao.getSellingSequence();
    }

    public void insertSelling(Selling selling) {
        sellingDao.insertSelling(selling);
    }

    public Vector readAll(){
        return sellingDao.readAll();
    }

    public Vector getDistinctDate(){
        return sellingDao.getDistinctDate();
    }

    public Vector getMonth(){
        return sellingDao.getMonth();
    }

    public int countSelling(){
        return sellingDao.countSelling();
    }
}
