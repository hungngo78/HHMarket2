using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using HHMarketWebApp.Models;

namespace HHMarketWebApp.Services
{
    public class AccountService
    {
        public async Task<User> authenticate(String username, String password)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.AUTHENTICATION_URL + "username=" + username + "&password=" + password;
            return await http.getAuthenticationAsync(url);
        }

        public async Task<User> getUserbyUserId(int userId) 
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.GET_USER_URL + userId.ToString();
            return await http.getAuthenticationAsync(url);
        }

        public async Task<User> addUser(User user)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.POST_USER_URL;
            return await http.postUserAsync(url, user);
        }

        public async Task<User> updateUser(User _user)
        {
            HttpConnection http = new HttpConnection();
            String url = Constant.PUT_USER_URL;
            return await http.putUserAsync(url, _user);
        }
    }
}