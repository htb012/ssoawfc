﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace CASIA_DB_Reader
{
    /// <summary>
    /// 使用刘老师的方法，具体参照论文。。。
    /// </summary>
    class extractFeature
    {
        public  const int NUMDIR = 8;
        public const double NU = 2;
        public const double BETA = 1;
        public double deltaX;
        public double deltaY;
        public const int DISTANCE = 500;
        public FileStream outputStream;
        public BinaryWriter output;
        public double firstPart, fOsc, sigmaO2, sigmaX2, sigmaY2;

        public extractFeature( string fileName)
        {
            //this.deltaX = deltaX;
            outputStream = File.Open(fileName, FileMode.Create);
            output = new BinaryWriter(outputStream);

            double alpha = ((NU + 1) / (NU - 1)) * Math.Tan(Math.PI / NUMDIR / 2);
            double deltaX = POTTool.WIDTH / NUMDIR;
            double fMax = 1 / (2 * deltaX);
            double fOsc = ((NU + 1) / (2 * NU)) * fMax;
            double sigmaU = ((NU - 1) / (BETA * (NU + 1))) * fOsc;

            double sigmaV = sigmaU / alpha;
            double sigmaX = 1 / (2 * sigmaU * Math.PI);
            sigmaX2 = sigmaX * sigmaX;
            double sigmaY = 1 / (2 * sigmaV * Math.PI);
            //double sigmaYPrime = sigmaX * alpha;
            sigmaY2 = sigmaY * sigmaY;
            double sigmaO = sigmaX * fOsc;
            sigmaO2 = sigmaO * sigmaO;
            firstPart = 1 / (2 * Math.PI * sigmaX * sigmaY);
        }

        //向特征文件中写入文件头信息（文字类的总数，最大维度数）
        public void writeFeatureFileMeta(int cate)
        {
            output.Write((Int32)cate);
            int dim = PatternTool.GRIDNUM * PatternTool.GRIDNUM * NUMDIR;
            output.Write((Int32)dim);
        }

        //向特征文件中写入一个int数据
        public void writeIntDate(int data)
        {
            output.Write((Int32)data);
        }

        public void wirteFeatureValue(double[] feature)
        {
            foreach (double fea in feature)
            {
                short feaShort = (short)fea;
                //Console.Write(feaShort + ",");
                output.Write((short)feaShort);
            }
        }


        public void closeFeatureFile()
        {
            output.Close();
            outputStream.Close();
        }

        //获取一个文字模型的特征值，这个文字必须是已经划分弹性单元格
        public double[] getFeature(CharPattern pat)
        {
            PatternTool.elasticMeshing(ref pat);
            double[] feature = new double[PatternTool.GRIDNUM * PatternTool.GRIDNUM * NUMDIR];
            int xm, ym;
            int xs = pat.boundary.Left, ys = pat.boundary.Top;
            for (int i = 0; i < pat.horGridLine.Count + 1; i++)
            {
                //单元格的Y轴中间值
                if (i < pat.horGridLine.Count)
                {
                    ym = (ys + pat.horGridLine[i]) / 2;
                    deltaY = pat.horGridLine[i] - ys;
                }
                else
                {
                    ym = (pat.horGridLine[pat.horGridLine.Count - 1] + pat.boundary.Bottom) / 2;
                    deltaY = pat.boundary.Bottom - pat.horGridLine[pat.horGridLine.Count - 1];
                    ys = pat.boundary.Top;
                }
                for (int j = 0; j < pat.verGridLine.Count + 1; j++)
                {
                    //单元格的x轴中间值
                    if (j < pat.verGridLine.Count)
                    {
                        xm = (xs + pat.verGridLine[j]) / 2;
                        //deltaX = pat.verGridLine[j] - xs;
                    }
                    else
                    {
                        xm = (pat.verGridLine[pat.verGridLine.Count - 1] + pat.boundary.Height) / 2;
                        //deltaX = pat.boundary.Right - pat.verGridLine[pat.verGridLine.Count - 1];
                        xs = pat.boundary.Left;
                    }
                    //double delta = (deltaX + deltaY) / 2;
                    //Console.Write("deltaX=" + deltaX + ",");
                    //if (deltaX < 0 || deltaY < 0 || delta<0)
                    //{
                    //    Console.Write("deltaX=" + deltaX + ",");
                    //}
                    //deltaX = delta;
                    //获得单元格中心点(xm,ym)的gabor特征值
                    double[] subFeature = gaborFeatures(xm, ym, pat);
                    //Console.WriteLine("subFeature.Length=" + subFeature.Length + ",");
                    for (int k = 0; k < subFeature.Length; k++)
                    {

                        int index = i * PatternTool.GRIDNUM * NUMDIR + j * NUMDIR + k;
                        //Console.Write(index+",");
                        feature[i * PatternTool.GRIDNUM * NUMDIR + j * NUMDIR + k] = subFeature[k];
                    }
                    if (j < pat.verGridLine.Count)
                    {
                        xs = pat.verGridLine[j];
                    }
                }
                if (i < pat.horGridLine.Count)
                {
                    ys = pat.horGridLine[i];
                }
            }
            return feature;
        }

        //获取文字某一个点的gabor特征值数组，数组的大小和方向变量dir的大小相同
        public double[] gaborFeatures(int x, int y, CharPattern pat)
        {
            double[] feature = new double[NUMDIR];

            foreach (Stroke stroke in pat.strokes)
            {
                foreach (point p in stroke.points)
                {
                    for (int i = 0; i < NUMDIR; i++)
                    {
                        feature[i] += 255 * gabor(p.x-x, p.y-y, Math.PI * i / (NUMDIR));
                        //Console.Write(feature[i]+",");
                    }
                 }
           }
            return feature;
        }

        public double gabor(int x, int y, double ori) {
            double xPrime = x * Math.Cos(ori) + y * Math.Sin(ori);
            double yPrime = -x * Math.Sin(ori) + y * Math.Cos(ori);
            double thirdPart = Math.Exp(2 * Math.PI * fOsc * xPrime) - Math.Exp(-2 * Math.PI * Math.PI * sigmaO2);
            double secPart = Math.Exp(0-((xPrime * xPrime) /( 2 * sigmaX2)+(yPrime*yPrime)/(2*sigmaY2)));
            double value =  firstPart * secPart * thirdPart;
            return Math.Abs(value);
        }
    }
}