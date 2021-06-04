
/*-
 * @program: Sales-Management-System
 *
 * @description: com.sales_management_System.User.DeleteUserItemPanel
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:27
 * */

package com.sales_management_System.User;

import dbase.Login.DbaseConnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class DeleteUserItemPanel extends JFrame implements ActionListener {
    private JLabel idLabel, nameLabel, passwordLabel, ageLabel, sexLabel, phoneLabel, addressLabel;
    private JTextField idField, nameField, passwordField, ageField, sexField, phoneField, addressField;
    private JButton indexButton, delButton;

    public DeleteUserItemPanel() {
        super();
        this.setSize(280, 560);
        this.setTitle("删除非法用户");
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

        indexButton = new JButton("查询");
        delButton = new JButton("删除");

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

        this.add(indexButton).setBounds(40, 460, 60, 25);
        this.add(delButton).setBounds(120, 460, 60, 25);

        this.setVisible(true);

        indexButton.addActionListener(this);
        delButton.addActionListener(this);
    }

    public void indexButton_actionPerformed() {
        String userId = idField.getText().trim();

        Connection con = null;
        ResultSet rs = null;
        Statement smt = null;

        con = DbaseConnect.getConn();
        try {
            String sql = "select * from dbase.usr where id='" + userId + "' ";

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
            con.close();
            smt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据连接出错，请稍后重试", "warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void delButton_actionPerformed() {

        String userId = idField.getText().trim();
        Connection con;
        Statement smt;
        try {
            con = DbaseConnect.getConn();
            String sssql = "delete  from dbase.usr where id='" + userId + "' ";
            smt = con.createStatement();
            int k = smt.executeUpdate(sssql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == indexButton) {
            indexButton_actionPerformed();
            JOptionPane.showMessageDialog(null, "查到了");
        } else if (e.getSource() == delButton) {
            delButton_actionPerformed();
            JOptionPane.showMessageDialog(null, "删除成功");
        }
    }
}