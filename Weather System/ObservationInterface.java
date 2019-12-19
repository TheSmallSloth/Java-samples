/** An ObservationInterface object represents data from one monthly observation within a weather station as found at the <a href='http://www.metoffice.gov.uk/public/weather/climate-historic/#?tab=climateHistoric'>MetOffice Historic Station Data</a>.
 * Observations are associated with a weather station in the WeatherStation, so that information about the WeatherStation should not be encoded in the Observation itself.
 * Data records include: year of observation; month of observation (1-12); mean daily maximum temperature; mean daily minimum temperature; days of air frost; total rainfall; total sunshine duration.
 * Observation records may be provisional.
 * You may assume that all observation data are present (i.e. ignore cases with --- in historic data files).
 *
 * You should include a constructor which takes as a parameter the year (as an int) and the month (as an int)
 **/


public interface ObservationInterface{

    void setYear(int year);
    int getYear();

    void setMonth(int month);
    int getMonth();

    void setTMax(double tMax);
    double getTMax();

    void setTMin(double tMin);
    double getTMin();

    void setDaysAirFrost(int af);
    int getDaysAirFrost();

    void setRainfall(double rain);
    double getRainfall();

    void setSunshine(double sunshine);
    double getSunshine();

    void setProvisional(boolean provisional);
    boolean isProvisional();

    /** @return monthly mean temperature, calculated from the average of the mean daily maximum and mean daily minimum temperature i.e. (tmax+tmin)/2.
     **/
    double getTMean();

    /** @return the observation formatted as in the MetOffice file i.e. data fields eight characters wide, doubles printed to one decimal place. E.g. <pre>   1925  12    4.3    -1.2      18    79.4    61.3</pre>

     * @see String#format(String, Object...)
     **/
    String toString();

    /** 
     * Update all fields based on a string provided.
     * @param observationLine A String formatted as in one line in the MetOffice file. You may assume that all data items are present and that there are no * or # markers on the data. Observations potinally have a Provisional marker at the end of the line. 
     **/
    void update(String observationLine);
}
