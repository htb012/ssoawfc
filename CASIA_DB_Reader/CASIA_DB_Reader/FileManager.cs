using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;


namespace CASIA_DB_Reader
{
    //对文件开启状态的管理
    public static class FileManager
    {
        /// <summary>
        /// 存放已经开启的文件
        /// </summary>
        static ArrayList openFileList, fileStreamList;


        static FileManager()
        {
            openFileList = new ArrayList();
            fileStreamList = new ArrayList();
        }

        /// <summary>
        /// 开启一个文件
        /// 在开启文件的时候，先对文件是否已经处于开启状态进行判断
        /// </summary>
        /// <param name="fileName"></param>
        /// <returns>文件流</returns>
        public static FileStream open(String fileName)
        {
            FileStream fs = isOpen(fileName);
            if (fs != null)
            {
                return fs;
            }
            else
            {
                fs = File.Open(fileName, FileMode.Open);
                openFileList.Add(fileName);
                fileStreamList.Add(fs);
                return fs;
            }
        }
        /// <summary>
        /// 判断该文件是否开启
        /// </summary>
        /// <param name="fileName"></param>
        /// <returns></returns>
        public static FileStream isOpen(String fileName)
        {
            if (!openFileList.Contains(fileName))
            {
                return null;
            }
            else
            {
                for (int i = 0; i < openFileList.Count; i++)
                {
                    if (fileName.Equals(openFileList[i]))
                    {
                        return (FileStream)fileStreamList[i];
                    }
                }
            }
            return null;
        }
        /// <summary>
        /// 手动关闭一个文件
        /// </summary>
        /// <param name="fileName"></param>
        /// <returns></returns>
        public static bool close(String fileName)
        {
            FileStream fs = isOpen(fileName);
            if (fs != null)
            {
                fs.Close();
                openFileList.Remove(fileName);
                fileStreamList.Remove(fs);
                return true;
            }
            return false;
        }

        /// <summary>
        /// 关闭所有正在打开的文件
        /// </summary>
        public static void closeAll()
        {
            for (int i = 0; i < fileStreamList.Count; i++)
            {
                ((FileStream)fileStreamList[i]).Close();
            }
            openFileList.Clear();
            fileStreamList.Clear();
        }
    }
}
