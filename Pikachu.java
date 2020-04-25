//Pikachu.java
import java.util.*;

public class Pikachu extends Pokemon
{
    private ArrayList<String> skill;
    public Pikachu(String name)
    {
        super(name);
        this.originName = "Pikachu";
        skill = new ArrayList<String>();
        skill.add("Thunder Punch");
        skill.add("Tail Whip");
        skill.add("Thunderbolt");
        skill.add("Thunder");
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
        if(tempSkill.equals("Thunder Punch")){
            System.out.println(this.getName() + " use Thunder Punch!");
            target.damaged(8);
        }
        else if(tempSkill.equals("Tail Whip")){
            System.out.println(this.getName() + " use Tail Whip!");
            target.damaged(0);
        }
        else if(tempSkill.equals("Thunderbolt")){
            System.out.println(this.getName() + " use Thunderbolt!");
            target.damaged(9);
        }
        else if(tempSkill.equals("Thunder")){
            System.out.println(this.getName() + " use Thunder!");
            target.damaged(11);
        }
        if(!target.isAlive()){
            this.recieveExp(target.level+5);
        }
        return true;
    }

}