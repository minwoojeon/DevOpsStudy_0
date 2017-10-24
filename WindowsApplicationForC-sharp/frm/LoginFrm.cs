using System;
using System.IO;
using System.Net;
using System.Text;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    public partial class LoginFrm : Form
    {
        private const string server = "http://127.0.0.1:807/binooApi/login/";

        public LoginFrm()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            // 로그인
            // 이부분은 web Django / Spring 접근 결과 저장/출력.
            string id = lgnId.Text;
            string pw = lgnPw.Text;
            
            System.Collections.Generic.Dictionary<string, string> item = new System.Collections.Generic.Dictionary<string, string>();
            item.Add("login_id", id);
            item.Add("login_pw", pw);
            string result = (String) WindowsFormsApplication1.frm.AlertWindow.httpAccess(item, server + id).ToString();
            if ("LGNS".Equals(result))
            {
                frm.ManagementWindow managementWindow = new frm.ManagementWindow();
                managementWindow.ShowDialog();
                this.Close();
            }
            else
            {
                MessageBox.Show(result);
            }
        }
    }
}
