/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.domain;

/**
 *
 * @author Kevin
 */
public class SellingDetail {

    private int sellingId;
    private int productId;
    private int quantity;
    private int discountProduct;

    public int getDiscountProduct() {
        return discountProduct;
    }

    public void setDiscountProduct(int discountProduct) {
        this.discountProduct = discountProduct;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSellingId() {
        return sellingId;
    }

    public void setSellingId(int sellingId) {
        this.sellingId = sellingId;
    }
}
