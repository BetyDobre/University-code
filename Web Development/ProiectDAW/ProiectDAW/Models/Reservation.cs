using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ProiectDAW.Models
{
    public class Reservation
    {
        [Required]
        public int ReservationId { get; set; }

        [Required, RegularExpression(@"(((0|1)[0-9]|2[0-9]|3[0-1])\/(0[1-9]|1[0-2])\/((19|20)\d\d))$", ErrorMessage = "Not a valid date!")]
        public string DataRezervare { get; set; }

        [Required, RegularExpression(@"(1|2|3)$")]
        public int NumarPersoane { get; set; }

        [Required, MinLength(2, ErrorMessage = "Name cannot be less than 2!"), MaxLength(50, ErrorMessage = "Name too long!")]
        public string NumeBooker { get; set; }

        [Required, RegularExpression(@"^07(\d{8})$", ErrorMessage = "This is not a valid phone number!")]
        public string NumarTelefon { get; set; }

        //one to many
        public int TripId { get; set; }
        public virtual Trip Trip { get; set; }

        [NotMapped]
        public IEnumerable<SelectListItem> TripsList { get; set; }

        //one to many
        public string UserId { get; set; }
        public virtual ApplicationUser User { get; set; }

    }
}