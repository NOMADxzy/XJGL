package gui.add;
import main.OperateSet;
import main.Student;
import java.lang.Thread;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class AddSubmitListener implements ActionListener{
    private JTextField xuehao;
    private JTextField xingming;
    private JComboBox  sexCB;
    private JComboBox monthCB;
    private JComboBox dayCB;
    private JTextField banji;

    public AddSubmitListener(JTextField xuehao, JTextField xingming,JComboBox sexCB,JComboBox monthCB,JComboBox dayCB, JTextField banji) {
        this.xuehao = xuehao;
        this.xingming = xingming;
        this.sexCB = sexCB;
        this.monthCB = monthCB;
        this.dayCB = dayCB;
        this.banji = banji;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sex = sexCB.getSelectedItem().toString();
        String birthday = monthCB.getSelectedItem().toString()+"月"+
                dayCB.getSelectedItem().toString()+"日";
        Connection conn = main.connectSQL.getConnection();
        OperateSet op = new OperateSet();
        Student stu = new Student(xuehao.getText(),xingming.getText(),
                sex,birthday,banji.getText());
        if (op.add_student(stu)){
            JOptionPane.showMessageDialog(null,"添加成功");
        }else {
            JOptionPane.showMessageDialog(null,"添加失败，学号已存在或班级不存在","提示",JOptionPane.ERROR_MESSAGE);

        }
    }
}
