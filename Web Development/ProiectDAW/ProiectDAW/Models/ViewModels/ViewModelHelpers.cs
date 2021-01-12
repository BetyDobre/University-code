using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ProiectDAW.Models.ViewModels
{
    public static class ViewModelHelpers
    {
        public static TripViewModel ToViewModel(this TripViewModel trip)
        {
            var tripViewModel = new TripViewModel
            {
                Nume = trip.Nume,
                TripId = trip.TripId,
                Locatie = trip.Locatie,
                Descriere = trip.Descriere,
                Pret = trip.Pret,
                PeriodId = trip.PeriodId,
                Data_inceput = trip.Data_inceput,
                Data_sfarsit = trip.Data_sfarsit
            };

            foreach (var category in trip.Categories)
            {
                tripViewModel.Categories.Add(new AssignedCategoryData
                {
                    CategoryId = category.CategoryId,
                    Nume= category.Nume,
                    Assigned = true
                });
            }

            return tripViewModel;
        }

        public static TripViewModel ToViewModel(this Trip trip, ICollection<Category> allDbCategories)
        {
            var tripViewModel = new TripViewModel
            {
                Nume = trip.Nume,
                TripId = trip.TripId,
                Locatie = trip.Locatie,
                Descriere = trip.Descriere,
                Pret = trip.Pret,
                PeriodId = trip.Period.PeriodId,
                Data_inceput = trip.Period.Data_inceput,
                Data_sfarsit = trip.Period.Data_sfarsit
            };

            ICollection<AssignedCategoryData> allCategories = new List<AssignedCategoryData>();
            foreach (var c in allDbCategories)
            {
                var assignedCategory = new AssignedCategoryData
                {
                    CategoryId = c.CategoryId,
                    Nume = c.Nume,
                    Assigned = trip.Categories.FirstOrDefault(x => x.CategoryId == c.CategoryId) != null
                };
                allCategories.Add(assignedCategory);
            }

            tripViewModel.Categories = allCategories;

            return tripViewModel;
        }

        public static Trip ToDomainModel( this TripViewModel tripViewModel)
        {
            var trip = new Trip();
            trip.Nume = tripViewModel.Nume;
            trip.TripId = tripViewModel.TripId;
            trip.Locatie = tripViewModel.Locatie;
            trip.Descriere = tripViewModel.Descriere;
            trip.Pret = tripViewModel.Pret;
            trip.Period = new Period { PeriodId = tripViewModel.PeriodId, Data_inceput = tripViewModel.Data_inceput, Data_sfarsit = tripViewModel.Data_sfarsit };

            return trip;
        }

    }
}