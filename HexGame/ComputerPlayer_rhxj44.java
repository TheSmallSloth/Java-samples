import java.io.*;
import java.util.ArrayList;
public class ComputerPlayer_rhxj44 implements PlayerInterface
{
    private Piece colour;
    private BufferedReader reader;
    private ArrayList<ArrayList<Integer>> opponentPieces;
    private ArrayList<ArrayList<Integer>> myPieces;
    private ArrayList<Integer> lineStart;
    private ArrayList<Integer> lineEnd;
    public ComputerPlayer_rhxj44()
    {
        colour = Piece.UNSET;
        reader = new BufferedReader(new InputStreamReader(System.in));
        myPieces = new ArrayList<ArrayList<Integer>>();
        opponentPieces = new ArrayList<ArrayList<Integer>>();
        lineStart = new ArrayList<Integer>();
        lineEnd = new ArrayList<Integer>();
    }

    public MoveInterface makeMove(Piece[][] boardView) throws NoValidMovesException
    {
        System.out.print(" ");
        for(int i = 0; i<boardView.length; i++)
        {
            System.out.print(String.format("%8d", i));
        }
        System.out.println("");
        System.out.print("    ");
        for(int i = 0; i<boardView.length; i++)
        {
            System.out.print("RRRRRRRR");
        }
        System.out.println("");
        System.out.print("    ");
        for(int i = 0; i<boardView.length; i++)
        {
            System.out.print("--------");
        }
        System.out.println("");
        for(int i = 0; i<boardView[0].length; i++)
        {
            for(int j = 0; j<((i+1)*4); j++)
            {
                System.out.print(" ");
            }
            System.out.print("B");

            for(int j = 0; j<boardView.length; j++)
            {
                System.out.print("|       ");
            }
            System.out.print("|B");
            System.out.println("");
            System.out.print(String.format("%3d", i));
            for(int j = 0; j<((i+1)*4)-3; j++)
            {
                System.out.print(" ");
            }
            System.out.print("B");
            for(int j = 0; j<boardView.length; j++)
            {
                if(boardView[j][i] == Piece.RED)
                {
                    System.out.print("|   R   ");                   
                }
                else if(boardView[j][i] == Piece.BLUE)
                {
                    System.out.print("|   B   ");                   
                }
                else
                {
                    System.out.print("|       ");
                }
            }
            System.out.print("|B");
            System.out.println("");

            for(int j = 0; j<((i+1)*4); j++)
            {
                System.out.print(" ");
            }
            System.out.print("B");
            for(int j = 0; j<boardView.length; j++)
            {
                System.out.print("|       ");
            }
            System.out.print("|B");
            System.out.println("");

            for(int j = 0; j<((i+1)*4); j++)
            {
                System.out.print(" ");
            }
            System.out.print("B");
            for(int j = 0; j<boardView.length; j++)
            {
                System.out.print("--------");
            }
            System.out.print("----B");
            System.out.println("");
        }
        for(int i = 0; i<(boardView[0].length*4)-1; i++)
        {
            System.out.print(" ");
        }
        for(int i = 0; i<boardView.length; i++)
        {
            System.out.print("RRRRRRRR");
        }
        System.out.println("RRRR");

        boolean validMoves = false;
        for(Piece[] i:boardView)
        {
            for(Piece j:i)
            {
                if(j == Piece.UNSET)
                {
                    validMoves = true;
                }
            }
        }

        if(!validMoves)
        {
            throw new NoValidMovesException();
        }
        if(this.colour == Piece.RED)
        {
            for(int i = 0; i<boardView.length; i++)
            {
                for(int j = 0; j<boardView[i].length; j++)
                {
                    if(boardView[i][j] == Piece.RED)
                    {
                        boolean alreadyKnow = false;
                        for(ArrayList<Integer> myPiece:myPieces)
                        {
                            if(myPiece.get(0) == i && myPiece.get(1) == j)
                            {
                                alreadyKnow = true;
                            }
                        }
                        if(!alreadyKnow)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(i);
                            newPiece.add(j);
                            myPieces.add(newPiece);
                        }
                    }
                    else if(boardView[i][j] == Piece.BLUE)
                    {
                        boolean alreadyKnow = false;
                        for(ArrayList<Integer> opponentPiece:opponentPieces)
                        {
                            if(opponentPiece.get(0) == i && opponentPiece.get(1) == j)
                            {
                                alreadyKnow = true;
                            }
                        }
                        if(!alreadyKnow)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(i);
                            newPiece.add(j);
                            opponentPieces.add(newPiece);
                        }
                    }
                }
            }
            if(opponentPieces.size() == 0)
            {
                int x = boardView.length/2;
                int y = (boardView[0].length/2);
                lineStart.add(x);
                lineStart.add(y);
                lineEnd.add(x);
                lineEnd.add(y);
                MoveInterface newMove = new Move();
                try
                {
                    newMove.setPosition(x,y);
                }
                catch(InvalidPositionException e)
                {

                }
                return newMove;
            }
            if(opponentPieces.size() == 1 && myPieces.size() == 0)
            {
                if(boardView[boardView.length/2][boardView[0].length/2] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    lineStart.add(boardView.length/2);
                    lineStart.add(boardView[0].length/2);
                    lineEnd.add(boardView.length/2);
                    lineEnd.add(boardView[0].length/2);
                    try
                    {
                        newMove.setPosition(boardView.length/2,boardView[0].length/2);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    return newMove;
                }
                else
                {
                    MoveInterface newMove = new Move();
                    lineStart.add((boardView.length/2)-2);
                    lineStart.add((boardView[0].length/2)+1);
                    lineEnd.add((boardView.length/2)-2);
                    lineEnd.add((boardView[0].length/2)+1);
                    try
                    {
                        newMove.setPosition((boardView.length/2)-2,(boardView[0].length/2)+1);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    return newMove;
                }
            }
            for(ArrayList<Integer> myPiece:myPieces)
            {
                if(myPiece.get(0) <= boardView.length-2 && myPiece.get(1) >= 1)
                {
                    if(boardView[myPiece.get(0)][myPiece.get(1)-1] == Piece.BLUE && boardView[myPiece.get(0)+1][myPiece.get(1)-1] == Piece.UNSET)
                    {
                        MoveInterface newMove = new Move();
                        try
                        {
                            newMove.setPosition(myPiece.get(0)+1, myPiece.get(1)-1);
                        }
                        catch(InvalidPositionException e)
                        {

                        }
                        if(myPiece.get(0) == lineStart.get(0) && myPiece.get(1) == lineStart.get(1))
                        {
                            lineStart.clear();
                            lineStart.add(myPiece.get(0)+1);
                            lineStart.add(myPiece.get(1)-1);
                        }
                        return newMove;
                    }                    
                    if(boardView[myPiece.get(0)+1][myPiece.get(1)-1] == Piece.BLUE && boardView[myPiece.get(0)][myPiece.get(1)-1] == Piece.UNSET)
                    {
                        MoveInterface newMove = new Move();
                        try
                        {
                            newMove.setPosition(myPiece.get(0), myPiece.get(1)-1);
                        }
                        catch(InvalidPositionException e)
                        {

                        }
                        if(myPiece.get(0) == lineStart.get(0) && myPiece.get(1) == lineStart.get(1))
                        {
                            lineStart.clear();
                            lineStart.add(myPiece.get(0));
                            lineStart.add(myPiece.get(1)-1);
                        }
                        return newMove;
                    }
                }
                if(myPiece.get(0) >= 1 && myPiece.get(1) <= boardView[0].length-2)
                {
                    if(boardView[myPiece.get(0)-1][myPiece.get(1)+1] == Piece.BLUE && boardView[myPiece.get(0)][myPiece.get(1)+1] == Piece.UNSET)
                    {
                        MoveInterface newMove = new Move();
                        try
                        {
                            newMove.setPosition(myPiece.get(0), myPiece.get(1)+1);
                        }
                        catch(InvalidPositionException e)
                        {

                        }
                        if(myPiece.get(0) == lineEnd.get(0) && myPiece.get(1) == lineEnd.get(1))
                        {
                            lineEnd.clear();
                            lineEnd.add(myPiece.get(0));
                            lineEnd.add(myPiece.get(1)+1);
                        }
                        return newMove;
                    }
                    if(boardView[myPiece.get(0)][myPiece.get(1)+1] == Piece.BLUE && boardView[myPiece.get(0)-1][myPiece.get(1)+1] == Piece.UNSET)
                    {
                        MoveInterface newMove = new Move();
                        try
                        {
                            newMove.setPosition(myPiece.get(0)-1, myPiece.get(1)+1);
                        }
                        catch(InvalidPositionException e)
                        {

                        }
                        if(myPiece.get(0) == lineEnd.get(0) && myPiece.get(1) == lineEnd.get(1))
                        {
                            lineEnd.clear();
                            lineEnd.add(myPiece.get(0)-1);
                            lineEnd.add(myPiece.get(1)+1);
                        }
                        return newMove;
                    }
                }
            }
            if(lineStart.get(0) <= boardView.length-2 && lineStart.get(1) > 2)
            {
                if(boardView[lineStart.get(0)+1][lineStart.get(1)-2] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0)+1;
                    int y = lineStart.get(1)-2;
                    try
                    {
                        newMove.setPosition(x, y);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(lineStart.get(0) < boardView.length-2 && boardView[lineStart.get(0)+2][lineStart.get(1)-2] == Piece.UNSET && boardView[lineStart.get(0)+1][lineStart.get(1)] == Piece.UNSET)
                {
                    int x = lineStart.get(0)+1;
                    int y = lineStart.get(1);
                    MoveInterface newMove = new Move();
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(lineStart.get(0) > 0 && boardView[lineStart.get(0)][lineStart.get(1)-2] == Piece.UNSET && boardView[lineStart.get(0)-1][lineStart.get(1)] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0)-1;
                    int y = lineStart.get(1);
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(boardView[lineStart.get(0)][lineStart.get(1)-1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0);
                    int y = lineStart.get(1)-1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(boardView[lineStart.get(0)+1][lineStart.get(1)-1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0)+1;
                    int y = lineStart.get(1)-1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
            }

            if(lineEnd.get(0) >= 1 && lineEnd.get(1) < boardView[0].length-3)
            {
                if(boardView[lineEnd.get(0)-1][lineEnd.get(1)+2] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0)-1;
                    int y = lineEnd.get(1)+2;
                    try
                    {
                        newMove.setPosition(x, y);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }

                if(lineEnd.get(0) < boardView.length-1 && boardView[lineEnd.get(0)][lineEnd.get(1)+2] == Piece.UNSET && boardView[lineEnd.get(0)+1][lineEnd.get(1)] == Piece.UNSET)
                {
                    int x = lineEnd.get(0)+1;
                    int y = lineEnd.get(1);
                    MoveInterface newMove = new Move();
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(lineEnd.get(0) >= 2 && boardView[lineEnd.get(0)-2][lineEnd.get(1)+2] == Piece.UNSET && boardView[lineEnd.get(0)-1][lineStart.get(1)] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0)-1;
                    int y = lineEnd.get(1);
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(boardView[lineEnd.get(0)][lineEnd.get(1)+1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0);
                    int y = lineEnd.get(1)+1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(boardView[lineEnd.get(0)-1][lineEnd.get(1)+1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0)-1;
                    int y = lineEnd.get(1)+1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
            }
            if(lineEnd.get(1) == boardView[0].length-3)
            {
                if(boardView[lineEnd.get(0)-1][boardView[0].length-1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0)-1;
                    int y = boardView[0].length-1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(boardView[lineEnd.get(0)][boardView[0].length-2] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0);
                    int y = boardView[0].length-2;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(lineEnd.get(0) > 0 && boardView[lineEnd.get(0)-1][boardView[0].length-2] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0)-1;
                    int y = boardView[0].length-2;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
            }
            if(lineStart.get(1) == 2)
            {
                if(boardView[lineStart.get(0)+1][0] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0)+1;
                    int y = 0;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(boardView[lineStart.get(0)][1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0);
                    int y = 1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(lineStart.get(0) < boardView.length-1 && boardView[lineStart.get(0)+1][1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0)+1;
                    int y = 1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
            }
            if(lineStart.get(0) == boardView.length-1 && lineStart.get(1) > 0)
            {
                if(boardView[lineStart.get(0)-1][lineStart.get(1)] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0)-1;
                    int y = lineStart.get(1);
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(boardView[lineStart.get(0)][lineStart.get(1)-1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0);
                    int y = lineStart.get(1)-1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
            }

            if(lineEnd.get(0) == 0 && lineEnd.get(1) < boardView[0].length-1)
            {
                if(boardView[lineEnd.get(0)+1][lineEnd.get(1)] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0)+1;
                    int y = lineEnd.get(1);
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(boardView[lineEnd.get(0)][lineEnd.get(1)+1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0);
                    int y = lineEnd.get(1)+1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
            }

            for(ArrayList<Integer> myPiece:myPieces)
            {
                if(myPiece.get(0) > 0 && myPiece.get(0) < boardView.length-1)
                {
                    if(myPiece.get(1) > 0)
                    {
                        if(boardView[myPiece.get(0)][myPiece.get(1)-1] == Piece.UNSET && boardView[myPiece.get(0)+1][myPiece.get(1)-1] == Piece.UNSET)
                        {
                            MoveInterface newMove = new Move();
                            try
                            {
                                newMove.setPosition(myPiece.get(0),myPiece.get(1)-1);
                            }
                            catch(InvalidPositionException e)
                            {
                            }
                            if(myPiece.get(0) == lineStart.get(0) && myPiece.get(1) == lineStart.get(1))
                            {
                                lineStart.clear();
                                lineStart.add(myPiece.get(0));
                                lineStart.add(myPiece.get(1)-1);
                            }
                            return newMove;
                        }
                    }
                    if(myPiece.get(1) < boardView[0].length-1)
                    {
                        if(boardView[myPiece.get(0)][myPiece.get(1)+1] == Piece.UNSET && boardView[myPiece.get(0)-1][myPiece.get(1)+1] == Piece.UNSET)
                        {
                            MoveInterface newMove = new Move();
                            try
                            {
                                newMove.setPosition(myPiece.get(0),myPiece.get(1)+1);
                            }
                            catch(InvalidPositionException e)
                            {
                            }
                            if(myPiece.get(0) == lineEnd.get(0) && myPiece.get(1) == lineEnd.get(1))
                            {
                                lineEnd.clear();
                                lineEnd.add(myPiece.get(0));
                                lineEnd.add(myPiece.get(1)+1);
                            }
                            return newMove;
                        }
                    }
                }
            }
            for(int i = 0; i<boardView.length; i++)
            {
                for(int j = 0; j<boardView[0].length; j++)
                {
                    if(boardView[i][j] == Piece.UNSET)
                    {
                        MoveInterface newMove = new Move();
                        try
                        {
                            newMove.setPosition(i,j);
                        }
                        catch(InvalidPositionException e)
                        {
                        }
                        return newMove;
                    }
                }
            }
        }
        if(this.colour == Piece.BLUE)
        {
            for(int i = 0; i<boardView.length; i++)
            {
                for(int j = 0; j<boardView[i].length; j++)
                {
                    if(boardView[i][j] == Piece.BLUE)
                    {
                        boolean alreadyKnow = false;
                        for(ArrayList<Integer> myPiece:myPieces)
                        {
                            if(myPiece.get(0) == i && myPiece.get(1) == j)
                            {
                                alreadyKnow = true;
                            }
                        }
                        if(!alreadyKnow)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(i);
                            newPiece.add(j);
                            myPieces.add(newPiece);
                        }
                    }
                    else if(boardView[i][j] == Piece.RED)
                    {
                        boolean alreadyKnow = false;
                        for(ArrayList<Integer> opponentPiece:opponentPieces)
                        {
                            if(opponentPiece.get(0) == i && opponentPiece.get(1) == j)
                            {
                                alreadyKnow = true;
                            }
                        }
                        if(!alreadyKnow)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(i);
                            newPiece.add(j);
                            opponentPieces.add(newPiece);
                        }
                    }
                }
            }
            if(opponentPieces.size() == 0)
            {
                int x = (boardView.length/2);
                int y = (boardView[0].length/2);
                lineStart.add(x);
                lineStart.add(y);
                lineEnd.add(x);
                lineEnd.add(y);
                MoveInterface newMove = new Move();
                try
                {
                    newMove.setPosition(x,y);
                }
                catch(InvalidPositionException e)
                {

                }
                return newMove;
            }
            if(opponentPieces.size() == 1 && myPieces.size() == 0)
            {
                if(boardView[boardView.length/2][boardView[0].length/2] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    lineStart.add(boardView.length/2);
                    lineStart.add(boardView[0].length/2);
                    lineEnd.add(boardView.length/2);
                    lineEnd.add(boardView[0].length/2);
                    try
                    {
                        newMove.setPosition(boardView.length/2,boardView[0].length/2);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    return newMove;
                }
                else
                {
                    MoveInterface newMove = new Move();
                    lineStart.add((boardView.length/2)+1);
                    lineStart.add((boardView[0].length/2)-2);
                    lineEnd.add((boardView.length/2)+1);
                    lineEnd.add((boardView[0].length/2)-2);
                    try
                    {
                        newMove.setPosition((boardView.length/2)+1,(boardView[0].length/2)-2);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    return newMove;
                }
            }
            for(ArrayList<Integer> myPiece:myPieces)
            {
                if(myPiece.get(0) <= boardView.length-2 && myPiece.get(1) >= 1)
                {
                    if(boardView[myPiece.get(0)+1][myPiece.get(1)] == Piece.RED && boardView[myPiece.get(0)+1][myPiece.get(1)-1] == Piece.UNSET)
                    {
                        MoveInterface newMove = new Move();
                        try
                        {
                            newMove.setPosition(myPiece.get(0)+1, myPiece.get(1)-1);
                        }
                        catch(InvalidPositionException e)
                        {

                        }
                        if(myPiece.get(0) == lineStart.get(0) && myPiece.get(1) == lineStart.get(1))
                        {
                            lineStart.clear();
                            lineStart.add(myPiece.get(0)+1);
                            lineStart.add(myPiece.get(1)-1);
                        }
                        return newMove;
                    }                    
                    if(boardView[myPiece.get(0)+1][myPiece.get(1)-1] == Piece.RED && boardView[myPiece.get(0)+1][myPiece.get(1)] == Piece.UNSET)
                    {
                        MoveInterface newMove = new Move();
                        try
                        {
                            newMove.setPosition(myPiece.get(0)+1, myPiece.get(1));
                        }
                        catch(InvalidPositionException e)
                        {

                        }
                        if(myPiece.get(0) == lineStart.get(0) && myPiece.get(1) == lineStart.get(1))
                        {
                            lineStart.clear();
                            lineStart.add(myPiece.get(0)+1);
                            lineStart.add(myPiece.get(1));
                        }
                        return newMove;
                    }
                }
                if(myPiece.get(0) >= 1 && myPiece.get(1) <= boardView[0].length-2)
                {
                    if(boardView[myPiece.get(0)-1][myPiece.get(1)] == Piece.RED && boardView[myPiece.get(0)-1][myPiece.get(1)+1] == Piece.UNSET)
                    {
                        MoveInterface newMove = new Move();
                        try
                        {
                            newMove.setPosition(myPiece.get(0)-1, myPiece.get(1)+1);
                        }
                        catch(InvalidPositionException e)
                        {

                        }
                        if(myPiece.get(0) == lineEnd.get(0) && myPiece.get(1) == lineEnd.get(1))
                        {
                            lineEnd.clear();
                            lineEnd.add(myPiece.get(0)-1);
                            lineEnd.add(myPiece.get(1)+1);
                        }
                        return newMove;
                    }
                    if(boardView[myPiece.get(0)-1][myPiece.get(1)+1] == Piece.RED && boardView[myPiece.get(0)-1][myPiece.get(1)] == Piece.UNSET)
                    {
                        MoveInterface newMove = new Move();
                        try
                        {
                            newMove.setPosition(myPiece.get(0)-1, myPiece.get(1));
                        }
                        catch(InvalidPositionException e)
                        {

                        }
                        if(myPiece.get(0) == lineEnd.get(0) && myPiece.get(1) == lineEnd.get(1))
                        {
                            lineEnd.clear();
                            lineEnd.add(myPiece.get(0)-1);
                            lineEnd.add(myPiece.get(1));
                        }
                        return newMove;
                    }
                }
            }
            if(lineStart.get(0) <= boardView.length-3 && lineStart.get(1) > 0) //Not on right edge or top
            {
                if(boardView[lineStart.get(0)+2][lineStart.get(1)-1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0)+2;
                    int y = lineStart.get(1)-1;
                    try
                    {
                        newMove.setPosition(x, y);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(lineStart.get(1) < boardView[0].length-1 && boardView[lineStart.get(0)+2][lineStart.get(1)] == Piece.UNSET && boardView[lineStart.get(0)][lineStart.get(1)+1] == Piece.UNSET)
                {
                    int x = lineStart.get(0);
                    int y = lineStart.get(1)+1;
                    MoveInterface newMove = new Move();
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(lineStart.get(1) > 1 && boardView[lineStart.get(0)+2][lineStart.get(1)-2] == Piece.UNSET && boardView[lineStart.get(0)][lineStart.get(1)-1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0);
                    int y = lineStart.get(1)-1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(boardView[lineStart.get(0)+1][lineStart.get(1)] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0)+1;
                    int y = lineStart.get(1);
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(boardView[lineStart.get(0)+1][lineStart.get(1)-1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0)+1;
                    int y = lineStart.get(1)-1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
            }
            if(lineEnd.get(0) >= 3 && lineEnd.get(1) < boardView[0].length-1)
            {
                if(boardView[lineEnd.get(0)-2][lineEnd.get(1)+1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0)-2;
                    int y = lineEnd.get(1)+1;
                    try
                    {
                        newMove.setPosition(x, y);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(lineEnd.get(1) <= boardView[0].length-3 && boardView[lineEnd.get(0)-2][lineEnd.get(1)+2] == Piece.UNSET && boardView[lineEnd.get(0)][lineEnd.get(1)+1] == Piece.UNSET)
                {
                    int x = lineEnd.get(0);
                    int y = lineEnd.get(1)+1;
                    MoveInterface newMove = new Move();
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(lineEnd.get(1) >= 1 && boardView[lineEnd.get(0)-2][lineEnd.get(1)] == Piece.UNSET && boardView[lineEnd.get(0)][lineStart.get(1)-1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0);
                    int y = lineEnd.get(1)-1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {

                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(boardView[lineEnd.get(0)-1][lineEnd.get(1)] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0)-1;
                    int y = lineEnd.get(1);
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(boardView[lineEnd.get(0)-1][lineEnd.get(1)+1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0)-1;
                    int y = lineEnd.get(1)+1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
            }
            if(lineEnd.get(0) == 2)
            {
                if(boardView[0][lineEnd.get(1)+1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = 0;
                    int y = lineEnd.get(1)+1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(boardView[1][lineEnd.get(1)] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = 1;
                    int y = lineEnd.get(1);
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(lineEnd.get(1) < boardView[0].length-1 && boardView[1][lineEnd.get(1)+1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = 1;
                    int y = lineEnd.get(1)+1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
            }
            if(lineStart.get(0) == boardView.length-3) //Two down
            {
                if(boardView[boardView.length-1][lineStart.get(1)-1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = boardView.length-1;
                    int y = lineStart.get(1)-1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(boardView[boardView.length-2][lineStart.get(1)] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = boardView.length-2;
                    int y = lineStart.get(1);
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(lineStart.get(1) > 0 && boardView[boardView.length-2][lineStart.get(1)-1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = boardView.length-2;
                    int y = lineStart.get(1)-1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
            }
            if(lineStart.get(1) == 0 && lineStart.get(0) < boardView.length-1) //On the edge
            {
                if(boardView[lineStart.get(0)][lineStart.get(1)+1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0);
                    int y = lineStart.get(1)+1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
                if(boardView[lineStart.get(0)+1][lineStart.get(1)] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineStart.get(0)+1;
                    int y = lineStart.get(1);
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineStart.clear();
                    lineStart.add(x);
                    lineStart.add(y);
                    return newMove;
                }
            }

            if(lineEnd.get(1) == boardView.length-1 && lineEnd.get(0) > 0)
            {
                if(boardView[lineEnd.get(0)][lineEnd.get(1)-1] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0);
                    int y = lineEnd.get(1)-1;
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
                if(boardView[lineEnd.get(0)-1][lineEnd.get(1)] == Piece.UNSET)
                {
                    MoveInterface newMove = new Move();
                    int x = lineEnd.get(0)-1;
                    int y = lineEnd.get(1);
                    try
                    {
                        newMove.setPosition(x,y);
                    }
                    catch(InvalidPositionException e)
                    {
                    }
                    lineEnd.clear();
                    lineEnd.add(x);
                    lineEnd.add(y);
                    return newMove;
                }
            }

            for(ArrayList<Integer> myPiece:myPieces)
            {
                if(myPiece.get(1) > 0 && myPiece.get(1) < boardView[0].length-1)
                {
                    if(myPiece.get(0) > 0)
                    {
                        if(boardView[myPiece.get(0)-1][myPiece.get(1)] == Piece.UNSET && boardView[myPiece.get(0)-1][myPiece.get(1)+1] == Piece.UNSET)
                        {
                            MoveInterface newMove = new Move();
                            try
                            {
                                newMove.setPosition(myPiece.get(0)-1,myPiece.get(1));
                            }
                            catch(InvalidPositionException e)
                            {
                            }
                            if(myPiece.get(0) == lineStart.get(0) && myPiece.get(1) == lineStart.get(1))
                            {
                                lineStart.clear();
                                lineStart.add(myPiece.get(0)-1);
                                lineStart.add(myPiece.get(1));
                            }
                            return newMove;
                        }
                    }
                    if(myPiece.get(0) < boardView.length-1)
                    {
                        if(boardView[myPiece.get(0)+1][myPiece.get(1)] == Piece.UNSET && boardView[myPiece.get(0)+1][myPiece.get(1)-1] == Piece.UNSET)
                        {
                            MoveInterface newMove = new Move();
                            try
                            {
                                newMove.setPosition(myPiece.get(0)+1,myPiece.get(1));
                            }
                            catch(InvalidPositionException e)
                            {
                            }
                            if(myPiece.get(0) == lineEnd.get(0) && myPiece.get(1) == lineEnd.get(1))
                            {
                                lineEnd.clear();
                                lineEnd.add(myPiece.get(0)+1);
                                lineEnd.add(myPiece.get(1));
                            }
                            return newMove;
                        }
                    }
                }
            }
            for(int i = 0; i<boardView.length; i++)
            {
                for(int j = 0; j<boardView[0].length; j++)
                {
                    if(boardView[i][j] == Piece.UNSET)
                    {
                        MoveInterface newMove = new Move();
                        try
                        {
                            newMove.setPosition(i,j);
                        }
                        catch(InvalidPositionException e)
                        {
                        }
                        return newMove;
                    }
                }
            }
        }
        return null;
    }

    public boolean setColour(Piece colour) throws InvalidColourException, ColourAlreadySetException
    {
        if(colour != Piece.RED && colour != Piece.BLUE)
        {
            throw new InvalidColourException();
        }
        if(this.colour != Piece.UNSET)
        {
            throw new ColourAlreadySetException();
        }
        this.colour=colour;
        return true;
    }

    public boolean finalGameState(GameState state)
    {
        if(state == GameState.WON)
        {
            if(colour == Piece.RED)
            {
                System.out.println("Red player wins");
            }
            else if(colour == Piece.BLUE)
            {
                System.out.println("Blue player wins");
            }
            else
            {
                System.out.println("Set player's colour before calling finalGameState");
            }
        }
        return true;
    }
}
