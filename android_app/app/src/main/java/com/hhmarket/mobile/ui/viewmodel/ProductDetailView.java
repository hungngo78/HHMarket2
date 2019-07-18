package com.hhmarket.mobile.ui.viewmodel;


import com.hhmarket.mobile.model.ProductDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDetailView {

    private List<ProductDetail> productDetails;
    private Map<String,List<ProductDetail>> listDataColor = new HashMap<>();
    private Map<String,List<ProductDetail>>  listDataSize  = new HashMap<>();

    public ProductDetailView(List<ProductDetail> list){

        if (list == null) {
            this.productDetails = new ArrayList<>();
        } else {
            this.productDetails = list;
        }

        processProductDetailColorAdapter();

    }


    private void processProductDetailColorAdapter() {
        for (int i = 0; i <productDetails.size(); i++) {
            List<ProductDetail> itemList ;

            if (listDataColor.containsKey(productDetails.get(i).getColor())) {
                itemList = listDataColor.get(productDetails.get(i).getColor());
            } else {
                itemList = new ArrayList<ProductDetail>();
            }
            itemList.add(productDetails.get(i));
            listDataColor.put(productDetails.get(i).getColor(), itemList);


            if (listDataSize.containsKey(productDetails.get(i).getSize())) {
                itemList = listDataSize.get(productDetails.get(i).getColor());
            } else {
                itemList = new ArrayList<ProductDetail>();
            }
            itemList.add(productDetails.get(i));
            listDataSize.put(productDetails.get(i).getSize(), itemList);

        }

    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public Map<String,List<ProductDetail>> getProductDetailColorAdapter() {
        return listDataColor;
    }

    public Map<String,List<ProductDetail>> getProductDetailSizeAdapter() {
        return listDataSize;
    }



}
