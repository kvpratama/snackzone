/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.ReportProduct;

import id.ac.ciputra.ift.snackzone.service.NeedService;
import id.ac.ciputra.ift.snackzone.service.ProductService;
import id.ac.ciputra.ift.snackzone.service.RawMaterialService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class ReportProductModel {

    public Vector readAllProduct() {
        ProductService productService = new ProductService();
        return productService.readAll();
    }

    public Vector readAllRawMaterial() {
        RawMaterialService materialService = new RawMaterialService();
        return materialService.readAll();
    }

    public Vector readAllNeed() {
        NeedService needService = new NeedService();
        return needService.readAllNeed();
    }
}
