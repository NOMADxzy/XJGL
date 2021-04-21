package gui.query.query1;
import main.OperateSet;
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
import java.util.Enumeration;
import java.util.List;

public class AddTableListener implements ActionListener {
    private JFrame frame;
    private DefaultTableModel model;
    private JTable table;
    private JTextField text;
    private ButtonGroup bg;

    public AddTableListener(JFrame frame,ButtonGroup bg,JTextField text) {
        this.frame = frame;
        this.bg = bg;
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        frame.setSize(470,320);
        inittable();
        frame.pack();
//        frame.setVisible(true);
//        frame.setVisible(true);
    }
    void inittable(){
        List<Student> stus = null;
        OperateSet op = new OperateSet();
        String type = "";
        for (Enumeration<AbstractButton> buttons=bg.getElements();buttons.hasMoreElements();){
            AbstractButton button = buttons.nextElement();
            if(button.isSelected()){
                type = button.getText();
                break;
            }
        }
        try {
            switch (type){
                case "学号": stus = op.select_by_sno(text.getText());
                    break;
                case "姓名": stus = op.select_by_sname(text.getText());
                    break;
                case "专业": stus = op.select_by_major(text.getText());
                    break;
            }
        }catch (SQLException error){
            System.out.println(error);
        }
        String[] col_lable = {"学号","姓名","性别","生日","班级"};
        String[][] stus_arr = getStusArray(stus);
        model = new DefaultTableModel(stus_arr,col_lable);
        table = new JTable(model);
        //居中对齐
//        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
//        dtcr.setHorizontalAlignment(JLabel.CENTER);
//        table.setDefaultRenderer(Object.class,dtcr);

        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(table);
//        JPanel table_panel = new JPanel();
//        table_panel.add(jsp);
//        frame.setVisible(false);
// admin       frame.getContentPane().remove(table_panel);
//        frame.setVisible(false);
        try{
            frame.getContentPane().remove(2);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("第一次");
        }
        frame.getContentPane().add(jsp,BorderLayout.SOUTH);
    }
    String[][] getStusArray(List<Student> stus){
        String[][] res = new String[stus.size()][5];
        for (int i =0;i<stus.size();i++){
            res[i] = stus.get(i).toArray();
        }
        return res;
    }
}
