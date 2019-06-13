using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using FormsAuth;
using HHMarketWebApp.Models;
using HHMarketWebApp.Services;

namespace HHMarketWebApp.Controllers
{
    public class MyLayoutPageController : Controller
    {
        // GET: MySearch
        public ActionResult Index()
        {
            // get 20 first categories for search combobox
            ProductionService service = new ProductionService();
            List<Category> categories =  Task.Run(() => service.getAllCategories()).Result;
            categories = categories.OrderBy(c => c.CategoryId).Take(20).ToList();

            ViewBag.categories = categories;
            return PartialView();
        }

        public ActionResult CartIcon()
        {
            int cartItemNumber = 0;

            if (FormsAuth.UserManager.User != null)  // user has loged in already
            {
                ShoppingService service = new ShoppingService();
                cartItemNumber = service.getCartItemNumber(UserManager.User.Id); 
            }
            else  // get from cookies
            {
                HttpCookie reqIDListCookies = Request.Cookies["ProductDetailIDlist"];
                if (reqIDListCookies != null)
                {
                    string dataAsString = reqIDListCookies.Value;
                    if (!dataAsString.Equals(string.Empty))
                    {
                        List<int> listdata = new List<int>();
                        listdata = dataAsString.Split(',').Select(x => Convert.ToInt32(x)).ToList();

                        cartItemNumber = listdata.Count();
                    }
                }
            }

            ViewBag.cartItemNumber = cartItemNumber;
            return PartialView();
        }
    }
}