import java.util.List;

/**
 * A MetOfficeInterface object represents data from the MetOffice <a href='http://www.metoffice.gov.uk/public/weather/climate-historic/#?tab=climateHistoric'>MetOffice Historic Station Data</a>.
 * The MetOffice holds a list of weather stations.
 *
 * You should include a constructor which takes no parameters.
 **/

public interface MetOfficeInterface{

    List<StationInterface> getWeatherStations();
    void addStation(StationInterface station);

    /**
     * @return The most recent year in which observations are recorded.  If no stations or no observations then return 0.
     **/
    int getLatestYear();

    /**
     * @return The earliest year in which observations are recorded. If no stations or no observations then return 0.
     **/
    int getEarliestYear();


    /**
     * @return The wettest place: the weather station with the highest annual total rainfall in the specified year. If there are no weather stations with observations in the given year then return null.
     **/
    StationInterface wettestPlace(int year);
    

    /**
     * @return The wettest place: the weather station with the highest annual total rainfall in any year
     **/
    StationInterface wettestPlace();

}
