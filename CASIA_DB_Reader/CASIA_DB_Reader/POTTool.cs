using System;
using System.Collections.Generic;
using System.Collections;
using System.Drawing;
using System.Linq;
using System.Text;

namespace CASIA_DB_Reader
{
    /// <summary>
    /// 尺寸更改
    /// </summary>
    class POTTool
    {
        //public const float WIDTH = 300;
        //public const float LENGTH = 300;
        public const float WIDTH = 150;
        public const float LENGTH = 150;
        public const float UNITDISTANCE = 14.0f;
        public const float STDDEVIATIONRATE = 1.9F;//剪切标准差倍率
        public const float SINTERVAL = 1.0f;


        /// <summary>
        /// 平移
        /// </summary>
        /// <param name="pointList"></param>
        public static void translational(ref CharPattern pat)
        {
            if (pat.isTranslational)
            {
                return;
            }
            else
            {
                pat.isTranslational = true;
                Rectangle rec = getBoundary(pat);
                translational(pat.strokes, rec);
                pat.isBoundary = false;
            }
        }

        /// <summary>
        /// 平移
        /// </summary>
        /// <param name="strokes"></param>
        /// <param name="rec"></param>
        public static void translational(List<Stroke> strokes, Rectangle rec)
        {
            int cutWidth, cutLength;
            cutWidth = rec.X;
            cutLength = rec.Y;

            foreach (Stroke stroke in strokes)
            {
                foreach (point point in stroke.points)
                {
                    point.x = (short)(point.x - cutWidth);
                    point.y = (short)(point.y - cutLength);
                }
            }
        }

        /// <summary>
        /// 变形
        /// </summary>
        /// <param name="pointList"></param>
        public static void reSize(ref CharPattern pat)
        {
            if (pat.isResize)
            {
                return;
            }
            else
            {
                pat.isResize = true;
                Rectangle rec = getBoundary(pat);
                reSize(pat.strokes, rec);
            }
        }

        /// <summary>
        /// 变形
        /// </summary>
        /// <param name="strokes"></param>
        /// <param name="rec"></param>
        public static void reSize(List<Stroke> strokes, Rectangle rec)
        {
            float rate;
            if (rec.Height > rec.Width)
            {
                rate = LENGTH / rec.Height;
            }
            else
            {
                rate = WIDTH / rec.Width;
            }

            foreach (Stroke stroke in strokes)
            {
                foreach (point point in stroke.points)
                {
                    point.x = (short)(point.x * rate);
                    point.y = (short)(point.y * rate);
                }
            }
        }

        /// <summary>
        ///线性填充
        /// </summary>
        /// <param name="pat"></param>
        public static void pointComplement(ref CharPattern pat)
        {
            pointComplement(pat.strokes, UNITDISTANCE);
        }

        /// <summary>
        /// 线性填充
        /// </summary>
        /// <param name="strokes"></param>
        /// <param name="unitDistance"></param>
        public static void pointComplement(List<Stroke> strokes, float unitDistance)
        {
            point prefPoint, nextPoint;

            foreach (Stroke stroke in strokes)
            {
                if (stroke.points.Count < 2)
                {
                    strokes.Remove(stroke);
                    break;
                }
                prefPoint = stroke.points[0];
                for (int index = 1; index < stroke.points.Count; index++)
                {
                    nextPoint = stroke.points[index];
                    float xDistance = nextPoint.x - prefPoint.x;
                    float yDistance = nextPoint.y - prefPoint.y;
                    double distance = getDistance(prefPoint, nextPoint);
                    int loopCount = (int)(distance / unitDistance);
                    for (int i = 1; i <= loopCount; i++)
                    {
                        short newX = (short)(prefPoint.x + (xDistance / loopCount * i));
                        short newY = (short)(prefPoint.y + (yDistance / loopCount * i));
                        point newPoint = new point(newX, newY);
                        //smoothing(pointList[index - 1], ref newPoint, pointList[index]);
                        stroke.points.Insert(index, newPoint);
                        index++;
                    }
                    prefPoint = nextPoint;
                }
            }
        }

        

