/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package id.ac.ciputra.ift.snackzone.domain;

/**
 *
 * @author Kevin
 */
public class Need {
    private int productId;
    private int rawMaterialId;
    private float need;

    public float getNeed() {
        return need;
    }

    public void setNeed(float need) {
        this.need = need;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(int rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }
}
