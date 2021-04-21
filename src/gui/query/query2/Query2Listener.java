package gui.query.query2;
import gui.query.BackToQueryListener;
import gui.query.query1.AddTableListener;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;

public class Query2Listener implements ActionListener {
    private JFrame old_frame;

    public Query2Listener(JFrame old_frame) {
        this.old_frame = old_frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame();
        frame.setTitle("查询课程信息");
        frame.setSize(400,320);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        Container container = frame.getContentPane();
        JPanel north = new JPanel();

        BorderLayout bl = new BorderLayout(5,5);
        frame.setLayout(bl);
        Dimension shape = new Dimension(100,30);

        JLabel label = new JLabel("学号:");
        north.add(label);

        JTextField text = new JTextField();
        text.setPreferredSize(shape);
        north.add(text);

        JButton summit_sno = new JButton("查询");
        summit_sno.setPreferredSize(new Dimension(60,30));
        north.add(summit_sno);
//        container.add(north,BorderLayout.NORTH);

        JButton back = new JButton("返回");
        back.setPreferredSize(shape);
        north.add(back);
        container.add(north,BorderLayout.NORTH);
//        center.add(back);
//        container.add(center,BorderLayout.CENTER);
        old_frame.dispose();
        frame.setVisible(true);

        AddTableListener_c atl_sno = new AddTableListener_c(frame,text);
        summit_sno.addActionListener(atl_sno);
        BackToQueryListener btq = new BackToQueryListener(frame);
        back.addActionListener(btq);
    }
}
