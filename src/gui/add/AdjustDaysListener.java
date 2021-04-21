package gui.add;
import gui.BackToMenu;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;

public class AdjustDaysListener implements ItemListener {
    private JComboBox dayCB;

    public AdjustDaysListener(JComboBox dayCB) {
        this.dayCB = dayCB;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            String[] arr31 = {"1","3","5","7","8","10","12"};
            if(e.getItem().toString().equals("2")) return;
            dayCB.addItem("29");
            dayCB.addItem("30");
            if(Arrays.asList(arr31).contains(e.getItem().toString())){
                dayCB.addItem("31");
            }
        }
    }
}
