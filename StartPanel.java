//StartPanel.java
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class StartPanel extends MyPanel
{
    public StartPanel(MainGame mg)
    {
        super(null);
        JLabel label = new JLabel("Enter player's name:  ");
        JTextField textField = new JTextField();
        JButton btn = new JButton("Confirm");
        label.setBounds(180, 200, 300, 30);
        textField.setBounds(240, 240, 200, 30);
        btn.setBounds(280, 280, 100, 30);
        this.add(label, 0);
        this.add(textField, 0);
        this.add(btn, 0);

        setStyle(null, 20);
        label.setForeground(Color.WHITE);

        textField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("User type: " + textField.getText());
                mg.setPlayerName(textField.getText());
                mg.changePanel(new ConfirmPanel(mg));
            }
        });

        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("User type: " + textField.getText() + " " + e.getActionCommand());
                mg.setPlayerName(textField.getText());
                mg.changePanel(new ConfirmPanel(mg));
            }
        });
        
    }
}