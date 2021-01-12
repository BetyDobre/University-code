using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ProiectDAW.Models.ViewModels
{
    public class CategoryViewModel
    {
        public int CategoryId { get; set; }

        public string Nume { get; set; }

        //many ot many
        public virtual ICollection<TripViewModel> Trips { get; set; }

    }
}