using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;

namespace CASIA_DB_Reader
{
    class GntLoader : DBLoader
    {
        protected FileStream fs;
        protected BinaryReader br;

        #region DBReader 成员

        public override bool load(string fileName)
        {
            fs = FileManager.open(fileName);
            if (fs == null)
            {
                return false;
            }
            else
            {
                br = new BinaryReader(fs);
                return true;
            }
        }

        public override Image nextChar()
        {
            int sampleSize = br.ReadInt32();
            br.ReadBytes(2);
            short width = br.ReadInt16();
            short height = br.ReadInt16();

            byte[] bitmap = br.ReadBytes(width * height);
            return ToGrayBitmap(bitmap, width, height);
        }

        #endregion

        /// <summary>
        /// 将一个字节数组转换为8bit灰度位图
        /// </summary>
        /// <param name="rawValues">显示字节数组</param>
        /// <param name="width">图像宽度</param>
        /// <param name="height">图像高度</param>
        /// <returns>位图</returns>
        public Bitmap ToGrayBitmap(byte[] rawValues, int width, int height)
        {
            //// 申请目标位图的变量，并将其内存区域锁定
            Bitmap bmp = new Bitmap(width, height, PixelFormat.Format8bppIndexed);
            BitmapData bmpData = bmp.LockBits(new Rectangle(0, 0, width, height),
                ImageLockMode.WriteOnly, PixelFormat.Format8bppIndexed);

            //// 获取图像参数
            int stride = bmpData.Stride;  // 扫描线的宽度
            int offset = stride - width;  // 显示宽度与扫描线宽度的间隙
            IntPtr iptr = bmpData.Scan0;  // 获取bmpData的内存起始位置
            int scanBytes = stride * height;   // 用stride宽度，表示这是内存区域的大小

            //// 下面把原始的显示大小字节数组转换为内存中实际存放的字节数组
            int posScan = 0, posReal = 0;   // 分别设置两个位置指针，指向源数组和目标数组
            byte[] pixelValues = new byte[scanBytes];  //为目标数组分配内存
            for (int x = 0; x < height; x++)
            {
                //// 下面的循环节是模拟行扫描
                for (int y = 0; y < width; y++)
                {
                    pixelValues[posScan++] = rawValues[posReal++];
                }
                posScan += offset;  //行扫描结束，要将目标位置指针移过那段“间隙”
            }

            //// 用Marshal的Copy方法，将刚才得到的内存字节数组复制到BitmapData中
            System.Runtime.InteropServices.Marshal.Copy(pixelValues, 0, iptr, scanBytes);
            bmp.UnlockBits(bmpData);  // 解锁内存区域

            //// 下面的代码是为了修改生成位图的索引表，从伪彩修改为灰度
            ColorPalette tempPalette;
            using (Bitmap tempBmp = new Bitmap(1, 1, PixelFormat.Format8bppIndexed))
            {
                tempPalette = tempBmp.Palette;
            }
            for (int i = 0; i < 256; i++)
            {
                tempPalette.Entries[i] = Color.FromArgb(i, i, i);
            }

            bmp.Palette = tempPalette;

            Console.WriteLine("x=" + bmp.Width + ",y=" + bmp.Height + ",width/height=" + width / height);
            //// 算法到此结束，返回结果
            //返回没有尺寸变换的图片
            return bmp;
            //强行转换为150*150，比例改变
            //return new Bitmap(bmp, WIDTH_PX, HEIGHT_PX);
            //不改变比例，转换为150*150
            //return reSize(bmp);
        }

        /// <summary>
        /// 图像变形，放大，不改变比例，转换为150*150
        /// </summary>
        /// <param name="image"></param>
        /// <returns></returns>
        public Bitmap reSize(Bitmap image)
        {
            Bitmap bmp = new Bitmap(WIDTH_PX, HEIGHT_PX, PixelFormat.Format24bppRgb);
            int x=0,y=0;
            int width = image.Width;
            int height = image.Height;
            if (width > height)
            {
                y = (HEIGHT_PX - height) / 2;
                height = WIDTH_PX / width * height;
                width = WIDTH_PX;
            }
            else
            {
                x = (WIDTH_PX - width) / 2;
                width = HEIGHT_PX / height * width;
                height = HEIGHT_PX;
            }
            Graphics g = Graphics.FromImage(bmp);//无法从索引Image对象中获取Graphics对象
            g.Clear(Color.White);
            Bitmap newImage = new Bitmap(image, width, height);
            g.DrawImage(newImage, x, y);
            return bmp;
            
        }

        public override CharPattern next()
        {
            return null;
        }
    }
}
