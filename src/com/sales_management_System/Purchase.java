package com.sales_management_System;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
//import java.lang.Integer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Purchase extends JFrame implements ActionListener{
    private JLabel idLabel,nameLabel,libraryLabel,priceLabel,saleLabel,newbookLabel;
    private JTextField idField,nameField,libraryField,priceField,saleField,newbookField;
    private JButton submitButton,resetButton;

    public Purchase(){
        super();
        this.setTitle("新机入库");
        this.setSize(300, 600);
        this.setVisible(true);
        this.setResizable(true);
	/*	this.setDefaultCloseOperation(EXIT_ON_CLOSE);//关闭界面时退出JVM虚拟机
		addWindowListener(new WindowAdapter(){       //点击关闭界面的叉号时跳出询问窗口
			  public void windowClosing(WindowEvent e){
				int n=JOptionPane.showConfirmDialog(null, "Are you sure closing this software?","warning",JOptionPane.YES_NO_OPTION);
				if(n==JOptionPane.YES_OPTION)
					System.exit(0);
			  }
		 });*/
        init();
    }

    private void init(){
        idLabel=new JLabel("编号:");
        nameLabel=new JLabel("无人机类型:");
        libraryLabel=new JLabel("进货量:");
        priceLabel=new JLabel("售价:");
        saleLabel=new JLabel("销量:");
        newbookLabel=new JLabel("新机");

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
/*		this.add(saleLabel).setBounds(40, 280, 80, 30);
		this.add(saleField).setBounds(100, 280, 80, 30);
		this.add(newbookLabel).setBounds(40, 340, 80, 30);
		this.add(newbookField).setBounds(100, 340, 80, 30);   */

        this.add(submitButton).setBounds(40, 340, 60, 25);
        this.add(resetButton).setBounds(120, 340, 60, 25);

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);

        submitButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    public void submitButton_actionPerformed(){
        String Id=idField.getText().trim();
        String Name=nameField.getText().trim();
        String Library=libraryField.getText().trim();
        int library=Integer.parseInt(Library);
        String Price=priceField.getText().trim();
        BigDecimal price=new BigDecimal(Price);
        //String Sale=saleField.getText().trim();
        //int sale=Integer.parseInt(Sale);
        //String newbook=newbookField.getText().trim();

        String url = "jdbc:mysql://localhost:3306/book";

        Connection con=null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Statement sta = null;

        if(Id.equals("") ||Name.equals("") || Library.equals("")||Price.equals("")){
            JOptionPane.showMessageDialog(this, "请完善信息", "warning", JOptionPane.WARNING_MESSAGE);
        }else{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, "root", "123456");
                if(con == null){
                    JOptionPane.showMessageDialog(this, "数据连接出错，请稍后重试", "warning", JOptionPane.WARNING_MESSAGE);
                    con.close();
                }else{

                    String sql="insert into book(id, name, library, price, sale) values(?, ?, ?, ?, ?)";
                    ps =  con.prepareStatement(sql);
                    ps.setString(1, Id);
                    ps.setString(2, Name);
                    ps.setInt(3, library);
                    ps.setBigDecimal(4, price);
                    ps.setInt(5, 0);
                    ps.executeUpdate();

                }}catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "数据连接出错，请稍后重试", "warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void resetButton_actionPerformed() {
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submitButton){
            submitButton_actionPerformed();
            JOptionPane.showMessageDialog(null, "无人机已录入库存");
        }else if(e.getSource() == resetButton){
            resetButton_actionPerformed();
        }
    }
}