using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using System.IO;

namespace CASIA_DB_Reader
{
    /// <summary>
    /// ppts文件读取器
    /// 
    /// 读取文件内容
    /// 
    /// </summary>
    class PttsLoader : potLoader
    {
        private long length;
        private FileStream fs;
        private BinaryReader br;
        private List<point> allPoints;//所有的点坐标
        private List<int> strkPointIndex;//笔画结束的点的标识
        private List<int> charStrkIndex;//字符的笔画结束的点的标识
        private int readIndex;

        /// <summary>
        /// 
        /// </summary>
        /// <param name="fileName">要开启的文件名</param>
        /// <returns></returns>
        public override bool load(string fileName)
        {
            allPoints = new List<point>();
            strkPointIndex = new List<int>();
            charStrkIndex = new List<int>();
            readIndex = 0;
            fs = FileManager.open(fileName);
            if (fs == null)
            {
                return false;
            }
            this.length = fs.Length;
            br = new BinaryReader(fs);
            this.loadPttsFile();
            return true;
        }

        /// <summary>
        /// 读取ptts文件内容
        /// </summary>
        private void loadPttsFile()
        {
            int headerSize = br.ReadInt32();
            br.ReadBytes(headerSize - 4);
            int pageLenth = br.ReadInt32();
            br.ReadBytes(4);
            int pageStrkNum = br.ReadInt32();
            int pointIndex = 0;//点的标识号
            for (int i = 0; i < pageStrkNum; i++)
            {
                short strkPointNum = br.ReadInt16();
                short x, y;
                for (int j = 0; j < strkPointNum; j++)
                {
                    x = br.ReadInt16();//点的坐标信息
                    y = br.ReadInt16();
                    allPoints.Add(new point(x, y));
                    pointIndex++;
                }
                allPoints.Add(new point(-1, 0));//添加笔画结束标识
                strkPointIndex.Add(pointIndex++);//记录笔画结束的点的标识
            }
            short lineNum = br.ReadInt16();
            for (int lineIndex = 0; lineIndex < lineNum; lineIndex++)
            {
                short lineStrkNum = br.ReadInt16();
                br.ReadBytes(2 * lineStrkNum);
                short lineCharNum = br.ReadInt16();
                short charFlag;
                String charCode;//字符的编码？
                short charStrkNum;
                for (int k = 0; k < lineCharNum; k++)
                {
                    //charFlag = br.ReadInt16();
                    //charCode = new String(br.ReadChars(2));
                    //Console.WriteLine(charCode);
                    br.ReadBytes(2);// 字符编码
                    charStrkNum = br.ReadInt16();//每个字符的笔画数目
                    br.ReadBytes(2 * charStrkNum - 2);//越过前面的笔画标识
                    charStrkIndex.Add(br.ReadInt16());//记录字符结束的笔画标识
                }
            }
        }
        /// <summary>
        /// 读取一个CASIA onLine数据库文件中的一个文字信息,
        /// 并返回文字的所有坐标信息
        /// </summary>
        /// <returns></returns>
        public override CharPattern next()
        {
            int charPointStart;
            if (readIndex == 0)
            {
                charPointStart = -1;
            }
            else
            {
                charPointStart = (int)strkPointIndex[(short)charStrkIndex[readIndex - 1]];
            }
            int charPointEnd = (int)strkPointIndex[(short)charStrkIndex[readIndex]];
            List<point> charStrkVector = (List<point>)allPoints.GetRange(charPointStart+1, charPointEnd - charPointStart);
            charStrkVector.Add(new point(-1, -1));//添加字符结束标记
            readIndex++;
            return POTTool.pointListToCharPattern(charStrkVector);
        }
    }
}
