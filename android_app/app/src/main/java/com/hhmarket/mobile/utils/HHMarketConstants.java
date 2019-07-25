package com.hhmarket.mobile.utils;

public class HHMarketConstants {
    public final static String S3_BUCKET_URL = "https://hungngobucket1.s3-us-west-1.amazonaws.com/";


    public final static String PROFILE_URL = HHMarketConstants.S3_BUCKET_URL + "profile1.jpg";
    //public final static String BACKGROUND_URL = HHMarketConstants.S3_BUCKET_URL + "nav_bg.jpg";

    public static final String BASE_URL = "http://ec2-34-238-44-113.compute-1.amazonaws.com/";

    public final static String TAG_HOME = "Categories";
    public final static String TAG_PRODUCTS = "Products";
    public final static String TAG_REVIEWS = "Customer Reviews";
    public final static String TAG_REVIEW_ADDING = "Write a Review";
    public final static String TAG_PRODUCT_DETAILS = "Product Details";
    public final static String TAG_SHOPPING_CART = "Shopping Cart";
    public final static String TAG_SETTINGS = "Settings";

    public final static String KEY_CATEGORY_ID = "category_id";
    public final static String KEY_PRODUCT_ID = "product_id";
    public final static String KEY_TITLE_DIALOG = "title_dialog";
    public final static String KEY_STRING_DATA ="key_string_data";
    public final static String KEY_PRODUCT = "product";

    public static String[] getPromotionImageList(){
        String[] imageList = new String[5];
        imageList[0] = HHMarketConstants.S3_BUCKET_URL + "promotion1.png";
        imageList[1] = HHMarketConstants.S3_BUCKET_URL  + "promotion2.png";
        imageList[2] = HHMarketConstants.S3_BUCKET_URL  + "promotion3.png";
        imageList[3] = HHMarketConstants.S3_BUCKET_URL  + "promotion4.png";
        imageList[4] = HHMarketConstants.S3_BUCKET_URL  + "promotion5.png";

        return imageList;
    }
}
