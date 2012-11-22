using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace CASIA_DB_Reader
{
    public partial class CASIA_DB_Loader : Form
    {
        private Graphics graphics;
        private Drawer drawer;
        public CASIA_DB_Loader()
        {
            InitializeComponent();
            this.graphics = this.panel1.CreateGraphics();
            drawer = new Drawer();
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.openFileDialog1.ShowDialog();
            String fileName = this.openFileDialog1.FileName;
            if (fileName != null && !"".Equals(fileName))
            {
                if (this.dbViewer.Checked == true)
                {
                    drawer.draw(graphics, fileName);
                }
                else { 
                    
                }
            }
        }

        private void openFileDialog1_FileOk(object sender, CancelEventArgs e)
        {

        }

        private void closeToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void panel1_MouseClick(object sender, MouseEventArgs e)
        {
            if (this.dbViewer.Checked == true)
            {
                drawer.drawNext();
            }
            else { 
            
            }
        }

        private void panel1_Paint(object sender, PaintEventArgs e)
        {

        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {

        }
    }
}
