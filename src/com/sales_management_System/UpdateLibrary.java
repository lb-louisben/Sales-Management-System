package com.sales_management_System;

/*-
 * @program: Sales-Management-System
 *
 * @description: UpdateLibrary
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:40
 * */


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;


public class UpdateLibrary extends JFrame implements ActionListener {
    private JLabel idLabel, nameLabel, libraryLabel, priceLabel, saleLabel;
    private JTextField idField, nameField, libraryField, priceField, saleField;
    private JButton inquireButton, updateButton;

    public UpdateLibrary() {
        super();
        this.setSize(280, 560);
        this.setTitle("修改库存");
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
        idLabel = new JLabel("编号:");
        nameLabel = new JLabel("无人机类型:");
        libraryLabel = new JLabel("库存:");
        priceLabel = new JLabel("售价:");
        saleLabel = new JLabel("销量:");

        idField = new JTextField(24);
        nameField = new JTextField(24);
        libraryField = new JTextField(24);
        priceField = new JTextField(24);
        saleField = new JTextField(24);

        inquireButton = new JButton("查询");
        updateButton = new JButton("修改");

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
        this.add(saleLabel).setBounds(40, 280, 80, 30);
        this.add(saleField).setBounds(100, 280, 80, 30);

        this.add(inquireButton).setBounds(40, 460, 60, 25);
        this.add(updateButton).setBounds(120, 460, 60, 25);

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);

        inquireButton.addActionListener(this);
        updateButton.addActionListener(this);
    }

    public void inquireButton_actionPerformed() {
        String Id = idField.getText().trim();

        String url = "jdbc:mysql://localhost:3306/book";
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
                String sql = "select * from book where id='" + Id + "' ";
                smt = con.createStatement();
                rs = smt.executeQuery(sql);
                while (rs.next()) {
                    idField.setText(rs.getString("id"));
                    nameField.setText(rs.getString("name"));
                    libraryField.setText(String.valueOf(rs.getInt("library")));
                    priceField.setText(rs.getString("price"));
                    saleField.setText(rs.getString("sale"));
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

        String Id = idField.getText().trim();
        String Name = nameField.getText().trim();
        String Library = libraryField.getText().trim();
        int library = Integer.parseInt(Library);
        String Price = priceField.getText().trim();
        BigDecimal price = new BigDecimal(Price);
        String Sale = saleField.getText().trim();
        int sale = Integer.parseInt(Sale);

        String url = "jdbc:mysql://localhost:3306/book";
        Connection con = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "123456");
            if (con == null) {
                JOptionPane.showMessageDialog(this, "数据连接出错，请稍后重试", "warning", JOptionPane.WARNING_MESSAGE);
                con.close();
            } else {
                String sql = "update book set name= ? , library= ? , price= ? , sale= ? where id = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, Name);
                ps.setInt(2, library);
                ps.setBigDecimal(3, price);
                ps.setInt(4, sale);
                ps.setString(5, Id);
                ps.executeUpdate();

                ps.executeUpdate();    //查询book的表结构

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
            JOptionPane.showMessageDialog(null, "更新成功");
        }
    }
}