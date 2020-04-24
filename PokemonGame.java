import java.util.*;
import java.io.*;

public class PokemonGame //extends Canvas
{
    public static void main(String args[])
    {
        System.out.println("Game started!");
        //Scanner input = new Scanner(System.in);
        //System.out.print("Enter your name: ");
        //String playerName = input.nextLine();
        //Trainer trainer = new Trainer(playerName);
        //System.out.println("Welcome to PokemonGame [" + trainer + "].");
        Trainer trainer = new Trainer("Satoshi");
        MainGame mg = new MainGame(trainer);
        //do{

        //    trainer.play();
        //}while(trainer.isPlaying());
        //input.close();
        System.out.println("End PokemonGame!");
    }
}