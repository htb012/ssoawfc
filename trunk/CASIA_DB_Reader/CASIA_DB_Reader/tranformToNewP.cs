using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace CASIA_DB_Reader
{
    /// <summary>
    /// 将CASIA数据库的pot文件转换为TEHONE格式的newP文件，
    /// 一个文件内存储一个GB2312编码的字的信息，一个字的多人书写的数据
    /// </summary>
    class tranformToNewP : transformFile
    {
        private List<FileStream> fileStreams;
        private string filePath;
        private int fileNameIndex = 0;
        /// <summary>
        ///文件转换，创建pot文件读取的fileStream
        /// </summary>
        /// <param name="filePath"></param>
        public tranformToNewP(string filePath)
        {
            this.filePath = filePath;
            fileStreams = new List<FileStream>();
            string[] files = this.openFolder(filePath);
            foreach (string file in files)
            {
                if (this.isPotFile(file))
                {
                    FileStream fs = File.OpenRead(file);
                    fileStreams.Add(fs);
                }
            }
        }

        /// <summary>
        /// 文件转换，创建pot文件读取的fileStream
        /// </summary>
        /// <param name="listFile">要进行转换的文件列表</param>
        /// <param name="DBFilePath">数据库的地址</param>
        public tranformToNewP(string listFile, string DBFilePath)
        {
            StreamReader sr = new StreamReader(listFile);
            fileStreams = new List<FileStream>();
            this.filePath = DBFilePath;
            while (!sr.EndOfStream)
            {
                string fileName = sr.ReadLine().Trim();
                fileName = DBFilePath + fileName + ".pot";
                FileStream fs = File.OpenRead(fileName);
                fileStreams.Add(fs);
            }
        }

        /// <summary>
        /// 文件转换，创建pot文件读取的fileStream
        /// </summary>
        /// <param name="listFile">要进行转换的文件列表</param>
        /// <param name="DBFilePath">数据库的地址</param>
        /// <param name="targetFloderPath">目标文件夹的地址</param>
        public tranformToNewP(string listFile, string DBFilePath, string targetFloderPath)
            : this(listFile, DBFilePath)
        {
            this.filePath = targetFloderPath;
        }

        /// <summary>
        /// 主转换方法，默认第一个文件左右标准编码集文件，采用方式2（效率高）
        /// 方式1：每次都搜索整个文件，搜索结束后，把文件读取指针复原到文件头处    优点：占资源少   缺点：一个文件要频繁读取pattern个数（大约4037次）的次数
        /// 方式2：预先读取所有文件内的pattern，每次整个pattern中搜索   优点：减少文件读取次数 缺点：占资源巨大
        /// 方式3：每次在文件内偏移量为10的范围内搜索某一个编码 
        /// 方式4：预先读取预先读取所有文件内的pattern，每次在偏移量为10的范围内搜索某一个编码
        /// </summary>
        public void tranform()
        {
            FileStream fs = fileStreams[0];
            List<CharPattern> pats = this.getPotPatterns(fs);
            //DateTime startTime, endTime;
            foreach (CharPattern pat in pats)
            {
                //startTime = DateTime.Now;
                List<CharPattern> codePats = new List<CharPattern>();
                Console.WriteLine("\rread code:" +pat.tagCode);
                codePats.Add(pat);
                for (int i = 1; i <= fileStreams.Count - 1; i++)
                {
                    CharPattern codePat = this.getPatternFormFileStream(pat.tagCode, fileStreams[i]);
                    if (codePat != null)
                    {
                        codePats.Add(codePat);
                    }
                }
                this.writeNewPFile(codePats);
                //endTime = DateTime.Now;
                //Console.WriteLine(endTime-startTime);
            }
        }

        /// <summary>
        /// 求距离的标准差
        /// </summary>
        /// <param name="encodesFile"></param>
        public void getCentroidStdDif(string encodesFile, string CentroidDisFile,string writeFileName)
        {
            int patCount = 0;
            int[] distance = new int[8];
            StreamReader sr = new StreamReader(encodesFile, gb2312);
            StreamReader cdsr = new StreamReader(CentroidDisFile, gb2312);
            while (!sr.EndOfStream)
            {
                string tagCode = sr.ReadLine().Trim();
                string centroidDisStr = cdsr.ReadLine().Trim();
                string[] str = centroidDisStr.Split(',');
                distance[0] = Convert.ToInt32(str[0]);
                distance[2] = Convert.ToInt32(str[1]);
                distance[4] = Convert.ToInt32(str[2]);
                distance[6] = Convert.ToInt32(str[3]);

                Console.WriteLine("\rread code:" + tagCode);
                foreach (FileStream fstream in fileStreams)
                {
                    CharPattern pat = this.getPatternFormFileStream(tagCode, fstream);
                    if (pat != null)
                    {
                        patCount++;
                        POTTool.translational(ref pat);//平移
                        POTTool.reSize(ref pat);//变形
                        point centroid = POTTool.getPatternCentroid(pat);
                        POTTool.getBoundary(pat);
                        distance[1] += (int)Math.Pow(centroid.x - pat.boundary.X - distance[0], 2);
                        distance[3] += (int)Math.Pow(centroid.y - pat.boundary.Y - distance[2], 2);
                        distance[5] += (int)Math.Pow(pat.boundary.Right - centroid.x - distance[4], 2);
                        distance[7] += (int)Math.Pow(pat.boundary.Bottom - centroid.y - distance[6], 2);
                    }
                }
                distance[1] /= patCount;
                distance[1] = (int)Math.Pow(distance[1], 0.5);
                distance[3] /= patCount;
                distance[3] = (int)Math.Pow(distance[3], 0.5);
                distance[5] /= patCount;
                distance[5] = (int)Math.Pow(distance[5], 0.5);
                distance[7] /= patCount;
                distance[7] = (int)Math.Pow(distance[7], 0.5);
                patCount = 0;
                writeCentroidDisFile(distance, writeFileName);
            }
        }
        
        
        /// <summary>
        ///  求距离的平均差
        /// </summary>
        /// <param name="encodesFile">编码文件</param>
        /// <param name="writeFileName">输出文件名</param>
        public void getCentroidDistance(string encodesFile,string writeFileName) {
            int patCount = 0;
            int[] distance = new int[4];
            StreamReader sr = new StreamReader(encodesFile, gb2312);
            while (!sr.EndOfStream)
            {
                string tagCode = sr.ReadLine().Trim();
                Console.WriteLine("\rread code:" + tagCode);
                foreach (FileStream fstream in fileStreams)
                {
                    CharPattern pat = this.getPatternFormFileStream(tagCode, fstream);
                    if (pat != null)
                    {
                        patCount++;
                        POTTool.translational(ref pat);//平移
                        POTTool.reSize(ref pat);//变形
                        point centroid = POTTool.getPatternCentroid(pat);
                        POTTool.getBoundary(pat);
                        distance[0] += centroid.x - pat.boundary.X;
                        distance[1] += centroid.y - pat.boundary.Y;
                        distance[2] += pat.boundary.Right - centroid.x;
                        distance[3] += pat.boundary.Bottom - centroid.y;
                    }
                }
                distance[0] /= patCount;
                distance[1] /= patCount;
                distance[2] /= patCount;
                distance[3] /= patCount;
                patCount = 0;
                writeCentroidDisFile(distance,writeFileName);
            }
        }

        /// <summary>d:\\test\\CentroidDisTrain.txt
        /// 输出距离文件
        /// </summary>
        /// <param name="distance"></param>
        /// <param name="fileName"></param>
        public void writeCentroidDisFile(int[] distance,string fileName) {
            StringBuilder sb = new StringBuilder();
            foreach (int dis in distance) {
                sb.Append(dis + ",");
            }
            sb.AppendLine();
            using (StreamWriter outfile = new StreamWriter(fileName, true, gb2312))
            {
                outfile.Write(sb.ToString());
                outfile.Close();
            }
        }

        /// <summary>
        /// 转换方法，读取从编码文件中读取编码集
        /// 
        /// </summary>
        /// <param name="encodesFile"></param>
        public void tranform(string encodesFile, string CentroidDisFile, float stdDevRate)
        {
            int patCount = 0, strokeNum = 0;
            StreamReader sr = new StreamReader(encodesFile, gb2312);
            //StreamReader cdsr = new StreamReader(CentroidDisFile, gb2312);
            while (!sr.EndOfStream)
            {
                List<CharPattern> codePats = new List<CharPattern>();
                string tagCode = sr.ReadLine().Trim();
                //string centroidDisStr = cdsr.ReadLine().Trim();
                //string[] str = centroidDisStr.Split(',');
                //int[] centroidDis = new int[8];
                //centroidDis[0] = Convert.ToInt32(str[0]);
                //centroidDis[1] = Convert.ToInt32(str[1]);
                //centroidDis[2] = Convert.ToInt32(str[2]);
                //centroidDis[3] = Convert.ToInt32(str[3]);
                //centroidDis[4] = Convert.ToInt32(str[4]);
                //centroidDis[5] = Convert.ToInt32(str[5]);
                //centroidDis[6] = Convert.ToInt32(str[6]);
                //centroidDis[7] = Convert.ToInt32(str[7]);
                Console.WriteLine("\rread code:" + tagCode);
                foreach (FileStream fstream in fileStreams)
                {
                    CharPattern pat = this.getPatternFormFileStream(tagCode, fstream);
                    if (pat != null)
                    {
                        strokeNum += pat.strokeNum;
                        POTTool.translational(ref pat);//平移
                        //POTTool.reSize(ref pat);//变形
                        //POTTool.pointComplement(pat.strokes, pointDistance);//短缺点补足
                        POTTool.normaliztion(ref pat, stdDevRate);//切除长尾巴
                        //POTTool.translational(ref pat);//平移
                        POTTool.pointComplement(ref pat);//短缺点补足
                        POTTool.reSize(ref pat);//变形
                        codePats.Add(pat);
                    }
                }
                patCount += codePats.Count;
                this.writeNewPFile(codePats);
            }
            Console.WriteLine("pattern count = " + patCount + ",strokeNum = " + strokeNum);
        }


        /// <summary>
        /// 转换方法，读取从编码文件中读取编码集
        /// 
        /// </summary>
        /// <param name="encodesFile"></param>
        public void tranform(string encodesFile, float sinterval,string feaFileName)
        {
            int patCount = 0, strokeNum = 0;
            StreamReader sr = new StreamReader(encodesFile, gb2312);
            extractFeature extFea = new extractFeature(feaFileName);
            extFea.writeFeatureFileMeta(4037);
            while (!sr.EndOfStream)
            {
                List<CharPattern> codePats = new List<CharPattern>();
                string tagCode = sr.ReadLine().Trim();
                Console.WriteLine("\rread code:" + tagCode);
                foreach (FileStream fstream in fileStreams)
                {
                    CharPattern pat = this.getPatternFormFileStream(tagCode, fstream);
                    if (pat != null)
                    {
                        strokeNum += pat.strokeNum;
                        POTTool.translational(ref pat);//平移
                        //POTTool.normaliztion(ref pat);//切除长尾巴
                        POTTool.pointComplement(ref pat);//短缺点补足
                        POTTool.reSize(ref pat);//变形
                        POTTool.gaussSmoothing(pat, sinterval);//高斯平滑
                        codePats.Add(pat);
                    }
                    else {
                        Console.WriteLine("no pat");
                    }
                }
                patCount += codePats.Count;
                //this.writeNewPFile(codePats);
                int t = codePats.Count;
                Console.WriteLine("Count:"+t);
                extFea.writeIntDate(codePats.Count);
                foreach (CharPattern pat in codePats)
                {
                    double[] feature = extFea.getFeature(pat);
                    extFea.wirteFeatureValue(feature);
                    for (int i = 0; i < feature.Length; i++)
                    {
                        //Console.Write((short)feature[i] + ",");
                    }
                    //Console.WriteLine();
                }
            }
            extFea.closeFeatureFile();
            Console.WriteLine("pattern count = " + patCount + ",strokeNum = " + strokeNum);
        }

        /// <summary>
        /// 方式2：预先读取所有文件内的pattern，每次整个pattern中搜索
        /// </summary>
        /// <param name="code"></param>
        /// <param name="fs"></param>
        /// <returns></returns>
        public CharPattern getPatternFormPatternList(string code, FileStream fs)
        {
            List<CharPattern> pats = this.getPotPatterns(fs);
            fs.Seek(0, SeekOrigin.Begin);
            foreach (CharPattern pat in pats)
            {
                if (pat.tagCode.Equals(code))
                {
                    return pat;
                }
            }
            return null;
        }

        /// <summary>
        /// 从文件流中获取一个potPattern,采用方式2
        /// </summary>
        /// <param name="code"></param>
        /// <param name="fs"></param>
        /// <returns></returns>
        public CharPattern getPatternFormFileStream(string code, FileStream fs)
        {
            long position = fs.Position;
            //bool loop = false;
            BinaryReader br = new BinaryReader(fs);
            do
            {
                if (fs.Position >= fs.Length)
                {
                    fs.Seek(0, SeekOrigin.Begin);
                    if (position == 0)
                    {
                        break;
                    }
                }
                CharPattern pat = this.getPotPattern(br);
                if (pat.tagCode.Equals(code))
                {
                    return pat;
                }
            }
            while (fs.Position != position);
            fs.Seek(position, SeekOrigin.Begin);
            //while ((fs.Position >= position)&&(loop)) ;
            return null;
        }

        /// <summary>
        /// 将同一个GB2312编码文件写入到newP文件中，文件名以编码命名
        /// </summary>
        /// <param name="patterns"></param>
        public void writeNewPFile(List<CharPattern> patterns)
        {
            StringBuilder fileName = new StringBuilder();
            fileName.Append(filePath);
            fileName.Append(String.Format("{0:0000}", fileNameIndex++));
            fileName.Append(".newp");
            this.writeFile(patterns, fileName.ToString());
        }
    }
}
