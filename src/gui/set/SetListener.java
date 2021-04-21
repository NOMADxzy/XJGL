package gui.set;
import gui.BackToMenu;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;

public class SetListener implements ActionListener {
    private JFrame old_frame;

    public SetListener(JFrame old_frame) {
        this.old_frame = old_frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame();
        frame.setTitle("录入成绩");
        frame.setSize(400,320);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);

        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,23);
        frame.setLayout(fl);

        String[] hints = {"学号  :","课程号:","分数  :"};
        JTextField[] tfs = new JTextField[3];
        Dimension shape = new Dimension(300,25);

        for(int i=0;i<3;i++){
            JLabel Hint = new JLabel(hints[i]);
            frame.add(Hint);
            tfs[i] = new JTextField();
            tfs[i].setPreferredSize(shape);
            frame.add(tfs[i]);
        }
        JButton submit = new JButton("确定");
        submit.setPreferredSize(new Dimension(100,25));
        frame.add(submit);

        JButton back = new JButton("返回");
        back.setPreferredSize(new Dimension(100,25));
        frame.add(back);

        old_frame.dispose();
        frame.setVisible(true);

        SetSubmmitListener ssl = new SetSubmmitListener(tfs[0],tfs[1],tfs[2]);
        submit.addActionListener(ssl);
        BackToMenu btm = new BackToMenu(frame);
        back.addActionListener(btm);
    }
}
