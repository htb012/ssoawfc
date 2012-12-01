using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace CASIA_DB_Reader
{
    class extractFeature
    {
        public const int DISTANCE = 200;
        public const int IOTA = 5;
        public const double THETA = Math.PI;
        public double lambda = 2 * Math.PI / IOTA;
        public double lamda2;
        public double theta2;
        public double[] dir;
        public static double maxFeatureValue=0;
        public static double minFeatureValue = 0;
        public FileStream outputStream;
        public BinaryWriter output;

        public extractFeature(string fileName)
        { 
            lamda2 = lambda * lambda;
            theta2 = THETA * THETA;
            dir = new double[] { 0, Math.PI * 0.25, Math.PI * 0.5, Math.PI * 0.75, Math.PI, Math.PI * 1.25, Math.PI * 1.5, Math.PI * 1.75};
            outputStream = File.Open(fileName, FileMode.Create);
            output = new BinaryWriter(outputStream);
        }

        //向特征文件中写入文件头信息（文字类的总数，最大维度数）
        public void writeFeatureFileMeta(int cate) {
            output.Write(cate);
            output.Write(PatternTool.GRIDNUM * PatternTool.GRIDNUM * dir.Length);
        }

        //向特征文件中写入一个int数据
        public void writeIntDate(int data) {
            output.Write(data);
        }

        public void wirteFeatureValue(double[] feature) {
            foreach (double fea in feature) {
                output.Write((short)fea);
            }
        }

        public void closeFeatureFile() {
            output.Close();
            outputStream.Close();
        }

        //获取一个文字模型的特征值，这个文字必须是已经划分弹性单元格
        public double[] getFeature(CharPattern  pat) {
            PatternTool.elasticMeshing(ref pat);
            double[] feature = new double[PatternTool.GRIDNUM * PatternTool.GRIDNUM * dir.Length];
            int xm, ym;
            int xs = 0, ys=0;
            for (int i = 0; i < pat.horGridLine.Count+1; i++)
            {
                if (i < pat.horGridLine.Count)
                {
                    ym = (ys + pat.horGridLine[i]) / 2;
                }
                else { 
                    ym = ( pat.horGridLine[i-1]+pat.boundary.Bottom)/2;
                }
                for (int j = 0; j < pat.verGridLine.Count+1; j++)
                {
                    if (j < pat.verGridLine.Count)
                    {
                        xm = (xs + pat.verGridLine[j]) / 2;
                    }
                    else {
                        xm = (pat.verGridLine[j - 1] + pat.boundary.Height) / 2;
                    }
                    double[] subFeature = gabor(xm, ym, pat);
                    //Console.WriteLine("subFeature.Length=" + subFeature.Length + ",");
                    for (int k = 0; k < subFeature.Length; k++){
                        int index = i * PatternTool.GRIDNUM * dir.Length + j * dir.Length + k;
                        //Console.Write(index+",");
                        feature[i * PatternTool.GRIDNUM * dir.Length + j * dir.Length + k] = subFeature[k];
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
        public double[] gabor(int x, int y, CharPattern pat) {
            double[] feature = new double[dir.Length];

                foreach (Stroke stroke in pat.strokes)
                {
                    foreach (point p in stroke.points)
                    {
                        double dis = (p.x - x) * (p.x - x) + (p.y - y) * (p.y - y);
                        if (dis < DISTANCE)
                        {
                            for (int i = 0; i < dir.Length; i++)
                            {
                                feature[i] += 500 * G(p.x - x, p.y - y, 5, dir[i]);
                                maxFeatureValue = feature[i] > maxFeatureValue ? feature[i] : maxFeatureValue;
                                minFeatureValue = feature[i] < minFeatureValue ? feature[i] : minFeatureValue;
                            }
                        }
                    }
            }
            return feature;
        }
        //某一个方向的gabor过滤器的特征值
        public double G(int x, int y, int iota, double dir) {
            double r = lambda * x * Math.Cos(dir) + lambda * y * Math.Sin(dir);
            double g1 = G1(x,y);
            return g1 * (Math.Cos(r) - Math.Exp(-(theta2 / 2))) + g1 * Math.Sin(r);
        }

        public double G1(int x, int y) {
            double temp = Math.Exp(-(x*x+y*y)*lamda2/(2*theta2));
            return lamda2 / theta2 * temp;
        }

    }
}
