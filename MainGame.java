//MainGame.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGame extends JFrame
{
    protected Trainer trainer;
    private Container mainContainer;

    public MainGame(Trainer trainer)
    {
        super("Pokemon Game");
        this.trainer = trainer;
        
        mainContainer = getContentPane();
        
        mainContainer.add(new StartPanel(this));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public void changePanel(JPanel newPanel)
    {
        mainContainer.removeAll();
        mainContainer.add(newPanel);
        mainContainer.invalidate();
        mainContainer.validate();
    }

    public void setPlayerName(String name)
    {
        this.trainer.setName(name);
    }
}