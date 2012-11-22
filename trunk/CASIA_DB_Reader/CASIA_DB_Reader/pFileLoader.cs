using System;
using System.IO;
using System.Collections.Generic;

namespace CASIA_DB_Reader
{
    class pFileLoader : potLoader
    {
        private FileStream fs;
        private BinaryReader br;

        /// <summary>
        /// 将数组中的数据，按照高8位和低8位，组合成一个short类型
        /// </summary>
        /// <param name="b"></param>
        /// <returns></returns>
        public short byteToShort(byte[] b)
        {
            ushort a = 0;
            a |= (ushort)(b[0] << 8);
            a |= b[1];
            return (short)a;
        }

        /// <summary>
        /// 是否到达一个字符表示结束的标志位
        /// </summary>
        /// <param name="b"></param>
        /// <returns></returns>
        public Boolean isCoordfinatesOver(byte[] b)
        {
            if ((b[0] == 128) && (b[1] == 128))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public override bool load(string fileName)
        {
            fs = FileManager.open(fileName);
            if (fs == null)
            {
                return false;
            }
            br = new BinaryReader(fs);
            return true;
        }

        public override CharPattern next()
        {
            Console.WriteLine("test");
            CharPattern pat = new CharPattern();
            short tempX, tempY;
            while (fs.Position < fs.Length)
            {
                short code = byteToShort(br.ReadBytes(2));
                short kakusu = byteToShort(br.ReadBytes(2));
                short type = byteToShort(br.ReadBytes(2));
                short coord = 0;
                byte[] tempCoord = br.ReadBytes(2);
                while (!isCoordfinatesOver(tempCoord))
                {
                    coord = byteToShort(tempCoord);

                    tempX = (short)(coord >> 8);
                    Console.WriteLine(tempX);
                    //tempY = coord 
                   // points.Add(new point((short)(tempCoord[1] + 50), (short)(50 - tempX)));
                    tempCoord = br.ReadBytes(2);
                }
                return pat;
            }
            return pat;
        }
    }
}
