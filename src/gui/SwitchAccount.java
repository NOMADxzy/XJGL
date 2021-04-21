package gui;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;

public class SwitchAccount implements ActionListener{
    private JFrame old_frame;

    public SwitchAccount(JFrame old_frame) {
        this.old_frame = old_frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UI_sets login = new UI_sets();
        old_frame.dispose();
        login.initUI();
    }
}
