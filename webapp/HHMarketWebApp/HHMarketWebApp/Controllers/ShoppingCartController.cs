using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using System.Web.Script.Serialization;
using FormsAuth;
using HHMarketWebApp.Models;
using HHMarketWebApp.Services;
using HHMarketWebApp.ViewModels;
using PagedList;

namespace HHMarketWebApp.Controllers
{
    public class ShoppingCartController : Controller
    {
        // Get
        public async Task<ActionResult> Index()
        {
            ShoppingService service = new ShoppingService();
            ProductionService productService = new ProductionService();
            ShoppingCart shoppingCart = new ShoppingCart();

            if (FormsAuth.UserManager.User != null)
            {
                /*{
                    Amount,
                    CartId,
                    Type,
                    CartDetailsId,
                    ExtendedPrice,
                    ProductDetailsId,
                    Price,
                    Picture,
                    ProductName,
                    ProductID,
                    Color,
                    UserId,
                    TotalAmountProduction
                }*/
                // get all shopping cart items of current user
                shoppingCart.listItemCart = await service.getCartItemsByUserId(UserManager.User.Id);
            }
            else  // get from Cookie
            {
                HttpCookie reqCookies;
                HttpCookie reqIDListCookies = Request.Cookies["ProductDetailIDlist"];
                if (reqIDListCookies != null)
                {                   
                    string dataAsString = reqIDListCookies.Value;
                    if (!dataAsString.Equals(string.Empty))
                    {
                        List<int> listdata = new List<int>();
                        List<CartItem> listCartItem = new List<CartItem>();

                        listdata = dataAsString.Split(',').Select(x => Convert.ToInt32(x)).ToList();
                        for (int i = 0; i < listdata.Count(); i++)
                        {
                            CartItem item = new CartItem();
                            item.ProductDetailsId = listdata[i];

                            reqCookies = Request.Cookies["CartItems[" + item.ProductDetailsId.ToString() + "]"];
                            if (reqCookies != null)
                            {
                                CartItem cookiesItem = new JavaScriptSerializer().Deserialize<CartItem>(reqCookies.Value);

                                item.Amount = cookiesItem.Amount;
                                item.Price = cookiesItem.Price;

                                listCartItem.Add(item);
                            }
                        }

                        // 
                        if (listCartItem.Count() > 0)
                        {
                            List<CartDetailItem> cartDetailItemList = new List<CartDetailItem>();

                            // for each cookies item
                            foreach (CartItem cookiesItem in listCartItem)
                            {
                                CartDetailItem cartDetailItem = new CartDetailItem();

                                // get more information from product & productDetails tables in Database
                                ProductionDetail productDetail = await productService.getProductDetailByProductDetailsIds(cookiesItem.ProductDetailsId);
                                Production product = await productService.getProductionByProductDetailsId(cookiesItem.ProductDetailsId);

                                cartDetailItem.ProductDetailsId = cookiesItem.ProductDetailsId;
                                cartDetailItem.Price = productDetail.Price;
                                cartDetailItem.Picture = productDetail.Picture;
                                cartDetailItem.Color = productDetail.Color;
                                cartDetailItem.TotalAmountProduction = productDetail.Amount;
                                cartDetailItem.Amount = cookiesItem.Amount;
                                cartDetailItem.ExtendedPrice = cookiesItem.Price;
                                cartDetailItem.CartId = 0;
                                cartDetailItem.Type = 0;
                                cartDetailItem.CartDetailsId = 0;
                                cartDetailItem.ProductID = product.ProductId;
                                cartDetailItem.ProductName = product.ProductionName;
                                cartDetailItem.UserId = 0;
                                
                                cartDetailItemList.Add(cartDetailItem);
                            }

                            shoppingCart.listItemCart = cartDetailItemList;
                        }
                    }
                }
            }

            return View(shoppingCart);
        }

