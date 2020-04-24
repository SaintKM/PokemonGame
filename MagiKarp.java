//MagiKarp.java
import java.io.*;
import java.util.*;

public class MagiKarp extends Pokemon
{
    private ArrayList<String> skill;
    public MagiKarp(String name)
    {
        super(name);
        this.atk = 1;
        skill = new ArrayList<String>();
        skill.add("Splash");
        skill.add("Tackle");
        skill.add("Splash");
        skill.add("Tackle");
    }

    public void showSkill()
    {
        for(int i = 0; i < skill.size(); i++){
            System.out.println((i+1) + "." + skill.get(i));
        }
    }

    public String getSkill(int skillNum)
    {
        return this.skill.get(skillNum);
    }

    public boolean useSkill(int skillNum, Pokemon target)
    {
        //Use Skill
        System.out.println(this.getName() + " attack!");
        String tempSkill = skill.get(skillNum-1);
        if(tempSkill.equals("Tackle")){
            System.out.println(this.getName() + " use Tackle!");
            target.damaged(4);
        }
        else if(tempSkill.equals("Splash")){
            System.out.println(this.getName() + " use Splash!");
            target.damaged(0);
        }
        if(!target.isAlive()){
            this.recieveExp(target.level+5);
        }
        return true;
    }

}