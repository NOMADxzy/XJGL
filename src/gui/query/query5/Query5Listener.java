package gui.query.query5;

import gui.query.BackToQueryListener;
import main.OperateSet;
import main.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Query5Listener implements ActionListener {
    private JFrame old_frame;

    public Query5Listener(JFrame old_frame) {
        this.old_frame = old_frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame();
        frame.setTitle("不及格学生信息");
//        frame.setSize(470,320);
        frame.setLocationRelativeTo(old_frame);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        Container container = frame.getContentPane();
        JPanel north = new JPanel();

        BorderLayout bl = new BorderLayout(5,5);
        frame.setLayout(bl);
        Dimension shape = new Dimension(240,30);

        JLabel label = new JLabel("不及格学生信息表:");
        north.add(label);

        JButton back = new JButton("返回");
        back.setPreferredSize(shape);
        north.add(back);

        List<Student> stus = null;
        OperateSet op = new OperateSet();
        try {
            stus = op.query_danger_s();
        }catch (SQLException error){
            System.out.println(error);
        }
        String[] col_lable = {"学号","姓名","性别","生日","班级"};
        String[][] stus_arr = getStusArray(stus);
        DefaultTableModel model = new DefaultTableModel(stus_arr,col_lable);
        JTable table = new JTable(model);
        //居中对齐
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,dtcr);

        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(table);
        container.add(north,BorderLayout.NORTH);
        container.add(jsp,BorderLayout.SOUTH);
        old_frame.dispose();
        frame.pack();
        frame.setVisible(true);

        BackToQueryListener btq = new BackToQueryListener(frame);
        back.addActionListener(btq);
    }
    String[][] getStusArray(List<Student> stus) {
        String[][] res = new String[stus.size()][5];
        for (int i = 0; i < stus.size(); i++) {
            res[i] = stus.get(i).toArray();
        }
        return res;
    }
}
