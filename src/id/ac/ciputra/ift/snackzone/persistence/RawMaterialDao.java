/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.RawMaterial;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public interface RawMaterialDao {

    public void insertRawMaterial(RawMaterial rawMaterial);

    public void updatePrice(double newPrice, String rawMaterialName);

    public void updateStock(int newStock, String productName);

    public void updateRawMaterial(RawMaterial rawMaterial, String oldName);

    public void updateRawMaterial(int product_id, int quantity);

    public Vector readAll();
}
