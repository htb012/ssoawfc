using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Drawing;

namespace CASIA_DB_Reader
{

    class TEHONLoader : DBLoader
    {

        StreamReader sr;
        FileStream fs;


        public override bool load(string fileName)
        {
            fs = new FileStream(fileName, FileMode.Open);
            sr = new StreamReader(fs);
            if (sr == null)
            {
                return false;
            }
            //读取文件头
            return true;
        }

        public List<CharPattern> loadAllFile()
        {

            List<CharPattern> pats = new List<CharPattern>();
            while (!sr.EndOfStream)
            {
                CharPattern pat = this.nextPattern();
                if (pat != null)
                {
                    pats.Add(pat);
                }
            }
            return pats;
        }

        public override System.Drawing.Image nextChar()
        {
            CharPattern pat = this.next();
            Pen pen = new Pen(Color.Gray);
            Bitmap bmp = new Bitmap(WIDTH_PX, HEIGHT_PX);
            Graphics graphics = Graphics.FromImage(bmp);     //创建该位图的Graphics对象

            POTTool.pointComplement(ref pat);//短缺点补足
            POTTool.translational(ref pat);
            POTTool.reSize(ref pat);
            POTTool.drawElasticMeshing(graphics, ref pat);
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

        public CharPattern nextPattern()
        {
            if (!sr.EndOfStream)
            {
                string str;
                while (Convert.ToChar(sr.Peek()) != '[')
                {
                    sr.ReadLine();
                }
                str = sr.ReadLine().Trim();
                CharPattern pat = new CharPattern();
                pat.tagCode = str.Substring(1, 1);
                Stroke stroke = new Stroke();
                ushort strokeNum=0;
                while (!sr.EndOfStream && (Convert.ToChar(sr.Peek()) != '['))
                {
                    str = sr.ReadLine().Trim();

                    string[] data = null;
                    if (str.Contains('\t'))
                    {
                        data = str.Split('\t');
                    }
                    else {
                        data = str.Split(' ');
                    }

                    short penState = Int16.Parse(data[0]);
                    short x = Int16.Parse(data[1]);
                    short y = Int16.Parse(data[2]);

                    if (penState != tehonPattern.UP)
                    {
                        stroke.points.Add(new point(x,y));
                    }
                    else
                    {
                        pat.strokes.Add(stroke);
                        stroke = new Stroke();
                        strokeNum++;
                    }
                }
                pat.strokeNum = strokeNum;
                return pat;
            }
            return null;
        }

        public override CharPattern next()
        {
            CharPattern pat = this.nextPattern();
            return pat;
        }
    }
}