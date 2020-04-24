import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

//PokemonStatusPanel.java

public class PokemonStatusPanel extends JPanel
{
    private Pokemon pokemon;
    private JLabel chooseLabel;
    private JButton chooseBtn;
    public PokemonStatusPanel(MainGame mg)
    {
        this.setLayout(null);
        //<--------------Display Pokemon----------------------->
        ArrayList<Pokemon> pokemonbag = mg.trainer.getPokemonBag();
        ArrayList<JLabel> pokemonName = new ArrayList<JLabel>();
        
        int bagSize = pokemonbag.size();
        if(pokemonbag.size() > 6)
            bagSize = 6;
        for(int i = 0; i < bagSize; i++){
            Pokemon p = pokemonbag.get(i);
            JLabel pokemonNameLv = new JLabel(p.getName() + " " + p.getLvStat());
            JLabel pokemonHpPp = new JLabel(p.getHpStat() + " " + p.getPpStat());
            pokemonNameLv.setBounds(150, 100+(60*i), 200, 20);
            pokemonHpPp.setBounds(150, 120+(60*i), 200, 20);
            pokemonName.add(pokemonNameLv);
            this.add(pokemonNameLv);
            this.add(pokemonHpPp);
        }

        JButton renameBtn = new JButton("Rename Pokemon");
        renameBtn.setBounds(500, 100, 150, 30);
        this.add(renameBtn);

        JButton backBtn = new JButton("back");
        backBtn.setBounds(320, 500, 100, 30);
        this.add(backBtn);

        //<--------------Choose Pokemon------------------------->
        JLabel label1 = new JLabel("Choose Pokemon to Rename.");
        label1.setBounds(490, 160, 200, 30);
        label1.setVisible(false);
        this.add(label1);
        JTextField textField = new JTextField(24);
        textField.setBounds(500, 200, 200, 30);
        textField.setVisible(false);
        this.add(textField);
        JButton confirmBtn = new JButton("confirm");
        confirmBtn.setBounds(550, 240, 100, 30);
        confirmBtn.setVisible(false);
        this.add(confirmBtn);
        //<-----Display Pokemon----->
        ArrayList<JButton> pokemonChooser = new ArrayList<JButton>();
        for(int i = 0; i < bagSize; i++){
            JButton pokemonNo = new JButton(pokemonbag.get(i).getName());
            pokemonNo.setBounds(500, 160+40*(i+1), 150, 30);
            pokemonNo.setVisible(false);
            this.add(pokemonNo);
            pokemonChooser.add(pokemonNo);

            pokemonNo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("Choose <" + e.getActionCommand() + ">.");
                    for(int i = 0; i < pokemonbag.size(); i++){
                        if(e.getActionCommand().equals(pokemonbag.get(i).getName())){
                            setPokemon(pokemonbag.get(i), pokemonName.get(i), pokemonNo);
                            renamePokemon(pokemonChooser, label1, textField, confirmBtn);
                        }
                    }
                }
            });
        }
        //<---------------------------------------------------->

        textField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String text = textField.getText();
                textField.setText("");
                System.out.println("Enter: " + text);
                chooseLabel.setText(text + " " + pokemon.getLvStat());
                chooseBtn.setText(text);
                getPokemon().setName(text);
                closeRenameTab(label1, textField, confirmBtn);
            }
        });

        confirmBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String text = textField.getText();
                textField.setText("");
                System.out.println("Enter: " + text);
                chooseLabel.setText(text + " " + pokemon.getLvStat());
                chooseBtn.setText(text);
                getPokemon().setName(text);
                closeRenameTab(label1, textField, confirmBtn);
            }
        });

        renameBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Rename Pokemon");
                displayPokemon(pokemonChooser, label1);
            }
        });

        backBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Go back to Menu.");
                mg.changePanel(new MenuPanel(mg));
            }
        });
    }

    public void displayPokemon(ArrayList<JButton> pokemonChooser, JLabel label)
    {
        label.setVisible(true);
        for(JButton btn: pokemonChooser){
            btn.setVisible(true);
        }
    }

    public void renamePokemon(ArrayList<JButton> pokemonChooser, JLabel label, JTextField textField, JButton confirmBtn)
    {
        label.setText("Enter new name: ");
        textField.setVisible(true);
        confirmBtn.setVisible(true);
        for(JButton btn: pokemonChooser){
            btn.setVisible(false);
        }
    }

    public void closeRenameTab(JLabel label, JTextField textField, JButton confirmBtn)
    {
        label.setVisible(false);
        textField.setVisible(false);
        confirmBtn.setVisible(false);
    }

    public Pokemon getPokemon()
    {
        return this.pokemon;
    }

    public void setPokemon(Pokemon pokemon, JLabel label, JButton btn)
    {
        this.chooseLabel = label;
        this.chooseBtn = btn;
        this.pokemon = pokemon;
    }
}