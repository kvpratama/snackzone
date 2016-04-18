/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.service;

import id.ac.ciputra.ift.snackzone.domain.SellingDetail;
import id.ac.ciputra.ift.snackzone.persistence.SellingDetailDao;
import id.ac.ciputra.ift.snackzone.persistence.SellingDetailJdbcDao;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class SellingDetailService {

    private SellingDetailDao sellingDetailDao;

    public SellingDetailService() {
        sellingDetailDao = new SellingDetailJdbcDao();
    }

    public void insertSellingDetail(SellingDetail sellingDetail) {
        sellingDetailDao.insertSellingDetail(sellingDetail);
    }

    public Vector readAll(){
        return sellingDetailDao.readAll();
    }

    public Vector getSellingDetail(String sellingId){
        return sellingDetailDao.getSellingDetail(sellingId);
    }
}
