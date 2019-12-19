import java.util.HashSet;
public class GameManager implements GameManagerInterface
{
    private PlayerInterface player1;
    private PlayerInterface player2;
    private HashSet<Piece> usedColours;
    private BoardInterface board;
    public GameManager()
    {
        player1 = new Player();
        player2 = new Player();
        usedColours = new HashSet<Piece>();
        board = new Board();
    }
    
    public boolean specifyPlayer(PlayerInterface player, Piece colour) throws ColourAlreadySetException
    {
        if(usedColours.contains(colour))
        {
            throw new ColourAlreadySetException();
        }
        try
        {
            player.setColour(colour);
        }
        catch(ColourAlreadySetException e)
        {
            return false;
        }
        catch(InvalidColourException e)
        {
            return false;
        }
        usedColours.add(colour);
        return true;
    }

    public boolean boardSize(int sizeX, int sizeY) throws InvalidBoardSizeException, BoardAlreadySizedException
    {
        return true;
    }
    
    public boolean playGame()
    {
        return true;
    }
}
