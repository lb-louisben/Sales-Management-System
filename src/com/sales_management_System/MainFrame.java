package com.sales_management_System;

/*-
 * @program: Sales-Management-System
 *
 * @description: MainFrame
 *
 * @author: LOUIS
 *
 * @creat: 2021-05-28-20:34
 * */


//模块功能：登录成功以后，显示整个系统的主界面（此为最重要界面）

import com.sales_management_System.Products.*;
import com.sales_management_System.User.DeleteUserItemPanel;
import com.sales_management_System.User.Inquire;
import com.sales_management_System.User.Regist;
import com.sales_management_System.User.UpdateUserItemPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {



    MenuBar menubarxxx = new MenuBar();

    Menu menux1 = new Menu("用户管理");
    Menu menux2 = new Menu("库存管理");
    Menu menux3 = new Menu("进货管理");
    Menu menux4 = new Menu("销售管理");
    Menu menux5 = new Menu("帮助");
    Menu menux6 = new Menu("退出系统");


    //用户管理
    MenuItem menuitemx101 = new MenuItem("删除用户");
    MenuItem menuitemx102 = new MenuItem("修改用户");
    MenuItem menuitemx103 = new MenuItem("查询用户");
    MenuItem menuitemx104 = new MenuItem("添加用户");

    //库存管理
    MenuItem menuitemx201 = new MenuItem("查询库存");
    MenuItem menuitemx202 = new MenuItem("修改库存");

    //进货管理
    MenuItem menuitemx301 = new MenuItem("新饮品入库");
    MenuItem menuitemx302 = new MenuItem("补货");

    //销售管理
    MenuItem menuitemx401 = new MenuItem("销售管理");

    //
    MenuItem menuItem601 = new MenuItem("关于");
    //
    MenuItem menuitem501 = new MenuItem("保存并退出");

    public MainFrame() {
        super();
        menubarxxx.add(menux1);
        menubarxxx.add(menux2);
        menubarxxx.add(menux3);
        menubarxxx.add(menux4);
        menubarxxx.add(menux5);
        menubarxxx.add(menux6);

        //用户管理
        menux1.add(menuitemx101);
        menux1.add(menuitemx102);
        menux1.add(menuitemx103);
        menux1.add(menuitemx104);

        //库存管理
        menux2.add(menuitemx201);
        menux2.add(menuitemx202);

        //进货管理
        menux3.add(menuitemx301);
        menux3.add(menuitemx302);

        //销售管理
        menux4.add(menuitemx401);

        //
        menux5.add(menuItem601);

        //
        menux6.add(menuitem501);

        this.setMenuBar(menubarxxx);
        menuitem501.addActionListener(this);
        //将按钮注册给按钮事件的监听者
        menuitemx101.addActionListener(this);
        menuitemx102.addActionListener(this);
        menuitemx103.addActionListener(this);
        menuitemx104.addActionListener(this);

        menuitemx201.addActionListener(this);
        menuitemx202.addActionListener(this);

        menuitemx301.addActionListener(this);
        menuitemx302.addActionListener(this);

        menuitemx401.addActionListener(this);
        menuItem601.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    //当按钮按下后的执行方法
    {
        if (e.getSource() == menuitem501) {
            System.out.println("成功了！");
            System.exit(0);       //退出当前的销售信息系统
        } else if (e.getSource() == menuitemx101) {                //删除用户
            DeleteUserItemPanel deleteUserItemPanel = new DeleteUserItemPanel();
        } else if (e.getSource() == menuitemx102) {                //修改用户
            UpdateUserItemPanel updateUserItemPanel = new UpdateUserItemPanel();
        } else if (e.getSource() == menuitemx103) {                //查询用户
            Inquire inquire = new Inquire();
        } else if (e.getSource() == menuitemx104) {                //添加用户
            Regist regist = new Regist();
        } else if (e.getSource() == menuitemx201) {                //查询库存
            InquireLibrary inquirelibrary = new InquireLibrary();
        } else if (e.getSource() == menuitemx202) {                //修改库存
            UpdateLibrary updatelibrary = new UpdateLibrary();
        } else if (e.getSource() == menuitemx301) {                //饮料进货入库
            Purchase purchase = new Purchase();
        } else if (e.getSource() == menuitemx302) {                //补货
            Replenishment replenishment = new Replenishment();
        } else if (e.getSource() == menuitemx401) {                //购买
            Sale sale = new Sale();
        } else if (e.getSource() == menuItem601) {
            Help help = new Help();
        }
    }
}