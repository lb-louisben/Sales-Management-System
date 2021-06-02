package com.sales_management_System;

/*-
 * @program: Sales-Management-System
 *
 * @description: Login
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:33
 * */

//模块功能：1.登陆界面，处理登陆信息; 2.注册页面，新用户注册

import dbase.Login.DbaseConnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {
    protected JTextField nameField;
    protected JPasswordField passwordField;
    private JButton loginButton, registButton;

    public Login() {
        super();
        this.setTitle("用户登录");
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "Are you sure closing this software?", "warning", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
        init();
    }

    private void init() {

        JLabel nameLabel = new JLabel("用户名 :");
        nameField = new JTextField(24);
        JLabel passwordLabel = new JLabel("  密码 :");
        passwordField = new JPasswordField(18);
        loginButton = new JButton("登录");
        registButton = new JButton("注册");

        //内容窗格默认布局管理器是FlowLayout
        this.setLayout(null);
        this.add(nameLabel).setBounds(40, 40, 80, 30);
        this.add(nameField).setBounds(100, 40, 80, 30);
        this.add(passwordLabel).setBounds(40, 100, 80, 30);
        this.add(passwordField).setBounds(100, 100, 80, 30);
        this.add(loginButton).setBounds(40, 160, 60, 25);
        this.add(registButton).setBounds(120, 160, 60, 25);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        loginButton.addActionListener(this);
        registButton.addActionListener(this);
    }

    public void loginButton_actionPerformed() {
        String userName = nameField.getText().trim();
        String passWord = new String(passwordField.getPassword()).trim();

        Connection con;
        Statement sta;
        ResultSet rs;
        boolean flag = false;

        if (userName.equals("") || passWord.equals("")) {
            JOptionPane.showMessageDialog(this, "请完善登录信息", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                con = DbaseConnect.getConn();
                if (con == null) {
                    JOptionPane.showMessageDialog(this, "数据连接出错，请稍后重试", "warning", JOptionPane.WARNING_MESSAGE);
                    con.close();
                } else {

                    String sql = "SELECT * FROM dbase.usr WHERE name='" + userName + "' AND password='" + passWord + "' ";
                    sta = con.createStatement();
                    rs = sta.executeQuery(sql);
                    String u_name, u_password;
                    while (rs.next()) {
                        u_name = rs.getString("name");
                        u_password = rs.getString("password");
                        if (u_name.equals(userName) && u_password.equals(passWord)) {
                            flag = true;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "数据连接出错，请稍后重试", "warning", JOptionPane.WARNING_MESSAGE);
            } finally {
                if (!flag) {
//                    System.out.println("查到数据了！");
                    this.setVisible(false);
                    Icon picture1 = new ImageIcon("src/pic.jpg");
                    JLabel logo = new JLabel();
                    logo.setIcon(picture1);
                    JFrame f = new MainFrame();
                    f.setSize(730, 487);
                    f.setTitle("'娃哈哈'饮料销售系统");
                    f.add(logo);
                    f.show();
                } else {
                    JOptionPane.showMessageDialog(this, "用户名或密码输入错误，请重新输入", "warning", JOptionPane.WARNING_MESSAGE);
                    nameField.setText("");
                    passwordField.setText("");
                }
            }
        }
    }

    public void registButton_actionPerformed() {

        this.setVisible(false);
        Regist regist = new Regist();
        regist.setSize(280, 560);
        regist.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            loginButton_actionPerformed();
        } else if (e.getSource() == registButton) {
            registButton_actionPerformed();
        }
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.setSize(300, 300);
        login.setVisible(true);
    }
}