import java.util.*;

public class AIAgent{
  Random rand;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

  public Move randomMove(Stack possibilities){

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    return(Move)possibilities.pop();
  }

  public Move nextBestMove(Stack whiteStack, Stack blackStack){
        Stack backup = (Stack) whiteStack.clone();
        Stack black = (Stack) blackStack.clone();
        Move wMove, sMove, aMove;
        Square blackPosition;
        int Point = 0;
        int cPiece = 0;
        aMove = null;

        while (!whiteStack.empty()) {
            wMove = (Move) whiteStack.pop();
            sMove = wMove;

            //assign 1 point to centre position on board
            if ((sMove.getStart().getYC() < sMove.getLanding().getYC())
                    && (sMove.getLanding().getXC() == 3) && (sMove.getLanding().getYC() == 3)
                    || (sMove.getLanding().getXC() == 4) && (sMove.getLanding().getYC() == 3)
                    || (sMove.getLanding().getXC() == 3) && (sMove.getLanding().getYC() == 4)
                    || (sMove.getLanding().getXC() == 4) && (sMove.getLanding().getYC() == 4)) {
                Point = 0;
                //assign best move
                if (Point > cPiece) {
                    cPiece = Point;
                    aMove = sMove;
                }
            }

            //return an attacking move if piece has higher point value than centre position
            //Note: I have assigned the BlackPawn 2 points which is unconventional so as to have the centre poisition take priority
            //Over a random move
            while (!black.isEmpty()) {
                Point = 0;
                blackPosition = (Square) black.pop();
                if ((sMove.getLanding().getXC() == blackPosition.getXC()) && (sMove.getLanding().getYC() == blackPosition.getYC())) {
                    //Check for black pawn and assign points
                    if (blackPosition.getName().equals("BlackPawn")) {
                        Point = 1;
                    }
                    //Check for black bishop/knight and assign points
                    else if (blackPosition.getName().equals("BlackBishop") || blackPosition.getName().equals("BlackKnight")) {
                        Point = 3;
                    }
                    //Check for black rook and assign points
                    else if (blackPosition.getName().equals("BlackRook")) {
                        Point = 5;
                    }
                    //Check for black queen and assign points
                    else if (blackPosition.getName().equals("BlackQueen")) {
                        Point = 9;
                    }
                }
                //update bestmove
                if (Point > cPiece) {
                    cPiece = Point;
                    aMove = sMove;
                }
            }
            //reload black squares
            black = (Stack) blackStack.clone();
        }
        // use best move if available or just go random
        if (cPiece > 0) {
            System.out.println("Best move selected by AI agent:" + cPiece);
            return aMove;
        }
        return randomMove(backup);
    }

  public Move twoLevelsDeep(Stack whiteStack, Stack blackStack) {
        Stack backup = (Stack) whiteStack.clone();
        Stack black = (Stack) blackStack.clone();
        Move wMove, sMove, aMove;
        Square blackPosition;
        int Point = 0;
        int cPiece = 0;
        aMove = null;
}
}
