package Chess.Parameters;
//CreateTime: 2022-03-15 7:52 p.m.

import Chess.ChessBoard.ChessBoardComponent;
import Chess.ChessBoard.ChessBoardPanel;
import Chess.ChessBoard.MomeryBoard2D;
import Chess.ChessPiecesPackage.*;
import Chess.ChessTimer.ChessTimerComponent;
import Chess.Listeners.ChessPiecesMouseListener;
import Chess.PawnChangeComponent.ComponentPanel;
import Chess.PiecesParentAndInterface.Pieces;
import Chess.PiecesParentAndInterface.PiecesComponent;
import Chess.ShowRoundComponent.RoundComponent;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

public class STATICPARAMETERS {
    public static int ColorStatus;
    public static PiecesComponent clicked;
    public static Component UnderMouseCursor;
    public static ChessBoardComponent[][] chessBoardComponents;
    public static ArrayList<Pieces> chessPiecesList;
    public static ArrayList<int[]> clickedPieceCanMove;
    public static MomeryBoard2D ChessPiece2DList;
    public static ChessBoardPanel Root;
    public static boolean Gaming;
    public static int Win;
    public static int WhiteKingID;
    public static int BlackKingID;
    public static String PawnChangeTo;
    public static boolean Changing;
    public static ComponentPanel changeComponent;
    public static Pawn needToInstead;
    public static JFrame MainWindow;
    public static int NoneReStartExit; //0 none 1 restart 2 exit
    public static int[] blackPawnMove1Step;
    public static int[] blackPawnMove2Step;
    public static PiecesComponent blackMove2Step;
    public static int[] whitePawnMove1Step;
    public static int[] whitePawnMove2Step;
    public static PiecesComponent whiteMove2Step;
    public static Pieces[][] transientBoard;
    public static ArrayList<Pieces> transientchessPiecesList;
    public static ChessTimerComponent uptimer;
    public static ChessTimerComponent downtimer;
    public static int DrawTime;
    public static RoundComponent ROUND_COMPONENT;
    public static Boolean startTimer;
    public static boolean IsContains(ArrayList<int[]> arr, int[] ... o){
        for (int[] ints : o) {
            for (int[] e : arr) {
                if (Arrays.equals(e,ints)) {
                    return true;
                }
            }
        }return false;
    }public static void Change(){
        if(STATICPARAMETERS.PawnChangeTo != null){
            if (CONSTANTPARAMETERS.ChooseWhite){
                if (STATICPARAMETERS.ColorStatus == 1){
                    STATICPARAMETERS.downtimer.start();
                }else{
                    STATICPARAMETERS.uptimer.start();
                }
            }else{
                if (STATICPARAMETERS.ColorStatus == 0){
                    STATICPARAMETERS.downtimer.start();
                }else{
                    STATICPARAMETERS.uptimer.start();
                }
            }
            Pieces newpieces =  STATICPARAMETERS.changeToOther(STATICPARAMETERS.PawnChangeTo,STATICPARAMETERS.needToInstead);

            STATICPARAMETERS.Root.remove(STATICPARAMETERS.needToInstead.getComponent());
            STATICPARAMETERS.Root.remove(STATICPARAMETERS.changeComponent);
            STATICPARAMETERS.ChessPiece2DList.board[STATICPARAMETERS.needToInstead.coordinate[1]][STATICPARAMETERS.needToInstead.coordinate[0]] = newpieces;
            newpieces.getComponent().addMouseListener(new ChessPiecesMouseListener());
            STATICPARAMETERS.chessPiecesList.set(STATICPARAMETERS.needToInstead.ID,newpieces);
            Component[] components = STATICPARAMETERS.Root.getComponents();
            for (Component component1 : components) {
                STATICPARAMETERS.Root.remove(component1);
            }
            STATICPARAMETERS.Root.add(newpieces.getComponent());
            for (Component component1 : components) {
                STATICPARAMETERS.Root.add(component1);
            }STATICPARAMETERS.PawnChangeTo = null;
        }
    }public static Pieces changeToOther(String s,Pieces pieces){
        switch (s){
            case "Queen":
                return new Queen(pieces.color,pieces.ID,pieces.coordinate);
            case "Bishop":
                return new Bishop(pieces.color,pieces.ID,pieces.coordinate);
            case "Rook":
                return new Rook(pieces.color,pieces.ID,pieces.coordinate);
            case "Knight":
                return new Knight(pieces.color,pieces.ID,pieces.coordinate);
            default:
                return null;
        }
    }public static AudioClip audioClip;

