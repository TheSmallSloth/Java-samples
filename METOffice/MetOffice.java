import java.util.*;
public class MetOffice implements MetOfficeInterface
{
    private List<StationInterface> stationList;
    public MetOffice()
    {
        stationList = new ArrayList<StationInterface>();
    }
    public List<StationInterface> getWeatherStations()
    {
        return stationList;
    }
    public void addStation(StationInterface station)
    {
        stationList.add(station);
    }
    public int getLatestYear()
    {
        int latestYear=stationList.get(0).getLatestYear();
        for(StationInterface station:stationList)
        {
            if(station.getLatestYear()>latestYear)
            {
                latestYear=station.getLatestYear();
            }
        }
        return latestYear;
    }
    public int getEarliestYear()
    {
        int earliestYear=stationList.get(0).getEarliestYear();
        for(StationInterface station:stationList)
        {
            if(station.getEarliestYear()<earliestYear)
            {
                earliestYear=station.getEarliestYear();
            }
        }
        return earliestYear;
    }
    public StationInterface wettestPlace(int year)
    {
        StationInterface wettestPlace=null;
        boolean anyObservations = false;
        for(StationInterface station:stationList)
        {
            for(ObservationInterface observation:station.getObservations())
            {
                if(observation.getYear() == year)
                {
                    wettestPlace = station;
                    anyObservations = true;
                }
            }
        }
        if(!anyObservations)
        {
            return null;
        }
        for(StationInterface station:stationList)
        {
            if(station.totalRainfall(year)>wettestPlace.totalRainfall(year))
            {
                wettestPlace = station;
            }
        }
        return wettestPlace;
    }
    public StationInterface wettestPlace()
    {
        if(stationList.size()<1)
        {
            return null;
        }
        StationInterface wettestPlace=stationList.get(0);
        for(StationInterface station:stationList)
        {
            if(station.totalRainfall(station.wettestYear())>wettestPlace.totalRainfall(wettestPlace.wettestYear()))
            {
                wettestPlace = station;
            }
        }
        return wettestPlace;
    } 
}