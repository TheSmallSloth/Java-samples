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
        
        return true;
    }
    

    public Piece[][] getBoardView() throws NoBoardDefinedException
    {
        return null;
    }

    public boolean placePiece(Piece colour, MoveInterface move) throws PositionAlreadyTakenException, InvalidPositionException, InvalidColourException
    {
        return true;
    }
    
    public Piece gameWon() throws NoBoardDefinedException
    {
        return null;
    }
}
