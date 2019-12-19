public class Player implements PlayerInterface
{
    private Piece playerColour;
    public Player()
    {
        playerColour = Piece.UNSET;
    }
    public MoveInterface makeMove(Piece[][] boardView) throws NoValidMovesException
    {
        return null;
    }
    

    public boolean setColour(Piece colour) throws InvalidColourException, ColourAlreadySetException
    {
        if(colour != Piece.RED && colour !=Piece.BLUE)
        {
            throw new InvalidColourException();
        }
        if(playerColour == Piece.UNSET)
        {
            playerColour = colour;
            return true;
        }
        else
        {
            throw new ColourAlreadySetException();
        }
    }
    
    
    public boolean finalGameState(GameState state)
    {
        return true;
    }
}
