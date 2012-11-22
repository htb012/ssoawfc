using System;
using System.Drawing;
using System.Collections.Generic;

namespace CASIA_DB_Reader
{
    abstract class DBLoader:DBReader
    {
        public const int WIDTH_PX = 150;
        public const int HEIGHT_PX = 150;


        public abstract bool load(String fileName);

        public abstract Image nextChar();

        public abstract CharPattern next();
    }
}
