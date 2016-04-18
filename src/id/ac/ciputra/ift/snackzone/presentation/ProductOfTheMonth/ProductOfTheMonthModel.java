/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.ProductOfTheMonth;

import id.ac.ciputra.ift.snackzone.domain.SellingDetail;
import id.ac.ciputra.ift.snackzone.service.ProductService;
import id.ac.ciputra.ift.snackzone.service.SellingDetailService;
import id.ac.ciputra.ift.snackzone.service.SellingService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class ProductOfTheMonthModel {

    public Vector getDistinctDate() {
        SellingService sService = new SellingService();
        return sService.getDistinctDate();
    }

    public Vector readAllSelling() {
        SellingService sService = new SellingService();
        return sService.readAll();
    }

    public Vector readAllProduct() {
        ProductService pService = new ProductService();
        return pService.readAll();
    }

    public Vector<SellingDetail> getSellingDetail(String sellingId) {
        SellingDetailService sdService = new SellingDetailService();
        return sdService.getSellingDetail(sellingId);
    }
}
