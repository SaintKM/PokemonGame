//BagPanel.java
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BagPanel extends JPanel
{
    private HashMap<String, Integer> bag;
    private Pokemon ally;
    public BagPanel(MainGame mg, Pokemon ally, Pokemon enemy)
    {
        this.bag = mg.trainer.getBag();
        this.setLayout(null);
        setPokemon(ally);
        JLabel label = new JLabel("Choose Item");
        label.setBounds(300, 160, 100, 30);
        this.add(label);
        JButton potionBtn = new JButton("Potion: " + bag.get("Potion"));
        JButton etherBtn = new JButton("Ether: " + bag.get("Ether"));
        JButton rcBtn = new JButton("Rare Candy: " + bag.get("Rare Candy"));
        JButton pokeBallBtn = new JButton("PokeBall: " + bag.get("PokeBall"));
        JButton closeBtn = new JButton("close");
        potionBtn.setBounds(300, 200, 150, 30);
        etherBtn.setBounds(300, 240, 150, 30);
        rcBtn.setBounds(300, 280, 150, 30);
        pokeBallBtn.setBounds(300, 320, 150, 30);
        closeBtn.setBounds(325, 360, 100, 30);
        this.add(potionBtn);
        this.add(etherBtn);
        this.add(rcBtn);
        this.add(pokeBallBtn);
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