        /// <summary>
        /// 高斯模糊，使用固定的单位间隔
        /// </summary>
        /// <param name="pat"></param>
        public static void gaussSmoothing(CharPattern pat)
        {
            gaussSmoothing(pat, SINTERVAL);
        }

        
        /// <summary>
        /// 高斯模糊，使用自定义的单位间隔
        /// </summary>
        /// <param name="pat"></param>
        public static void gaussSmoothing(CharPattern pat, float sinterval)
        {
            const int winsize = 5;
            const int step = winsize / 2;
            const float sigma = 0.7f;
            float[,] blurmask = new float[winsize, winsize];
            float sigma2 = 2 * sigma * sigma;
            float total = 0;
            //生成高斯模糊模板（高斯模板，按中心点对称）
            for (int i = -step; i < step + 1; i++)
            {
                for (int j = -step; j < step + 1; j++)
                {
                    blurmask[i + step, j + step] = (float)(Math.Exp(-(i * i + j * j) / sigma2));
                    total += blurmask[i + step, j + step];
                }
            }

            float x = 0, y = 0;
            //按笔画为单位做高斯模糊处理
            foreach (Stroke stroke in pat.strokes)
            {
                Stroke strokeTemp = new Stroke();//用于存放高斯模糊后的笔画
                foreach (point point1 in stroke.points)
                {
                    total = 0;
                    x = 0;
                    y = 0;
                    //和同一笔画内的每个点，按照高斯模板处理
                    foreach (point point2 in stroke.points)
                    {
                        if (point1.Equals(point2)) continue;
                        float disX = point2.x - point1.x;
                        float disY = point2.y - point1.y;
                        
                        int stepx = (int)(disX / sinterval + step + 0.5);
                        int stepy = (int)(disY / sinterval + step + 0.5);
                        //点间的距离太大，不处理
                        if (stepx >= winsize || stepy >= winsize) continue;
                        if (stepx <= 0 || stepy <= 0) continue;
                        x += point2.x * blurmask[stepx, stepy];
                        y += point2.y * blurmask[stepx, stepy];
                        total += blurmask[stepx, stepy];
                    }
                    //加上自身的权值
                    x += point1.x * blurmask[step, step];
                    y += point1.y * blurmask[step, step];
                    total += blurmask[step, step];
                    strokeTemp.points.Add(new point((short)(x / total), (short)(y / total)));
                }
                stroke.points = strokeTemp.points;
            }
        }

        /// <summary>
        /// 线性平滑
        /// </summary>
        /// <param name="prePoint"></param>
        /// <param name="midPoint"></param>
        /// <param name="lastPoint"></param>
        /// <returns></returns>
        public static point smoothing(point prePoint, ref point midPoint, point lastPoint)
        {
            midPoint.x = (short)(prePoint.x * 03 + midPoint.x * 0.4 + lastPoint.x * 0.3);
            midPoint.y = (short)(prePoint.y * 03 + midPoint.y * 0.4 + lastPoint.y * 0.3);
            return midPoint;
        }


        /// <summary>
        /// 获取字符边界
        /// </summary>
        /// <param name="t"></param>
        /// <returns></returns>
        public static Rectangle getBoundary(CharPattern pat)
        {

            //if (!pat.isBoundary)
            //{
            short[] xArray = new short[2] { short.MaxValue, 0 };
            short[] yArray = new short[2] { short.MaxValue, 0 };


            foreach (Stroke stroke in pat.strokes)
            {
                foreach (point point in stroke.points)
                {
                    sort(ref xArray, point.x);
                    sort(ref yArray, point.y);
                }
            }
            pat.isBoundary = true;
            pat.boundary = new Rectangle(xArray[0], yArray[0], xArray[1] - xArray[0] + 1, yArray[1] - yArray[0] + 1);
            //}
            return pat.boundary;
        }


        /// <summary>
        /// 将点的集合，转换为charPattern
        /// </summary>
        /// <param name="points"></param>
        /// <returns></returns>
        public static CharPattern pointListToCharPattern(List<point> points)
        {
            if (points == null)
            {
                return null;
            }
            else
            {
                CharPattern pat = new CharPattern();
                Stroke stroke = new Stroke();

                foreach (point point in points)
                {
                    if (point.x != -1)
                    {
                        stroke.points.Add(new point(point.x, point.y));
                    }
                    else
                    {
                        if (point.y != -1)
                        {
                            pat.strokes.Add(stroke);
                            stroke = new Stroke();
                        }
                    }
                }
                return pat;
            }
        }

