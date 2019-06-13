using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using HHMarketWebApp.Models;
using HHMarketWebApp.Services;
using PagedList;

namespace HHMarketWebApp.Controllers
{
    public class CategoryController : Controller
    {
        // GET: Category
        public async Task<ActionResult> Index(int? page)
        {
            // get all categories in system
            ProductionService service = new ProductionService();
            List<Category> categories = await service.getAllCategories();

            int pageSize = 8;
            int pageNumber = (page ?? 1);

            return View(categories.ToPagedList(pageNumber, pageSize));

        }
    }
}