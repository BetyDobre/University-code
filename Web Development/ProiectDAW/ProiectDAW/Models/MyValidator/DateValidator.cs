using ProiectDAW.Models.ViewModels;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace ProiectDAW.Models.MyValidator
{
    public class DateValidator : ValidationAttribute
    {

        private ValidationResult validateDate(string data_inceput, string endDate)
        {
            Boolean cond = true;

            if (endDate == null || data_inceput == null)
            {
                return new ValidationResult("Date is not valid!");
            }

            string[] parts = endDate.Split('/');

            if (int.Parse(parts[0]) > 31 || int.Parse(parts[1]) > 12 || int.Parse(parts[2]) < 2020)
            {
                cond = false;
            }

            string[] startDate = data_inceput.Split('/');

            if (int.Parse(startDate[2]) == int.Parse(parts[2]))
            {
                if (int.Parse(startDate[1]) == int.Parse(parts[1]))
                {
                    if (int.Parse(startDate[0]) > int.Parse(parts[0]))
                    {
                        cond = false;
                    }
                }
                else if (int.Parse(startDate[1]) > int.Parse(parts[1]))
                {
                    cond = false;
                }
            }
            else if (int.Parse(startDate[2]) > int.Parse(parts[2]))
            {
                cond = false;
            }

            return cond ? ValidationResult.Success : new ValidationResult("Date is not valid!");

        }

        protected override ValidationResult IsValid(object value, ValidationContext validationContext)
        {

            if (typeof(Period).IsInstanceOfType(validationContext.ObjectInstance))
            {
                var period = (Period)validationContext.ObjectInstance;
                return validateDate(period.Data_inceput, period.Data_sfarsit);
            }

            TripViewModel tp = (TripViewModel)validationContext.ObjectInstance;
            return validateDate(tp.Data_inceput, tp.Data_sfarsit);
        }
    }
}
