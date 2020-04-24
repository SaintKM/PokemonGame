//MenuPanel.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel
{
    public MenuPanel(MainGame mg)
    {
        this.setLayout(null);
        JLabel label = new JLabel("[In World Map]");
        JButton fightPokemonBtn = new JButton("Fight Wild Pokemon");
        JButton bagBtn = new JButton("Bag");
        JButton pokeBagBtn = new JButton("PokemonBag");
        JButton healPokemonBtn = new JButton("Heal Pokemon in PokemonCenter");
        JButton exitBtn = new JButton("Exit Game");
        label.setBounds(250, 160, 250, 30);
        fightPokemonBtn.setBounds(250, 200, 250, 30);
        bagBtn.setBounds(250, 240, 250, 30);
        pokeBagBtn.setBounds(250, 280, 250, 30);
        healPokemonBtn.setBounds(250, 320, 250, 30);
        exitBtn.setBounds(250, 360, 250, 30);
        this.add(label);
        this.add(fightPokemonBtn);
        this.add(bagBtn);
        this.add(pokeBagBtn);
        this.add(healPokemonBtn);
        this.add(exitBtn);

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
    }
}