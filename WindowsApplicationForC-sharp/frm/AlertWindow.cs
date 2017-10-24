using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApplication1.frm
{
    public partial class AlertWindow : Form
    {
        public AlertWindow()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Close(); // 닫기
        }

        private void button2_Click(object sender, EventArgs e)
        {
            // 닫기
        }

        private void AlertWindow_Load(object sender, EventArgs e)
        {

        }
        

        public static Object httpAccess(System.Collections.Generic.Dictionary<string, string> args, string serverAddress )
        {
            // 이부분은 web Django / Spring 접근 결과 저장/출력.
            StringBuilder bufData = new StringBuilder();
            int cnt = 1;
            foreach (string key in args.Keys) {
                bufData.Append( key + "=" + args[key] + (cnt == args.Keys.Count ? "" : "&" ));
            }
            byte[] sendData = UTF8Encoding.UTF8.GetBytes(bufData.ToString());
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(serverAddress);
            request.ContentType = "application/x-www-form-urlencoded; charset=UTF-8";
            request.Method = "POST";
            request.ContentLength = sendData.Length;
            Stream httpStream = request.GetRequestStream();
            httpStream.Write(sendData, 0, sendData.Length);
            try
            {
                WebResponse response = request.GetResponse();
                StreamReader reader = new StreamReader(response.GetResponseStream(), Encoding.UTF8);
                String result = reader.ReadToEnd();
                
                reader.Close();
                response.Close();
                return result;
            }
            catch (Exception)
            {
                MessageBox.Show("");
            }
            return false;
        }
    }
}
