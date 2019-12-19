public class Position implements PositionInterface
{
    private double latitude;
    private double longitude;
    public Position(double latitude, double longitude)
    {
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public double getLatitude()
    {
        return latitude;
    }
    public void setLatitude(double latitude)
    {
        this.latitude=latitude;
    }
    public double getLongitude()
    {
        return longitude;
    }
    public void setLongitude(double longitude)
    {
        this.longitude=longitude;
    }
    public boolean northOf(PositionInterface p)
    {
        return this.latitude > p.getLatitude();  
    }
    public String toString()
    {
        return "Latitude: " + this.latitude + " Longitude: " + this.longitude;
    }
}