/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.InsertNewProduct;

import id.ac.ciputra.ift.snackzone.domain.Need;
import id.ac.ciputra.ift.snackzone.domain.Product;
import id.ac.ciputra.ift.snackzone.domain.RawMaterial;
import id.ac.ciputra.ift.snackzone.service.NeedService;
import id.ac.ciputra.ift.snackzone.service.ProductService;
import id.ac.ciputra.ift.snackzone.service.RawMaterialService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class InsertNewProductModel {

    protected void inputProduct(String productName, Double productPrice,
            Float need, RawMaterial rawMaterial) {
        Product newProduct = new Product();
        ProductService pService = new ProductService();

        newProduct.setProductName(productName);
        newProduct.setPrice(productPrice);

        pService.insertProduct(newProduct);

        Vector<Product> product = pService.readAll();
        short i = 0;
        while (!product.get(i).getProductName().equals(productName)) {
            i++;
        }

        Need n = new Need();
        n.setProductId(product.get(i).getProductId());
        n.setRawMaterialId(rawMaterial.getRawMaterialId());
        n.setNeed(need);

        NeedService nService = new NeedService();
        nService.insertNeed(n);

//        int t = Math.round(rawMaterial.getStock() / n.getNeed());
//        pService.updateProductStock(t, productName);
    }

    protected Vector readAll() {
        ProductService pService = new ProductService();
        return pService.readAll();
    }

    protected Vector readAllRawMateria(){
        RawMaterialService materialService = new RawMaterialService();
        return materialService.readAll();
    }
}
