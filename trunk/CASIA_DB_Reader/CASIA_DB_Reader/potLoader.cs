using System;
using System.IO;
using System.Text;
using System.Drawing;
using System.Collections.Generic;

namespace CASIA_DB_Reader
{
    class potLoader : DBLoader
    {
        private long length;
        private FileStream fs;
        private BinaryReader br;
        private short sampleSize;
        private short storkNum;
        private CharPattern pat;
        private Encoding gb2312;
        /// <summary>
        /// 
        /// </summary>
        public potLoader()
        {
            pat = new CharPattern();
            gb2312 = Encoding.GetEncoding("gb2312");
        }

        public override bool load(String fileName)
        {
            fs = FileManager.open(fileName);
            if (fs == null)
            {
                return false;
            }
            this.length = fs.Length;
            br = new BinaryReader(fs);
            return true;
        }


        public override CharPattern next()
        {
            CharPattern pat = new CharPattern();
            if (fs.Position >= length)
            {
                return null;
            }
            sampleSize = br.ReadInt16();
            br.ReadBytes(4);
            pat.strokeNum = (ushort)br.ReadInt16();
            Stroke stroke = new Stroke();
            short x, y;
            do
            {
                x = br.ReadInt16();
                y = br.ReadInt16();
                if (x != -1)
                {
                    stroke.points.Add(new point(x, y));
                }
                else
                {
                    if (y != -1)
                    {
                        if (stroke.points.Count <= 1) {
                            Console.WriteLine("have point stroke!");
                        }
                        pat.strokes.Add(stroke);
                        stroke = new Stroke();
                    }
                }
            } while (y != -1);
            return pat;
        }

        public bool closeFile()
        {
            br.Close();
            fs.Close();
            return true;
        }


        public override Image nextChar()
        {
            Pen pen = new Pen(Color.Black);
            Bitmap bmp = new Bitmap(WIDTH_PX, HEIGHT_PX);
            Graphics graphics = Graphics.FromImage(bmp);     //创建该位图的Graphics对象
            CharPattern pat = next();
            POTTool.interPoint(ref pat);//短缺点补足
            POTTool.translational(ref pat);
            POTTool.reSize(ref pat);
            point prePoint, nextPoint;
            foreach (Stroke stroke in pat.strokes)
            {
                prePoint = stroke.points[0];
                for (int index = 1; index < stroke.points.Count; index++)
                {
                    nextPoint = stroke.points[index];

                    graphics.DrawLine(pen, prePoint.x, prePoint.y, nextPoint.x, nextPoint.y);
                    graphics.FillRectangle(new SolidBrush(Color.Red), prePoint.x, prePoint.y, 3, 3);

                    prePoint = nextPoint;
                }
                graphics.FillRectangle(new SolidBrush(Color.Red), prePoint.x, prePoint.y, 3, 3);
            }
            return bmp;
        }
    }
}