        /// <summary>
        /// 切除过长的笔画，使用每个点到重心点的距离的标准差来计算（一个sample内部的计算）,使用默认标准差偏差率STDDEVIATIONRATE
        /// </summary>
        /// <param name="pat"></param>
        public static void normaliztion(ref CharPattern pat)
        {
            normaliztion(ref pat, STDDEVIATIONRATE);
        }

        /// <summary>
        /// 切除过长的笔画，使用每个点到重心点的距离的标准差来计算（一个sample内部的计算）
        /// </summary>
        /// <param name="pat"></param>
        /// <param name="stdDevRate"></param>
        public static void normaliztion(ref CharPattern pat, float stdDevRate)
        {
            point centroid = getOnlineCenter(pat);
            double distance = 0;
            int count = 0;
            double aveDistance = 0, stdDev = 0;
            foreach (Stroke stroke in pat.strokes)
            {
                foreach (point point in stroke.points)
                {
                    distance += getDistance(point, centroid);
                    count++;
                }
            }
            aveDistance = (int)distance / count;
            distance = 0;
            foreach (Stroke stroke in pat.strokes)
            {
                foreach (point point in stroke.points)
                {
                    distance += Math.Pow(getDistance(point, centroid) - aveDistance, 2);
                }
            }
            distance /= count;//方差
            stdDev = (int)Math.Sqrt(distance);
            distance = 0;
            double cutDistance = aveDistance + stdDevRate * stdDev;
            foreach (Stroke stroke in pat.strokes)
            {
                for (int i = 0; i < stroke.points.Count; i++)
                {
                    if (getDistance(stroke.points[i], centroid) > cutDistance)
                    {
                        if (i == 0)
                        {
                            if (stroke.points.Count == 1)
                            {
                                stroke.points.RemoveAt(0);
                                continue;
                            }
                            if (getDistance(stroke.points[1], centroid) > cutDistance)
                            {
                                stroke.points.RemoveAt(0);
                                i--;
                            }
                            else
                            {
                                stroke.points[0] = cutPointComplement(stroke.points[0], stroke.points[1], centroid, cutDistance);
                            }
                        }
                        else
                        {
                            stroke.points[i] = cutPointComplement(stroke.points[i], stroke.points[i - 1], centroid, cutDistance);
                            //if (i < stroke.points.Count - 1)
                            //{
                            //    stroke.points.RemoveRange(i + 1, stroke.points.Count - i - 1);
                            //    break;
                            //}
                        }
                    }
                }
            }
        }


        /// <summary>
        /// 在被切除的2点之间，补充边界点。防止笔画丢失
        /// </summary>
        /// <param name="cutPoint"></param>
        /// <param name="prePoint"></param>
        /// <param name="centerPoint"></param>
        /// <param name="cutDistance"></param>
        /// <returns></returns>
        public static point cutPointComplement(point cutPoint, point fixedPoint, point centerPoint, double cutDistance)
        {
            double firDis = getDistance(fixedPoint, centerPoint);
            double oriDis = getDistance(fixedPoint, cutPoint);
            int xDis = cutPoint.x - fixedPoint.x;
            int yDis = cutPoint.y - fixedPoint.y;


            int start = (int)Math.Abs(cutDistance - firDis);
            int end = (int)Math.Abs(cutDistance + firDis);
            point tempPoint = new point();
            int temp = start;
            double rate;
            for (; temp <= end; temp++)
            {
                rate = temp / oriDis;
                tempPoint.x = (short)(fixedPoint.x + rate * xDis);
                tempPoint.y = (short)(fixedPoint.y + rate * yDis);
                if (getDistance(centerPoint, tempPoint) > cutDistance)
                {
                    temp -= 1;
                    break;
                }
            }

            rate = temp / oriDis;
            tempPoint.x = (short)(fixedPoint.x + rate * xDis);
            tempPoint.y = (short)(fixedPoint.y + rate * yDis);

            return tempPoint;

        }

