package gui.add;
import gui.BackToMenu;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;

public class AddListener implements ActionListener {
    private JFrame old_frame;
    private String button_type;

    public AddListener(JFrame old_frame) {
        this.old_frame = old_frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame();
        frame.setTitle("新增学生");
        frame.setSize(400,320);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);

        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,21);
        frame.setLayout(fl);

        JComboBox sexCB = new JComboBox(new String[]{"男","女"});
        JComboBox monthCB = new JComboBox(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"});
        JComboBox dayCB = new JComboBox(new String[]{"1","2","3","4","5","6","7","8","9","10",
                "11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"});
        String[] hints = {"学号:","姓名:","性别:","生日:","班级:"};
        JTextField[] tfs = new JTextField[5];
        Dimension shape = new Dimension(130,25);

        for(int i=0;i<5;i++){
            JLabel Hint = new JLabel(hints[i]);
            frame.add(Hint);
            if(i==2){
                frame.add(sexCB);
            }else if(i==3){
                frame.add(monthCB);
                frame.add(new JLabel("月"));
                frame.add(dayCB);
                frame.add(new JLabel("日"));
            }else {
                tfs[i] = new JTextField();
                tfs[i].setPreferredSize(shape);
                frame.add(tfs[i]);
            }
        }
        tfs[4].setPreferredSize(new Dimension(30,30));
        JButton submit = new JButton("确定");
        submit.setPreferredSize(new Dimension(100,25));
        frame.add(submit);

        JButton back = new JButton("返回");
        back.setPreferredSize(new Dimension(100,25));
        frame.add(back);

        old_frame.dispose();
        frame.setVisible(true);

//        AdjustDaysListener adl = new AdjustDaysListener(dayCB);
//        monthCB.addItemListener(adl);
        AddSubmitListener asl = new AddSubmitListener(tfs[0],tfs[1],sexCB,monthCB,dayCB,tfs[4]);
        submit.addActionListener(asl);
        BackToMenu btm = new BackToMenu(frame);
        back.addActionListener(btm);
    }


}
