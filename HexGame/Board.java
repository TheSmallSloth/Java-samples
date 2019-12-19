import java.util.ArrayList;
public class Board implements BoardInterface
{
    private int boardSizeX;
    private int boardSizeY;
    private Piece[][] board;
    public Board()
    {
        boardSizeX = 0;
        boardSizeY = 0;
    }

    public boolean setBoardSize(int sizeX, int sizeY) throws InvalidBoardSizeException, BoardAlreadySizedException
    {
        if(boardSizeX != 0 && boardSizeY != 0)
        {
            throw new BoardAlreadySizedException();
        }
        if(sizeX<1 || sizeY<1)
        {
            throw new InvalidBoardSizeException();
        }
        boardSizeX = sizeX;
        boardSizeY = sizeY;
        board = new Piece[sizeX][sizeY];
        for(int i = 0; i<boardSizeX; i++)
        {
            for(int j = 0; j<boardSizeY; j++)
            {
                board[i][j] = Piece.UNSET;
            }
        }
        return true;
    }

    public Piece[][] getBoardView() throws NoBoardDefinedException
    {
        if(boardSizeX == 0 || boardSizeY == 0)
        {
            throw new NoBoardDefinedException();
        }
        return board;
    }

    public boolean placePiece(Piece colour, MoveInterface move) throws PositionAlreadyTakenException, InvalidPositionException, InvalidColourException, NoBoardDefinedException
    {
        if(boardSizeX == 0 || boardSizeY == 0)
        {
            throw new NoBoardDefinedException();
        }
        if(move.getXPosition() > (boardSizeX-1) || move.getXPosition() < 0 || move.getYPosition() > (boardSizeY-1) || move.getYPosition() < 0)
        {
            throw new InvalidPositionException();
        }
        if(board[move.getXPosition()][move.getYPosition()] != Piece.UNSET)
        {
            throw new PositionAlreadyTakenException();
        }
        if(colour != Piece.RED && colour != Piece.BLUE)
        {
            throw new InvalidColourException();
        }
        board[move.getXPosition()][move.getYPosition()] = colour;
        return true;
    }

    public Piece gameWon() throws NoBoardDefinedException
    {
        if(boardSizeX == 0 || boardSizeY == 0)
        {
            throw new NoBoardDefinedException();
        }
        ArrayList<ArrayList<Integer>> bluePieces = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> redPieces = new ArrayList<ArrayList<Integer>>();
        boolean blueWin = false;
        boolean redWin = false;
        for(int i = 0; i<boardSizeY; i++)
        {
            if(board[0][i] == Piece.BLUE)
            {
                ArrayList<Integer> piece = new ArrayList<Integer>();
                piece.add(0);
                piece.add(i);
                bluePieces.add(piece);
            }
        }
        int numBlue = 0;
        while(bluePieces.size() != numBlue)
        {
            ArrayList<ArrayList<Integer>> tempList= new ArrayList<ArrayList<Integer>>();
            numBlue = bluePieces.size();
            for(ArrayList<Integer> piece:bluePieces)
            {
                int x = piece.get(0)-1;
                int y = piece.get(1);
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.BLUE)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:bluePieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
                x = piece.get(0);
                y = piece.get(1)-1;
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.BLUE)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:bluePieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
                x = piece.get(0)+1;
                y = piece.get(1)-1;
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.BLUE)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:bluePieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
                x = piece.get(0)+1;
                y = piece.get(1);
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.BLUE)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:bluePieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
                x = piece.get(0);
                y = piece.get(1)+1;
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.BLUE)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:bluePieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
                x = piece.get(0)-1;
                y = piece.get(1)+1;
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.BLUE)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:bluePieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
            }
            for(ArrayList<Integer> temp:tempList)
            {
                bluePieces.add(temp);
            }
        }
        for(ArrayList<Integer> piece:bluePieces)
        {
            if(piece.get(0) == boardSizeX-1)
            {
                return Piece.BLUE;
            }
        }
        
        
        for(int i = 0; i<boardSizeX; i++)
        {
            if(board[i][0] == Piece.RED)
            {
                ArrayList<Integer> piece = new ArrayList<Integer>();
                piece.add(i);
                piece.add(0);
                redPieces.add(piece);
            }
        }
        int numRed = 0;
        while(redPieces.size() != numRed)
        {
            ArrayList<ArrayList<Integer>> tempList= new ArrayList<ArrayList<Integer>>();
            numRed = redPieces.size();
            for(ArrayList<Integer> piece:redPieces)
            {
                int x = piece.get(0)-1;
                int y = piece.get(1);
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.RED)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:redPieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
                x = piece.get(0);
                y = piece.get(1)-1;
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.RED)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:redPieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
                x = piece.get(0)+1;
                y = piece.get(1)-1;
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.RED)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:redPieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
                x = piece.get(0)+1;
                y = piece.get(1);
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.RED)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:redPieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
                x = piece.get(0);
                y = piece.get(1)+1;
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.RED)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:redPieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
                x = piece.get(0)-1;
                y = piece.get(1)+1;
                if(x>=0 && x<boardSizeX && y >= 0 && y<boardSizeY)
                {
                    if(board[x][y] == Piece.RED)
                    {
                        boolean alreadyFound = false;
                        for(ArrayList<Integer> foundPiece:redPieces)
                        {
                            if(foundPiece.get(0) == x && foundPiece.get(1) == y)
                            {
                                alreadyFound = true;
                            }
                        }
                        if(!alreadyFound)
                        {
                            ArrayList<Integer> newPiece = new ArrayList<Integer>();
                            newPiece.add(x);
                            newPiece.add(y);
                            tempList.add(newPiece);
                        }
                    }
                }
            }
            for(ArrayList<Integer> temp:tempList)
            {
                redPieces.add(temp);
            }
        }
        for(ArrayList<Integer> piece:redPieces)
        {
            if(piece.get(1) == boardSizeY-1)
            {
                return Piece.RED;
            }
        }
        return Piece.UNSET;
    }
}
