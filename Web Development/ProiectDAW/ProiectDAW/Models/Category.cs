using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace ProiectDAW.Models
{
    public class Category
    {
        [Required]
        public int CategoryId { get; set; }

        [Required, MinLength(4, ErrorMessage = "Category name cannot be less than 4!"), MaxLength(30, ErrorMessage = "Category name too long!")]
        public string Nume { get; set; }

        //many ot many
        public virtual ICollection<Trip> Trips { get; set; }

    }
}