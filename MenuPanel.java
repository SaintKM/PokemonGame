//MenuPanel.java
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends MyPanel
{
    public MenuPanel(MainGame mg)
    {
        super(null);

        JLabel label = new JLabel("[In World Map]");
        label.setBounds(260, 160, 300, 30);
        label.setFont(mg.getGameFont(20));
        label.setForeground(new Color(204, 0, 0));

        JButton fightPokemonBtn = new JButton("Fight Wild Pokemon");
        JButton bagBtn = new JButton("Bag");
        JButton pokeBagBtn = new JButton("PokemonBag");
        JButton healPokemonBtn = new JButton("Heal Pokemon in PokemonCenter");
        JButton exitBtn = new JButton("Exit Game");
        ImagePanel bgImg = loadImageFile("menubg");
        
        fightPokemonBtn.setBounds(250, 200, 280, 30);
        bagBtn.setBounds(250, 240, 280, 30);
        pokeBagBtn.setBounds(250, 280, 280, 30);
        healPokemonBtn.setBounds(250, 320, 280, 30);
        bgImg.setBounds(0, 0, 800, 600);
        exitBtn.setBounds(250, 360, 280, 30);
        this.add(label, 1, 0);
        this.add(fightPokemonBtn, 1, 0);
        this.add(bagBtn, 1, 0);
        this.add(pokeBagBtn, 1, 0);
        this.add(healPokemonBtn, 1, 0);
        this.add(exitBtn, 1, 0);
        this.add(bgImg, 0, 0);

        fightPokemonBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Fight Wild Pokemon.");
                mg.changePanel(new ChoosePokemonPanel(mg));
            }
        });

        bagBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Open Bag.");
                mg.changePanel(new BagPanel(mg, null, null));
            }
        });

        pokeBagBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("PokemonBag.");
                mg.changePanel(new PokemonStatusPanel(mg));
            }
        });

        healPokemonBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Heal Pokemon in PokemonCenter.");
                mg.trainer.healPokemon();
            }
        });

        exitBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Exit Game.");
                mg.dispatchEvent(new WindowEvent(mg, WindowEvent.WINDOW_CLOSING));
            }
        });

        setStyle(null, null);
    }
    
    public ImagePanel loadImageFile(String imgName)
    {
        ImagePanel bgPanel = new ImagePanel();
        String filePath = "image/" + imgName + ".png";
        File img = new File(filePath);
        bgPanel.displayImage(img, 800, 600);
        bgPanel.setOpaque(true);
        return bgPanel;
    }
}