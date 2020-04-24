//Pidgey.java
import java.io.*;
import java.util.*;

public class Pidgey extends Pokemon
{
    private ArrayList<String> skill;
    public Pidgey(String name)
    {
        super(name);
        skill = new ArrayList<String>();
        skill.add("Tackle");
        skill.add("Wing Attack");
        skill.add("Fly");
        skill.add("Gust");
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
        else if(tempSkill.equals("Wing Attack")){
            System.out.println(this.getName() + " use Wing Attack!");
            target.damaged(6);
        }
        else if(tempSkill.equals("Fly")){
            System.out.println(this.getName() + " use Fly!");
            target.damaged(10);
        }
        else if(tempSkill.equals("Gust")){
            System.out.println(this.getName() + " use Gust!");
            target.damaged(4);
        }
        if(!target.isAlive()){
            this.recieveExp(target.level+5);
        }
        return true;
    }

}