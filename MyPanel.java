//MyPanel.java
import javax.swing.*;
import java.awt.*;
public abstract class MyPanel extends JLayeredPane
{
    public MyPanel(Color color)
    {
        this.setLayout(null);
        this.setOpaque(true);
        if(color != null)
            setBackground(color);
        else
            setBackground(Color.WHITE);
        //setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4.0f)));
        this.setBounds(0, 0, 800, 600);
    }

    public void setStyle(String modify, Integer fontSize)
    {
        Font font;
        if(fontSize != null)
            font = new Font("Arial", Font.BOLD, fontSize);
        else
            font = new Font("Arial", Font.BOLD, 14);
        if(modify == null){
            for (Component comp : getComponents()) {
                if (comp instanceof JButton) {
                    ((JButton)comp).setFont(new Font("Arial", Font.BOLD, 14));
                    ((JButton)comp).setBackground(new Color(255, 222, 0));
                    ((JButton)comp).setForeground(new Color(59, 76, 202));
                }
                else if (comp instanceof JLabel) {
                    ((JLabel)comp).setFont(font);
                    ((JLabel)comp).setForeground(new Color(59, 76, 202));
                }
            }
        }
        else if(modify.equals("font")){
            for (Component comp : getComponents()) {
                if (comp instanceof JLabel) {
                    ((JLabel)comp).setFont(font);
                    ((JLabel)comp).setForeground(new Color(59, 76, 202));
                }
            }
        }else if(modify.equals("button")){
            for (Component comp : getComponents()) {
                if (comp instanceof JButton) {
                    ((JButton)comp).setFont(new Font("Arial", Font.BOLD, 14));
                    ((JButton)comp).setBackground(new Color(255, 222, 0));
                    ((JButton)comp).setForeground(new Color(59, 76, 202));
                }
            }
        }
    }
}