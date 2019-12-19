public class Move implements MoveInterface
{
    private int x;
    private int y;
    private boolean conceded;
    public Move()
    {
        this.conceded = false;
    }
    public boolean setPosition(int x, int y) throws InvalidPositionException
    {
        this.x = x;
        this.y = y;
        if(x<0 || y<0)
        {
            throw new InvalidPositionException();
        }        
        return true;
    }
    

    public boolean hasConceded()
    {
        return conceded;
    }
    

    public int getXPosition()
    {
        return x;
    }
    

    public int getYPosition()
    {
        return y;
    }
    

    public boolean setConceded()
    {
        this.conceded = true;
        return true;
    }
}
