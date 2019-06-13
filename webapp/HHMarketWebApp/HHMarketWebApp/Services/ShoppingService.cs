using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using HHMarketWebApp.Models;
using HHMarketWebApp.ViewModels;

namespace HHMarketWebApp.Services
{
    public class ShoppingService
    {
        public async Task<Cart> addCart(Cart _cart)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.ADD_CART_URL;

            return await http.addCartAsync(url, _cart);
        }

        public async Task<Cart> getCart(int userId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_CART_URL + userId.ToString();

            return await http.getCartAsync(url);
        }

        public int getCartItemNumber(int userId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_CART_ITEM_NUMBER_URL + userId.ToString();

            return Task.Run(() => http.getCartItemNumberAsync(url)).Result;
        }

        public async Task<List<CartDetailItem>> getCartItemsByUserId(int userId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_CART_ITEMS_URL + userId.ToString();
            return await http.getCartItemsAsync(url);
        }

        public async Task<CartDetailItem> getCartItemByCartIdAndProductDetailsId(int cartId, int productDetailsId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_CART_ITEM_URL + cartId.ToString() + "/" + productDetailsId.ToString();
            return await http.getCartItemAsync(url);
        }

        public async Task<CartDetailItem> addCartItem(CartDetailItem item)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.POST_CART_ITEM_URL;
            return await http.postCartItemAsync(url, item);
        }
        public async Task<CartDetailItem> updateQuantity(CartDetailItem item)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.PUT_UPDATE_QUANTITY_URL;
            return await http.putCartItemAsync(url, item);
        }

        public async Task<Order> order(int userId)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.POST_ORDER_URL + userId.ToString();
            return await http.postOrderAsync(url);
        }
    }
}