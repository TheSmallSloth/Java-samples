/**
 * PositionInterface objects store a geographical position as a latitude and longitude.
 * You should include a constructor which takes two double parameters, for latitude and longitude.
 *
 * @version 1.0
 * @author Steven Bradley
 *
 */

public interface PositionInterface{

    double getLatitude();
    
    void setLatitude(double latitude);
    
    double getLongitude();
    
    void setLongitude(double longitude);
    
    /** Compare the latitude with another position
     *  @param p The position to be compared with
     *  @return true if and only if this object is north of (i.e. has higher latitude than) the parameter object
     */
    boolean northOf(PositionInterface p);

    /** @return text representation which should include the numerical representation of the latitude and longitude
     */
    String toString();
}
