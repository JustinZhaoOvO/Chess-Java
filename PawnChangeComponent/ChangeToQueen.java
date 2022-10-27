package Chess.PawnChangeComponent;
//CreateTime: 2022-03-17 1:52 p.m.

import Chess.Paramenter.CONTANTPARAMETERS;
import Chess.Paramenter.STATICPARAMETERS;

import javax.swing.*;
import java.awt.*;

public class ChangeToQueen extends JPanel {
    private int color;
    private Image pieceImage;
    public ChangeToQueen(int color ){
        this.color = color;
        if (this.color == CONTANTPARAMETERS.Black){
            pieceImage = new ImageIcon("C:\\Programming\\Java files\\JavaIntellij\\src\\Chess\\ChessPiecesElements\\QueenBlack.png").getImage();
        }else{
            pieceImage = new ImageIcon("C:\\Programming\\Java files\\JavaIntellij\\src\\Chess\\ChessPiecesElements\\QueenWhite.png").getImage();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(CONTANTPARAMETERS.ChooseComponentColor);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.drawImage(pieceImage,0,0,this.getWidth(),this.getHeight(),null);
        if (STATICPARAMETERS.UnderMouseCursor == this){
            g.setColor(CONTANTPARAMETERS.lightGreen);
            for (int i = 0; i < this.getWidth() / 5; i+=2) {
                g.drawRect(i,i,this.getWidth() - i*2,getHeight() - i*2);
            }
        }
    }
}
