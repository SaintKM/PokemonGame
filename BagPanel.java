//BagPanel.java
import java.io.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class BagPanel extends MyPanel
{
    private HashMap<String, Integer> bag;
    private Pokemon ally;
    public BagPanel(MainGame mg, Pokemon ally, Pokemon enemy)
    {
        super(null);
        this.bag = mg.trainer.getBag();
        setPokemon(ally);
        JLabel label = new JLabel("Choose Item");
        label.setBounds(300, 160, 100, 30);
        this.add(label);
        JButton potionBtn = new JButton("Potion: " + bag.get("Potion"));
        JButton etherBtn = new JButton("Ether: " + bag.get("Ether"));
        JButton rcBtn = new JButton("Rare Candy: " + bag.get("Rare Candy"));
        JButton pokeBallBtn = new JButton("PokeBall: " + bag.get("PokeBall"));
        JButton closeBtn = new JButton("close");
        ImagePanel potionImg = loadImageFile("potion");
        ImagePanel etherImg = loadImageFile("ether");
        ImagePanel rcImg = loadImageFile("rare-candy");
        ImagePanel pokeBallImg = loadImageFile("poke-ball");
        potionBtn.setBounds(300, 200, 150, 30);
        potionImg.setBounds(266, 200, 32, 32);
        etherBtn.setBounds(300, 240, 150, 30);
        etherImg.setBounds(266, 240, 32, 32);
        rcBtn.setBounds(300, 280, 150, 30);
        rcImg.setBounds(266, 280, 32, 32);
        pokeBallBtn.setBounds(300, 320, 150, 30);
        pokeBallImg.setBounds(266, 320, 32, 32);
        closeBtn.setBounds(325, 360, 100, 30);
        this.add(potionBtn, 1, 0);
        this.add(potionImg, 2, 0);
        this.add(etherBtn, 1, 0);
        this.add(etherImg, 2, 0);
        this.add(rcBtn, 1, 0);
        this.add(rcImg, 2, 0);
        this.add(pokeBallBtn, 1, 0);
        this.add(pokeBallImg, 2, 0);
        this.add(closeBtn);
        
        //<--------------Choose Pokemon------------------------->
        JLabel label1 = new JLabel("Choose pokemon to use item.");
        label1.setBounds(70, 160, 200, 30);
        label1.setVisible(false);
        this.add(label1);
        //<-----Display Pokemon----->
        ArrayList<Pokemon> pokemonbag = mg.trainer.getPokemonBag();
        int bagSize = pokemonbag.size();
        if(pokemonbag.size() > 6)
            bagSize = 6;
        ArrayList<JButton> pokemonChooser = new ArrayList<JButton>();
        for(int i = 0; i < bagSize; i++){
            JButton pokemonNo = new JButton(pokemonbag.get(i).getName());
            pokemonNo.setBounds(75, 160+40*(i+1), 150, 30);
            pokemonNo.setVisible(false);
            this.add(pokemonNo);
            pokemonChooser.add(pokemonNo);

            pokemonNo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("Choose <" + e.getActionCommand() + ">.");
                    for(Pokemon p: pokemonbag){
                        if(e.getActionCommand().equals(p.getName()))
                            setPokemon(p);
                    }
                }
            });
        }
        //<---------------------------------------------------->

        potionBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                Pokemon pokemon = getAlly();
                if(bag.get("Potion") > 0){
                    if(getAlly() == null)
                        displayPokemon(label1, pokemonChooser);
                    else if(pokemon.hp < pokemon.maxHP){
                        bag.put("Potion", bag.get("Potion")-1);
                        potionBtn.setText("Potion: " + bag.get("Potion"));
                        System.out.println(mg.trainer.getName() + " use Potion.");
                        pokemon.healHP(20);
                    }
                    else
                        System.out.println(pokemon.getName() + " 's HP is full.");
                }
                if(enemy != null)
                    mg.changePanel(new FightPokemonPanel(mg, ally, enemy));
            }
        });

        etherBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                Pokemon pokemon = getAlly();
                if(bag.get("Ether") > 0){
                    if(getAlly() == null)
                        displayPokemon(label1, pokemonChooser);
                    else if(pokemon.pp < pokemon.maxPP){
                        bag.put("Ether", bag.get("Ether")-1);
                        etherBtn.setText("Ether: " + bag.get("Ether"));
                        System.out.println(mg.trainer.getName() + " use Ether.");
                        pokemon.healPP(20);
                    }
                    else
                        System.out.println(pokemon.getName() + " 's PP is full.");
                }
                if(enemy != null)
                    mg.changePanel(new FightPokemonPanel(mg, ally, enemy));
            }
        });

        rcBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                Pokemon pokemon = getAlly();
                if(bag.get("Rare Candy") > 0 && enemy == null){
                    if(getAlly() == null)
                        displayPokemon(label1, pokemonChooser);
                    else if(enemy == null){
                        bag.put("Rare Candy", bag.get("Rare Candy")-1);
                        rcBtn.setText("Rare Candy: " + bag.get("Rare Candy"));
                        System.out.println(mg.trainer.getName() + " use Rare Candy.");
                        pokemon.levelUp();
                    }
                }
                else
                    System.out.println("Can't use right now.");
            }
        });

        pokeBallBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                if(bag.get("PokeBall") > 0 && enemy != null){
                    bag.put("PokeBall", bag.get("PokeBall")-1);
                    rcBtn.setText("PokeBall: " + bag.get("PokeBall"));
                    System.out.println(mg.trainer.getName() + " use PokeBall.");
                    mg.trainer.getPokemonBag().add(enemy);
                    System.out.println("<" + enemy.getName() +"> si catched!");
                    mg.changePanel(new MenuPanel(mg));
                }
                else
                    System.out.println("Can't use right now.");
            }
        });

        closeBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Close!");
                if(enemy != null)
                    mg.changePanel(new FightPokemonPanel(mg, ally, enemy));
                else
                    mg.changePanel(new MenuPanel(mg));
            }
        });
        
        setStyle(null, null);
        
    }

    public ImagePanel loadImageFile(String imgName)
    {
        ImagePanel bgPanel = new ImagePanel();
        String filePath = "image/" + imgName + ".png";
        File img = new File(filePath);
        bgPanel.displayImage(img, 32, 32);
        bgPanel.setOpaque(true);
        return bgPanel;
    }

    public void displayPokemon(JLabel label, ArrayList<JButton> pokemonChooser)
    {
        label.setVisible(true);
        for(JButton btn: pokemonChooser){
            btn.setVisible(true);
        }
    }

    public Pokemon getAlly()
    {
        return this.ally;
    }

    public void setPokemon(Pokemon p)
    {
        this.ally = p;
    }
}