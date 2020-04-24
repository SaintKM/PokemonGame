//Trainer.java
import java.util.*;

public class Trainer
{
    private String name;
    private ArrayList<Pokemon> pokemonbag;
    private HashMap<String, Integer> bag;
    private Scanner input = new Scanner(System.in);
    private boolean isPlaying = true;
    private boolean isBattling = false;
    private boolean isEnemyTurn = false;
    public Trainer(String name){
        this.name = name;
        pokemonbag  = new ArrayList<Pokemon>();
        pokemonbag.add(new Pikachu("Pikachu"));
        pokemonbag.add(new Mewtwo("Mewtwo"));
        bag = new HashMap<String, Integer>();
        bag.put("Potion", 10);
        bag.put("Ether", 5);
        bag.put("Rare Candy", 2);
        bag.put("PokeBall", 10);
    }

    public void play()
    {
        System.out.println("[In World Map]\n1.Fight Wild Pokemon\n2.Bag\n3.PokemonBag\n4.Heal Pokemon in PokemonCenter\n5.Exit Game");
        System.out.print("Choose Action: ");
        String action = input.nextLine();
        System.out.println("---------------------------------");
        if(action.equals("1")){
            enterBattle();
        }
        else if(action.equals("2")){
            openBag(null);
        }
        else if(action.equals("3")){
            System.out.println("\tPokemon");
            printPokemon();
            changePokemonName();
        }
        else if(action.equals("4")){
            healPokemon();
        }
        else if(action.equals("5")){
            isPlaying = false;
        }
        System.out.println("---------------------------------");
    }

    public void enterBattle()
    {
        //------Random Wild Pokemon to Encounter------------
        ArrayList<Pokemon> wildPokemon = new ArrayList<Pokemon>();
        wildPokemon.add(new Pikachu("Wild Pikachu"));
        wildPokemon.add(new Pidgey("Wild Pidgey"));
        wildPokemon.add(new MagiKarp("Wild MagiKarp"));
        wildPokemon.add(new Mewtwo("Wild MewTwo"));
        int rand = (int)(Math.random() * wildPokemon.size());
        System.out.println("Wild Pokemon encounter! Enconter <" + wildPokemon.get(rand) + ">.");
        Pokemon enemy = wildPokemon.get(rand);

        //------Choose Pokemon to Fight---------------------
        System.out.println("Choose your pokemon!");
        for(int i = 0; i < pokemonbag.size(); i++){
            System.out.println((i+1) + "." + pokemonbag.get(i));
        }
        System.out.print("Enter your choice: ");
        String action = input.nextLine();
        int tmp = Integer.parseInt(action)-1;
        if(pokemonbag.get(tmp) != null)
            System.out.println("Chooses " + pokemonbag.get(tmp));
        Pokemon ally = pokemonbag.get(tmp);

        //------Enter Battle with wild pokemon--------------
        System.out.println("Enter battle between <" + ally + "> and <" + enemy + ">.");
        System.out.println("---------------------------------");
        ally.getBattleStat("ally");
        enemy.getBattleStat("enemy");

        //------Choose action-------------------------------
        isBattling = true;
        do{
            isEnemyTurn = false;
            System.out.println("Choose action");
            System.out.println("1.Attack\n2.Bag\n3.Run Away");
            System.out.print("Enter your action : ");
            action = input.nextLine();
            if(action.equals("1")){
                //Attack Enemy
                System.out.println("Choose skill");
                ally.showSkill();
                System.out.print("Enter your choice: ");
                action = input.nextLine();
                tmp = Integer.parseInt(action)-1;
                if(ally.useSkill(tmp, enemy)){
                    ally.getBattleStat("ally");
                    enemy.getBattleStat("enemy");
                }
                isEnemyTurn = true;
            }
            else if(action.equals("2")){
                isEnemyTurn = false;
                openBag(enemy);
                if(isBattling){
                    ally.getBattleStat("ally");
                    enemy.getBattleStat("enemy");
                }
            }
            else if(action.equals("3")){
                System.out.println("Run Away");
                isBattling = false;
            }
            enemyTurn(ally, enemy);

        }while(ally.isAlive() && enemy.isAlive() && isBattling);
        System.out.println("End Battle!");
    }

