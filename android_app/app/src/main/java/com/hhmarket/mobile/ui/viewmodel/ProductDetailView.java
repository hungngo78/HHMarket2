package com.hhmarket.mobile.ui.viewmodel;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hhmarket.mobile.model.ProductDetail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            listDataSize.put(productDetails.get(i).getSize(), itemList);

        }

    }
    public Map<String,List<ProductDetail>> getProductDetailColorAdapter() {
        return listDataColor;
    }

    public Map<String,List<ProductDetail>> getProductDetailSizeAdapter() {
        return listDataSize;
    }

    class ProductDetailDataView {

        public Integer productDetailsId;

        public Integer productId;

        public String name;

        public String description;

        public float price;

        public String size;

        public String picture;

        public String  color;

        public Integer  amount;

        ProductDetailDataView() {

        }

    }


}
