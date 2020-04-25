public class PokemonGame
{
    public static void main(String args[])
    {
        System.out.println("Game started!");
        Trainer trainer = new Trainer("Satoshi");
        MainGame mg = new MainGame(trainer);
        System.out.println("End PokemonGame!");
    }
}