using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HHMarketWebApp.Services
{
    public class Constant
    {
        // local service
        public const String BASE_URL = "http://localhost:8080/";
        // on cloud service
        // public const String BASE_URL = "http://http://ec2-34-238-44-113.compute-1.amazonaws.com:80/";

        // Acount
        public const String AUTHENTICATION_URL = "account/login?";
        public const String GET_USER_URL = "account/get/";
        public const String POST_USER_URL = "account/add";
        public const String PUT_USER_URL = "account/update";

        // Production
        public const String GET_ALL_CATEGORIES_URL = "category/get_all_categories";
        public const String GET_PRODUCTS_BY_CATEGORY_ID_URL = "category/get_products_by_category/";
        public const String GET_PRODUCTS_BY_PRODUCT_DETAILS_ID_URL = "category/get_production_by_product_details_id/";
        public const String GET_PRODUCTION_BY_PRODUCT_ID_URL = "category/get_production_by_productId/";
        public const String GET_PRODUCT_TITLE_BY_PRODUCT_ID_URL = "category/get_product_title_by_id/";
        public const String GET_PRODUCT_DETAILS_BY_PRODUCT_DETAILS_ID_URL = "category/get_product_details_by_product_details_id/";
        public const String GET_PRODUCT_DETAILS_BY_PRODUCT_ID_URL = "category/get_product_details_by_productId/";
        public const String GET_REVIEWS_BY_PRODUCT_ID_URL = "review/get_reviews_by_productId/";
        public const String GET_RATING_BY_PRODUCT_ID_URL = "review/get_rating_by_productId/";
        public const String POST_REVIEW_URL = "review/add_review_item";

        // Shopping
        public const String GET_CART_URL = "shopping/get_cart/";
        public const String ADD_CART_URL = "shopping/add_cart";
        public const String GET_CART_ITEM_NUMBER_URL = "shopping/get_cart_item_numer/";
        public const String GET_CART_ITEMS_URL = "shopping/get_cart_items/";
        public const String GET_CART_ITEM_URL = "shopping/get_cart_item/";
        public const String POST_CART_ITEM_URL = "shopping/add_shoping_item";
        public const String PUT_UPDATE_QUANTITY_URL = "shopping/update_quantity";
        public const String POST_ORDER_URL = "shopping/order/";

        // Search
        public const String SEARCH_URL = "category/searching?";
    }
}