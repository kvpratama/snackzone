/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.Need;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public interface NeedDao {

    public void insertNeed(Need need);

    public Vector readAllNeed();

    public void updateNeed(int productId, int ewRawMaterialId, float newNeed);

    public void updateNeedRawMaterial(int productId, int newRawMaterialId);
}
