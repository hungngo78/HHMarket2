package com.hhmarket.mobile.di;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    /**
     * Response
     *    {
     *       "orderId": 17,
     *       "orderDate": "2019-06-03",
     *       "status": 0,
     *       "deliveryDate": "2019-06-03",
     *       "deliveryFee": 0,
     *       "note": "Note of user"
     *    }
     */

    @SerializedName("orderId")
    @Expose
    private int orderId;

    @SerializedName("orderDate")
    @Expose
    private String orderDate;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("deliveryFee")
    @Expose
    private float deliveryFee;

    @SerializedName("note")
    @Expose
    private String note;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(float deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
