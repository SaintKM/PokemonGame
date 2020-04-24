//StartPanel.java
import javax.swing.*;
import java.awt.event.*;

public class StartPanel extends JPanel
{
    public StartPanel(MainGame mg)
    {
        this.setLayout(null);
        JLabel label = new JLabel("Enter player's name:  ");
        JTextField textField = new JTextField(24);
        JButton btn = new JButton("Confirm");
        label.setBounds(200, 200, 200, 30);
        textField.setBounds(240, 240, 200, 30);
        btn.setBounds(280, 280, 80, 30);
        this.add(label);
        this.add(textField);
        this.add(btn);

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