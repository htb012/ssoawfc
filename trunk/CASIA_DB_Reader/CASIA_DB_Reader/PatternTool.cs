using System;
using System.Collections.Generic;
using System.Collections;
using System.Drawing;
using System.Linq;
using System.Text;
using System.IO;

namespace CASIA_DB_Reader
{
    /// <summary>
    /// 特征提取相关
    /// 弹性网格等
    /// </summary>
    class PatternTool
    {
        public const short GRIDNUM = 8;//8个网格数，7条网格线
        //绘制网络线
        public static void drawElasticMeshing(Graphics graphics, ref CharPattern pat)
        {
            //获取弹性网格线
            elasticMeshing(ref pat);
            //绘制网格线
            Pen pen = new Pen(Color.Green);
            foreach (int value in pat.horGridLine)
            {
                graphics.DrawLine(pen, 0, value, POTTool.WIDTH, value);
            }
            foreach (int value in pat.verGridLine)
            {
                graphics.DrawLine(pen, value, 0, value, POTTool.LENGTH);
            }
        }

        //获取弹性网格的网线
        public static void elasticMeshing(ref CharPattern pat)
        {
            Rectangle rec = POTTool.getBoundary(pat);
            int[] horGridLine = new int[GRIDNUM - 1];//横向网格线
            int[] verGridLine = new int[GRIDNUM - 1];//纵向网格线

            //投影
            double[] prox = new double[rec.Right + 1];
            double[] proy = new double[rec.Bottom + 1];
            //获取投影
            POTTool.getProjection(pat.strokes, ref prox, ref proy);
            double xGrandTotal = 0, yGrandTotal = 0;
            //计算X轴和Y轴的投影总值
            foreach (double value in prox)
            {
                xGrandTotal += value;
            }
            foreach (double value in proy)
            {
                yGrandTotal += value;
            }
            //纵向网格的投影平均值
            double xGrandAve = xGrandTotal / GRIDNUM;
            //横向网格的投影平均值
            double yGrandAve = yGrandTotal / GRIDNUM;

            int GridLineIndex = 0;
            xGrandTotal = 0;
            //划分纵向网格线的位置
            for (int i = 0; i <= rec.Right; i++)
            {
                xGrandTotal += prox[i];
                int currentIndex = (int)(xGrandTotal / xGrandAve) - 1;
                while ( GridLineIndex < currentIndex)
                {
                    verGridLine[GridLineIndex++] = i;
                    if (GridLineIndex >= GRIDNUM - 1)
                    {
                        break;
                    }
                }

            }
            //划分横向网格线的位置
            GridLineIndex = 0;
            yGrandTotal = 0;
            for (int i = 0; i <= rec.Bottom; i++)
            {
                yGrandTotal += proy[i];
                if (yGrandTotal >= (GridLineIndex + 1) * yGrandAve)
                {
                    horGridLine[GridLineIndex++] = i;
                }
                if (GridLineIndex >= GRIDNUM - 1)
                {
                    break;
                }
            }
            pat.horGridLine = horGridLine.ToList();
            pat.verGridLine = verGridLine.ToList();
        }

        public void makeOffLineIndexFile(string encodesFile)
        {
            List<short> indexToDicCount = new List<short>();
            List<byte[]> dicCountToCode = new List<byte[]>();
            Encoding gb2312 = Encoding.GetEncoding("gb2312");
            StreamReader sr = new StreamReader(encodesFile, gb2312);
             FileStream outputStream = FileManager.open("OffLineIndexTest.dic");
             BinaryWriter output = new BinaryWriter(outputStream);
             while (!sr.EndOfStream)
             {
                string tagCode = sr.ReadLine().Trim();
                dicCountToCode.Add(gb2312.GetBytes(tagCode));
             }
             for (int i = 0; i < dicCountToCode.Count; i++) {
                 indexToDicCount.Add((short)i);
                 output.Write(i);
             }
             
             output.Write(dicCountToCode.Count);

        }
    }
}
