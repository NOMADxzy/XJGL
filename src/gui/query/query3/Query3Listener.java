package gui.query.query3;
import gui.query.BackToQueryListener;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;

public class Query3Listener implements ActionListener {
    private JFrame old_frame;

    public Query3Listener(JFrame old_frame) {
        this.old_frame = old_frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame();
        frame.setTitle("查询平均成绩");
        frame.setSize(400,320);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);

        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,30);
        frame.setLayout(fl);
        Dimension shape = new Dimension(240,30);

        JLabel label = new JLabel("学号:");
        frame.add(label);

        JTextField text = new JTextField();
        text.setPreferredSize(shape);
        frame.add(text);

        JButton summit_sno = new JButton("查询");
        summit_sno.setPreferredSize(new Dimension(60,30));
        frame.add(summit_sno);

        JButton back = new JButton("返回");
        back.setPreferredSize(shape);
        frame.add(back);
        old_frame.dispose();
        frame.setVisible(true);

        AddTableListener_avg atl_sno = new AddTableListener_avg(frame,text);
        summit_sno.addActionListener(atl_sno);
        BackToQueryListener btq = new BackToQueryListener(frame);
        back.addActionListener(btq);
    }
}
