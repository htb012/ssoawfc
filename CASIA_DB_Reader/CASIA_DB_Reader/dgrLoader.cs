using System;
using System.Collections;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;

namespace CASIA_DB_Reader
{
    /// <summary>
    /// 离线文本文件*.dgr的读取，继承自
    /// </summary>
    class dgrLoader : GntLoader
    {
        private ArrayList bitmapList;//每个字的位图数据列表
        private ArrayList charInfoList;//每个字的位图的长宽的信息列表
        private int bitmapReadIndex = 0;

        public dgrLoader()
        {
            bitmapList = new ArrayList();
            charInfoList = new ArrayList();
        }

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
            }
            this.loadDgrFile();
            return true;
        }

        /// <summary>
        /// 读取dgr文件的信息
        /// </summary>
        public void loadDgrFile()
        {
            int headSize = br.ReadInt32();
            String formatCode = new String(br.ReadChars(8));
            String Illustration = new String(br.ReadChars(headSize - 36));
            String codeType = new String(br.ReadChars(20));
            short codeLength = br.ReadInt16();
            //br.ReadBytes(headSize - 6);
            short bitsPerPix = br.ReadInt16();
            int height = br.ReadInt32();
            int width = br.ReadInt32();
            int lineNum = br.ReadInt32();
            for (int lineIndex = 0; lineIndex < lineNum; lineIndex++)
            {
                int charNum = br.ReadInt32();
                for (int charIndex = 0; charIndex < charNum; charIndex++)
                {
                    br.ReadBytes(codeLength);
                    short x = br.ReadInt16();
                    short y = br.ReadInt16();
                    short charHeight = br.ReadInt16();
                    short charWidth = br.ReadInt16();
                    //将位图数据存储到列表中
                    bitmapList.Add(br.ReadBytes(charHeight * charWidth));
                    //将位图的长宽信息存储到列表中
                    charInfoList.Add(charHeight);
                    charInfoList.Add(charWidth);
                }
            }
        }



        public override Image nextChar()
        {
            short charHeight = (short)charInfoList[bitmapReadIndex * 2];
            short charWidth = (short)charInfoList[bitmapReadIndex * 2 + 1];
            byte[] bitmap = (byte[])bitmapList[bitmapReadIndex];
            bitmapReadIndex++;
            return ToGrayBitmap(bitmap, charWidth, charHeight);
        }

    }
}