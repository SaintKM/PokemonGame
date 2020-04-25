//Mewtwo.java
import java.io.*;
import java.util.*;

public class Mewtwo extends Pokemon
{
    private ArrayList<String> skill;
    public Mewtwo(String name)
    {
        super(name);
        this.originName = "Mewtwo";
        skill = new ArrayList<String>();
        skill.add("Psycho Cut");
        skill.add("Phychic");
        skill.add("Self-Destruct");
        skill.add("Future Sight");
    }

    public String getOrigin()
    {
        return this.originName;
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
        if(tempSkill.equals("Psycho Cut")){
            System.out.println(this.getName() + " use Psycho Cut!");
            target.damaged(7);
        }
        else if(tempSkill.equals("Phychic")){
            System.out.println(this.getName() + " use Phychic!");
            target.damaged(9);
        }
        else if(tempSkill.equals("Self-Destruct")){
            System.out.println(this.getName() + " use Self-Destruct!");
            target.damaged(20);
        }
        else if(tempSkill.equals("Future Sight")){
            System.out.println(this.getName() + " use Future Sight!");
            target.damaged(12);
            
        }
        if(!target.isAlive()){
            this.recieveExp(target.level+5);
        }
        return true;
    }

}