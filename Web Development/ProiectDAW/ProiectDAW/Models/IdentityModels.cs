using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Security.Claims;
using System.Threading.Tasks;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;

namespace ProiectDAW.Models
{
    // You can add profile data for the user by adding more properties to your ApplicationUser class, please visit https://go.microsoft.com/fwlink/?LinkID=317594 to learn more.
    public class ApplicationUser : IdentityUser
    {
        public async Task<ClaimsIdentity> GenerateUserIdentityAsync(UserManager<ApplicationUser> manager)
        {
            // Note the authenticationType must match the one defined in CookieAuthenticationOptions.AuthenticationType
            var userIdentity = await manager.CreateIdentityAsync(this, DefaultAuthenticationTypes.ApplicationCookie);
            // Add custom user claims here
            return userIdentity;
        }

        //many to one
        public virtual ICollection<Reservation> Revervations { get; set; }
    }

    public class ApplicationDbContext : IdentityDbContext<ApplicationUser>
    {
        private string v;

        public ApplicationDbContext()
            : base("DefaultConnection", throwIfV1Schema: false)
        {
            Database.SetInitializer<ApplicationDbContext>(new Initp());
        }

        public ApplicationDbContext(string v)
        {
            this.v = v;
        }

        public DbSet<Trip> Trips { get; set; }
        public DbSet<Period> Periods { get; set; }
        public DbSet<Category> Categories { get; set; }
        public DbSet<Reservation> Reservations { get; set; }

        public static ApplicationDbContext Create()
        {
            return new ApplicationDbContext();
        }

    }

    public class Initp : DropCreateDatabaseIfModelChanges<ApplicationDbContext>
    {
        protected override void Seed(ApplicationDbContext ctx)
        {

            ctx.Categories.Add(new Category { Nume = "Estivale" });
            ctx.Categories.Add(new Category { Nume = "Croaziere" });


            ctx.Trips.Add(new Trip { Nume = "Excursie de iarna", Locatie = "Busteni", Descriere = "Ski si snowboarding", Pret = 300,
                Categories = new List<Category> {
                    new Category { Nume = "Montane"},
                    new Category { Nume = "Sporturi iarna"}
                },
                Period = new Period { Data_inceput = "10/10/2021", Data_sfarsit = "11/10/2021"}
            });

            ctx.Reservations.Add(new Reservation
            {
                NumeBooker = "Popescu Andreea",
                NumarPersoane = 2,
                NumarTelefon = "0754321784",
                DataRezervare = "22/02/2022",
                Trip = new Trip
                {
                    Nume = "Circuit Transilvania",
                    Locatie = "Transilvania",
                    Descriere = "Vizitarea obiectivelor principale din zona Transilvaniei: Sibiu, Catelul Huniazilor, Alba-Iulia, Cetatea Devei etc.",
                    Pret = 400,
                    Categories = new List<Category> {
                                    new Category { Nume = "Turistice"},
                                    new Category { Nume = "Istorice"}
                                },
                    Period = new Period { Data_inceput = "10/08/2021", Data_sfarsit = "18/08/2021" }
                }
            });

            ctx.SaveChanges();

            base.Seed(ctx);
        }
    }
}