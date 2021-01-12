using ProiectDAW.Models;
using ProiectDAW.Models.ViewModels;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ProiectDAW.Controllers
{
    public class TripController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        public ActionResult Index()
        {

            List<Trip> trips = db.Trips.Include("Categories").Include("Period").ToList();
            ViewBag.Trips = trips;

            return View();
        }

        public ActionResult Details(int? id)
        {
            if (id.HasValue)
            {
                Trip trip = db.Trips.Find(id);
                if (trip != null)
                {
                    return View(trip);
                }
                return HttpNotFound("Couldn't find the trip with id " + id.ToString() + "!");
            }
            return HttpNotFound("Missing trip id parameter!");
        }

        [HttpGet]
        [Authorize(Roles = "Admin")]
        public ActionResult New()
        {
            var tripViewModel = new TripViewModel { Categories = PopulateCategoryData(),  Reservations = new List<Reservation>() };

            return View(tripViewModel);
        }

        [HttpPost]
        [Authorize(Roles = "Admin")]
        public ActionResult New(TripViewModel tripViewModel)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    Period period = new Period
                    {
                        Data_inceput = tripViewModel.Data_inceput,
                        Data_sfarsit = tripViewModel.Data_sfarsit
                    };
                    db.Periods.Add(period);
                    var trip = new Trip
                    {
                        Nume = tripViewModel.Nume,
                        Descriere = tripViewModel.Descriere,
                        Locatie = tripViewModel.Locatie,
                        Pret = tripViewModel.Pret,
                        Period = period
                    };

                    AddOrUpdateCategories(trip, tripViewModel.Categories);
                    db.Trips.Add(trip);
                    db.SaveChanges();

                    return RedirectToAction("Index");
                }
                return View(tripViewModel);
            }
            catch (Exception e)
            {
                return View(tripViewModel);
            }
        
        }

        private void AddOrUpdateCategories(Trip trip, IEnumerable<AssignedCategoryData> assignedCategories)
        {
            if (assignedCategories == null) return;

            if(trip.TripId != 0)
            {
                foreach (var category in trip.Categories.ToList())
                {
                    trip.Categories.Remove(category);
                }
                foreach (var category in assignedCategories.Where(c => c.Assigned))
                {
                    trip.Categories.Add(db.Categories.Find(category.CategoryId));
                }
            }
            else
            {
                foreach (var assignedCategory in assignedCategories.Where(c => c.Assigned))
                {
                    var category = new Category { CategoryId = assignedCategory.CategoryId };
                    db.Categories.Attach(category);
                    trip.Categories.Add(category);
                }
            }
        }

        private ICollection<AssignedCategoryData> PopulateCategoryData()
        {
            var categories = db.Categories;
            var assignedCategories = new List<AssignedCategoryData>();

            foreach (var item in categories)
            {
                assignedCategories.Add(new AssignedCategoryData
                {
                    CategoryId = item.CategoryId,
                    Nume = item.Nume,
                    Assigned = false
                });
            }
            return assignedCategories;
        }

        [HttpGet]
        [Authorize(Roles = "Admin")]
        public ActionResult Edit(int? id)
        {
            if (id.HasValue)
            {
                var allCategories = db.Categories.ToList();
                var trip = db.Trips.Include("Categories").Include("Period").FirstOrDefault(x => x.TripId == id);

                if (trip == null)
                {
                    return HttpNotFound("Couldn't find the trip with id " + id.ToString());
                }

                var tripViewModel = trip.ToViewModel(allCategories);

                return View(tripViewModel);
            }
            return HttpNotFound("Missing trip id parameter!");
        }

        [HttpPut]
        [Authorize(Roles = "Admin")]
        public ActionResult Edit(TripViewModel tripViewModel)
        {
            try
            {
                if (ModelState.IsValid)
                {

                    var originalTrip = db.Trips.Find(tripViewModel.TripId);

                    Period period = db.Periods.Find(originalTrip.Period.PeriodId);
                    period.Data_inceput = tripViewModel.Data_inceput;
                    period.Data_sfarsit = tripViewModel.Data_sfarsit;

                    AddOrUpdateCategories(originalTrip, tripViewModel.Categories);
                    db.Entry(originalTrip).CurrentValues.SetValues(tripViewModel);

                    db.SaveChanges();

                    return RedirectToAction("Index");
                }
                return View(tripViewModel);
            }
            catch (Exception e)
            {
                return View(tripViewModel);
            }
        }

        [HttpDelete]
        [Authorize(Roles = "Admin")]
        public ActionResult Delete(int id)
        {
            Trip trip = db.Trips.Find(id);
            Period period = db.Periods.Find(trip.Period.PeriodId);

            if (trip != null)
            {
                db.Trips.Remove(trip);
                db.Periods.Remove(period);
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return HttpNotFound("Couldn't find the trip with id " + id.ToString());
        }
    }
}
    
