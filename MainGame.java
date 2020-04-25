//MainGame.java
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MainGame extends JFrame
{
    protected Trainer trainer;
    private Container mainContainer;
    private JLayeredPane panel;
    private JLayeredPane layeredPane;
    private ImagePanel bgPanel;

    public MainGame(Trainer trainer)
    {
        super("Pokemon Game");
        this.trainer = trainer;
        mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout());
        layeredPane = new JLayeredPane();
        bgPanel = new ImagePanel();

        File img = new File("image/pokemon-sword-switch-hero.jpg");
        bgPanel.displayImage(img, 800, 600);
        bgPanel.setBounds(0, 0, 800, 600);
        bgPanel.setOpaque(true);
        layeredPane.add(bgPanel, 0, 0);

        JLayeredPane stPanel = new StartPanel(this);
        stPanel.setBounds(0, 0, 800, 600);
        stPanel.setOpaque(false);
        layeredPane.add(stPanel, 1, 0);

        this.panel = stPanel;
        mainContainer.add(layeredPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public Font getGameFont(int fontsize)
    {
        return new Font("Courier", Font.PLAIN, fontsize);
    }

    public void changePanel(JLayeredPane newPanel)
    {
        layeredPane.remove(this.panel);
        newPanel.setOpaque(true);
        layeredPane.add(newPanel, 1, 0);
        this.panel = newPanel;
        mainContainer.invalidate();
        mainContainer.validate();
    }

    public void setPlayerName(String name)
    {
        this.trainer.setName(name);
    }
}