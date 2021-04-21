package gui;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;

public class BackToMenu implements ActionListener{
    private JFrame old_frame;

    public BackToMenu(JFrame old_frame) {
        this.old_frame = old_frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UI_sets menu = new UI_sets();
        old_frame.dispose();
        menu.menu();
    }
}
