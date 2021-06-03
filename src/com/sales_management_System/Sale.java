package com.sales_management_System;

/*-
 * @program: Sales-Management-System
 *
 * @description: Sale
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:38
 * */


import dbase.Login.DbaseConnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Sale extends JFrame implements ActionListener {
    private JLabel idLabel, nameLabel, libraryLabel, priceLabel, numLabel;
    private JTextField idField, nameField, libraryField, priceField, numField;
    private JButton indexButton, submitButton;

    public Sale() {
        super();
        this.setSize(300, 700);
        this.setTitle("销售管理");
        this.setLocation(700, 250);
        this.setVisible(true);
        this.setResizable(true);
        init();
    }

    private void init() {
        idLabel = new JLabel("编号:");
        nameLabel = new JLabel("饮料类型:");
        libraryLabel = new JLabel("库存:");
        priceLabel = new JLabel("售价:");
        numLabel = new JLabel("销售数量:");


        idField = new JTextField(24);
        nameField = new JTextField(24);
        libraryField = new JTextField(24);
        priceField = new JTextField(24);
        numField = new JTextField(24);


        indexButton = new JButton("查询");
        submitButton = new JButton("确认");


        //内容窗格默认布局管理器是FlowLayout
        this.setLayout(null);
        this.add(idLabel).setBounds(40, 40, 80, 30);
        this.add(idField).setBounds(100, 40, 80, 30);
        this.add(nameLabel).setBounds(40, 100, 80, 30);
        this.add(nameField).setBounds(100, 100, 80, 30);
        this.add(libraryLabel).setBounds(40, 160, 80, 30);
        this.add(libraryField).setBounds(100, 160, 80, 30);
        this.add(priceLabel).setBounds(40, 220, 80, 30);
        this.add(priceField).setBounds(100, 220, 80, 30);
        this.add(numLabel).setBounds(40, 280, 80, 30);
        this.add(numField).setBounds(100, 280, 80, 30);
        this.add(indexButton).setBounds(40, 460, 60, 25);
        this.add(submitButton).setBounds(120, 460, 60, 25);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        indexButton.addActionListener(this);
        submitButton.addActionListener(this);
    }

    public void indexButton_actionPerformed() {
        String Id = idField.getText().trim();

        Connection con;
        ResultSet rs;
        Statement smt;
        try {
            con = DbaseConnect.getConn();
            String sql = "select * from dbase.book where id= '" + Id + "' ";
            smt = con.createStatement();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                nameField.setText(rs.getString("name"));
                libraryField.setText(rs.getString("library"));
                libraryField.setText(String.valueOf(rs.getInt("library")));
                priceField.setText(String.valueOf(rs.getBigDecimal("price")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库连接错误", "warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void submitButton_actionPerformed() {
        String Id = idField.getText().trim();
        String num = numField.getText().trim();
        int number = Integer.parseInt(num);

        Connection con;
        PreparedStatement ps;

        if (Id.equals("") || num.equals("")) {
            JOptionPane.showMessageDialog(this, "连接错误", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                con = DbaseConnect.getConn();
                String sql2 = "update dbase.book set  library = library - ? , sale = sale + ? where id=?";
                ps = con.prepareStatement(sql2);
                ps.setInt(1, number);
                ps.setInt(2, number);
                ps.setString(3, Id);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "数据库连接错误", "warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == indexButton) {
            indexButton_actionPerformed();
        }
        if (e.getSource() == submitButton) {
            submitButton_actionPerformed();
            JOptionPane.showMessageDialog(null, "库存更新成功");
        }
    }
}