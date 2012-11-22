using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CASIA_DB_Reader
{
    public class Stroke
    {
        public List<point> points { get; set; }

        public Stroke() {
            points = new List<point>();
        }
    }
}
