//ConfirmPanel.java
import javax.swing.*;
import java.awt.event.*;

public class ConfirmPanel extends JPanel
{
    public ConfirmPanel(MainGame mg)
    {
        this.setLayout(null);
        JLabel playerNameLabel = new JLabel(mg.trainer.getName());
        JButton confirmNameButton = new JButton("next");
        playerNameLabel.setBounds(300, 200, 100, 30);
        confirmNameButton.setBounds(300, 240, 100, 30);
        this.add(playerNameLabel);
        this.add(confirmNameButton);

        confirmNameButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Next!");
                mg.changePanel(new MenuPanel(mg));
            }
        });
    }
}