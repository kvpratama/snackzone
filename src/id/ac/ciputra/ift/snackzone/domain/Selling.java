/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package id.ac.ciputra.ift.snackzone.domain;

import java.sql.Date;

/**
 *
 * @author Kevin
 */
public class Selling {
    private int sellingId;
    private String cashier;
    private Date sellingDate;
    private int totalQuantity;
    private double totalPrice;
    private String paymentMethod;
    private String cardNumber;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public Date getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(Date date) {
        this.sellingDate = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getSellingId() {
        return sellingId;
    }

    public void setSellingId(int sellingId) {
        this.sellingId = sellingId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
