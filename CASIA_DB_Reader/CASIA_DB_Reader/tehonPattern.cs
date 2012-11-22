using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CASIA_DB_Reader
{
    class tehonPattern
    {
        /// <summary>
        /// 笔的状态,落笔
        /// </summary>
        public const short DOWN = 2;
        /// <summary>
        /// 笔的状态,运笔
        /// </summary>
        public const short MOVE = 0;
        /// <summary>
        /// 笔的状态,起笔
        /// </summary>
        public const short UP = 4;

        public  String tagCode { get; set; }

        public List<tPoint> tPoints { get; set; }

        public tehonPattern(){
            tPoints = new List<tPoint>();
        }

    }

    /// <summary>
    /// 用于tehon的点的表示，比原有point类，多一个关于点的状态pState的表示，
    /// </summary>
    class tPoint:point{
        /// <summary>
        /// point的状态，笔的状态（2：落笔；0：运笔；4：起笔，收笔）
        /// </summary>
        public short pState { get; set; }

        public tPoint(short x, short y, short pState)
        {
            this.x = x;
            this.y = y;
            this.pState = pState;
        }
    }
}
