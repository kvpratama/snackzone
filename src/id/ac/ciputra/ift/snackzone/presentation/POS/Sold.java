/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package id.ac.ciputra.ift.snackzone.presentation.POS;

/**
 *
 * @author Kevin
 */
public class Sold {
    private int productId;
    private int quantity;
    private int discount;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
