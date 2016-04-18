/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package id.ac.ciputra.ift.snackzone.service;

import id.ac.ciputra.ift.snackzone.domain.Need;
import id.ac.ciputra.ift.snackzone.persistence.NeedDao;
import id.ac.ciputra.ift.snackzone.persistence.NeedJdbcDao;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class NeedService {
    private NeedDao needDao;

    public NeedService() {
        needDao = new NeedJdbcDao();
    }
    public void insertNeed(Need need){
        needDao.insertNeed(need);
    }
    public Vector readAllNeed(){
        return needDao.readAllNeed();
    }

    public void updateNeed(int productId, int newRawMaterialId, float newNeed){
        needDao.updateNeed(productId, newRawMaterialId, newNeed);
    }

    public void updateNeedRawMaterial(int productId, int newRawMaterialId){
        needDao.updateNeedRawMaterial(productId, newRawMaterialId);
    }
}
