package com.hhmarket.mobile.model;

public interface ClickListenerOnAdapter<T> {

    void onClick(T object );
    void setPosition(int position);
    int getPosition();

}
