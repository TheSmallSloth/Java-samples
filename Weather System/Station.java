import java.util.*;
public class Station implements StationInterface
{
    private String name;
    private PositionInterface p;
    private int heightASL;
    private ArrayList<ObservationInterface> observations = new ArrayList<ObservationInterface>();
    public Station(String name)
    {
        this.name=name;
    }


    public void setName(String name)
    {
        this.name=name;
    }


    public String getName()
    {
        return this.name;
    }


    public void setPosition(PositionInterface position)
    {
        this.p=position;
    }


    public PositionInterface getPosition()
    {
        return this.p;
    }


    public void setHeight(int heightASL)
    {
        this.heightASL=heightASL;
    }


    public int getHeight()
    {
        return heightASL;
    }


    public List<ObservationInterface> getObservations()
    {
        return this.observations;
    }


    public void addObservation(ObservationInterface observation)
    {
        this.observations.add(observation);
    }


    public ObservationInterface getObservation(int month, int year)
    {
        for(ObservationInterface observation:observations)
        {
            if(observation.getMonth() == month)
            {
                if(observation.getYear() == year)
                {
                    return observation;
                }
            }
        }
        return null;
    }


    public int getLatestYear()
    {
        if(observations.size()<1)
        {
            return 0;
        }
        int latest = observations.get(0).getYear();
        for(ObservationInterface observation:observations)
        {
            if(observation.getYear()>latest)
            {
                latest = observation.getYear();
            }
        }
        return latest;
    }


    public int getEarliestYear()
    {
        if(observations.size()<1)
        {
            return 0;
        }
        int earliest = observations.get(0).getYear();
        for(ObservationInterface observation:observations)
        {
            if(observation.getYear()<earliest)
            {
                earliest = observation.getYear();
            }
        }
        return earliest;
    }


    public double totalRainfall(int year)
    {
        double total=0.0;
        for(ObservationInterface observation:observations)
        {
            if(observation.getYear()==year)
            {
                total+=observation.getRainfall();
            }
        }
        return total;
    }
    public int wettestYear()
    {
        int wettestYear=0;
        for(ObservationInterface observation:observations)
        {
            if(totalRainfall(observation.getYear())>totalRainfall(wettestYear))
            {
                wettestYear=observation.getYear();
            }
        }
        return wettestYear;
    }
    public double averageSunshine(int month)
    {
        double hours=0.0f;
        int count=0;
        for(ObservationInterface observation:observations)
        {
            if(observation.getMonth()==month)
            {
                hours+=observation.getSunshine();
                count+=1;
            }
        }
        if(hours == 0.0)
        {
            return 0.0;
        }
        return hours/count;
    }
}