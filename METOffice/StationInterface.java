import java.util.List;

/**
 * A StationInterface object represents data from one weather station as at the <a href='http://www.metoffice.gov.uk/public/weather/climate-historic/#?tab=climateHistoric'>MetOffice Historic Station Data</a>.
 * Information held about a weather station includes: name of station; position (latitude and longitude); height above sea level; list of monthly observations.
 *
 * You should include a constructor which takes as a parameter the name of the weather station (as a String).
 **/

public interface StationInterface{

    void setName(String name);
    String getName();

    void setPosition(PositionInterface position);
    PositionInterface getPosition();

    void setHeight(int heightASL);
    int getHeight();

    List<ObservationInterface> getObservations();
    void addObservation(ObservationInterface observation);

    /**
     * @param month The month to search for
     * @param year The year to search for
     * @return The observation associated with the specified month and year. Returns null if no associated observation
     **/
    ObservationInterface getObservation(int month, int year);

    /**
     * @return The most recent year in which observations are recorded.  If there are no observations then return 0.
     **/
    int getLatestYear();

    /**
     * @return The earliest year in which observations are recorded. If there are no observations then return 0.
     **/
    int getEarliestYear();

    /**  
     * @return The total rainfall observed in the specified year 
     **/
    double totalRainfall(int year);

    /**
     * @return The wettest year: the year with the highest annual total rainfall. If there are no observations then return 0.
     **/
    int wettestYear();

    /**
     * @return The average number of hours sunshine for a particular month, averaged over all years that have an observation for that month. If there are no observations for that month then return 0.0.
     **/
    double averageSunshine(int month);

}
