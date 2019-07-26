package com.hhmarket.mobile.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
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
import java.util.Set;

public class DialogProductFragment extends androidx.fragment.app.DialogFragment {
    public static final String TAG = "Dialog";

    // is this dialog for size or color ?
    private boolean isSize;

    private ClickListener<ProductDetail> mOnDialogItemClickListener;

    // data set of adapter used by recyclerview
    private List<ProductDetail> list;

    private Toolbar toolbar;

    DialogProductFragment(ClickListener<ProductDetail> clickListener)  {
        this.mOnDialogItemClickListener = clickListener;
    }

    // productDetailColorList -> color,
    // productDetailSizeList -> size
    // currentItem -> current size
    public void setDataDisplay(Map<String,List<ProductDetail>> productDetailColorList,
                               Map<String,List<ProductDetail>> productDetailSizeList,
                               String currentItem, boolean isSize) {


        Map<String, ProductDetail> clor = new HashMap<>();
        this.isSize = isSize;
        list = new ArrayList<ProductDetail>();

        Set<String> keyColor = productDetailColorList.keySet(); // list all of colors
        Iterator<String> a = keyColor.iterator();

        Set<String> keySize = productDetailSizeList.keySet(); // list all of sizes
        Iterator<String> b = keySize.iterator();

        List<ProductDetail> itemSameSize = productDetailSizeList.get(currentItem);
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
                ProductDetail item  = productDetailColorList.get(corKey).get(0);
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

        ProductDetailColorListAdapter adapter = new ProductDetailColorListAdapter(mOnDialogItemClickListener);
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
