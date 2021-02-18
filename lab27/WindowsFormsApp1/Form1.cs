using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Security.Cryptography;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsFormsApp1;
using System.IO;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            string s = "";
            StreamReader sr = new StreamReader("in.txt");
            while (!sr.EndOfStream)
            {
                s += sr.ReadLine();
            }
            sr.Close();
            textBox1.Text = s;
        }

        private void button1_Click(object sender, EventArgs e)
        {

            


            textBox2.Text = Hashing.ComputeHash(textBox1.Text, Hashing.Supported_HA.SHA256, null);
          
            StreamWriter sw = new StreamWriter("out1.txt");
            sw.WriteLine(textBox2.Text);
            sw.Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            label1.Text = (Hashing.Confirm(textBox1.Text, textBox2.Text, Hashing.Supported_HA.SHA256) ? "Верно" : "что-то пошло не так");
        }

        private void label2_Click(object sender, EventArgs e)
        {

        }
    }
}

