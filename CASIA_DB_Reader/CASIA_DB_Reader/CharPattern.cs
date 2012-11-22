using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;

namespace CASIA_DB_Reader
{
    public class CharPattern
    {
        public ushort sampleSize { get; set; }
        public string tagCode { get; set; }
        public ushort strokeNum { get; set; }
        public  List<Stroke> strokes{ get; set; }
        /// <summary>
        /// 是否已经获取边界
        /// </summary>
        public bool isBoundary { get; set; }
        /// <summary>
        /// 是否已经平移
        /// </summary>
        public bool isTranslational { get; set; }
        public bool isResize { get; set; }
        public Rectangle boundary { get; set; }

        public CharPattern()
        {
            strokes = new List<Stroke>();
            boundary = new Rectangle();
            isBoundary = false;
            isTranslational = false;
        }
    }
}
