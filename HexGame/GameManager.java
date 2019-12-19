import java.util.HashSet;
import java.io.*;
public class GameManager implements GameManagerInterface
{
    private PlayerInterface red;
    private PlayerInterface blue;
    private HashSet<Piece> usedColours;
    private BoardInterface board;
    public GameManager()
    {
        usedColours = new HashSet<Piece>();
        board = new Board();
    }

    public static void main(String[] args)
    {
        GameManagerInterface gameManager;
        gameManager = new GameManager();
        try
        {
            gameManager.boardSize(10,10);
        }
        catch(InvalidBoardSizeException e)
        {
            System.out.println("Error: The board size given was invalid");
            return;
        }
        catch(BoardAlreadySizedException e)
        {
            System.out.println("Error: Board was already sized");
            return;
        }
        PlayerInterface redPlayer = new HumanPlayer();
        PlayerInterface bluePlayer = new ComputerPlayer_rhxj44();
        try
        {
            gameManager.specifyPlayer(redPlayer, Piece.RED);
        }
        catch(InvalidColourException e)
        {
            System.out.println("Error: An invalid colour was given to a player");
            return;
        }
        catch(ColourAlreadySetException e)
        {
            System.out.println("Error: Player's colour was already set");
            return;
        }
        try
        {
            gameManager.specifyPlayer(bluePlayer, Piece.BLUE);
        }
        catch(InvalidColourException e)
        {
            System.out.println("Error: An invalid colour was given to a player");
        }
        catch(ColourAlreadySetException e)
        {
            System.out.println("Error: Player's colour was already set");
        }
        if(redPlayer != null && bluePlayer != null)
        {
            gameManager.playGame();
        }
    }

    public boolean specifyPlayer(PlayerInterface player, Piece colour) throws InvalidColourException, ColourAlreadySetException
    {
        if(colour != Piece.RED && colour != Piece.BLUE)
        {
            throw new InvalidColourException();
        }
        if(usedColours.contains(colour))
        {
            throw new ColourAlreadySetException();
        }
        if(player == null)
        {
            System.out.println("Error: Player was set to null");
            return false;
        }
        try
        {
            if(!player.setColour(colour))
            {
                System.out.println("Setting player's colour failed for unknown reason");
                return false;
            }
        }
        catch(ColourAlreadySetException e)
        {
            throw new ColourAlreadySetException();
        }
        catch(InvalidColourException e)
        {
            throw new InvalidColourException();
        }
        if(colour == Piece.RED)
        {
            red = player;
        }
        else if(colour == Piece.BLUE)
        {
            blue = player;
        }
        usedColours.add(colour);
        return true;
    }

    public boolean boardSize(int sizeX, int sizeY) throws InvalidBoardSizeException, BoardAlreadySizedException
    {
        try
        {
            if(!board.setBoardSize(sizeX, sizeY))
            {
                System.out.println("Setting board size failed for unknown reason");
                return false;
            }
        }
        catch(InvalidBoardSizeException e)
        {
            throw new InvalidBoardSizeException();
        }
        catch(BoardAlreadySizedException e)
        {
            throw new BoardAlreadySizedException();
        }
        return true;
    }

    public boolean playGame()
    {
        boolean redWin = false;
        boolean blueWin = false;
        Piece winner = Piece.UNSET;
        MoveInterface redMove = new Move();
        MoveInterface blueMove = new Move();
        while(true)
        {
            System.out.println("Red player's turn");
            boolean valid = false;
            while(!valid)
            {
                valid = true;
                try
                {
                    redMove = red.makeMove(board.getBoardView());
                }
                catch(NoValidMovesException e)
                {
                    System.out.println("No valid moves are available");
                    break;
                }
                catch(NoBoardDefinedException e)
                {
                    System.out.println("Error: Game was played without defining a board");
                    return false;
                }

                if(redMove.hasConceded())
                {
                    blueWin = true;
                    break;
                }
                else
                {
                    try
                    {
                        board.placePiece(Piece.RED, redMove);
                    }
                    catch(PositionAlreadyTakenException e)
                    {
                        System.out.println("Position already taken, try again");
                        valid = false;
                    }
                    catch(InvalidPositionException e)
                    {
                        System.out.println("Invalid position given, try again");
                        valid = false;
                    }
                    catch(InvalidColourException e)
                    {
                        System.out.println("Error: Player tried to play game with an invalid colour");
                        return false;
                    }
                    catch(NoBoardDefinedException e)
                    {
                        System.out.println("Error: Game was played without defining a board");
                        return false;
                    }
                }
            }
            if(blueWin)
            {
                break;
            }
            winner = Piece.UNSET;
            try
            {
                winner = board.gameWon();
            }
            catch(NoBoardDefinedException e)
            {
                System.out.println("Error: Game was played without defining a board");
                return false;
            }
            if(winner == Piece.RED)
            {
                redWin=true;
                break;
            }
            else if(winner == Piece.BLUE)
            {
                blueWin = true;
                break;
            }
            System.out.println("Blue player's turn");
            valid = false;
            while(!valid)
            {
                valid = true;
                try
                {
                    blueMove = blue.makeMove(board.getBoardView());
                }
                catch(NoValidMovesException e)
                {
                    System.out.println("No valid moves are available");
                    break;
                }
                catch(NoBoardDefinedException e)
                {
                    System.out.println("Error: Game was played without defining a board");
                    return false;
                }
                if(blueMove.hasConceded())
                {
                    redWin = true;
                    break;
                }
                else
                {
                    try
                    {
                        board.placePiece(Piece.BLUE, blueMove);
                    }
                    catch(PositionAlreadyTakenException e)
                    {
                        System.out.println("Position already taken, try again");
                        valid = false;
                    }
                    catch(InvalidPositionException e)
                    {
                        System.out.println("Invalid position given, try again");
                        valid = false;
                    }
                    catch(InvalidColourException e)
                    {
                        System.out.println("Error: Player tried to play game with an invalid colour");
                        return false;
                    }
                    catch(NoBoardDefinedException e)
                    {
                        System.out.println("Error: Game was played without defining a board");
                        return false;
                    }
                }
            }
            if(redWin)
            {
                break;
            }
            try
            {
                winner = board.gameWon();
            }
            catch(NoBoardDefinedException e)
            {
                System.out.println("Error: Game was played without defining a board");
                break;
            }
            if(winner == Piece.RED)
            {
                redWin=true;
                break;
            }
            else if(winner == Piece.BLUE)
            {
                blueWin = true;
                break;
            }
        }
        Piece[][] boardView;
        try
        {
            boardView = board.getBoardView();
        }
        catch(NoBoardDefinedException e)
        {
            System.out.println("Error: Game was played without defining a board");
            return false;
        }
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
        if(redWin)
        {
            red.finalGameState(GameState.WON);
        }
        else if(blueWin)
        {
            blue.finalGameState(GameState.WON);
        }
        else
        {
            System.out.println("Neither player won, must have been an error");
            return false;
        }
        return true;
    }
}
