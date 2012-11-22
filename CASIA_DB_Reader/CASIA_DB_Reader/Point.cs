using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CASIA_DB_Reader
{
    /// <summary>
    /// 坐标类，用于在线识别
    /// </summary>
    public class point
    {
        public short x { get; set; }
        public short y { get; set; }

        public point()
        {
        }

        public point(short x, short y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
