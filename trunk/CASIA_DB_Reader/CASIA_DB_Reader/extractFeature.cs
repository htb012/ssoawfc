using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CASIA_DB_Reader
{
    class extractFeature
    {
        public const int IOTA = 5;
        public const double THETA = Math.PI;
        public double lambda = 2 * Math.PI / IOTA;
        public double lamda2;
        public double theta2;
        public double[] dir;

        public extractFeature() { 
            lamda2 = lambda * lambda;
            theta2 = THETA * THETA;
            dir = new double[] {0,Math.PI*0.25,Math.PI*0.5,Math.PI*0.75};
        }

        public void writeFeatureFile(string fileName) { 
            
        }

        public double[] getFeature(CharPattern  pat) {
            double[] feature = new double[256];
            int xm, ym;
            int xs = 0, ys=0;
            for (int i = 0; i < pat.horGridLine.Count; i++)
            {
                ym = (ys + pat.horGridLine[i]) / 2;
                for (int j = 0; j < pat.verGridLine.Count; j++)
                {
                    xm = (xs + pat.verGridLine[i]) / 2;
                    double[] subFeature = gabor(xm, ym, pat);
                    for (int i = 0; i < subFeature.Length; i++){
                        feature[i * POTTool.GRIDNUM * dir.Length + j * dir.Length + i] = subFeature[i];
                    }
                    xs = pat.verGridLine[i];
                }
                ys = pat.horGridLine[i];
            }
            return feature;
        }

        public double[] gabor(int x, int y, CharPattern pat) {
            double[] feature = new double[dir.Length];
            for (int i = 0; i < dir.Length; i++)
            {
                foreach (Stroke stroke in pat.strokes)
                {
                    foreach (point p in stroke.points)
                    {
                        double dic = (p.x - x) * (p.x - x) + (p.y - y) * (p.y - y);
                        if (dic < 100)
                        {
                            feature[i] += G(p.x - x, p.y - y, 5, dir[i]);
                        }
                    }

                }
            }
            return feature;
        }

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