        [HttpPost]
        public async Task<ActionResult> AddToCart(CartItem item)
        {
            if (ModelState.IsValid)
            {       
                if (UserManager.User != null)  // If user has loged in already
                {
                    ShoppingService service = new ShoppingService();

                    CartDetailItem cartDetailItem = new CartDetailItem();
                    cartDetailItem.UserId = UserManager.User.Id;
                    cartDetailItem.ProductDetailsId = item.ProductDetailsId;
                    cartDetailItem.Amount = 1;
                    cartDetailItem.ExtendedPrice = item.Price;
                    cartDetailItem.Type = 0;

                    /* add cart item into system */
                    cartDetailItem = await service.addCartItem(cartDetailItem);

                    if (cartDetailItem == null)
                    {
                        return this.Json(new
                        {
                            EnableError = true,
                            ErrorTitle = "Error",
                            ErrorMsg = "Something goes wrong, please try again later"
                        });
                    }
                }
                else     // user hasn't loged in 
                {
                    // get tmp Cart in cookie
                    string dateOfOpen = string.Empty;
                    HttpCookie reqCookies = Request.Cookies["CartInfo"];
                    if (reqCookies != null)  // there is a temporary Cart in cookies 
                    {
                        dateOfOpen = reqCookies["DateOfOpen"].ToString();

                        /* save Cart Item in cookie */
                        // read saved cart item for given ProductDetailId
                        HttpCookie reqCartItemCookies = Request.Cookies["CartItems["+ item.ProductDetailsId.ToString() + "]"];  // cartItem from request
                        HttpCookie respCartItemCookie;  // cartItem in response
                        if (reqCartItemCookies != null)
                        {
                            CartItem mItem = new JavaScriptSerializer().Deserialize<CartItem>(reqCartItemCookies.Value);
                            mItem.Amount = (short)(mItem.Amount + item.Amount);
                            string myObjectJson = new JavaScriptSerializer().Serialize(mItem);
                            respCartItemCookie = new HttpCookie("CartItems[" + item.ProductDetailsId.ToString() + "]", myObjectJson)
                            {
                                Expires = DateTime.Now.AddDays(1)
                            };
                        }
                        else
                        {
                            item.Amount = 1;
                            string myObjectJson = new JavaScriptSerializer().Serialize(item);
                            respCartItemCookie = new HttpCookie("CartItems[" + item.ProductDetailsId + "]", myObjectJson)
                            {
                                Expires = DateTime.Now.AddDays(1)
                            };
                        }
                        HttpContext.Response.Cookies.Add(respCartItemCookie);

                        /* add productDetailID in cookies */
                        HttpCookie reqIDListCookies = Request.Cookies["ProductDetailIDlist"];
                        if (reqIDListCookies != null)
                        {
                            string yourListString = string.Empty;
                            string dataAsString = reqIDListCookies.Value;
                            List<int> listdata = new List<int>();
                            if (!dataAsString.Equals(string.Empty))
                            {
                                listdata = dataAsString.Split(',').Select(x => Convert.ToInt32(x)).ToList();
                            }
                            if (!listdata.Contains(item.ProductDetailsId))
                            {
                                listdata.Add(item.ProductDetailsId);
                            }
                            // Stringify your list
                            yourListString = String.Join(",", listdata);
                            HttpCookie IDListCookies = new HttpCookie("ProductDetailIDlist", yourListString)
                            {
                                Expires = DateTime.Now.AddDays(1)
                            };
                            HttpContext.Response.Cookies.Add(IDListCookies);
                        }
                        else
                        {
                            List<int> listdata = new List<int>();
                            listdata.Add(item.ProductDetailsId);

                            // Stringify your list
                            var yourListString = String.Join(",", listdata);
                            HttpCookie IDListCookies = new HttpCookie("ProductDetailIDlist", yourListString)
                            {
                                Expires = DateTime.Now.AddDays(1)
                            };
                            HttpContext.Response.Cookies.Add(IDListCookies);
                        }
                    }
                    else  // There is not any tmp Shopping Cart for this user 
                    {
                        // create new Shopping Cart in Cookie                   
                        HttpCookie cartCookie = new HttpCookie("CartInfo")
                        {
                            Expires = DateTime.Now.AddYears(1)
                        };
                        cartCookie["DateOfOpen"] = DateTime.Now.ToString();
                        HttpContext.Response.Cookies.Add(cartCookie);

                        // save Cart Item in cookie
                        item.Amount = 1;
                        string myObjectJson = new JavaScriptSerializer().Serialize(item);
                        HttpCookie cartItemCookie = new HttpCookie("CartItems[" + item.ProductDetailsId + "]", myObjectJson)
                        {
                            Expires = DateTime.Now.AddDays(1)
                        };
                        HttpContext.Response.Cookies.Add(cartItemCookie);

                        /* add productDetailID into cookies */
                        List<int> listdata = new List<int>();
                        listdata.Add(item.ProductDetailsId);
                        // Stringify your list
                        var yourListString = String.Join(",", listdata);
                        HttpCookie IDListCookies = new HttpCookie("ProductDetailIDlist", yourListString)
                        {
                            Expires = DateTime.Now.AddDays(1)
                        };
                        HttpContext.Response.Cookies.Add(IDListCookies);
                    }
                }
                    
                ModelState.Clear();
                return this.Json(new
                {
                    EnableSuccess = true,
                    SuccessTitle = "Successful!",
                    SuccessMsg = "Add cart successfully order!"
                });
            }

            return this.Json(new
            {
                EnableError = true,
                ErrorTitle = "Error",
                ErrorMsg = "Something goes wrong, please try again later"
            });

        }

