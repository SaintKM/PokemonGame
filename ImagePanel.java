//ImagePanel.java
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;

public class ImagePanel extends JPanel
{
    private int imgWidth, imgHeight;
    private BufferedImage panelImage;

    public ImagePanel()
    {
        panelImage = null;
        setBackground(Color.black);
    }

    public void displayImage(File f, int panelWidth, int panelHeight)
    {
        BufferedImage image = loadImage(f);
        if(image != null){
            imgWidth = image.getWidth();
            imgHeight = image.getHeight();
            //double percentage = 1;
            /*if(imgWidth > panelWidth || imgHeight > panelHeight){
                double percentage1 = panelWidth/imgWidth;
                double percentage2 = panelHeight/imgHeight;
                percentage = percentage1 > percentage2 ? percentage2 : percentage1;
                if(percentage < 0.1)
                    percentage = 0.1;
            }*/
            //System.out.println(panelWidth + " : " + panelHeight);
            //System.out.println(imgWidth * percentage + " : " + imgHeight * percentage);
            Image newImage = image.getScaledInstance((int)(panelWidth), (int)(panelHeight), Image.SCALE_DEFAULT);
            BufferedImage bfNewImage = new BufferedImage(newImage.getWidth(null), newImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = bfNewImage.createGraphics();
            bGr.drawImage(newImage, 0, 0, null);
            bGr.dispose();
            panelImage = bfNewImage;
            invalidate();
            repaint();
        }
    }

    private BufferedImage loadImage(File imageFile)
    {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            if(image == null || (image.getWidth()) < 0)
                return null;
            return image;
        }
        catch (IOException e) {
            return null;
        }
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(imgWidth, imgHeight);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Dimension size = getSize();
        g.clearRect(0, 0, size.width, size.height);
        if(panelImage != null)
            g.drawImage(panelImage, 0, 0, null);
    }
}