    public void enemyTurn(Pokemon ally, Pokemon enemy)
    {
        if(isEnemyTurn && enemy.isAlive() && isBattling){
            System.out.println(enemy.getName() + " attack!");
            ally.damaged(enemy.atk);
            ally.getBattleStat("ally");
            enemy.getBattleStat("enemy");
        }
    }

    public void openBag(Pokemon enemy){
        System.out.println("Open Bag");
        for(String item: bag.keySet()){
            System.out.println("\t" + item + " x " + bag.get(item));
        }
        input.nextLine();
        System.out.print("Enter item name: ");
        String item = input.nextLine();
        if(item.equals("Potion")){
            System.out.println("Which Pokemon you want to heal HP?");
            printPokemon();
            System.out.print("Enter Pokemon number: ");
            String action = input.nextLine();
            int tmp = Integer.parseInt(action)-1;
            pokemonbag.get(tmp).healHP(20);
            bag.put("Potion", bag.get("Potion")-1);
            if(isBattling)
                isEnemyTurn = true;
        }
        else if(item.equals("Ether")){
            System.out.println("Which Pokemon you want to heal PP?");
            printPokemon();
            System.out.print("Enter Pokemon number: ");
            String action = input.nextLine();
            int tmp = Integer.parseInt(action)-1;
            pokemonbag.get(tmp).healPP(10);
            bag.put("Ether", bag.get("Ether")-1);
            if(isBattling)
                isEnemyTurn = true;
        }
        else if(item.equals("Rare Candy")){
            if(isBattling)
                System.out.println("Can't use right now.");
            else{
                System.out.println("Which Pokemon you want to level-up?");
                printPokemon();
                System.out.print("Enter Pokemon number: ");
                String action = input.nextLine();
                int tmp = Integer.parseInt(action)-1;
                pokemonbag.get(tmp).levelUp();
                bag.put("Rare Candy", bag.get("Rare Candy")-1);
            }
        }
        else if(item.equals("PokeBall")){
            if(!isBattling)
                System.out.println("Can't use right now.");
            else{
                System.out.println("Throw a pokeball!");
                pokemonbag.add(enemy);
                System.out.println("Great! Catched <" + enemy.getName() + ">.");
                bag.put("PokeBall", bag.get("PokeBall")-1);
                isBattling = false;
            }
        }
    }

    public void healPokemon()
    {
        for(Pokemon pokemon: pokemonbag){
            pokemon.refresh();
            System.out.println("\t" + pokemon.getName() + " Healed");
        }
        System.out.println("Pokemon Healed!");
    }

    public void printPokemon()
    {
        for(int i = 0; i < pokemonbag.size(); i++){
            Pokemon temp = pokemonbag.get(i);
            System.out.println((i+1) + "." + temp.getStats());
        }
    }

    public void changePokemonName()
    {
        System.out.print("Do you want to change pokemon's name?(y/n): ");
        input.nextLine();
        String confirm = input.nextLine();
        if(confirm.equals("y")){
            System.out.print("Enter pokemon number to change name: ");
            String action = input.nextLine();
            int tmp = Integer.parseInt(action)-1;
            Pokemon temp = pokemonbag.get(tmp);
            System.out.print("Enter new name of <" + temp.getName() + ">: ");
            input.nextLine();
            String newName = input.nextLine();
            temp.setName(newName);
        }
    }

    public ArrayList<Pokemon> getPokemonBag()
    {
        return this.pokemonbag;
    }

    public HashMap<String, Integer> getBag()
    {
        return this.bag;
    }

    public boolean isPlaying()
    {
        return isPlaying;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public String toString()
    {
        return this.name;
    }
}