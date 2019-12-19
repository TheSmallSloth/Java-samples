public class Move implements MoveInterface
{
    public Move()
    {
        
    }
    public boolean setPosition(int x, int y) throws InvalidPositionException
    {
        return true;
    }
    

    public boolean hasConceded()
    {
        return true;
    }
    

    public int getXPosition()
    {
        return 1;
    }
    

    public int getYPosition()
    {
        return 1;
    }
    

    public boolean setConceded()
    {
        return true;
    }
}
