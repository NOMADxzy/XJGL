package gui.query;
import java.lang.Thread;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

public class Main{


    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setTitle("查询信息");
        frame.setSize(400,320);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);

        Container container = frame.getContentPane();
        JPanel north = new JPanel();
        JPanel center = new JPanel();
        JPanel south = new JPanel();

        BorderLayout bl = new BorderLayout(5,5);
        frame.setLayout(bl);
        Dimension shape = new Dimension(240,30);

        JLabel label = new JLabel("关键字");
        north.add(label);

        JTextField text = new JTextField();
        text.setPreferredSize(shape);
        north.add(text);
        container.add(north,BorderLayout.NORTH);

        JLabel ways_text = new JLabel("查询方式");
        center.add(ways_text);

        ButtonGroup bg = new ButtonGroup();
        JRadioButton rb_sno = new JRadioButton("学号",true);
        JRadioButton rb_sna = new JRadioButton("姓名",false);
        JRadioButton rb_maj = new JRadioButton("专业",false);
        bg.add(rb_sno);
        bg.add(rb_sna);
        bg.add(rb_maj);
        center.add(rb_sno);
        center.add(rb_sna);
        center.add(rb_maj);

        JButton summit = new JButton("查询");
        summit.setPreferredSize(new Dimension(60,30));
        center.add(summit);

        JButton back = new JButton("返回");
        back.setPreferredSize(shape);
        center.add(back);
        container.add(center,BorderLayout.CENTER);
        frame.setVisible(true);

        Thread.sleep(2000);
        frame.setVisible(false);
        frame.setSize(470,320);
        JPanel panel = new JPanel();
        panel.add(new JButton("1"));
        frame.getContentPane().add(panel,BorderLayout.SOUTH);
        Thread.sleep(2000);
        frame.getContentPane().remove(3);
        frame.setVisible(true);

    }

}