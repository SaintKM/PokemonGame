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
}