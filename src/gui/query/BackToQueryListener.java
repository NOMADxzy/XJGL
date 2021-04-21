package gui.query;

import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;

public class BackToQueryListener implements ActionListener{
    private JFrame oldframe;

    public BackToQueryListener(JFrame oldframe) {
        this.oldframe = oldframe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        QueryListener querymenu = new QueryListener(oldframe);
        querymenu.actionPerformed(e);
    }
}
