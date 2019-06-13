using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using System.Web.Mvc;
using HHMarketWebApp.Models;
using FormsAuth;
using PagedList;
using HHMarketWebApp.ViewModels;
using HHMarketWebApp.Services;

namespace HHMarketWebApp.Controllers
{
    public class ProductionController : Controller
    {
        public async Task<ActionResult> Index(int categoryId, int? page)
        {
            DBModelContainer db = new DBModelContainer();
            ProductionService service = new ProductionService();

            // get all products of a given category
            List<Production> prs = await service.getProductsByCategoryId(categoryId);
            if (prs != null && prs.Count > 0)
            {
                int pageSize = 16;
                int pageNumber = (page ?? 1);

                return View(prs.ToPagedList(pageNumber, pageSize));
            } 

            return RedirectToAction("Index", "Category");
        }

        public async Task<ActionResult> ProductDetail(int id)
        {
            DBModelContainer db = new DBModelContainer();
            ProductionDetail pr = new ProductionDetail();
            pr.ProductId = id;

            // get product title equal to CategoryName + " > " + names.ProductName;
            ProductionService service = new ProductionService();
            pr.Title = (await service.getProductTitleByProductId(id)).Title;

            /*{
                Color,
                Description,
                Price,
                Size,
                Amount,
                Picture,
                ProductDetailsId,
                ProductId,
                ProductionName
            }*/
            // get all productDetails by a given product
            pr.listdata = await service.getProductDetailsByProductId(id);

            /*{
                ProductId,
           
                Title,
                Content,
                OverallRating,

                ReviewId,
                UserId,
                UserName,
                ReviewDate
            }*/
            // get all reviews about a given product                                  
            pr.reviewListData = await service.getReviewsByProductId(id);

            return View(pr);
        }

        public async Task<ActionResult> ReviewProduction(int id)
        {
            ProductionService service = new ProductionService();
            ReviewDetails reviewDetails = new ReviewDetails();

            /*{
                ProductId,
                ProductionName,
                MinPrice,
                MaxPrice,
                Picture,
                Color
            }*/
            // get product info by productId
            reviewDetails.product = await service.getProductionByProductId(id);

            /*
            {
                ReviewId,
                Title,
                Content,
                OverallRating,
                UserName,
                ReviewDate,
            }*/
            // get all reviews about a product
            List<ReviewProduction> reviewList = await service.getReviewsByProductId(id);
            reviewDetails.reviewList = reviewList;


            /*
            rating.oneStarReviewNumber = reviewNumber.Count;
            rating.twoStarReviewNumber = reviewNumber.Count;
            rating.threeStarReviewNumber = reviewNumber.Count;
            rating.fourStarReviewNumber = reviewNumber.Count;
            rating.fiveStarReviewNumber = reviewNumber.Count;
 
            rating.oneStarReviewPercent
            rating.twoStarReviewPercent
            rating.threeStarReviewPercent
            rating.fourStarReviewPercent
            rating.fiveStarReviewPercent
         
            rating.overrallRating */
            // get all rating information of a product 
            reviewDetails.rating = await service.getRatingByProductId(id);  

            // render View
            return View(reviewDetails);
        }

        [HttpPost]
        //public ActionResult AddNew([Bind(Include = "OverallRating,Title,Content")] ReviewProduction1 model)
        public async Task<ActionResult> AddNewReview(ReviewProduction model)
        {
            if (UserManager.User != null)
            {
                if (ModelState.IsValid)
                {
                    ProductionService service = new ProductionService();

                    // add new review of a product
                    model.UserId = UserManager.User.Id;
                    await service.addReview(model);

                    /*{
                        ReviewId,
                        Title,
                        Content,
                        OverallRating,
                        UserName,
                        ReviewDate,
                    }*/
                    // get all reviews of a product                                                        
                    List<ReviewProduction> reviewList = await service.getReviewsByProductId(model.ProductId);

                    return PartialView("_PartialPage_ReviewList", reviewList);
                }

                return this.Json(new
                {
                    ResponseType = Config.SOMETHING_WRONG_WITH_POST_REQUEST,
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
