/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.POS;

import id.ac.ciputra.ift.snackzone.domain.Product;
import id.ac.ciputra.ift.snackzone.domain.Selling;
import id.ac.ciputra.ift.snackzone.domain.SellingDetail;
import id.ac.ciputra.ift.snackzone.service.ProductService;
import id.ac.ciputra.ift.snackzone.service.RawMaterialService;
import id.ac.ciputra.ift.snackzone.service.SellingDetailService;
import id.ac.ciputra.ift.snackzone.service.SellingService;
import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class PointOfSaleModel {

    public Vector<Product> readProduct() {
        ProductService pService = new ProductService();
        return pService.readAll();
    }

    public int getSellingId(){
        SellingService sService = new SellingService();
        return sService.countSelling() + 179;
    }

    public void insertSelling(int sellingId, String cashier, Date date, int totalQuantity,
            double totalPrice, String paymentMethod, String cardNumber,
            Vector<Sold> sold) {

        Selling selling = new Selling();
        selling.setSellingId(sellingId);
        selling.setCashier(cashier);
        selling.setSellingDate(date);
        selling.setTotalQuantity(totalQuantity);
        selling.setTotalPrice(totalPrice);
        selling.setPaymentMethod(paymentMethod);
        selling.setCardNumber(cardNumber);

        SellingService sService = new SellingService();
        sService.insertSelling(selling);

        Vector<SellingDetail> sellingDetail = new Vector<SellingDetail>();

        for (int i = 0; i < sold.size(); i++) {
            SellingDetail sd = new SellingDetail();
            sd.setSellingId(sellingId);
            sd.setProductId(sold.get(i).getProductId());
            sd.setQuantity(sold.get(i).getQuantity());
            sd.setDiscountProduct(sold.get(i).getDiscount());
            sellingDetail.add(sd);
        }
        for (int i = 0; i < sellingDetail.size(); i++) {
            SellingDetailService sdService = new SellingDetailService();
            sdService.insertSellingDetail(sellingDetail.get(i));
        }
        for (int i = 0; i < sold.size(); i++) {
            RawMaterialService rService = new RawMaterialService();
            rService.updateRawMaterial(sold.get(i).getProductId(), sold.get(i).getQuantity());
        }
    }
}
