using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Drawing;
using System.Drawing.Imaging;

namespace CASIA_DB_Reader
{
    /// <summary>
    /// </summary>
    class test : transformFile
    {
        Bitmap bmp;
        // 测试程序，添加验证pot文件内部，字符个数的函数
        public void getFileCharCount(string filePath)
        {
            StringBuilder sb = new StringBuilder();
            string[] files = this.openFolder(filePath);
            int patsCounts = 0;
            foreach (string file in files)
            {
                if (isPotFile(file))
                {
                    int patCount = this.openFile(file).Count;
                    patsCounts += patCount;
                    sb.AppendLine(file + ":\t" + patCount);
                }
            }
            sb.AppendLine("counts=" + patsCounts);
            using (StreamWriter outfile = new StreamWriter("d:\\test\\FilePatternNum.txt"))
            {
                outfile.Write(sb.ToString());
                outfile.Close();
            }
        }

        // 测试程序，添加验证pot文件内部字符
        public void getFileChar(string fileName)
        {
            StringBuilder sb = new StringBuilder();
            if (isPotFile(fileName))
            {
                List<CharPattern> pots = this.openFile(fileName);
                foreach (CharPattern pot in pots)
                {
                    sb.AppendLine(pot.tagCode);
                }
            }
            using (StreamWriter outfile = new StreamWriter("d:\\test\\FilePattern.txt", false, Encoding.GetEncoding("gb2312")))
            {
                outfile.Write(sb.ToString());
                outfile.Close();
            }
        }

        public void todo()
        {
            StreamReader sr = new StreamReader("E:\\DB\\CASIC_use\\onLine\\FilePattern4037.txt", Encoding.GetEncoding("gb2312"));
            while (!sr.EndOfStream)
            {
                string str = sr.ReadLine();
                Console.WriteLine(str);
            }
        }



        public void writeCharToFile(string str, string fileName)
        {
            StringBuilder sb = new StringBuilder();
            sb.AppendLine(str);
            using (StreamWriter outfile = new StreamWriter(fileName))
            {
                outfile.Write(sb.ToString());
                outfile.Close();
            }
        }

        public bool changeAllTehonToImage(string folderName)
        {
            String[] files = Directory.GetFiles(folderName);
            foreach (string file in files)
            {
                if (file.Contains("newp"))
                {
                    string newFileName = file.Replace("newp", "jpg");
                    Console.WriteLine(newFileName);
                    this.tehonToImage(file, newFileName);
                }
            }
            return true;

        }

        public bool tehonToImage(string filename, string safeFileName)
        {
            TEHONLoader loader = new TEHONLoader();
            loader.load(filename);
            CharPattern pat;
            int index = 0;
             bmp = new Bitmap(6000, 6000);
            Graphics graphics = Graphics.FromImage(bmp);
            graphics.Clear(Color.White);
            Pen pen = new Pen(Color.Black);
            pat = loader.next();
            point prePoint, nextPoint;
            while (pat != null)
            {
                POTTool.translational(ref pat);
                POTTool.reSize(ref pat);
                int xIncrease = index % 20 * 300;
                int yIncrease = index / 20 * 300;
                graphics.DrawRectangle(new Pen(Color.Red), xIncrease, yIncrease, 300, 300);
                foreach (Stroke stroke in pat.strokes)
                {
                    prePoint = stroke.points[0];
                    for (int i = 1; i < stroke.points.Count; i++)
                    {
                        nextPoint = stroke.points[i];
                        graphics.DrawLine(pen, xIncrease + prePoint.x, yIncrease + prePoint.y, xIncrease + nextPoint.x, yIncrease + nextPoint.y);
                        prePoint = nextPoint;
                    }
                }
                pat = loader.next();
                index++;
            }
            bmp.Save(safeFileName, ImageFormat.Jpeg);
            graphics = null;
            bmp = null;
            return true;
        }

        public static void tutu()
        {
            float a = 5.0f;
            int b = 3;
            Console.WriteLine(a / b);
        }

        public static void tt() {
            CharPattern pat = new CharPattern();
            Stroke stroke = new Stroke();
            stroke.points.Add(new point(0, 0));

            stroke.points.Add(new point(400, 400));
            pat.strokes.Add(stroke);

            point p = POTTool.getOnlineCenter(pat);
        }
    }
}
