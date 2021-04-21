package gui;
import gui.add.AddListener;
import gui.query.QueryListener;
import gui.query.query1.Query1Listener;
import gui.query.query2.Query2Listener;
import gui.query.query3.Query3Listener;
import gui.query.query4.Query4Listener;
import gui.query.query5.Query5Listener;
import gui.set.SetListener;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;

public class UI_sets {
    public static void main(String[] args) {
        UI_sets UIsets = new UI_sets();
        UIsets.initUI();

    }
    public void initUI(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setTitle("学籍管理");
        frame.setSize(400,320);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,10);
        frame.setLayout(fl);

        ImageIcon picture = new ImageIcon("picture/xd_logo.jpeg","西安电子科技大学");
        JLabel img = new JLabel(picture);
        Dimension dim_img = new Dimension(400,150);
        img.setPreferredSize(dim_img);
        frame.add(img);

        JLabel zh = new JLabel("账号：");
        frame.add(zh);

        JTextField textfield = new JTextField();
        Dimension dim1 = new Dimension(300,30);
        textfield.setPreferredSize(dim1);
        frame.add(textfield);

        JLabel mm = new JLabel("密码：");
        frame.add(mm);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(dim1);
        frame.add(passwordField);

        Dimension dim2 = new Dimension(100,30);
        JButton button = new JButton("登录");
        button.setPreferredSize(dim2);
        frame.add(button);
        JButton button_zc = new JButton("注册");
        button_zc.setPreferredSize(dim2);
        frame.add(button_zc);

        frame.setVisible(true);
        LoginListener ll = new LoginListener(frame,textfield,passwordField);
        button.addActionListener(ll);
    }
    public void menu() {
        JFrame frame = new JFrame();
        frame.setTitle("主菜单");
        frame.setSize(400,320);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);

        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,77);
        frame.setLayout(fl);

        Dimension shape = new Dimension(120,30);

        JButton add = new JButton("新增学生");
        add.setPreferredSize(shape);
        frame.add(add);

        JButton setgrade = new JButton("录入成绩");
        setgrade.setPreferredSize(shape);
        frame.add(setgrade);

        JButton query = new JButton("查询信息");
        query.setPreferredSize(shape);
        frame.add(query);

        JButton esc = new JButton("切换用户");
        esc.setPreferredSize(shape);
        frame.add(esc);

        frame.setVisible(true);
        AddListener al = new AddListener(frame);
        add.addActionListener(al);
        SetListener sl = new SetListener(frame);
        setgrade.addActionListener(sl);
        QueryListener ql = new QueryListener(frame);
        query.addActionListener(ql);
        SwitchAccount sa = new SwitchAccount(frame);
        esc.addActionListener(sa);
    }
    public void community_menu(){
        JFrame frame = new JFrame();
        frame.setTitle("查询信息");
        frame.setSize(400,320);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);

        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,30);
        frame.setLayout(fl);

        Dimension shape = new Dimension(180,30);
        String[] choices = {"查询学生信息","查询所修课程","查询平均成绩","查询授课教师"};
        JButton[] tfs = new JButton[5];

        for(int i=0;i<4;i++){
            tfs[i] = new JButton(choices[i]);
            tfs[i].setPreferredSize(shape);
            frame.add(tfs[i]);
        }
        tfs[4] = new JButton("查询快被开除的学生");
        tfs[4].setPreferredSize(new Dimension(360,30));
        frame.add(tfs[4]);

        JButton back = new JButton("切换用户");
        back.setPreferredSize(new Dimension(100,25));
        frame.add(back);
        frame.setVisible(true);

        Query1Listener q1l = new Query1Listener(frame);
        tfs[0].addActionListener(q1l);
        Query2Listener q2l = new Query2Listener(frame);
        tfs[1].addActionListener(q2l);
        Query3Listener q3l = new Query3Listener(frame);
        tfs[2].addActionListener(q3l);
        Query4Listener q4l = new Query4Listener(frame);
        tfs[3].addActionListener(q4l);
        Query5Listener q5l = new Query5Listener(frame);
        tfs[4].addActionListener(q5l);
        SwitchAccount sa = new SwitchAccount(frame);
        back.addActionListener(sa);
    }

}
