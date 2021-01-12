using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;
using ProiectDAW.Models;
using System;
using System.Data.Entity;
using System.Linq;
using System.Web.Mvc;

namespace ProiectDAW.Controllers
{
    [Authorize(Roles = "Admin")]
    public class UsersController : Controller
    {
        private ApplicationDbContext ctx = new ApplicationDbContext();
        public ActionResult Index()
        {
            ViewBag.UsersList = ctx.Users
            .OrderBy(u => u.UserName)
            .ToList();

            return View();
        }
        public ActionResult Details(string id)
        {
            if (String.IsNullOrEmpty(id))
            {
                return HttpNotFound("Missing the id parameter!");
            }
            ApplicationUser user = ctx.Users
            .Include("Roles")
            .FirstOrDefault(u => u.Id.Equals(id));
            if (user != null)
            {
                ViewBag.UserRole = ctx.Roles.Find(user.Roles.First().RoleId).Name;
                return View(user);
            }
            return HttpNotFound("Cloudn't find the user with given id!");
        }
        public ActionResult Edit(string id)
        {
            if (String.IsNullOrEmpty(id))
            {
                return HttpNotFound("Missing the id parameter!");
            }
            UserViewModel uvm = new UserViewModel();
            uvm.User = ctx.Users.Find(id);
            IdentityRole userRole = ctx.Roles.Find(uvm.User.Roles.First().RoleId);
            if (userRole.Name.Equals(""))
            {
                RedirectToAction("Edit");
            }
            else
            {
                uvm.RoleName = userRole.Name;
                RedirectToAction("Index");
            }
            return View(uvm);
        }
        [HttpPut]
        public ActionResult Edit(string id, UserViewModel uvm)
        {
            ApplicationUser user = ctx.Users.Find(id);
            try
            {
                if (TryUpdateModel(user))
                {
                    var um = new UserManager<ApplicationUser>(new UserStore<ApplicationUser>(ctx));
                    foreach (var r in ctx.Roles.ToList())
                    {
                        um.RemoveFromRole(user.Id, r.Name);
                    }
                    um.AddToRole(user.Id, uvm.RoleName);
                    ctx.SaveChanges();
                }
                return RedirectToAction("Index");
            }
            catch (Exception e)
            {
                return View(uvm);
            }
        }

        [HttpDelete]
        public ActionResult Delete(string id)
        {
            ApplicationUser user = ctx.Users.Find(id);
            if (user != null)
            {
                ctx.Users.Remove(user);
                ctx.SaveChanges();
                return RedirectToAction("Index");
            }
            return HttpNotFound("Couldn't find the user with id " + id);
        }
    }

}