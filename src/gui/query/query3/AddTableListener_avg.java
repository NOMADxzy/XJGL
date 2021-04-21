package gui.query.query3;
import main.OperateSet;
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

public class AddTableListener_avg implements ActionListener{
    private JFrame frame;
    private DefaultTableModel model;
    private JTable table;
    private JTextField text;

    public AddTableListener_avg(JFrame frame, JTextField text) {
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
        float avg_all=0;
        float avg_compul=0;
        OperateSet op = new OperateSet();
        try {
            avg_all = op.query_avg_grade(text.getText());
            avg_compul = op.query_avg_grade_Compul(text.getText());
        }catch (SQLException error){
            System.out.println(error);
        }
        String[][] avg = new String[1][2];
        avg[0][0] = avg_all+"";
        avg[0][1] = avg_compul+"";
        model = new DefaultTableModel(avg,new String[]{"所有课程","必修课程"});
        table = new JTable(model);
        //居中对齐
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,dtcr);

        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(table);
        frame.add(jsp);
    }
}
