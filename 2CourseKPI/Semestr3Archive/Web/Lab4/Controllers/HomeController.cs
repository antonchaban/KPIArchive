using Lab3.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Threading.Tasks;

namespace Lab3.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public async Task<ActionResult> Index(NotificationBarModel notificationBarModel)
        {
            return View(notificationBarModel);
        }
        

        [HttpGet]
        [Route("/savetext/{text}")]
        public async Task<ActionResult> SaveText(string text)
        {
            string fullFileName = "db.txt";
            StreamWriter streamWriter =
                new StreamWriter(fullFileName, append: true);
            streamWriter.WriteLine(text);
            streamWriter.Close();
            return NoContent();
        }

        [HttpGet]
        [Route("/gettext")]
        public async Task<string> GetText()
        {
            string fullFileName = "db.txt";
            StreamReader streamWriter =
                new StreamReader(fullFileName);
            var text = streamWriter.ReadToEnd();
            streamWriter.Close();
            return text;
        }


        [HttpGet]
        [Route("/clear")]
        public async Task Clear()
        {
            string fullFileName = "db.txt";
            StreamWriter s =
                new StreamWriter(fullFileName);
            s.Write("");
            s.Close();
        }

        public ActionResult Page2()
        {
            string fullFileName = "db.txt";
            StreamReader streamReader =
                new StreamReader(new FileStream(fullFileName, FileMode.Open, FileAccess.Read));
            var content = streamReader.ReadToEnd();
            streamReader.Close();
            return View(new NotificationBarModel()
            {
                Content = content
            });
        }
    }
}
