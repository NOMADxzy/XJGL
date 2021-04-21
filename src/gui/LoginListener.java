package gui;
import java.lang.Thread;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class LoginListener implements ActionListener{
    private javax.swing.JTextField text;
    private javax.swing.JPasswordField password;
    private javax.swing.JFrame frame;

    public LoginListener(JFrame frame,JTextField text, JPasswordField password) {
        this.text = text;
        this.password = password;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            UI_sets ui = new UI_sets();
            Connection conn = main.connectSQL.getConnection();
            String flag = "";
            String sql = "select password from admin where account=?";
            String sql_= "select password from users where account=?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,text.getText());
            ResultSet rs = pst.executeQuery();

            pst = conn.prepareStatement(sql_);
            pst.setString(1,text.getText());
            ResultSet rs_ = pst.executeQuery();
            if(rs.next()){//账号存在
                if(password.getText().equals(rs.getString(1))){//密码正确
                    flag = "管理员";
                } else {//密码错误
                    flag = "密码错误";
                }
            }else flag = "账号不存在";
            if(flag.equals("账号不存在")){
                if(rs_.next()){
                    if(password.getText().equals(rs_.getString(1))){
                        flag = "用户";
                    }else flag = "密码错误";
                }
            }
            switch (flag){
                case "管理员":
                    frame.dispose();
                    ui.menu();
                break;
                case "用户":
                    frame.dispose();
                    ui.community_menu();
                break;
                case "密码错误":
                    JOptionPane.showMessageDialog(frame,"密码错误","提示",JOptionPane.ERROR_MESSAGE);
                break;
                case "账号不存在":
                    JOptionPane.showMessageDialog(frame,"账号不存在","提示",JOptionPane.ERROR_MESSAGE);
                break;
            }
            pst.close();
            conn.close();
        }catch (SQLException w) {
            System.out.println(w);
        }

    }
}