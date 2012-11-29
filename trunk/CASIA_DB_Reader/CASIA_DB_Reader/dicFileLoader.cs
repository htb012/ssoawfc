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
            Console.WriteLine("dim=" + dim);
            int count,data;
            while (fs.CanRead)
            {
                count = br.ReadInt32();
                Console.WriteLine("count=" + count);
                for (int i = 0; i < count; i++)
                {
                    for (int j = 0; j < dim; j++)
                    {
                        data = br.ReadInt16();
                        //Console.Write(",data[" + j + "]=" + data);
                        Console.Write(data+",");
                    }
                    Console.WriteLine();
                }
                Console.WriteLine();
            }
        }
    }
}