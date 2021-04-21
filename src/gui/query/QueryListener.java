package gui.query;
import gui.BackToMenu;
import gui.query.query1.Query1Listener;
import gui.query.query2.Query2Listener;
import gui.query.query3.Query3Listener;
import gui.query.query4.Query4Listener;
import gui.query.query5.Query5Listener;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;

public class QueryListener implements ActionListener{
    private JFrame old_frame;
    public QueryListener(){}

    public QueryListener(JFrame old_frame) {
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

        JButton back = new JButton("返回");
        back.setPreferredSize(new Dimension(100,25));
        frame.add(back);

        old_frame.dispose();
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
        BackToMenu btm = new BackToMenu(frame);
        back.addActionListener(btm);

    }
}
