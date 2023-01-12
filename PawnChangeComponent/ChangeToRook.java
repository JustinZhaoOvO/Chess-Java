package Chess.PawnChangeComponent;
//CreateTime: 2022-03-17 11:27 a.m.

import Chess.Parameters.CONSTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;

import javax.swing.*;
import java.awt.*;

public class ChangeToRook extends JPanel {
    private int color;
    private Image pieceImage;
    public ChangeToRook(int color ){
        this.color = color;
        if (this.color == CONSTANTPARAMETERS.Black){
            pieceImage = new ImageIcon("C:\\Programming\\Java files\\JavaIntellij\\src\\Chess\\ChessPiecesElements\\RookBlack.png").getImage();
        }else{
            pieceImage = new ImageIcon("C:\\Programming\\Java files\\JavaIntellij\\src\\Chess\\ChessPiecesElements\\RookWhite.png").getImage();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(CONSTANTPARAMETERS.ChooseComponentColor);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.drawImage(pieceImage,0,0,this.getWidth(),this.getHeight(),null);
        if (STATICPARAMETERS.UnderMouseCursor == this){
            g.setColor(CONSTANTPARAMETERS.lightGreen);
            for (int i = 0; i < this.getWidth() / 5; i+=2) {
                g.drawRect(i,i,this.getWidth() - i*2,getHeight() - i*2);
            }
        }

    }
}
