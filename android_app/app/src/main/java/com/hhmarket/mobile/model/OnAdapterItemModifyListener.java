package com.hhmarket.mobile.model;

public interface OnAdapterItemModifyListener<T> {
    void onModify(T object, int position);
    public int getModifiedPosition();
}
