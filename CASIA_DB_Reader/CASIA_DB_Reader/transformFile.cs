using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace CASIA_DB_Reader
{
    /// <summary>
    /// 将CASIA在线单字文件pot文件，转换为TEHON txt文件
    /// 只单单将一个pot文件转换为tehon txt格式的文件。
    /// </summary>
    class transformFile
    {
        public Encoding gb2312;
        public transformFile()
        {
            gb2312 = Encoding.GetEncoding("gb2312");
        }

        public void transformAFile(String fileName)
        {
            Console.WriteLine("\rtransfrom" + fileName);
            List<CharPattern> pats;
            pats = this.openFile(fileName);
            fileName = fileName.Replace("pot", "txt");
            writeFile(pats, fileName);
        }

        public void transformAllFile(String filePath)
        {
            String[] files = openFolder(filePath);
            foreach (String fileName in files)
            {
                if (isPotFile(fileName))
                {
                    transformAFile(fileName);
                }
            }
        }

        /// <summary>
        /// 打开一个pot文件，并读取
        /// </summary>
        public List<CharPattern> openFile(String fileName)
        {
            
            FileStream fs = FileManager.open(fileName);
            //br.Close();
            //fs.Close();
            return this.getPotPatterns(fs);
        }

        /// <summary>
        /// 从一个FileStream中读取一个文件内的所有Pot pattern
        /// </summary>
        /// <param name="br"></param>
        /// <returns></returns>
        public List<CharPattern> getPotPatterns(FileStream fs)
        {
            BinaryReader br = new BinaryReader(fs);
            List<CharPattern> pots = new List<CharPattern>();
            while (fs.Position < fs.Length)
            {
                CharPattern pot = this.getPotPattern(br);
                pots.Add(pot);
            }
            return pots;
        }

        /// <summary>
        /// 从一个binaryReader中读取一个文件内的一个Pot pattern
        /// </summary>
        /// <param name="br"></param>
        /// <returns></returns>
        public CharPattern getPotPattern(BinaryReader br) {
            CharPattern pat = new CharPattern();
            pat.sampleSize = br.ReadUInt16();
            byte[] bytes = br.ReadBytes(4);
            //数组反转（重点，查看邮件记录20120417）
            Array.Reverse(bytes);
            int[] tagCodeInfo = getTagCodeInfo(bytes);
            char[] c = gb2312.GetChars(bytes, tagCodeInfo[0], tagCodeInfo[1]);
            if (tagCodeInfo[1] == 1)
            {
                c = ToSBC(c);
            }
            pat.tagCode = new string(c);

            //Console.WriteLine(new String(pat.TagCode));
            //br.ReadBytes(2);
            pat.strokeNum = br.ReadUInt16();
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
                        pat.strokes.Add(stroke);
                        stroke = new Stroke();
                    }
                }
            } while (y != -1);
            return pat;
        }

        /// <summary>
        /// 写出TEHON txt文件到存储器
        /// </summary>
        /// <returns></returns>
        public bool writeFile(List<CharPattern> pats, String fileName)
        {
            StringBuilder sb = new StringBuilder();
            foreach (CharPattern pat in pats)
            {
                sb.AppendLine("[" + pat.tagCode.Trim().ToString() + "]");

                foreach (Stroke stroke in pat.strokes) {
                    if (stroke.points.Count < 2)
                    {
                        break;
                    }
                    //笔画的起始点
                    sb.AppendLine(tehonPattern.DOWN + "\t" + stroke.points[0].x + "\t" + stroke.points[0].y);
                    for (int i = 1; i < stroke.points.Count-1; i++) {
                        sb.AppendLine(tehonPattern.MOVE + "\t" + stroke.points[i].x + "\t" + stroke.points[i].y);
                    }
                    //笔画的终点
                    sb.AppendLine(tehonPattern.UP + "\t" + stroke.points[stroke.points.Count - 1].x + "\t" + stroke.points[stroke.points.Count - 1].y);
                }
            }

            using (StreamWriter outfile = new StreamWriter(fileName,false,gb2312))
            {
                outfile.Write(sb.ToString());
                outfile.Close();
            }
            return true;
        }

        /// <summary>
        /// 获取一个文件夹下的所有文件
        /// </summary>
        /// <param name="filePath"></param>
        /// <returns></returns>
        public String[] openFolder(String filePath)
        {
            String[] files = Directory.GetFiles(filePath);
            return files;
        }

        /// <summary>
        /// 判断一个文件是否为pot文件  
        /// </summary>
        /// <param name="fileName"></param>
        /// <returns></returns>
        public bool isPotFile(String fileName)
        {
            return fileName.Contains("pot");
        }

        /// <summary>
        /// 获取字符编码的开始位置和长度
        /// </summary>
        public int[] getTagCodeInfo(byte[] bytes)
        {
            int index = 0;
            int start = -1;
            int count = 0;
            foreach (byte b in bytes)
            {
                if (b != '\0')
                {
                    if (start == -1)
                    {
                        start = index;
                    }
                    count++;
                }
                index++;
            }
            //Console.WriteLine("start=" + start + ",count=" + count + ",index=" + index);
            return new int[]{start,count};
        }

        /// <summary>
        /// 转全角的函数(SBC case)
        /// </summary>
        /// <param name="input">任意字符串</param>
        /// <returns>全角字符串</returns>
        ///<remarks>
        ///全角空格为12288，半角空格为32
        ///其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
        ///</remarks>
        public char[] ToSBC(char[] c)
        {
            for (int i = 0; i < c.Length; i++)
            {
                if (c[i] == 32)
                {
                    c[i] = (char)12288;
                    continue;
                }
                if (c[i] < 127)
                    c[i] = (char)(c[i] + 65248);
            }
            return c;
        }
    }
}
