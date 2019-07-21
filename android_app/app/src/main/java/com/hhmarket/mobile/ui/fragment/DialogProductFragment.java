package com.hhmarket.mobile.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hhmarket.mobile.R;
import com.hhmarket.mobile.model.ClickListener;
import com.hhmarket.mobile.model.ProductDetail;
import com.hhmarket.mobile.ui.adapter.ProductDetailColorListAdapter;
import com.hhmarket.mobile.utils.HHMarketConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class DialogProductFragment extends androidx.fragment.app.DialogFragment {

    public static final String TAG = "Dialog";
    private Map<String,List<ProductDetail>> producdetail;
    private List<ProductDetail> productList;
    private  String currentItem;
    private ClickListener<ProductDetail> clickListener;
    private List<ProductDetail> list;
    private boolean isSize;

    private Toolbar toolbar;

    DialogProductFragment(ClickListener<ProductDetail> clickListener)  {

        this.clickListener = clickListener;

    }

    // productDetailCheckList -> color, productDetailList -> size
    // currentItem -> current size
    public void setDataDisplay(Map<String,List<ProductDetail>> productDetailCheckList, Map<String,List<ProductDetail>> productDetailList,
                               String currentItem, List<ProductDetail> productList,boolean isSize) {
        producdetail = productDetailList;
        this.currentItem = currentItem;
        this.productList = productList;
        Map<String, ProductDetail> clor = new HashMap<>();
        this.isSize = isSize;
        list = new ArrayList<ProductDetail>();
        Set<String> keyColor = productDetailCheckList.keySet(); // list all of colors
        Iterator<String> a = keyColor.iterator();

        Set<String> keySize = productDetailList.keySet(); // list all of sizes
        Iterator<String> b = keySize.iterator();

        List<ProductDetail> itemSameSize = productDetailList.get(currentItem);
        for( int i = 0; i< itemSameSize.size() ; i++) {
            ProductDetail item = itemSameSize.get(i);
            item.setAvailable(true);
            list.add(item);
            if (isSize)
                clor.put(item.getSize(),item);
            else
                clor.put(item.getColor(),item);
        }
        while(a.hasNext()){
            String corKey = a.next();
            if(!clor.containsKey(corKey)) {
                ProductDetail item  = productDetailCheckList.get(corKey).get(0);
                item.setAvailable(false);
                list.add(item);

            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_productdetail_list, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbarDialog);
        RecyclerView lv = (RecyclerView ) view.findViewById(R.id.products_list);
        ProductDetailColorListAdapter adapter = new ProductDetailColorListAdapter(clickListener);
        adapter.setProductList(list,isSize);
        lv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        if (getArguments() != null){
            toolbar.setTitle("" + getArguments().getString(HHMarketConstants.KEY_TITLE_DIALOG));
        } else {
            toolbar.setTitle("Select");
        }

        toolbar.setTitleTextColor(getContext().getColor(android.R.color.white));
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            //make full screen
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

}
