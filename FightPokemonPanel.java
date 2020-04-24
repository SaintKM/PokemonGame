//FightPokemonPanel.java
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class FightPokemonPanel extends JPanel
{
    private Trainer trainer;
    private Pokemon ally;
    private Pokemon enemy;
    public FightPokemonPanel(MainGame mg, Pokemon ally, Pokemon enemy)
    {
        this.trainer = mg.trainer;
        this.ally = ally;
        this.enemy = enemy;
        this.setLayout(null);

        //<--------------Display Pokemon Status-------------->
        //<-----Ally----->
        JLabel allyName = new JLabel(ally.getName());
        JLabel allyStatus1 = new JLabel(ally.getHpStat());
        JLabel allyStatus2 = new JLabel(ally.getPpStat());
        JLabel allyStatus3 = new JLabel(ally.getLvStat());
        allyName.setBounds(100,300,100,30);
        allyStatus1.setBounds(100,330,100,30);
        allyStatus2.setBounds(100,360,100,30);
        allyStatus3.setBounds(100,390,100,30);
        this.add(allyName);
        this.add(allyStatus1);
        this.add(allyStatus2);
        this.add(allyStatus3);

        //<-----Enemy----->
        JLabel enemyName = new JLabel(enemy.getName());
        JLabel enemyStatus1 = new JLabel(enemy.getHpStat());
        JLabel enemyStatus2 = new JLabel("LV: " + enemy.level);
        enemyName.setBounds(600,50,100,30);
        enemyStatus1.setBounds(600,80,100,30);
        enemyStatus2.setBounds(600,110,100,30);
        this.add(enemyName);
        this.add(enemyStatus1);
        this.add(enemyStatus2);
        //<---------------Action Button---------------------->
        JButton attack = new JButton("Attack");
        JButton bag = new JButton("Bag");
        JButton runaway = new JButton("Run Away");
        attack.setBounds(600, 400, 100, 30);
        bag.setBounds(600, 440, 100, 30);
        runaway.setBounds(600, 480, 100, 30);
        this.add(attack);
        this.add(bag);
        this.add(runaway);
        //<---------------Skill Button----------------------->
        ArrayList<JButton> skill = new ArrayList<JButton>();
        for(int i = 0; i < 4; i++)
        {
            JButton skillBtn = new JButton(ally.getSkill(i));
            skillBtn.setVisible(false);
            skillBtn.setBounds(400, 360+(40*i), 150, 30);
            skill.add(skillBtn);
            this.add(skill.get(i));
        }

        //<------------Skill Button Listener---------------->
        skill.get(0).addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(ally.pp > 0){
                    ally.pp--;
                    ally.useSkill(1, enemy);
                    enemyTurn(enemy, mg);
                    updateStatus(allyStatus1, allyStatus2, allyStatus3, enemyStatus1);
                }
            }
        });

        skill.get(1).addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(ally.pp > 0){
                    ally.pp--;
                    ally.useSkill(2, enemy);
                    enemyTurn(enemy, mg);
                    updateStatus(allyStatus1, allyStatus2, allyStatus3, enemyStatus1);
                }
            }
        });

        skill.get(2).addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(ally.pp > 0){
                    ally.pp--;
                    ally.useSkill(3, enemy);
                    enemyTurn(enemy, mg);
                    updateStatus(allyStatus1, allyStatus2, allyStatus3, enemyStatus1);
                }
            }
        });

        skill.get(3).addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(ally.pp > 0){
                    ally.pp--;
                    ally.useSkill(4, enemy);
                    enemyTurn(enemy, mg);
                    updateStatus(allyStatus1, allyStatus2, allyStatus3, enemyStatus1);
                }
            }
        });

        //<--------------------------------------------------->
        attack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Attack!");
                for(int i = 0; i < 4; i++){
                    skill.get(i).setVisible(true);
                }
            }
        });

        bag.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Open Bag!");
                mg.changePanel(new BagPanel(mg, ally, enemy));
            }
        });

        runaway.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Run Away!");
                mg.changePanel(new MenuPanel(mg));
            }
        });
    }


    public void updateStatus(JLabel allyStatus1, JLabel allyStatus2, JLabel allyStatus3, JLabel enemyStatus1)
    {
        allyStatus1.setText(ally.getHpStat());
        allyStatus2.setText(ally.getPpStat());
        allyStatus3.setText(ally.getLvStat());
        enemyStatus1.setText(enemy.getHpStat());
    }

    public void enemyTurn(Pokemon enemy, MainGame mg)
    {
        if(enemy.isAlive()){
            System.out.println(enemy.getName() + " attack!");
            ally.damaged(enemy.atk);
            if(!ally.isAlive()){
                System.out.println("You Lose! End Battle!");
                mg.changePanel(new MenuPanel(mg));
            }
        }
        else{
            System.out.println("You Win! End Battle!");
            mg.changePanel(new MenuPanel(mg));
        }
    }
}