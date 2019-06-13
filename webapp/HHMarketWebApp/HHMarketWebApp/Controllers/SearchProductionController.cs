
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Mvc;
using HHMarketWebApp.Models;
using HHMarketWebApp.Services;
using HHMarketWebApp.ViewModels;
using PagedList;

namespace HHMarketWebApp.Controllers
{
    public class SearchProductionController : Controller
    {

        [HttpPost]
        public async Task<ActionResult> Index(SearchModel model)
        {
            ProductionService service = new ProductionService();
            List<SearchProduction> searchProductionList = await service.search(model.CategoryId, model.Criteria);

            int pageSize = 16;
            int pageNumber = 1;
       
            return PartialView("Index", searchProductionList.ToPagedList(pageNumber, pageSize));        
        }



    }
}
