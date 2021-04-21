package gui.query.query1;
import gui.query.BackToQueryListener;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;

public class Query1Listener implements ActionListener {
    private JFrame old_frame;

    public Query1Listener(JFrame old_frame) {
        this.old_frame = old_frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
//        back.setPreferredSize(shape);
        center.add(back);
        container.add(center,BorderLayout.CENTER);
        old_frame.dispose();
        frame.setVisible(true);

        AddTableListener atl = new AddTableListener(frame,bg,text);
        summit.addActionListener(atl);
        BackToQueryListener btq = new BackToQueryListener(frame);
        back.addActionListener(btq);
    }
}

//public class Query1Listener implements ActionListener {
//    private JFrame old_frame;
//
//    public Query1Listener(JFrame old_frame) {
//        this.old_frame = old_frame;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JFrame frame = new JFrame();
//        frame.setTitle("查询信息");
//        frame.setSize(400,320);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(3);
//        frame.setResizable(false);
//
//        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,30);
//        frame.setLayout(fl);
//        Dimension shape = new Dimension(240,30);
//
//        JLabel label = new JLabel("关键字");
//        frame.add(label);
//
//        JTextField text = new JTextField();
//        text.setPreferredSize(shape);
//        frame.add(text);
//
//        JLabel ways_text = new JLabel("查询方式");
//        frame.add(ways_text);
//
//        ButtonGroup bg = new ButtonGroup();
//        JRadioButton rb_sno = new JRadioButton("学号",true);
//        JRadioButton rb_sna = new JRadioButton("姓名",false);
//        JRadioButton rb_maj = new JRadioButton("专业",false);
//        bg.add(rb_sno);
//        bg.add(rb_sna);
//        bg.add(rb_maj);
//        frame.add(rb_sno);
//        frame.add(rb_sna);
//        frame.add(rb_maj);
//
//        JButton summit = new JButton("查询");
//        summit.setPreferredSize(new Dimension(60,30));
//        frame.add(summit);
//
//        JButton back = new JButton("返回");
//        back.setPreferredSize(shape);
//        frame.add(back);
//        old_frame.dispose();
//        JFrame frame0 = frame;
//        frame0.setVisible(true);
//        frame.setVisible(true);
//
//        AddTableListener atl = new AddTableListener(frame,bg,text);
//        summit.addActionListener(atl);
//        BackToQueryListener btq = new BackToQueryListener(frame);
//        back.addActionListener(btq);
//    }
//}

