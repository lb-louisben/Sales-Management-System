package com.sales_management_System;

/*-
 * @program: Sales-Management-System
 *
 * @description: Test
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:39
 * */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame implements ActionListener {
    MenuBar menubarxxx = new MenuBar();

    Menu menux1 = new Menu("File");
    Menu menux2 = new Menu("Edit");
    Menu menux3 = new Menu("save");
    Menu menux4 = new Menu("exit");

    MenuItem menuitemx101 = new MenuItem("open");
    MenuItem menuitemx102 = new MenuItem("close");
    MenuItem menuitemx103 = new MenuItem("find");

    Menu menux5 = new Menu("print");
    MenuItem menuitem501 = new MenuItem("preview");
    MenuItem menuitem502 = new MenuItem("printOUT");

    public Test() {
        super();
        menubarxxx.add(menux1);
        menubarxxx.add(menux2);
        menubarxxx.add(menux3);
        menubarxxx.add(menux4);

        menux1.add(menuitemx101);
        menux1.add(menuitemx102);
        menux1.add(menuitemx103);

        menux1.add(menux5);

        menux5.add(menuitem501);
        menux5.add(menuitem502);

        this.setMenuBar(menubarxxx);
        menuitem502.addActionListener(this);
        //将按钮注册给按钮事件的监听者
    }

    public void actionPerformed(ActionEvent e)
    //当按钮按下后的执行方法
    {
        System.out.println("成功了！");
        System.exit(0);       //退出当前的销售信息系统
    }

    public static void main(String args[]) {
        Icon picture1 = new ImageIcon("b1.JPG");
        JLabel logo = new JLabel();
        logo.setIcon(picture1);
        JFrame f = new Test();
        f.setSize(700, 300);
        f.add(logo);
        f.show();
    }
}