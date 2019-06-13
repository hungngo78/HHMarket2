
using System.Web.Mvc;
using HHMarketWebApp.Models;
using FormsAuth;
using System.Web;
using System.Linq;
using HHMarketWebApp.Services;
using System.Threading.Tasks;

namespace HHMarketWebApp.Controllers
{
    public class RegistrationController : Controller
    {
        private DBModelContainer db = new DBModelContainer();

        //GET
        public ActionResult Index()
        {
             return View();
        }

        //GET
        public async Task<ActionResult> Edit()
        {
            if (UserManager.User != null)
            {
                if (ModelState.IsValid)
                {
                    AccountService service = new AccountService();

                    // get user info 
                    User user = await service.getUserbyUserId(UserManager.User.Id);

                    return View("Index", user);
                }
            }

            return RedirectToAction("Index", "Category");
        }

        // POST 
        [HttpPost]       
        public async Task<ActionResult> Update([Bind(Include = "UserName,Password,Email, City, FirstName, LastName, State,Zipcode, Address")] User _user)
        {
            if (ModelState.IsValid)
            {
                AccountService service = new AccountService();

                if (UserManager.User != null)
                {
                    /*UserName
                    Password,
                    Email,
                    City,
                    FirstName,
                    LastName,
                    State,
                    ZipCode,
                    Address*/
                    // update user info
                    _user.UserId = UserManager.User.Id;
                    await service.updateUser(_user);

                    ModelState.Clear();

                    return RedirectToAction("Index", "Category");
                }
                else
                {
                    // add new user
                    User user = await service.addUser(_user);

                    // let that newly added user loged in automatically
                    Logon logon = new Logon();
                    logon.Username = user.UserName;
                    logon.Password = user.Password;
                    if (user.UserId > 0 && UserManager.ValidateUser(logon, Response))
                    {
                        ModelState.Clear();
                        return RedirectToAction("Index", "Category");
                    }
                }

                ModelState.Clear();
            }

            ViewBag.RegisterFailure = 1;    // errors ocured 
            return View("Index", _user);
        } 
    }
}
