using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;
using System.Web.Helpers;
using System.Web.Mvc;

namespace ProiectDAW.Models
{
    public class Trip
    {     
        public Trip()
        {
            Categories = new List<Category>();
        }

        [ForeignKey("Period")]
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

        //many to many 
        public virtual ICollection<Category> Categories { get; set; }

        //many to one
        public virtual ICollection<Reservation> Reservations { get; set; }

        //one to one
        [Required]
        public virtual Period Period { get; set; }
    }
}