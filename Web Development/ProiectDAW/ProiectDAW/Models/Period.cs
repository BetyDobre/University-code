using ProiectDAW.Models.MyValidator;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace ProiectDAW.Models
{
    public class Period
    {
        [Required]
        public int PeriodId { get; set; }

        [Required, RegularExpression(@"(((0|1)[0-9]|2[0-9]|3[0-1])\/(0[1-9]|1[0-2])\/((19|20)\d\d))$", ErrorMessage = "Not a valid date!")]
        public string Data_inceput { get; set; }    
        
        [Required, DateValidator]
        public string Data_sfarsit { get; set; }

        //one to one
        public virtual Trip Trip { get; set; }
    }

}