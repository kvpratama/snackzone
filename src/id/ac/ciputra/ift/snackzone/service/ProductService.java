/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.service;

import id.ac.ciputra.ift.snackzone.domain.Product;
import id.ac.ciputra.ift.snackzone.persistence.ProductDao;
import id.ac.ciputra.ift.snackzone.persistence.ProductJdbcDao;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class ProductService {

    private ProductDao productDao;

    public ProductService() {
        productDao = new ProductJdbcDao();
    }

    public void insertProduct(Product product) {
        productDao.insertProduct(product);
    }

    public void updateProductPrice(double newPrice, String productName) {
        productDao.updateProductPrice(newPrice, productName);
    }

    public Vector readAll() {
        return productDao.readAll();
    }
}
