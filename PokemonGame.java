//Only static main
/*import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;*/
import java.util.Scanner;
import java.io.*;

public class PokemonGame //extends Canvas
{
    //declare variable
    //static Pokemon pikachuu;
    public static void main(String args[])
    {
        System.out.println("Game started!");
        Pokemon pikachuu = new Pokemon("pikachuu");
        //Scanner sc = new Scanner(System.in);
        //int num = sc.nextInt();
            /*JFrame frame = new JFrame("Pokemon Demo");
            Canvas canvas = new PokemonGame();
            canvas.setSize(800, 800);
            frame.add(canvas);
            frame.pack();
            frame.setVisible(true);*/
        while(true) {
            System.out.println(pikachuu.getStats());
            pikachuu.recieveExp(5);
            wait(1000);
        }
    }

    private static void wait(int miliseconds)
    {
        try {
            Thread.sleep(miliseconds);
        }
        catch (Exception e) { }
    }

    /*public void paint(Graphics g)
    {
        g.drawString(pikachuu.getStats(), 10, 10);
    }*/

}