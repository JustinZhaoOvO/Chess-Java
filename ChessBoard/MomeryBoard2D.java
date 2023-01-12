package Chess.ChessBoard;
//CreateTime: 2022-03-11 5:36 p.m.

import Chess.ChessPiecesPackage.*;
import Chess.Parameters.CONSTANTPARAMETERS;
import Chess.Parameters.STATICPARAMETERS;
import Chess.PiecesParentAndInterface.Pieces;

import java.util.ArrayList;
import java.util.Collections;

public class MomeryBoard2D {
    public Pieces[][] board;
    private int ID = 0;
    private int color;
    public ArrayList<Pieces> piecesArrayList;
    private Pieces nowPieces;

    public MomeryBoard2D(ArrayList<Pieces> piecesArrayList, boolean white){ // boolean white : choose white or not white(black)
        this.piecesArrayList = piecesArrayList;
        this.board = new Pieces[CONSTANTPARAMETERS.ChessBoardLength][CONSTANTPARAMETERS.ChessBoardLength];
        final int[] needTofill = new int[]{CONSTANTPARAMETERS.oppositeKingRow, CONSTANTPARAMETERS.oppositePawnRow, CONSTANTPARAMETERS.myKingRow, CONSTANTPARAMETERS.myPawnRow};
        for (int i : needTofill) {
           if (i == CONSTANTPARAMETERS.oppositePawnRow || i == CONSTANTPARAMETERS.myPawnRow ){
               color = ((i == CONSTANTPARAMETERS.oppositePawnRow) ^ white)? CONSTANTPARAMETERS.White: CONSTANTPARAMETERS.Black;
               for (int i1 = 0; i1 < CONSTANTPARAMETERS.ChessBoardLength; i1++) {
                   nowPieces = new Pawn(color,ID++,new int[]{i1,i});
                   board[i][i1] = nowPieces;
                   piecesArrayList.add(nowPieces);
               }
           }else{
               color = ((i == CONSTANTPARAMETERS.oppositeKingRow) ^ white)? CONSTANTPARAMETERS.White: CONSTANTPARAMETERS.Black;
               for (int i1 = 0; i1 < CONSTANTPARAMETERS.ChessBoardLength; i1++) {
                   if (i1 == 0 || i1 == 7){
                       nowPieces = new Rook(color,ID++,new int[]{i1,i});
                       board[i][i1] = nowPieces;
                       piecesArrayList.add(nowPieces);
                   }else if(i1 == 1 || i1 == 6){
                       nowPieces = new Knight(color,ID++,new int[]{i1,i});
                       board[i][i1] = nowPieces;
                       piecesArrayList.add(nowPieces);
                   }else if(i1 == 2 || i1 == 5){
                       nowPieces = new Bishop(color,ID++,new int[]{i1,i});
                       board[i][i1] = nowPieces;
                       piecesArrayList.add(nowPieces);
                   }else if(i1 == 3){
                       nowPieces = new Queen(color,ID++,new int[]{i1,i});
                       board[i][i1] = nowPieces;
                       piecesArrayList.add(nowPieces);
                   }else{
                       nowPieces = new King(color,ID++,new int[]{i1,i});
                       board[i][i1] = nowPieces;
                       piecesArrayList.add(nowPieces);
                   }
               }

           }
        }
        Collections.sort(piecesArrayList);
        ((King) piecesArrayList.get(STATICPARAMETERS.BlackKingID)).setLeftSideRook(piecesArrayList.get(STATICPARAMETERS.BlackKingID - 4));
        ((King) piecesArrayList.get(STATICPARAMETERS.BlackKingID)).setRightSideRook(piecesArrayList.get(STATICPARAMETERS.BlackKingID + 3));
        ((King) piecesArrayList.get(STATICPARAMETERS.WhiteKingID)).setLeftSideRook(piecesArrayList.get(STATICPARAMETERS.WhiteKingID - 4));
        ((King) piecesArrayList.get(STATICPARAMETERS.WhiteKingID)).setRightSideRook(piecesArrayList.get(STATICPARAMETERS.WhiteKingID + 3));
    }

    public void updateboard(int[] original, int[] now){
        board[now[1]][now[0]] = board[original[1]][original[0]];
        board[original[1]][original[0]] = null;
    }

    public void deletePiece(int[] coordinate){
        board[coordinate[1]][coordinate[0]] = null;
    }
}
