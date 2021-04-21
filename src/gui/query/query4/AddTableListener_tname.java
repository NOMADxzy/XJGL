package gui.query.query4;

import main.OperateSet;
import main.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class AddTableListener_tname implements ActionListener{
    private JFrame frame;
    private DefaultTableModel model;
    private JTable table;
    private JTextField text;

    public AddTableListener_tname(JFrame frame, JTextField text) {
        this.frame = frame;
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        frame.setSize(400,320);
        inittable();
        frame.setVisible(true);
    }
    void inittable(){
        OperateSet op = new OperateSet();
        List<Teacher> t_list = null;
        try {
            t_list = op.query_tname(text.getText());
        }catch (SQLException error){
            System.out.println(error);
        }
        String[][] name_arr = new String[t_list.size()][4];
        for(int i=0;i<t_list.size();i++){
            name_arr[i][0] = t_list.get(i).getTname();
            name_arr[i][1] = t_list.get(i).getCname();
            name_arr[i][2] = t_list.get(i).getTsex();
            name_arr[i][3] = String.valueOf(t_list.get(i).getTage());
        }
        model = new DefaultTableModel(name_arr,new String[]{"教师姓名","课程","性别","年龄"});
        table = new JTable(model);
        //居中对齐
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,dtcr);

        JScrollPane jsp = new JScrollPane();
//        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        jsp.setViewportView(table);
        try{
            frame.getContentPane().remove(1);
        }catch(ArrayIndexOutOfBoundsException e ){
            System.out.println("第一次");
        }
        frame.getContentPane().add(jsp, BorderLayout.CENTER);
    }
}
