using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ProiectDAW.Models.ViewModels
{
    public class AssignedCategoryData
    {
        public int CategoryId { get; set; }

        public string Nume { get; set; }
        public bool Assigned { get; set; }
    }
}