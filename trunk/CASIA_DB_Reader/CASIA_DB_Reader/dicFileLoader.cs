using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace CASIA_DB_Reader
{
    class dicFileLoader
    {
        private FileStream fs;
        private BinaryReader br;
        public dicFileLoader()
        {

        }

        public void readDicFile(string fileName)
        {
            fs = File.OpenRead(fileName);
            br = new BinaryReader(fs);
            int classNum = br.ReadInt32();
            Console.WriteLine("classNum="+classNum);
            int dim = br.ReadInt32();
            int dimTo = br.ReadInt32();
        }
    }
}