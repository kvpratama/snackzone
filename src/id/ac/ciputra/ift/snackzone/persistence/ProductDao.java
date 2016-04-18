/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.persistence;

import id.ac.ciputra.ift.snackzone.domain.Product;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public interface ProductDao {

    public void insertProduct(Product product);

    public void updateProductPrice(double newPrice, String productName);

    public void updateProduct(Product product, String oldName);

    public Vector readAll();
}
