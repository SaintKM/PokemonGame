//ChoosePokemonPanel.java
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChoosePokemonPanel extends JPanel
{
    private Trainer trainer;
    private ArrayList<Pokemon> wildPokemon;
    public ChoosePokemonPanel(MainGame mg)
    {
        this.trainer = mg.trainer;
        this.setLayout(null);

        fightWildPokemon(mg);
    }
    
    public void fightWildPokemon(MainGame mg)
    {
        //<--------For FightPokemonPanel------------------------------------->
        wildPokemon = new ArrayList<Pokemon>();
        wildPokemon.add(new Pikachu("Wild Pikachu"));
        wildPokemon.add(new Pidgey("Wild Pidgey"));
        wildPokemon.add(new MagiKarp("Wild MagiKarp"));
        wildPokemon.add(new Mewtwo("Wild MewTwo"));
        
        System.out.println("Pokemon size: " + mg.trainer.getPokemonBag().size());
        JLabel label = new JLabel("Choose Pokemon to Fight");
        label.setBounds(75, 200, 150, 30);
        this.add(label);

        JLabel label2 = new JLabel(" <Encounter> ");
        label2.setBounds(300, 250, 100, 30);
        this.add(label2);

        //<---------Enemy Pokemon------------------------------------------------->
        int rand = (int)(Math.random() * wildPokemon.size());
        System.out.println("Wild Pokemon encounter! Enconter <" + wildPokemon.get(rand) + ">.");
        JLabel enemyLabel = new JLabel(wildPokemon.get(rand).getName());

        enemyLabel.setBounds(500, 250, 100, 30);
        this.add(enemyLabel);
        
        //<---------Ally Pokemon-------------------------------------------------->
        ArrayList<Pokemon> pokemonbag = trainer.getPokemonBag();
        int bagSize = pokemonbag.size();
        if(pokemonbag.size() > 6)
            bagSize = 6;
        for(int i = 0; i < bagSize; i++){
            JButton pokemonNo = new JButton(pokemonbag.get(i).getName());
            pokemonNo.setBounds(50, 200+40*(i+1), 200, 30);
            this.add(pokemonNo);

            pokemonNo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("Choose <" + e.getActionCommand() + ">.");
                    for(Pokemon p: pokemonbag){
                        if(e.getActionCommand().equals(p.getName())){
                            if(p.isAlive())
                                mg.changePanel(new FightPokemonPanel(mg, p, wildPokemon.get(rand)));
                            else
                                System.out.println("Can't select this Pokemon.");
                        }
                    }
                }
            });
        }
        //<-------------------------------------------------------------------------->

        JButton cancelBtn = new JButton("cancel");
        cancelBtn.setBounds(300, 400, 100, 25);
        this.add(cancelBtn);
        cancelBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Cancel go back to Menu.");
                mg.changePanel(new MenuPanel(mg));
            }
        });
    }
}