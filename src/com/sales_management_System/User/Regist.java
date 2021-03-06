package com.sales_management_System.User;

/*-
 * @program: Sales-Management-System
 *
 * @description: Regist
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:36
 * */


//模块功能：新用户注册

import dbase.Login.DbaseConnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Regist extends JFrame implements ActionListener {
    private JLabel idLabel, nameLabel, passwordLabel, ageLabel, sexLabel, phoneLabel, addressLabel;
    private JTextField idField, nameField, passwordField, ageField, sexField, phoneField, addressField;
    private JButton submitButton, resetButton;

    public Regist() {
        super();
        this.setTitle("新用户信息注册");
        this.setSize(300, 600);
        this.setVisible(true);
        this.setResizable(true);
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

        submitButton = new JButton("确定");
        resetButton = new JButton("重置");

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

        this.add(submitButton).setBounds(40, 460, 60, 25);
        this.add(resetButton).setBounds(120, 460, 60, 25);

        submitButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    public void submitButton_actionPerformed() {
        String userId = idField.getText().trim();
        String userName = nameField.getText().trim();
        String passWord = passwordField.getText().trim();
        String userAge = ageField.getText().trim();
        int Age = Integer.parseInt(userAge);
        String userSex = sexField.getText().trim();
        String userPhone = phoneField.getText().trim();
        String userAddress = addressField.getText().trim();


        Connection con ;
        PreparedStatement ps ;

        if (userId.equals("") || userName.equals("") || passWord.equals("") || userAge.equals("") || userSex.equals("") || userPhone.equals("") || userAddress.equals("")) {
            JOptionPane.showMessageDialog(this, "请完善登录信息", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                con = DbaseConnect.getConn();
                String sql = "insert into dbase.usr(id, name, password, age, sex, phone, address) values(?, ?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, userId);
                ps.setString(2, userName);
                ps.setString(3, passWord);
                ps.setInt(4, Age);
                ps.setString(5, userSex);
                ps.setString(6, userPhone);
                ps.setString(7, userAddress);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "数据连接出错，请稍后重试", "warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void resetButton_actionPerformed() {
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            submitButton_actionPerformed();
            JOptionPane.showMessageDialog(null, "注册成功");
        } else if (e.getSource() == resetButton) {
            resetButton_actionPerformed();
        }
    }
}