using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;
using System.Net;
using System.Web;
using System.Web.Mvc;
using HHMarketWebApp.Models;
using FormsAuth;
using System.Web.Script.Serialization;
using HHMarketWebApp.Services;
using HHMarketWebApp.ViewModels;

namespace HHMarketWebApp.Controllers
{
    public class UserController : Controller
    {
        // GET: User
        public ActionResult Login()
        {
            return View();
        }

        // POST 
        [HttpPost]
        //public ActionResult Login(String username, String password)
        public async Task<ActionResult> Login([Bind(Include = "Username,Password")]Logon logon)
        {
            if (ModelState.IsValid)
            {
                // Authenticate the user.
                if (UserManager.ValidateUser(logon, Response))
                {
                    /* move temporary shopping cart to DB */
                    string dateOfOpen = string.Empty;

                    // get tmp Cart in cookie
                    HttpCookie reqCartInfoCookies = HttpContext.Request.Cookies["CartInfo"];
                    if (reqCartInfoCookies != null)  // there is a temporary Cart in cookies 
                    {
                        dateOfOpen = reqCartInfoCookies["DateOfOpen"].ToString();

                        Cart cart = null;

                        // get existing Cart               
                        ShoppingService shoppingService = new ShoppingService();
                        cart = await shoppingService.getCart(UserManager.User.Id);

                        // there is no existing Shopping Cart for this user -> create new Shopping Cart
                        if (cart == null)
                        {
                            cart = new Cart();
                            cart.UserId = UserManager.User.Id;
                            cart.DateOpen = DateTime.Parse(dateOfOpen);

                            // add cart into system
                            cart = await shoppingService.addCart(cart);
                        }

                        HttpCookie reqIDListCookies = Request.Cookies["ProductDetailIDlist"];
                        if (reqIDListCookies != null)
                        {
                            string dataAsString = reqIDListCookies.Value;
                            if (!dataAsString.Equals(string.Empty))
                            {
                                List<int> listdata = new List<int>();                                
                                listdata = dataAsString.Split(',').Select(x => Int32.Parse(x)).ToList();
                                for (int i = 0; i < listdata.Count(); i++)
                                {
                                    HttpCookie reqCartItemCookies = Request.Cookies["CartItems[" + listdata[i].ToString() + "]"];
                                    if (reqCartItemCookies != null)
                                    {
                                        CartItem cookiesItem = new JavaScriptSerializer()
                                                                            .Deserialize<CartItem>(reqCartItemCookies.Value);

                                        // get Cart Item of this ProductDetailId in DB                                        
                                        int productDetailId = listdata[i];                                        
                                        CartDetailItem detail = await shoppingService
                                                        .getCartItemByCartIdAndProductDetailsId(cart.CartId, productDetailId);
                                        if (detail == null)
                                        {
                                            detail = new CartDetailItem();

                                            detail.UserId = UserManager.User.Id;
                                            detail.Amount = (short)cookiesItem.Amount;
                                            detail.ExtendedPrice = cookiesItem.Price;
                                            detail.Type = 0;
                                            detail.ProductDetailsId = listdata[i];
                                            detail.CartId = cart.CartId;
    
                                            // add into system
                                            await shoppingService.addCartItem(detail);
                                        }
                                        else
                                        {
                                            detail.Amount += (short)cookiesItem.Amount;

                                            // update into system
                                            await shoppingService.updateQuantity(detail);
                                        }

                                        /* remove cart item of this ProductDetailId in cookies */                                        
                                        var respCartItemscookies = new HttpCookie("CartItems[" + listdata[i].ToString() + "]");
                                        respCartItemscookies.Expires = DateTime.Now.AddDays(-1D);
                                        Response.Cookies.Add(respCartItemscookies);
                                    }
                                }

                                /* update productDetailID list in cookies */
                                HttpCookie respIDListCookies = new HttpCookie("ProductDetailIDlist", "")
                                {
                                    Expires = DateTime.Now.AddDays(1)
                                };
                                HttpContext.Response.Cookies.Add(respIDListCookies);
                            }
                        }
                    }

                    // Redirect to the secure area.
                    return RedirectToAction("Index", "Category");
                }
            }

            ViewBag.LoginFailure = 1;
            return View();
        }

        // GET
        public ActionResult Logout()
        {
            // Clear the user session and forms auth ticket.
            UserManager.Logoff(Session, Response);

            return RedirectToAction("Login", "User");
        }
    }
}
