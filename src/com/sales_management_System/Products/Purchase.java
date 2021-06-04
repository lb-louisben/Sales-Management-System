package com.sales_management_System.Products;

/*-
 * @program: Sales-Management-System
 *
 * @description: Purchase
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:35
 * */

//模块功能：新用户注册

import dbase.Login.DbaseConnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Purchase extends JFrame implements ActionListener{
    private JLabel idLabel,nameLabel,libraryLabel,priceLabel,saleLabel,newbookLabel;
    private JTextField idField,nameField,libraryField,priceField,saleField,newbookField;
    private JButton submitButton,resetButton;

    public Purchase(){
        super();
        this.setTitle("新品入库");
        this.setSize(300, 600);
        this.setVisible(true);
        this.setResizable(true);
        init();
    }

    private void init(){
        idLabel=new JLabel("编号:");
        nameLabel=new JLabel("饮料类型:");
        libraryLabel=new JLabel("进货量:");
        priceLabel=new JLabel("售价:");
        saleLabel=new JLabel("销量:");
        newbookLabel=new JLabel("新品");

        idField=new JTextField(24);
        nameField=new JTextField(24);
        libraryField=new JTextField(24);
        priceField=new JTextField(24);
        saleField=new JTextField(24);
        newbookField=new JTextField("24");

        submitButton=new JButton("确定");
        resetButton=new JButton("重置");

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


        this.add(submitButton).setBounds(40, 340, 60, 25);
        this.add(resetButton).setBounds(120, 340, 60, 25);

        submitButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    public void submitButton_actionPerformed() {
        String Id = idField.getText().trim();
        String Name = nameField.getText().trim();
        String Library = libraryField.getText().trim();
        int library = Integer.parseInt(Library);
        String Price = priceField.getText().trim();
        BigDecimal price = new BigDecimal(Price);

        Connection con;
        PreparedStatement ps;

        if (Id.equals("") || Name.equals("") || Library.equals("") || Price.equals("")) {
            JOptionPane.showMessageDialog(this, "请完善信息", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                con = DbaseConnect.getConn();
                String sql = "insert into dbase.book(id, name, library, price, sale) values(?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, Id);
                ps.setString(2, Name);
                ps.setInt(3, library);
                ps.setBigDecimal(4, price);
                ps.setInt(5, 0);
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "数据库操作失败，请稍后重试", "warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void resetButton_actionPerformed() {
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submitButton){
            submitButton_actionPerformed();
            JOptionPane.showMessageDialog(null, "饮料已录入库存");
        }else if(e.getSource() == resetButton){
            resetButton_actionPerformed();
        }
    }
}