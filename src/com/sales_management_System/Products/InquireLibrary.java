package com.sales_management_System.Products;

/*-
 * @program: Sales-Management-System
 *
 * @description: InquireLibrary
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:32
 * */


import dbase.Login.DbaseConnect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class InquireLibrary extends JFrame implements ActionListener {
    private JLabel idLabel, nameLabel, libraryLabel, priceLabel, saleLabel;
    private JTextField idField, nameField, libraryField, priceField, saleField;
    private JButton indexButton, delButton;

    public InquireLibrary() {
        super();
        this.setSize(280, 560);
        this.setTitle("查询库存");
        this.setVisible(true);
        this.setResizable(true);
/*		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭界面时退出JVM虚拟机
		addWindowListener(new WindowAdapter(){       //点击关闭界面的叉号时跳出询问窗口
			  public void windowClosing(WindowEvent e){
				int n=JOptionPane.showConfirmDialog(null, "Are you sure closing this software?","warning",JOptionPane.YES_NO_OPTION);
				if(n==JOptionPane.YES_OPTION)
					System.exit(0);
			  }
		 });        */
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

        indexButton = new JButton("查询");

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


        this.add(indexButton).setBounds(100, 380, 60, 25);

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);

        indexButton.addActionListener(this);

    }

    public void indexButton_actionPerformed() {
        String Id = idField.getText().trim();

        String url = "jdbc:mysql://localhost:3306/dbase";
        Connection con ;
        ResultSet rs ;
        Statement smt ;
        try {
            con = DbaseConnect.getConn();
            String sql = "select * from dbase.book where id='" + Id + "' ";
            smt = con.createStatement();
            rs = smt.executeQuery(sql);
            while (rs.next()) {
                nameField.setText(rs.getString("name"));
                libraryField.setText(rs.getString("library"));
                priceField.setText(String.valueOf(rs.getInt("price")));
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

    public void delButton_actionPerformed() {

        String Id = idField.getText().trim();
        Connection con ;
        Statement smt;
        try {
            con = DbaseConnect.getConn();

            String sssql = "delete  from dbase.book where id='" + Id + "' ";
            smt = con.createStatement();
            int k = smt.executeUpdate(sssql);
        } catch (Exception e) {
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == indexButton) {
            indexButton_actionPerformed();
        } else if (e.getSource() == delButton) {
            delButton_actionPerformed();
        }
    }
}
