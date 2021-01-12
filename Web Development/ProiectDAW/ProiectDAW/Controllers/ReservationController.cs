using Microsoft.AspNet.Identity;
using ProiectDAW.Models;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;

namespace ProiectDAW.Controllers
{
    public class ReservationController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        [Authorize(Roles = "Admin,Booker")]
        public ActionResult Index()
        {
            List<Reservation> reservations = db.Reservations.Include("Trip").Include("User").ToList();
            List<Reservation> myReservations = new List<Reservation>();

            foreach(var res in reservations)
            {
                if (res.UserId == User.Identity.GetUserId())
                {
                    myReservations.Add(res);
                }
            }

            if (User.IsInRole("Admin"))
            {
                ViewBag.Reservations = reservations;
            }
            else
            {
                ViewBag.Reservations = myReservations;
            }

            return View();
        }

        [NonAction] 
        public IEnumerable<SelectListItem> GetAllTrips()
        {
            var selectList = new List<SelectListItem>();
            foreach (var trip in db.Trips.ToList())
            {
                selectList.Add(new SelectListItem
                {
                    Value = trip.TripId.ToString(),
                    Text = trip.Nume
                });
            }

            return selectList;
        }

        [HttpGet]
        [Authorize(Roles = "Admin,Booker")]
        public ActionResult New()
        {
            Reservation reservation = new Reservation();
            reservation.TripsList = GetAllTrips();
            reservation.DataRezervare = DateTime.UtcNow.Date.ToString("dd/MM/yyyy");
            reservation.UserId = User.Identity.GetUserId();

            return View(reservation);
        }

        [HttpPost]
        [Authorize(Roles = "Admin,Booker")]
        public ActionResult New(Reservation reservationRequest)
        {
            try
            {
                reservationRequest.TripsList = GetAllTrips();
                reservationRequest.UserId = User.Identity.GetUserId();
                reservationRequest.DataRezervare = DateTime.UtcNow.Date.ToString("dd/MM/yyyy");

                db.Reservations.Add(reservationRequest);
                db.SaveChanges();

                if (User.IsInRole("Booker"))
                {
                    return RedirectToAction("Index", "Trip");
                }
                else
                {
                    return RedirectToAction("Index");
                }

            }
            catch (Exception e)
            {
                return View(reservationRequest);
            }
        }

        [HttpGet]
        [Authorize(Roles = "Admin,Booker")]
        public ActionResult Edit(int? id)
        {
            if (id.HasValue)
            {
                Reservation reservation = db.Reservations.Find(id);
                if (reservation == null)
                {
                    return HttpNotFound("Couldn't find the reservation with id " + id.ToString());
                }
                reservation.TripsList = GetAllTrips();
                return View(reservation);
            }
            return HttpNotFound("Missing reservation id parameter!");
        }

        [Authorize(Roles = "Admin,Booker")]
        [HttpPut]
        public ActionResult Edit(int id, Reservation reservationRequest)
        {
            try
            {
                reservationRequest.TripsList = GetAllTrips();

                Reservation reservation = db.Reservations.Include("Trip").Include("User").SingleOrDefault(b => b.ReservationId.Equals(id));

                reservation.DataRezervare = DateTime.UtcNow.Date.ToString("dd/MM/yyyy");
                reservation.NumarPersoane = reservationRequest.NumarPersoane;
                reservation.NumeBooker = reservationRequest.NumeBooker;
                reservation.NumarTelefon = reservationRequest.NumarTelefon;
                reservation.UserId = User.Identity.GetUserId();
                reservation.TripId = reservationRequest.TripId;
                db.SaveChanges();

                return RedirectToAction("Index");

            }
            catch (Exception e)
            {
                return View(reservationRequest);
            }
        }

        [HttpDelete]
        [Authorize(Roles = "Admin,Booker")]
        public ActionResult Delete(int id)
        {
            Reservation reservation = db.Reservations.Find(id);
            if (reservation != null)
            {
                db.Reservations.Remove(reservation);
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return HttpNotFound("Couldn't find the reservation with id " + id.ToString());
        }
    }
}