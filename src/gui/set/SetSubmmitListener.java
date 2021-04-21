package gui.set;
import main.OperateSet;
import main.Student;
import java.lang.Thread;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class SetSubmmitListener implements ActionListener {
    private JTextField sno;
    private JTextField cno;
    private JTextField grade_str;

    public SetSubmmitListener(JTextField sno, JTextField cno, JTextField grade) {
        this.sno = sno;
        this.cno = cno;
        this.grade_str = grade;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection conn = main.connectSQL.getConnection();
        OperateSet op = new OperateSet();
        float grade = Float.parseFloat(grade_str.getText());
        int flag = 2;
        try {
            flag = op.set_grade(sno.getText(),cno.getText(),grade);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        switch (flag){
            case 1: JOptionPane.showMessageDialog(null,"保存成功");
            break;
            case 0: JOptionPane.showMessageDialog(null,"失败!,该学生已补考过","提示",JOptionPane.ERROR_MESSAGE);
            break;
            case -1: JOptionPane.showMessageDialog(null,"学号或课程号有误","提示",JOptionPane.ERROR_MESSAGE);
            break;
            case 2: JOptionPane.showMessageDialog(null,"错误，分数需在0到100之间");
            default: break;
        }
    }
}