    static {
        try {
            audioClip = Applet.newAudioClip(new File("C:\\Programming\\Java files\\JavaIntellij\\src\\Chess\\ChessSound.wav").toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }public static Pieces[][] deepCopy(Pieces[][] original) {

        final Pieces[][] result = new Pieces[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }public static void GameOver(){

        if (!STATICPARAMETERS.Gaming){
            return;
        }
        if (STATICPARAMETERS.DrawTime >= 50) {
            STATICPARAMETERS.Gaming = false;
            return;

        }int i1;
        int i2;
        if (STATICPARAMETERS.ColorStatus == 0){
            if (CONSTANTPARAMETERS.ChooseWhite){
                i1 = 0;
                i2 = 16;
            }else{
                i1 = 16;
                i2 = 32;
            }
        }else{
            if (!(CONSTANTPARAMETERS.ChooseWhite)){
                i1 = 0;
                i2 = 16;
            }else{
                i1 = 16;
                i2 = 32;
            }
        }
        for (int i = i1; i < i2; i++) {
            if (STATICPARAMETERS.chessPiecesList.get(i) != null) {
                if (STATICPARAMETERS.chessPiecesList.get(i).getAllCanMoveCoordinatesCheck().size() != 0) {
                    STATICPARAMETERS.Gaming = true;
                    return;
                }
            }
        }STATICPARAMETERS.Gaming = false;
    }public static boolean CheckCanMove(int[] orginal , int[] aftermove,Pieces thispiece){
        Pieces atePiece;
        atePiece = STATICPARAMETERS.ChessPiece2DList.board[aftermove[1]][aftermove[0]];
        STATICPARAMETERS.transientBoard = STATICPARAMETERS.deepCopy(STATICPARAMETERS.ChessPiece2DList.board);
        STATICPARAMETERS.transientchessPiecesList = new ArrayList<>(STATICPARAMETERS.chessPiecesList.size());
        STATICPARAMETERS.transientchessPiecesList.addAll((ArrayList<Pieces>) STATICPARAMETERS.chessPiecesList.clone());
        STATICPARAMETERS.ChessPiece2DList.updateboard(orginal , aftermove);
        if (atePiece instanceof King){
            return true;
        }
        if (atePiece != null) {
            STATICPARAMETERS.chessPiecesList.set(atePiece.ID, null);
        }
        int i1;
        int i2;
        Pieces pieces;
        if (STATICPARAMETERS.ColorStatus == 0) {
            pieces = STATICPARAMETERS.chessPiecesList.get(STATICPARAMETERS.BlackKingID);
            if (CONSTANTPARAMETERS.ChooseWhite) {
                i1 = 16;
                i2 = 32;
            } else {
                i1 = 0;
                i2 = 16;
            }
        } else {
            pieces = STATICPARAMETERS.chessPiecesList.get(STATICPARAMETERS.WhiteKingID);
            if (!CONSTANTPARAMETERS.ChooseWhite) {
                i1 = 16;
                i2 = 32;
            }else {
                i1 = 0;
                i2 = 16;
            }
        }for (int i = i1; i < i2; i++) {
            if (STATICPARAMETERS.chessPiecesList.get(i) == null) {
                continue;
            }
            boolean b = STATICPARAMETERS.IsContains(STATICPARAMETERS.chessPiecesList.get(i).getAllCanMoveCoordinates(), thispiece instanceof King?  aftermove:pieces.coordinate);
            if (b) {
                STATICPARAMETERS.ChessPiece2DList.board = STATICPARAMETERS.transientBoard;
                STATICPARAMETERS.chessPiecesList = STATICPARAMETERS.transientchessPiecesList;
                return false;
            }
        }
        STATICPARAMETERS.ChessPiece2DList.board = STATICPARAMETERS.transientBoard;
        for (int i = 0; i < STATICPARAMETERS.transientchessPiecesList.size(); i++) {
            STATICPARAMETERS.chessPiecesList.set(i,STATICPARAMETERS.transientchessPiecesList.get(i));
        }
        return true;
    }

}
