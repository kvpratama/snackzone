/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ciputra.ift.snackzone.presentation.UpdateProduct;

import id.ac.ciputra.ift.snackzone.domain.RawMaterial;
import id.ac.ciputra.ift.snackzone.service.NeedService;
import id.ac.ciputra.ift.snackzone.service.ProductService;
import id.ac.ciputra.ift.snackzone.service.RawMaterialService;
import java.util.Vector;

/**
 *
 * @author Kevin
 */
public class UpdateProductModel {

    protected Vector getAllRawMaterialName() {
        Vector readAllRowMaterial = new Vector();
        Vector<String> rawMaterialName = new Vector<String>();
        RawMaterialService rService = new RawMaterialService();
        readAllRowMaterial = rService.readAll();
        for (int i = 0; i < readAllRowMaterial.size(); i++) {
            RawMaterial r = (RawMaterial) readAllRowMaterial.elementAt(i);
            rawMaterialName.add(r.getRawMaterialName());
        }
        return rawMaterialName;
    }

    protected Vector readAllProduct() {
        ProductService pService = new ProductService();
        return pService.readAll();
    }

    protected Vector readAllNeed() {
        NeedService nService = new NeedService();
        return nService.readAllNeed();
    }

    protected Vector readAllRawMaterial() {
        RawMaterialService rService = new RawMaterialService();
        return rService.readAll();
    }

    protected void updateRawMaterial(RawMaterial rawMaterial, String oldName) {
        RawMaterialService rService = new RawMaterialService();
        rService.updateRawMaterial(rawMaterial, oldName);
    }

    protected void updateProduct(double newPrice, String productName) {
        ProductService pService = new ProductService();
        pService.updateProductPrice(newPrice, productName);
    }

    protected void updateNeed(int productId, int newRawMaterialId, float newNeed) {
        NeedService needService = new NeedService();
        needService.updateNeed(productId, newRawMaterialId, newNeed);
    }

    protected void updateRawMateriaNeed(int productId, int rawMaterialId) {
        NeedService needService = new NeedService();
        needService.updateNeedRawMaterial(productId, rawMaterialId);
    }
}
