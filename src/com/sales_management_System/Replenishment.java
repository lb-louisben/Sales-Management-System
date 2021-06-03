package com.sales_management_System;

/*-
 * @program: Sales-Management-System
 *
 * @description: Replenishment
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:37
 * */


import dbase.Login.DbaseConnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Replenishment extends JFrame implements ActionListener {
    private JLabel idLabel, nameLabel, libraryLabel;
    private JTextField idField, nameField, libraryField;
    private JButton indexButton, submitButton;

    public Replenishment() {
        super();
        this.setSize(300, 700);
        this.setTitle("补货");
        this.setLocation(700, 250);
        this.setVisible(true);
        this.setResizable(true);
        init();
    }

    private void init() {
        idLabel = new JLabel("编号:");
        nameLabel = new JLabel("饮料类型:");
        libraryLabel = new JLabel("进货量:");


        idField = new JTextField(24);
        nameField = new JTextField(24);
        libraryField = new JTextField(24);


        indexButton = new JButton("查询");
        submitButton = new JButton("补充");


        //内容窗格默认布局管理器是FlowLayout
        this.setLayout(null);
        this.add(idLabel).setBounds(40, 40, 80, 30);
        this.add(idField).setBounds(100, 40, 80, 30);
        this.add(nameLabel).setBounds(40, 100, 80, 30);
        this.add(nameField).setBounds(100, 100, 80, 30);
        this.add(libraryLabel).setBounds(40, 160, 80, 30);
        this.add(libraryField).setBounds(100, 160, 80, 30);
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

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "数据库连接错误", "warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void submitButton_actionPerformed() {
        String Id = idField.getText().trim();
        String name = nameField.getText().trim();
        String library = libraryField.getText().trim();
        int surplus = Integer.parseInt(library);

        Connection con;
        PreparedStatement ps;

        if (Id.equals("") || name.equals("") || library.equals("")) {
            JOptionPane.showMessageDialog(this, "连接错误", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                con = DbaseConnect.getConn();
                String sql2 = "update dbase.book set  library = library + ? where id=?";
                ps = con.prepareStatement(sql2);
                ps.setInt(1, surplus);
                ps.setString(2, Id);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "数据库chaozuo连接错误", "warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == indexButton) {
            indexButton_actionPerformed();
        }
        if (e.getSource() == submitButton) {
            submitButton_actionPerformed();
            JOptionPane.showMessageDialog(null, "补货成功");
        }
    }
}