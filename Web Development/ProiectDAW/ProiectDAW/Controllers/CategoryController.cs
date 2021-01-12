using ProiectDAW.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ProiectDAW.Controllers
{
    [Authorize(Roles = "Admin")]
    public class CategoryController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        public ActionResult Index()
        {
            List<Category> categories = db.Categories.ToList();
            ViewBag.Categories = categories;

            return View();
        }

        public ActionResult New()
        {
            Category category = new Category();
            category.Trips = new List<Trip>();
            return View(category);
        }

        [HttpPost]
        public ActionResult New(Category CategoryRequest)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    db.Categories.Add(CategoryRequest);
                    db.SaveChanges();
                    return RedirectToAction("Index");
                }
                return View(CategoryRequest);
            }
            catch (Exception e)
            {
                return View(CategoryRequest);
            }
        }

        [HttpGet]
        public ActionResult Edit(int? id)
        {
            if (id.HasValue)
            {
                Category category = db.Categories.Find(id);
                if (category == null)
                {
                    return HttpNotFound("Couldn't find the category with id " + id.ToString());
                }
                return View(category);
            }
            return HttpNotFound("Missing Category id parameter!");
        }

        [HttpPut]
        public ActionResult Edit(int id, Category CategoryRequest)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    Category category = db.Categories.SingleOrDefault(p => p.CategoryId.Equals(id));

                    if (TryUpdateModel(category))
                    {
                        category.Nume = CategoryRequest.Nume;
                        db.SaveChanges();
                    }
                    return RedirectToAction("Index");
                }
                return View(CategoryRequest);
            }
            catch (Exception e)
            {
                return View(CategoryRequest);
            }
        }

        [HttpDelete]
        public ActionResult Delete(int id)
        {
            Category category = db.Categories.Find(id);
            if (category != null)
            {
                db.Categories.Remove(category);
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return HttpNotFound("Couldn't find the category with id " + id.ToString());
        }
    }

}