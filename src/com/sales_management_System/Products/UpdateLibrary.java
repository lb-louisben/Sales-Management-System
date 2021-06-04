package com.sales_management_System.Products;

/*-
 * @program: Sales-Management-System
 *
 * @description: UpdateLibrary
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:40
 * */


import dbase.Login.DbaseConnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


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
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭界面时退出JVM虚拟机
//		addWindowListener(new WindowAdapter(){       //点击关闭界面的叉号时跳出询问窗口
//			  public void windowClosing(WindowEvent e){
//				int n=JOptionPane.showConfirmDialog(null, "Are you sure closing this software?","warning",JOptionPane.YES_NO_OPTION);
//				if(n==JOptionPane.YES_OPTION)
//					System.exit(0);
//			  }
//		 });
        init();
    }

    private void init() {
        idLabel = new JLabel("编号:");
        nameLabel = new JLabel("饮料类型:");
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


        inquireButton.addActionListener(this);
        updateButton.addActionListener(this);
    }

    public void inquireButton_actionPerformed() {
        String Id = idField.getText().trim();

        Connection con;
        ResultSet rs;
        Statement smt;
        try {
            con = DbaseConnect.getConn();
            String sql = "select * from dbase.book where id='" + Id + "' ";
            smt = con.createStatement();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                idField.setText(rs.getString("id"));
                nameField.setText(rs.getString("name"));
                libraryField.setText(String.valueOf(rs.getInt("library")));
                priceField.setText(rs.getString("price"));
                saleField.setText(rs.getString("sale"));
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

        Connection con;
        PreparedStatement ps;

        try {
            con = DbaseConnect.getConn();
            String sql = "update dbase.book set name= ? , library= ? , price= ? , sale= ? where id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, Name);
            ps.setInt(2, library);
            ps.setBigDecimal(3, price);
            ps.setInt(4, sale);
            ps.setString(5, Id);
                ps.executeUpdate();

            ps.executeUpdate();

                con.close();
                ps.close();
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