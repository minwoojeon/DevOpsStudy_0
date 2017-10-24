namespace WindowsFormsApplication1
{
    partial class LoginFrm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.label4 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.lgnId = new System.Windows.Forms.TextBox();
            this.lgnPw = new System.Windows.Forms.TextBox();
            this.toolTip1 = new System.Windows.Forms.ToolTip(this.components);
            this.lgnBtn = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(30, 25);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(16, 12);
            this.label4.TabIndex = 0;
            this.label4.Text = "ID";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(30, 65);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(23, 12);
            this.label2.TabIndex = 1;
            this.label2.Text = "PW";
            // 
            // lgnId
            // 
            this.lgnId.Location = new System.Drawing.Point(100, 25);
            this.lgnId.MaxLength = 30;
            this.lgnId.Name = "lgnId";
            this.lgnId.Size = new System.Drawing.Size(100, 21);
            this.lgnId.TabIndex = 2;
            // 
            // lgnPw
            // 
            this.lgnPw.Location = new System.Drawing.Point(100, 65);
            this.lgnPw.MaxLength = 50;
            this.lgnPw.Name = "lgnPw";
            this.lgnPw.Size = new System.Drawing.Size(100, 21);
            this.lgnPw.TabIndex = 3;
            // 
            // lgnBtn
            // 
            this.lgnBtn.Location = new System.Drawing.Point(230, 21);
            this.lgnBtn.Name = "lgnBtn";
            this.lgnBtn.Size = new System.Drawing.Size(75, 63);
            this.lgnBtn.TabIndex = 4;
            this.lgnBtn.Text = "접속";
            this.lgnBtn.UseVisualStyleBackColor = true;
            this.lgnBtn.Click += new System.EventHandler(this.button1_Click);
            // 
            // LoginFrm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(326, 117);
            this.Controls.Add(this.lgnBtn);
            this.Controls.Add(this.lgnPw);
            this.Controls.Add(this.lgnId);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label4);
            this.Name = "LoginFrm";
            this.Text = "LoginFrm";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox lgnId;
        private System.Windows.Forms.TextBox lgnPw;
        private System.Windows.Forms.ToolTip toolTip1;
        private System.Windows.Forms.Button lgnBtn;
    }
}

