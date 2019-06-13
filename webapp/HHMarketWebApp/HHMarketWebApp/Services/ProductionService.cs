using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using HHMarketWebApp.Models;
using HHMarketWebApp.ViewModels;

namespace HHMarketWebApp.Services
{
    public class ProductionService
    {
        public async Task<List<Category>> getAllCategories()
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_ALL_CATEGORIES_URL;
            return await http.getCategoryAsync(url);
        }

        public async Task<List<Production>> getProductsByCategoryId(int categoryId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_PRODUCTS_BY_CATEGORY_ID_URL + categoryId.ToString();
            return await http.getProductsByCategoryAsync(url);
        }

        public async Task<Production> getProductionByProductDetailsId(int productDetailsId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_PRODUCTS_BY_PRODUCT_DETAILS_ID_URL + productDetailsId.ToString();
            return await http.getProductionAsync(url);
        }

        public async Task<Production> getProductionByProductId(int productId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_PRODUCTION_BY_PRODUCT_ID_URL + productId.ToString();
            return await http.getProductionAsync(url);
        }

        public async Task<ProductTitle> getProductTitleByProductId(int productId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_PRODUCT_TITLE_BY_PRODUCT_ID_URL + productId.ToString();
            return await http.getProductTitleByIdAsync(url);
        }

        public async Task<List<ProductionDetail>> getProductDetailsByProductId(int productId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_PRODUCT_DETAILS_BY_PRODUCT_ID_URL + productId.ToString();
            return await http.getProductDetailsByProductIdAsync(url);
        }

        public async Task<ProductionDetail> getProductDetailByProductDetailsIds(int productDetailsId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_PRODUCT_DETAILS_BY_PRODUCT_DETAILS_ID_URL + productDetailsId.ToString();
            return await http.getProductDetailsByProductDetailsIdAsync(url);
        }

        /* Review */
        public async Task<List<ReviewProduction>> getReviewsByProductId(int productId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_REVIEWS_BY_PRODUCT_ID_URL + productId.ToString();
            return await http.getReviewsByProductIdAsync(url);
        }

        public async Task<Rating> getRatingByProductId(int productId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_RATING_BY_PRODUCT_ID_URL + productId.ToString();
            return await http.getRatingByProductIdAsync(url);
        }

        public async Task<Review> addReview(ReviewProduction review)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.POST_REVIEW_URL;
            return await http.postReviewAsync(url, review);
        }

        /* Search */
        public async Task<List<SearchProduction>> search(int categoryId, string criteria)
        {
            HttpConnection http = new HttpConnection();
           
            String url = Constant.SEARCH_URL + "categoryId=" + categoryId.ToString() + "&criteria=" + criteria;
            if (categoryId == 0) 
                url = Constant.SEARCH_URL + "criteria=" + criteria;

            return await http.getSearchResultsAsync(url);
        }
    }
}