        /// <summary>
        /// 去除 字的最后一笔太长现象,使用边界到重点的距离的标准差（从所有的字体sample中计算）
        /// </summary>
        public static void normaliztion(ref CharPattern pat, int[] centroidDis)
        {
            point centroid = getPatternCentroid(pat);
            int[] distance = new int[4];
            distance[0] = centroid.x - pat.boundary.X;
            distance[1] = centroid.y - pat.boundary.Y;
            distance[2] = pat.boundary.Right - centroid.x;
            distance[3] = pat.boundary.Bottom - centroid.y;
            int[] sort = bubbleSort(distance);
            bool isChanged = false;
            if (distance[0] > centroidDis[0] + centroidDis[1])
            {
                distance[0] = (int)(centroidDis[0] + centroidDis[1]);
                isChanged = true;
            }
            if (distance[1] > centroidDis[2] + centroidDis[3])
            {
                distance[1] = (int)(centroidDis[2] + centroidDis[3]);
                isChanged = true;
            }
            if (distance[2] > centroidDis[4] + centroidDis[5])
            {
                distance[2] = (int)(centroidDis[4] + centroidDis[5]);
                isChanged = true;
            }
            if (distance[3] > centroidDis[6] + centroidDis[7])
            {
                distance[3] = (int)(centroidDis[6] + centroidDis[7]);
                isChanged = true;
            }

            if (isChanged)
            {
                Rectangle rec = new Rectangle(centroid.x - distance[0], centroid.y - distance[1], distance[0] + distance[3], distance[1] + distance[3]);
                cutTail(ref pat, rec);
            }

        }
        /// <summary>
        /// 切除尾巴
        /// </summary>
        public static void cutTail(ref CharPattern pat, Rectangle rec)
        {
            foreach (Stroke stroke in pat.strokes)
            {
                for (int i = 0; i < stroke.points.Count; i++)
                {
                    if (inBoundary(stroke.points[i], rec))
                    {
                        stroke.points.RemoveAt(i);
                    }
                }
            }
        }

        /// <summary>
        /// 某一个点是否在矩形范围内
        /// </summary>
        /// <param name="p"></param>
        /// <param name="rec"></param>
        /// <returns></returns>
        public static bool inBoundary(point p, Rectangle rec)
        {
            if (p.x > rec.X && p.x < rec.Right)
            {
                if (p.y > rec.Y && p.y < rec.Bottom)
                {
                    return true;
                }
            }
            return false;
        }

        /// <summary>
        /// 获取在线字符的中心点
        /// </summary>
        /// <param name="pat"></param>
        /// <returns></returns>
        public static point getOnlineCenter(CharPattern pat)
        {
            long xDis = 0, yDis = 0;
            int pointCount = 0;
            foreach (Stroke stroke in pat.strokes)
            {
                foreach (point point in stroke.points)
                {
                    xDis += point.x;
                    yDis += point.y;
                    pointCount++;
                }
            }
            return new point((short)(xDis / pointCount), (short)(yDis / pointCount));
        }

        /// <summary>
        /// 获取pattern的重心点
        /// </summary>
        /// <param name="pat"></param>
        /// <returns></returns>
        public static point getPatternCentroid(CharPattern pat)
        {
            Rectangle rec = getBoundary(pat);

            double[] prox = new double[rec.Right + 1];
            double[] proy = new double[rec.Bottom + 1];

            getProjection(pat.strokes, ref prox, ref proy);
            short x = (short)getCentroid(ref prox, rec.Right);
            short y = (short)getCentroid(ref proy, rec.Bottom);
            return new point(x, y);

        }

