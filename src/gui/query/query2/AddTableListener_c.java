package gui.query.query2;
import main.OperateSet;
import main.STinfo;
import main.Student;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddTableListener_c implements ActionListener{
    private JFrame frame;
    private DefaultTableModel model;
    private JTable table;
    private JTextField text;

    public AddTableListener_c(JFrame frame, JTextField text) {
        this.frame = frame;
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        frame.setVisible(false);
//        frame.setSize(470,320);
        inittable();
        frame.pack();
//        frame.setVisible(true);
    }
    void inittable(){
        List<STinfo> sts = null;
        OperateSet op = new OperateSet();
        try {
            sts = op.query_s_c(text.getText());
        }catch (SQLException error){
            System.out.println(error);
        }
        String[] col_lable = {"课程","性质","学期","学分","成绩"};
        String[][] sts_arr = getStusArray(sts);
        model = new DefaultTableModel(sts_arr,col_lable);
        table = new JTable(model);
        //居中对齐
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,dtcr);

        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(table);
        try{
            frame.getContentPane().remove(1);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("第一次");
        }
        frame.getContentPane().add(jsp,BorderLayout.SOUTH);

    }
    String[][] getStusArray(List<STinfo> sts){
        String[][] res = new String[sts.size()][5];
        for (int i =0;i<sts.size();i++){
            res[i] = sts.get(i).toArray();
        }
        return res;
    }
}
