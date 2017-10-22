using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    public partial class LoginFrm : Form
    {
        public LoginFrm()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            // 로그인
            // 데이터는 비밀번호만 간단히 암호화하여 웹 전송.
            // 이부분은 web Django / Ruby on Rails / Spring 접근 결과 저장/출력.

            // 성공시
            frm.ManagementWindow managementWindow = new frm.ManagementWindow();
            managementWindow.ShowDialog();
            // 실패시
            frm.AlertWindow alertWindow = new frm.AlertWindow();
            alertWindow.ShowDialog();
        }
    }
}
