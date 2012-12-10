using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CASIA_DB_Reader
{
    class extractFeature
    {
        public  const double NUMDIR = 8;
        public const double NU = 2;
        public const double BETA = 1.177;
        public double deltaX;

        
        public extractFeature(double deltaX){
            oris = new double[] { 0, Math.PI * 0.125, Math.PI * 0.25, Math.PI * 0.375, Math.PI * 0.5, Math.PI * 0.625, Math.PI * 0.75, Math.PI * 0.875};
            this.deltaX = deltaX;
        }





        public double gabor(int x, int y, float f, double ori) {
            double xPrime = x * Math.Cos(ori) + y * Math.Sin(ori);
            double yPrime = -x * Math.Sin(ori) + y * Math.Cos(ori);
            double alpha = ((NU+1)/(NU-1))*Math.Tan(Math.PI/NUMDIR/2);
            double fMax = 1 / (2 * deltaX);
            double fOsc = ((NU + 1) / (2 * NU)) * fMax;
            double sigmaU = ((NU - 1) / (BETA * (NU + 1))) * fOsc;
            double sigmaV = sigmaU/alpha;
            double thirdPart = Math.Exp(2*Math.PI*fOsc*xPrime)-Math.Exp(-2*Math.PI*Math.PI*);

            return 0;
        }
    }
}
