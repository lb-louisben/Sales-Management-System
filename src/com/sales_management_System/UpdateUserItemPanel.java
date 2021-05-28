package com.sales_management_System;

/*-
 * @program: Sales-Management-System
 *
 * @description: UpdateUserItemPanel
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:42
 * */


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class UpdateUserItemPanel extends JFrame implements ActionListener {
    private JLabel idLabel, nameLabel, passwordLabel, ageLabel, sexLabel, phoneLabel, addressLabel;
    private JTextField idField, nameField, passwordField, ageField, sexField, phoneField, addressField;
    private JButton inquireButton, updateButton;

    public UpdateUserItemPanel() {
        super();
        this.setSize(280, 560);
        this.setTitle("修改和完善用户信息");
        this.setVisible(true);
        this.setResizable(true);
		/*this.setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭界面时退出JVM虚拟机
		addWindowListener(new WindowAdapter(){       //点击关闭界面的叉号时跳出询问窗口
			  public void windowClosing(WindowEvent e){
				int n=JOptionPane.showConfirmDialog(null, "Are you sure closing this software?","warning",JOptionPane.YES_NO_OPTION);
				if(n==JOptionPane.YES_OPTION)
					System.exit(0);
			  }
		 });*/
        init();
    }

    private void init() {
        idLabel = new JLabel("用户id:");
        nameLabel = new JLabel("用户名:");
        passwordLabel = new JLabel("密  码:");
        ageLabel = new JLabel("年  龄:");
        sexLabel = new JLabel("性  别:");
        phoneLabel = new JLabel("电  话:");
        addressLabel = new JLabel("地  址:");

        idField = new JTextField(24);
        nameField = new JTextField(24);
        passwordField = new JTextField(24);
        ageField = new JTextField(24);
        sexField = new JTextField(24);
        phoneField = new JTextField(24);
        addressField = new JTextField(24);

        inquireButton = new JButton("查询");
        updateButton = new JButton("修改");

        //内容窗格默认布局管理器是FlowLayout
        this.setLayout(null);
        this.add(idLabel).setBounds(40, 40, 80, 30);
        this.add(idField).setBounds(100, 40, 80, 30);
        this.add(nameLabel).setBounds(40, 100, 80, 30);
        this.add(nameField).setBounds(100, 100, 80, 30);
        this.add(passwordLabel).setBounds(40, 160, 80, 30);
        this.add(passwordField).setBounds(100, 160, 80, 30);
        this.add(ageLabel).setBounds(40, 220, 80, 30);
        this.add(ageField).setBounds(100, 220, 80, 30);
        this.add(sexLabel).setBounds(40, 280, 80, 30);
        this.add(sexField).setBounds(100, 280, 80, 30);
        this.add(phoneLabel).setBounds(40, 340, 80, 30);
        this.add(phoneField).setBounds(100, 340, 80, 30);
        this.add(addressLabel).setBounds(40, 400, 80, 30);
        this.add(addressField).setBounds(100, 400, 80, 30);

        this.add(inquireButton).setBounds(40, 460, 60, 25);
        this.add(updateButton).setBounds(120, 460, 60, 25);

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);

        inquireButton.addActionListener(this);
        updateButton.addActionListener(this);
    }

    public void inquireButton_actionPerformed() {
        String userId = idField.getText().trim();

        String url = "jdbc:mysql://localhost:3306/usr_01";
        Connection con = null;
        ResultSet rs = null;
        Statement smt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "123456");
            if (con == null) {
                JOptionPane.showMessageDialog(this, "数据连接出错，请稍后重试", "warning", JOptionPane.WARNING_MESSAGE);
                con.close();
            } else {
                String sql = "select * from usr where id='" + userId + "' ";
                smt = con.createStatement();
                rs = smt.executeQuery(sql);
                while (rs.next()) {
                    nameField.setText(rs.getString("name"));
                    passwordField.setText(rs.getString("password"));
                    ageField.setText(String.valueOf(rs.getInt("age")));
                    sexField.setText(rs.getString("sex"));
                    phoneField.setText(rs.getString("phone"));
                    addressField.setText(rs.getString("address"));
                }
            }
            con.close();
            smt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据连接出错，请稍后重试", "warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void updateButton_actionPerformed() {

        String userId = idField.getText().trim();
        String userName = nameField.getText().trim();
        String passWord = passwordField.getText().trim();
        String userAge = ageField.getText().trim();
        int Age = Integer.parseInt(userAge);
        String userSex = sexField.getText().trim();
        String userPhone = phoneField.getText().trim();
        String userAddress = addressField.getText().trim();
        String url = "jdbc:mysql://localhost:3306/dbase";
        Connection con = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "root");
            if (con == null) {
                JOptionPane.showMessageDialog(this, "数据连接出错，请稍后重试", "warning", JOptionPane.WARNING_MESSAGE);
                con.close();
            } else {
                String upsql = "update buyer set  name = ? , password= ? , age = ? , sex= ? , phone= ? , address= ?   where id = ?";
                ps = con.prepareStatement(upsql);
                ps.setString(1, userName);
                ps.setString(2, passWord);
                ps.setInt(3, Age);
                ps.setString(4, userSex);
                ps.setString(5, userPhone);
                ps.setString(6, userAddress);
                ps.setString(7, userId);

                ps.executeUpdate();

                con.close();
                ps.close();
            }
        } catch (Exception e) {
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inquireButton) {
            inquireButton_actionPerformed();
        } else if (e.getSource() == updateButton) {
            updateButton_actionPerformed();
            JOptionPane.showMessageDialog(null, "修改成功");
        }
    }
}