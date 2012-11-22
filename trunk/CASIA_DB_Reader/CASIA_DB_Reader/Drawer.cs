using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;

namespace CASIA_DB_Reader
{
    /// <summary>
    /// 绘图类，在panel上绘制从DB读取出的文件内容
    /// </summary>
    class Drawer
    {
        Graphics graphics;
        DBReader reader;
        Pen pen;

        public Drawer()
        {
            pen = new Pen(Color.Black);
        }

        /// <summary>
        /// 绘制方法
        /// </summary>
        /// <param name="graphics"></param>
        /// <param name="filePath"></param>
        public void draw(Graphics graphics, String filePath)
        {
            this.graphics = graphics;
            filePath = filePath.ToLowerInvariant();
            if (filePath.Contains("pot"))
            {
                reader = new potLoader();
            }
            else if (filePath.EndsWith("gnt"))
            {
                reader = new GntLoader();
            }
            else if (filePath.EndsWith("ptts"))
            {
                reader = new PttsLoader();
            }
            else if (filePath.EndsWith("dgr"))
            {
                reader = new dgrLoader();
            }
            else if (filePath.EndsWith("newp")|| filePath.EndsWith("txt"))
            {
                reader = new TEHONLoader();
            }
            else if (filePath.EndsWith("p"))
            {
                reader = new pFileLoader();
            }
            if (reader.load(filePath))
            {
                this.drawNext();
            }
        }

        /// <summary>
        /// 绘制一个文字
        /// </summary>
        public void drawImageChar(int xIncrease, int yIncrease)
        {
            graphics.DrawImage(reader.nextChar(), xIncrease, yIncrease);
            graphics.DrawRectangle(new Pen(Color.LightGray), xIncrease, yIncrease, DBLoader.WIDTH_PX, DBLoader.HEIGHT_PX);
        }

        /// <summary>
        /// 绘制下一个
        /// </summary>
        public void drawNext()
        {
            if (this.graphics != null)
            {
                graphics.Clear(Color.White);
                for (short i = 0; i < 15; i++)
                {
                    this.drawImageChar((i % 5) * DBLoader.WIDTH_PX, (i / 5) * DBLoader.HEIGHT_PX);
                }
            }
        }

    }
}
