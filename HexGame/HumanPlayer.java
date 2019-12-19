import java.io.*;
public class HumanPlayer implements PlayerInterface
{
    private Piece colour;
    private BufferedReader reader;
    public HumanPlayer()
    {
        colour = Piece.UNSET;
        reader = new BufferedReader(new InputStreamReader(System.in));
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

        int x = 0;
        int y = 0;
        String input;

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
            System.out.println("Red player, type an x value, or type concede");
        }

        else
        {
            System.out.println("Blue player, type an x value, or type concede");
        }
        boolean valid = false;
        while(!valid)
        {
            valid = true;
            try
            {
                input = reader.readLine();
            }
            catch(IOException e)
            {
                System.out.println("Input error, try again");
                input = "a";
                valid = false;
            }

            if(valid)
            {
                if(input.equals("concede") || input.equals("CONCEDE") || input.equals("Concede"))
                {
                    MoveInterface newMove = new Move();
                    newMove.setConceded();
                    return newMove;
                }

                try
                {
                    x = Integer.parseInt(input);
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Input error, try again");
                    valid = false;
                }
            }
        }

        if(this.colour == Piece.RED)
        {
            System.out.println("Red player, type a y value, or type concede");
        }

        else
        {
            System.out.println("Blue player, type a y value, or type concede");
        }
        valid = false;
        while(!valid)
        {
            valid = true;
            try
            {
                input = reader.readLine();
            }
            catch(IOException e)
            {
                System.out.println("Input error, try again");
                input = "a";
                valid = false;
            }

            if(valid)
            {
                if(input.equals("concede") || input.equals("CONCEDE") || input.equals("Concede"))
                {
                    MoveInterface newMove = new Move();
                    newMove.setConceded();
                    return newMove;
                }

                try
                {
                    y = Integer.parseInt(input);
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Input error, try again");
                    valid = false;
                }
            }
        }
        MoveInterface newMove = new Move();
        try
        {
            if(!newMove.setPosition(x,y))
            {
                return null;
            }
        }
        catch(InvalidPositionException e)
        {
        }
        return newMove;
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
