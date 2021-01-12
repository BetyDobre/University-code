using ProiectDAW.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ProiectDAW.Controllers
{
    [Authorize(Roles = "Admin")]
    public class PeriodController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        public ActionResult Index()
        {
            List<Period> periods = db.Periods.ToList();
            ViewBag.Periods = periods;

            return View();
        }

        [HttpGet]
        public ActionResult Edit(int? id)
        {
            if (id.HasValue)
            {
                Period period = db.Periods.Find(id);
                if (period == null)
                {
                    return HttpNotFound("Couldn't find the period with id " + id.ToString());
                }
                return View(period);
            }
            return HttpNotFound("Missing period id parameter!");
        }

        [HttpPut]
        public ActionResult Edit (int id, Period periodRequest)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    Period period = db.Periods.SingleOrDefault(p => p.PeriodId.Equals(id));

                    if (TryUpdateModel(period))
                    {
                        period.Data_inceput = periodRequest.Data_inceput;
                        period.Data_sfarsit = periodRequest.Data_sfarsit;
                        db.SaveChanges();
                    }
                    return RedirectToAction("Index");
                }
                return View(periodRequest);
            }
            catch(Exception e)
            {
                return View(periodRequest);
            }
        }

        [HttpDelete]
        public ActionResult Delete(int id)
        {
            Period period = db.Periods.Find(id);
            Trip trip = db.Trips.Find(period.Trip.TripId);
            if (period != null)
            {
                db.Periods.Remove(period);
                db.Trips.Remove(trip);
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return HttpNotFound("Couldn't find the period with id " + id.ToString());
        }
    }
}