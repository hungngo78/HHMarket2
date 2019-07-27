using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Web;
using System.Web.Helpers;
using HHMarketWebApp.Models;
using HHMarketWebApp.ViewModels;

namespace HHMarketWebApp.Services
{
    public class NumberOfCartItem
    {
        public int Number_of_cart_items { get; set; }
    }

    public class ProductTitle
    {
        public int ProductId { get; set; }
        public String Title { get; set; }
    }

    // https://docs.microsoft.com/en-us/aspnet/web-api/overview/advanced/calling-a-web-api-from-a-net-client
    // install the Web API Client Libraries package.
    public class HttpConnection
    {
        private HttpClient client = new HttpClient();

        public HttpConnection()
        {
            client.BaseAddress = new Uri(Constant.BASE_URL);
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }

        /* Account */
        public async Task<User> getAuthenticationAsync(string path)
        {
            User user = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                user = await response.Content.ReadAsAsync<User>();
            }
            return user;
        }

        public async Task<User> postUserAsync(string path, User _user)
        {            
            HttpResponseMessage response = await client.PostAsJsonAsync(path, _user);
            response.EnsureSuccessStatusCode();

            // Deserialize the updated product from the response body.
            User user = await response.Content.ReadAsAsync<User>();
            return user;
        }

        public async Task<User> putUserAsync(string path, User _user)
        {
            HttpResponseMessage response = await client.PutAsJsonAsync(path, _user);
            response.EnsureSuccessStatusCode();

            // Deserialize the updated product from the response body.
            User user = await response.Content.ReadAsAsync<User>();
            return user;
        }


        /* Production */
        public async Task<List<Category>> getCategoryAsync(string path)
        {
            List<Category> categoryList = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                categoryList = await response.Content.ReadAsAsync<List<Category>>();
            }
            return categoryList;
        }

        public async Task<List<Production>> getProductsByCategoryAsync(string path)
        {
            List<Production> productList = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                productList = await response.Content.ReadAsAsync<List<Production>>();
            }
            return productList;
        }

        public async Task<Production> getProductionAsync(string path)
        {
            Production product = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                product = await response.Content.ReadAsAsync<Production>();
            }
            return product;
        }

        public async Task<ProductTitle> getProductTitleByIdAsync(string path)
        {
            ProductTitle product = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                product = await response.Content.ReadAsAsync<ProductTitle>();
            }
            return product;
        }

        public async Task<List<ProductionDetail>> getProductDetailsByProductIdAsync(string path)
        {
            List<ProductionDetail> productDetailsList = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                productDetailsList = await response.Content.ReadAsAsync<List<ProductionDetail>>();
            }
            return productDetailsList;
        }

        public async Task<ProductionDetail> getProductDetailsByProductDetailsIdAsync(string path)
        {
            ProductionDetail productDetails = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                productDetails = await response.Content.ReadAsAsync<ProductionDetail>();
            }
            return productDetails;
        }


        /* Review */
        public async Task<List<ReviewProduction>> getReviewsByProductIdAsync(string path)
        {
            List<ReviewProduction> reviewProductionList = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                reviewProductionList = await response.Content.ReadAsAsync<List<ReviewProduction>>();
            }
            return reviewProductionList;
        }

        public async Task<Rating> getRatingByProductIdAsync(string path)
        {
            Rating rating = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                rating = await response.Content.ReadAsAsync<Rating>();
            }
            return rating;
        }

        public async Task<Review> postReviewAsync(string path, ReviewProduction _review)
        {
            HttpResponseMessage response = await client.PostAsJsonAsync(path, _review);
            response.EnsureSuccessStatusCode();

            // Deserialize the updated product from the response body.
            Review review = await response.Content.ReadAsAsync<Review>();
            return review;
        }


        /* Shopping */
        public async Task<Cart> getCartAsync(string path)
        {
            Cart obj = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                obj = await response.Content.ReadAsAsync<Cart>();
            }
            return obj;
        }

        public async Task<Cart> addCartAsync(string path, Cart _cart)
        {
            Cart cart = null;
            HttpResponseMessage response = await client.PostAsJsonAsync(path, _cart);
            if (response.IsSuccessStatusCode)
            {
                // Deserialize the updated product from the response body.
                cart = await response.Content.ReadAsAsync<Cart>();
            }
            return cart;
        }

        public async Task<int> getCartItemNumberAsync(string path)
        {
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                NumberOfCartItem obj = await response.Content.ReadAsAsync<NumberOfCartItem>();
                return obj.Number_of_cart_items;
            }
            return 0;
        }

        public async Task<List<CartDetailItem>> getCartItemsAsync(string path)
        {
            List<CartDetailItem> obj = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                obj = await response.Content.ReadAsAsync<List<CartDetailItem>>();
            }
            return obj;
        }

        public async Task<CartDetailItem> getCartItemAsync(string path)
        {
            CartDetailItem obj = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                obj = await response.Content.ReadAsAsync<CartDetailItem>();
            }
            return obj;
        }

        public async Task<CartDetailItem> postCartItemAsync(string path, CartDetailItem item)
        {
            CartDetailItem cartItem = null;
            HttpResponseMessage response = await client.PostAsJsonAsync(path, item);
            if (response.IsSuccessStatusCode)
            {
                // Deserialize the updated product from the response body.
                cartItem  = await response.Content.ReadAsAsync<CartDetailItem>();
            }
            return cartItem;
        }

        public async Task<CartDetailItem> putCartItemAsync(string path, CartDetailItem item)
        {
            CartDetailItem cartItem = null;
            HttpResponseMessage response = await client.PutAsJsonAsync(path, item);
            if (response.IsSuccessStatusCode)
            {
                // Deserialize the updated product from the response body.
                cartItem = await response.Content.ReadAsAsync<CartDetailItem>();
            }
            return cartItem;
        }

        public async Task<int> deleteCartItemAsync(string path)
        {
            int cartItemId = -1;
            HttpResponseMessage response = await client.DeleteAsync(path);
            if (response.IsSuccessStatusCode)
            {
                // Deserialize the updated product from the response body.
                cartItemId = await response.Content.ReadAsAsync<int>();
            }
            return cartItemId;
        }

        public async Task<Order> postOrderAsync(string path)
        {
            Order order = null;
            HttpResponseMessage response = await client.PostAsJsonAsync(path, "");
            if (response.IsSuccessStatusCode)
            {
                // Deserialize the updated product from the response body.
                order = await response.Content.ReadAsAsync<Order>();
            }
            return order;
        }


        /* Search */
        public async Task<List<SearchProduction>> getSearchResultsAsync(string path)
        {
            List<SearchProduction> searchResults = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                searchResults = await response.Content.ReadAsAsync<List<SearchProduction>>();
            }
            return searchResults;
        }
    }
}