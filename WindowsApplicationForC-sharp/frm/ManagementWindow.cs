using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using Excel = Microsoft.Office.Interop.Excel;

namespace WindowsFormsApplication1.frm
{
    public partial class ManagementWindow : Form
    {
        public ManagementWindow()
        {
            InitializeComponent();
        }

        private void toolTip1_Popup(object sender, PopupEventArgs e)
        {

        }

        private void button8_Click(object sender, EventArgs e)
        {
            // 플레이어 리스트 가져오기

            openFileDialog1.FileName = "";
            // 변수선언
            string sFileName = "";
            
            // 파일 읽기 다이얼로그 호출. 결과가 취소인 경우 여기서 실행을 멈춘다.
            if (this.openFileDialog1.ShowDialog() == DialogResult.Cancel)
            {
                return;
            }


            // 파일 읽기 다이얼로그로 부터 파일경로 획득
            if (!this.openFileDialog1.FileName.Equals(""))
            {
                sFileName = openFileDialog1.FileName;
            }
            else
            {
                return;
            }

            
        }

        /*
            2017.10.22 botbinoo.
            TODO : 프로퍼티에서 데이터를 가져온 뒤 출력에 필요한 형태를 가공하여 리턴합니다.
        */
        private List<List<object>> readableProperties(string fileName)
        {
            List<List<object>> result = new List<List<object>>();

            string[] textValue = System.IO.File.ReadAllLines(fileName);

            if (textValue.Length > 0)
            {
                for (int i = 0; i < textValue.Length; i++)
                {
                    Console.WriteLine("Text File " + (i + 1).ToString() + "번째 줄.");
                    // 읽어온 내용을 한줄 씩 출력 합니다.
                    Console.WriteLine(textValue[i]);
                    Console.WriteLine();
                }
            }

            return result;
        }

        /*
            2017.10.22 botbinoo.
            TODO : 엑셀에서 데이터를 가져온 뒤 출력에 필요한 형태를 가공하여 리턴합니다.
        */
        private List<List<object>> readableExcel( string fileName ) {
            List<List<object>> result = new List<List<object>>();

            Excel.Workbook workbook = null;
            Excel.Worksheet worksheet = null;
            Excel.Application application = null;
            //Application.StartupPath

            //application = new Excel.ApplicationClass();
            // 엑셀 파일 열기
            workbook = application.Workbooks.Open(fileName, 0, true, 5, "", "", true, Microsoft.Office.Interop.Excel.XlPlatform.xlWindows, "\t", false, false, 0, true, 1, 0);
            // 워크시트 인덱스로 접근하기
            worksheet = (Excel.Worksheet)workbook.Worksheets.get_Item(1);
            // 데이터를 읽을 범위 설정
            Excel.Range range = worksheet.get_Range("B2", "C435");

            for (int cnt = 1; cnt <= range.Rows.Count - 1; cnt++)
            {
                //Id = Convert.ToInt32((range.Cells[cnt, 1] as Excel.Range).Value2);
                //name = Convert.ToString((range.Cells[cnt, 2] as Excel.Range).Value2);
            }

            // 해제
            workbook.Close(true, null, null);
            application.Quit();
            releaseObject(worksheet);
            releaseObject(workbook);
            releaseObject(application);

            return result;
        }

        /*
            2017.10.22 botbinoo.
            TODO : 불필요해진 데이터를 GC 합니다.
        */
        private void releaseObject(object obj)
        {
            obj = null;
            GC.Collect();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            // 설정파일 가져오기
        }

        private void button6_Click(object sender, EventArgs e)
        {
            // 설정 저장하기

        }

        private void button2_Click(object sender, EventArgs e)
        {
            // 공지 가져오기
        }

        private void button3_Click(object sender, EventArgs e)
        {
            // 공지 내보내기
        }

        private void button5_Click(object sender, EventArgs e)
        {
            // 공지 리스트 삭제하기
        }

        private void button4_Click(object sender, EventArgs e)
        {
            // 공지 리스트 추가하기
        }

        private void button9_Click(object sender, EventArgs e)
        {
            // 아이템명으로 아이템 코드 조회하기
            // web 연결하여 조회한 결과를 알럿창에 출력.
        }

        private void button1_Click(object sender, EventArgs e)
        {
            // 로그아웃 : 다시 로그인 폼으로
            // global id -> GC

            LoginFrm lgnFrm = new LoginFrm();
            lgnFrm.Show();
            this.Close();
        }

        private void button10_Click(object sender, EventArgs e)
        {
            // 실제 DB 에 저장.
            // 이부분은 web Django / Ruby on Rails / Spring 접근 결과 저장.
            AlertWindow alertWindow = new AlertWindow();
            alertWindow.ShowDialog();
        }
    }
}
