/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.service;

import id.ac.ciputra.ift.snackzone.domain.RawMaterial;
import id.ac.ciputra.ift.snackzone.persistence.RawMaterialDao;
import id.ac.ciputra.ift.snackzone.persistence.RawMaterialJdbcDao;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class RawMaterialService {

    private RawMaterialDao rawMaterialDao;

    public RawMaterialService() {
        rawMaterialDao = new RawMaterialJdbcDao();
    }

    public void insertRawMaterial(RawMaterial rawMaterial){
        rawMaterialDao.insertRawMaterial(rawMaterial);
    }

    public Vector readAll(){
        return rawMaterialDao.readAll();
    }

    public void updateRawMaterial(RawMaterial rawMaterial, String oldName){
        rawMaterialDao.updateRawMaterial(rawMaterial, oldName);
    }

    public void updateRawMaterial(int product_id, int quantity){
        rawMaterialDao.updateRawMaterial(product_id, quantity);
    }
}
