using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace CASIA_DB_Reader
{
    static class Program
    {
        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        [STAThread]
        static void Main(string[] args)
        {

            //Application.EnableVisualStyles();
            //Application.SetCompatibleTextRenderingDefault(false);
            //Application.Run(new CASIA_DB_Loader());

            //DBReader loader = new dgrLoader();
            //loader.load("D:\\006-P16.dgr");
            //loader.next();

            ////pot文件转TEHON txt文件
            //transformFile tranform = new transformFile();
            //tranform.transformAllFile("D:\\学习\\农工大\\DB\\Data\\");
            //tranform.transformAllFile("E:\\DB\\CASIC_use\\onLine\\CASIA-OLHWDB1.0\\Data\\");
            //tranform.transformAllFile("E:\\DB\\CASIC_use\\onLine\\CASIA-OLHWDB1.2\\Data\\");
            //tranform.test();E:\\DB\\CASIC_use\\onLine\\CASIA-OLHWDB1.0\\Data\\

            //测试
            //test t = new test();
            //t.tehonToImage("Z:\\newTest\\DB\\0000.newp", "d:\\test\\0800.jpg");
            //t.changeAllTehonToImage("Z:\\newTest\\train\\");
            //t.writeCharToFile("美媒：卡梅隆拟邀中国参与《阿凡达》续集说话汀", "z:\\test\\tt.txt");
            //t.todo();
            //t.getFileCharCount("D:\\学习\\农工大\\DB\\1.0");
            //t.getFileChar("D:\\学习\\农工大\\DB\\1.0\\353.pot");

            //测试dic文件的正确性
            //dicFileLoader dic = new dicFileLoader();
            //dic.readDicFile("z:\\shixi\\TehonTest17-20.ft2");

            //test.tt();

            ////args[0] be used of data file list(*.txt)
            ////args[1] database floder
            ////args[2] target file floder
            ////args[3] encode file
            ////args[4] 
            ////pot文件转转换为TEHONE格式的newP文件

            tranformToNewP tranform = new tranformToNewP(args[0], args[1], args[2]);
            tranform.tranform(args[3], Convert.ToSingle(args[4]), args[5], Convert.ToInt32(args[6]));


            //特征提取



            //test t = new test();
            //t.tehonToImage("E:\\localTest\\db\\0000.newp", "d:\\test\\0000.jpg");

            //tranform.getCentroidDistance(args[3], args[4]);//获取字符边界与的重心的距离的平均值
            //tranform.getCentroidStdDif(args[3],args[4],args[5]);//获取字符边界与的重心的距离的标准差
            //tranform.tranform(args[3],args[4], Convert.ToSingle(args[5]));
            //tranformToNewP tranform = new tranformToNewP("Z:\\CASIA\\onLine\\CASIA-OLHWDB1.1\\Data\\");
            //tranform.tranform("d:\\test\\FilePattern3926.txt");
        }
    }
}
