package com.sales_management_System;

/*-
 * @program: Sales-Management-System
 *
 * @description: Help
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:39
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Help extends JFrame implements ActionListener {
    Label lbl = new Label();

    public Help() {
        super("“娃哈哈”饮料销售系统");
        this.setLayout(new FlowLayout());
        Label lbl1 = new Label("“娃哈哈”饮料销售系统说明");
        lbl1.setForeground(new Color(0, 1, 0));
        lbl1.setFont(new Font("宋体", Font.BOLD, 40));
        this.add(lbl1);
        Label lbl2 = new Label("“娃哈哈”饮料销售系统结构是有登录页面，注册页面，用户管理，储存管理，");
        lbl2.setForeground(new Color(0, 1, 0));
        lbl2.setFont(new Font("宋体", Font.BOLD, 20));
        this.add(lbl2);
        Label lbl3 = new Label("销售管理，帮助，退出系统六大选项，用户管理又分4个小点：删除，修改，");
        lbl3.setForeground(new Color(0, 1, 0));
        lbl3.setFont(new Font("宋体", Font.BOLD, 20));
        this.add(lbl3);
        Label lbl4 = new Label("查询以及注册，库存管理又分两个小点：查找，并修改库存；进货管理主要是");
        lbl4.setForeground(new Color(0, 1, 0));
        lbl4.setFont(new Font("宋体", Font.BOLD, 20));
        this.add(lbl4);
        Label lbl5 = new Label("\"对新的产品和老的产品的录入和介绍，销售管理系统针对产品的介绍，并卖");
        lbl5.setForeground(new Color(0, 1, 0));
        lbl5.setFont(new Font("宋体", Font.BOLD, 20));
        this.add(lbl5);
        Label lbl6 = new Label("掉了多少产品。帮助中的关于是对系统的简单介绍。最后退出系统结束操作。");
        lbl6.setForeground(new Color(0, 1, 0));
        lbl6.setFont(new Font("宋体", Font.BOLD, 20));
        this.add(lbl6);
        this.setSize(800, 500);
        this.setTitle("关于");
        this.setLocation(800, 300);
        this.setVisible(true);
        this.setResizable(true);
    }
    public void actionPerformed(ActionEvent e) {
    }
}