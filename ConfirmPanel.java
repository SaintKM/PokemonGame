//ConfirmPanel.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfirmPanel extends MyPanel
{
    public ConfirmPanel(MainGame mg)
    {
        super(new Color(59, 76, 202));
        this.setBounds(200, 100, 400, 300);

        JLabel playerNameLabel = new JLabel(mg.trainer.getName());
        playerNameLabel.setBounds(50, 50, 300, 30);
        playerNameLabel.setFont(mg.getGameFont(20));

        JButton nextButton = new JButton("next");
        nextButton.setBounds(50, 150, 300, 30);
        nextButton.setFont(mg.getGameFont(16));
        nextButton.setForeground(Color.BLACK);

        this.add(playerNameLabel);
        this.add(nextButton);

        setStyle(null, 24);
        playerNameLabel.setForeground(Color.WHITE);

        nextButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Next!");
                mg.changePanel(new MenuPanel(mg));
            }
        });
    }
}