using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;

namespace CASIA_DB_Reader
{
    /// <summary>
    /// 数据库文件的读取，将被potLoader,pptsLoader,gntLoader,dgrLoader实现
    /// </summary>
    interface DBReader
    {

        /// <summary>
        /// 开启一个文件
        /// </summary>
        /// <param name="fileName"></param>
        /// <returns></returns>
        bool load(String fileName);
        /// <summary>
        /// 读取一个CASIA数据库文件中的一个文字信息
        /// 返回一张150PX*150PX像素的文字图片
        /// </summary>
        /// <returns></returns>
        Image nextChar();
        /// <summary>
        /// 读取一个CASIA onLine数据库文件中的一个文字信息,
        /// 并返回文字的所有坐标信息
        /// </summary>
        /// <returns></returns>
        CharPattern next();
    }
}
