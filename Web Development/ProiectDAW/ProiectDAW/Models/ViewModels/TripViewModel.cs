using ProiectDAW.Models.MyValidator;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ProiectDAW.Models.ViewModels
{
    public class TripViewModel
    {

        public TripViewModel()
        {
            Categories = new Collection<AssignedCategoryData>();
        }

        [Required]
        public int TripId { get; set; }

        [Required, MinLength(3, ErrorMessage = "Title cannot be less than 3!"), MaxLength(100, ErrorMessage = "Title too long!")]
        public string Nume { get; set; }

        [Required, MinLength(2, ErrorMessage = "Location cannot be less than 2!"), MaxLength(100, ErrorMessage = "Location too long!")]
        public string Locatie { get; set; }

        [Required, MaxLength(500, ErrorMessage = "Description too long!")]
        public string Descriere { get; set; }

        [Required]
        public int Pret { get; set; }
        public virtual ICollection<AssignedCategoryData> Categories { get; set; }

        //many to one
        public virtual ICollection<Reservation> Reservations { get; set; }

        [Required]
        public int PeriodId { get; set; }

        [Required, RegularExpression(@"(((0|1)[0-9]|2[0-9]|3[0-1])\/(0[1-9]|1[0-2])\/((19|20)\d\d))$", ErrorMessage = "Not a valid date!")]
        public string Data_inceput { get; set; }

        [Required, DateValidator]
        public string Data_sfarsit { get; set; }

    }
}