        [HttpPost]
        public async Task<ActionResult> UpdateQuantity(CartDetailItem model)
        {
            if (ModelState.IsValid)
            {
                ShoppingService service = new ShoppingService();
                CartDetailItem cartDetailItem = await service.updateQuantity(model);

                ModelState.Clear();
                if (cartDetailItem != null)
                {
                    return this.Json(new
                    {
                        EnableSuccess = true,
                        SuccessTitle = "Successful!",
                        SuccessMsg = "Thank you so much for your order!"
                    });
                }
            }

            return this.Json(new
            {
                EnableError = true,
                ErrorTitle = "Error",
                ErrorMsg = "Something goes wrong, please try again later"
            });
        }        

        [HttpPost]
        public async Task<ActionResult> RemoveCartItem(CartDetailItem model)
        {
            if (ModelState.IsValid)
            {
                if (FormsAuth.UserManager.User != null)
                {                 
                    ShoppingService service = new ShoppingService();
                    model.Amount = 0;
                    CartDetailItem cartDetailItem = await service.updateQuantity(model);

                    if (cartDetailItem == null)
                    {
                        return this.Json(new
                        {
                            EnableError = true,
                            ErrorTitle = "Error",
                            ErrorMsg = "Something goes wrong, please try again later"
                        });
                    }
                }
                else
                {
                    /* remove cart item of this ProductDetailId in cookies */
                    HttpCookie reqCartItemCookies = Request.Cookies["CartItems[" + model.ProductDetailsId.ToString() + "]"]; 
                    if (reqCartItemCookies != null)
                    {
                        var c = new HttpCookie("CartItems[" + model.ProductDetailsId.ToString() + "]");
                        c.Expires = DateTime.Now.AddDays(-1D);
                        Response.Cookies.Add(c);     
                    }

                    /* update productDetailID list in cookies */
                    HttpCookie reqIDListCookies = Request.Cookies["ProductDetailIDlist"];
                    if (reqIDListCookies != null)
                    {
                        string dataAsString = reqIDListCookies.Value;
                        List<int> listdata = new List<int>();
                        listdata = dataAsString.Split(',').Select(x => Convert.ToInt32(x)).ToList();
                        listdata.Remove(model.ProductDetailsId);

                        // Stringify your list
                        var yourListString = String.Join(",", listdata);

                        HttpCookie IDListCookies = new HttpCookie("ProductDetailIDlist", yourListString)
                        {
                            Expires = DateTime.Now.AddDays(1)
                        };
                        HttpContext.Response.Cookies.Add(IDListCookies);
                    }
                }

                ModelState.Clear();
                return this.Json(new
                {
                    EnableSuccess = true,
                    SuccessTitle = "Successful!",
                    SuccessMsg = "Thank you so much for your order!"
                });

            }

            return this.Json(new
            {
                EnableError = true,
                ErrorTitle = "Error",
                ErrorMsg = "Something goes wrong, please try again later"
            });
        }

        [HttpPost]
        public async Task<ActionResult> Order(CartDetailItem model)
        {
            if (UserManager.User != null)
            {
                if (ModelState.IsValid)
                {                    
                    ShoppingService service = new ShoppingService();
                    Order order = await service.order(UserManager.User.Id);
                    
                    ModelState.Clear();

                    if (order != null)
                    {
                        return this.Json(new
                        {
                            ResponseType = 0,
                            Msg = "Thank you so much for your order!"
                        });
                    }
                }

                return this.Json(new
                {
                    ResponseType = 1,
                    Msg = "Something goes wrong, please try again later"
                });
            }

            return this.Json(new
            {
                ResponseType = Config.NEED_LOGIN,
                Msg = "Please login first"
            });
        }
    }
}