        /// <summary>
        /// 获取投影
        /// </summary>
        /// <param name="points"></param>
        /// <param name="prox"></param>
        /// <param name="proy"></param>
        public static void getProjection(List<Stroke> strokes, ref double[] prox, ref double[] proy)
        {


            point prePoint, nextPoint;

            foreach (Stroke stroke in strokes)
            {
                prePoint = stroke.points[0];
                for (int index = 1; index < stroke.points.Count; index++)
                {
                    nextPoint = stroke.points[index];
                    if (prePoint.x < nextPoint.x)
                    {
                        if (prePoint.y < nextPoint.y)
                        {
                            setProjection(ref prox, ref proy, prePoint.x, nextPoint.x, prePoint.y, nextPoint.y);
                        }
                        else
                        {
                            setProjection(ref prox, ref proy, prePoint.x, nextPoint.x, nextPoint.y, prePoint.y);
                        }
                    }
                    else
                    {
                        if (prePoint.y < nextPoint.y)
                        {
                            setProjection(ref prox, ref proy, nextPoint.x, prePoint.x, prePoint.y, nextPoint.y);
                        }
                        else
                        {
                            setProjection(ref prox, ref proy, nextPoint.x, prePoint.x, nextPoint.y, prePoint.y);
                        }
                    }
                    prePoint = nextPoint;
                }
            }
        }

        /// <summary>
        /// 设置投影
        /// </summary>
        /// <param name="prox"></param>
        /// <param name="proy"></param>
        /// <param name="x0"></param>
        /// <param name="x1"></param>
        /// <param name="y0"></param>
        /// <param name="y1"></param>
        public static void setProjection(ref double[] prox, ref double[] proy, int x0, int x1, int y0, int y1)
        {
            int k;
            int w = x1 - x0 + 1;
            int h = y1 - y0 + 1;
            float l = Math.Max(w, h);
            double w0 = l / w;
            double w1 = l / h;
            for (k = x0; k <= x1; k++) prox[k] += w0;
            for (k = y0; k <= y1; k++) proy[k] += w1;
        }

        /// <summary>
        /// 获取质心
        /// </summary>
        /// <param name="pro"></param>
        /// <param name="length"></param>
        /// <returns></returns>
        public static double getCentroid(ref double[] pro, int length)
        {
            int i;
            double c = 0;
            double sum = 0;
            for (i = 0; i < length; i++)
            {
                double v = pro[i];
                if (v == 0) continue;
                sum += v;
                c += i * v;
            }
            if (sum > 0.0001)
            {
                c /= sum;
            }
            else { c = 0; }
            return c;
        }


        public static point getCtoBound(int[] pro)
        {
            bool startFlag = true;
            int startBound, endBound;
            startBound = 0;
            endBound = pro.Count() - 1;
            for (int i = 0; i < pro.Count(); i++)
            {
                if (startFlag && pro[i] < 2)
                {
                    startBound = i;
                }
                else
                {
                    startFlag = false;
                    if (pro[i] > 2)
                    {
                        endBound = i == (pro.Count() - 1) ? (pro.Count() - 1) : (i - 1);
                    }
                }
            }
            return new point((short)startBound, (short)endBound);
        }

        /// <summary>
        /// 获取2点间的距离
        /// </summary>
        /// <param name="prefPoint"></param>
        /// <param name="nextPoint"></param>
        /// <returns></returns>
        public static double getDistance(point prefPoint, point nextPoint)
        {
            double dis = Math.Sqrt(Math.Pow(prefPoint.x - nextPoint.x, 2) + Math.Pow(prefPoint.y - nextPoint.y, 2));
            return dis;
        }


        /// <summary>
        ///  排序
        /// </summary>
        /// <param name="dataArray"></param>
        /// <param name="data"></param>
        /// <param name="flag"></param>
        /// <returns></returns>
        public static void sort(ref short[] dataArray, short data)
        {
            if (data < dataArray[0])
            {
                dataArray[0] = data;
            } if (data > dataArray[1])
            {
                dataArray[1] = data;
            }
        }

        /// <summary>
        /// 数值交换
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="a"></param>
        /// <param name="b"></param>

        public static void swap<T>(ref T a, ref T b)
        {
            T t = a;
            a = b;
            b = t;
        }

        /// <summary>
        /// 冒泡排序
        /// </summary>
        /// <param name="dataArray"></param>
        /// <returns></returns>
        public static int[] bubbleSort(int[] dataArray)
        {
            int[] sort = { 0, 1, 2, 3 };
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3 - i; j++)
                    if (dataArray[sort[j]] > dataArray[sort[j + 1]])
                    {
                        swap<int>(ref sort[j], ref sort[j + 1]);
                    }
            }
            return sort;
        }
    }
}
