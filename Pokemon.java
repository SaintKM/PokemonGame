import java.io.*;
import java.lang.Math;

public abstract class Pokemon
{
    protected String name;
    protected int maxHP, maxPP, hp, pp, atk, def, level, exp, levelUpExp;
    protected int leastHP = 10;
    protected int leastPP = 5;
    protected int leastATK = 5;
    protected int leastDEF = 3;
    protected String originName;

    public Pokemon(String name)
    {
        this.name = name;
        level = 1;
        exp = 0;
        levelUpExp = 5;
        maxHP = (int)(Math.random() * 12) + leastHP;
        hp = maxHP;
        maxPP = (int)(Math.random() * 8) + leastPP;
        pp = maxPP;
        atk = (int)Math.random() + leastATK;
        def = (int)(Math.random() * 10) + leastDEF;
    }

    public abstract String getOrigin();

    public void damaged(int damage)
    {
        if(this.hp-damage > 0){
            this.hp -= damage;
        }
        else{
            this.hp = 0;
        }
    }

    public void healHP(int amount)
    {
        if(hp >= maxHP){
            System.out.println("Can't heal hp is full.");
        }
        else if(this.hp+amount > maxHP){
            this.hp = maxHP;
        }
        else{
            this.hp += amount;
        }
        System.out.println(this.getName() + "'s HP is restored!");
    }

    public void healPP(int amount)
    {
        if(pp >= maxPP){
            System.out.println("Can't heal pp is full.");
        }
        else if(this.pp+amount > maxPP){
            this.pp = maxPP;
        }
        else{
            this.pp += amount;
        }
        System.out.println(this.getName() + "'s PP is restored!");
    }

    public void refresh()
    {
        this.hp = maxHP;
        this.pp = maxPP;
    }

    public String getStats()
    {
        return  name +
                " Level: " + level + " Exp: " + exp + "/" + levelUpExp +
                " HP: " + hp + "/" + maxHP +
                " PP: " + pp + "/" + maxPP;
    }

    public String getHpStat()
    {
        return "HP: " + this.hp + "/" + this.maxHP;
    }
    
    public String getLvStat()
    {
        return "LV: " + this.level + "  EXP: " + this.exp + "/" + this.levelUpExp;
    }

    public String getPpStat()
    {
        return "PP: " + this.pp + "/" + this.maxPP;
    }

    public void getBattleStat(String stat)
    {
        if(stat.equals("ally"))
            System.out.println("---------------------------------" + 
                                "\nName : " + name +
                                "\nLV : " + level + "\tExp : " + exp + "/" + levelUpExp +
                                "\nHP : " + hp + "/" + maxHP +
                                "\nPP : " + pp + "/" + maxPP +
                                "\n---------------------------------");
        else if(stat.equals("enemy"))
            System.out.println("Name : " + name +
                                "\nHP : " + hp + "/" + maxHP +
                                "\n---------------------------------");
    }

    public void recieveExp(int rexp)
    {
        exp += rexp;
        System.out.println("<" + this.name + "> get " + rexp + " exp.");
        if (exp >= levelUpExp) {
            exp = exp % levelUpExp;
            levelUp();
        }
    }

    public void levelUp()
    {
        level++;
        levelUpExp += 5;
        statusUp();
        refresh();
        System.out.println(this.getName() + " Level up!");
    }

    public void statusUp()
    {
        maxHP += 2;
        maxPP += 1;
        atk++;
        def++;
    }

    public abstract void showSkill();
    public abstract boolean useSkill(int skillNum, Pokemon target);
    public abstract String getSkill(int skillNum);

    public boolean isAlive()
    {
        if(hp > 0)
            return true;
        else
            return false;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return name;
    }
}