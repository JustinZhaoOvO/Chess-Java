package Chess.RestartOrExitComponent;
//CreateTime: 2022-03-18 11:02 p.m.

import Chess.Listeners.ExitComponentMouseListener;
import Chess.Parameters.CONSTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;

import javax.swing.*;
import java.awt.*;

public class ExitComponent extends JPanel{
    private final Image exit;

    public ExitComponent() {
        exit = new ImageIcon("C:\\Programming\\Java files\\JavaIntellij\\src\\Chess\\RestartOrExitComponent\\exit.png").getImage();
        this.setOpaque(false);
        this.addMouseListener(new ExitComponentMouseListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(exit,0,0,this.getWidth(),this.getHeight(),null);
        if (STATICPARAMETERS.UnderMouseCursor == this){
            g.setColor(CONSTANTPARAMETERS.LightBlue);
            for (int i = 0; i < this.getWidth() / 5; i+=2) {
                g.drawRect(i,i,this.getWidth() - i*2,getHeight() - i*2);
            }
        }
    }
}
