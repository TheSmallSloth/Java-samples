public class Observation implements ObservationInterface
{
    private int year;
    private int month;
    private double tMax;
    private double tMin;
    private int daysAirFrost;
    private double rainFall;
    private double sunshine;
    private boolean isProvisional;
    public Observation(int year,int month)
    {
        this.year=year;
        this.month=month;
    }

    public void setYear(int year)
    {
        this.year=year;
    }

    public int getYear()
    {
        return this.year;
    }

    public void setMonth(int month)
    {
        this.month=month;
    }

    public int getMonth()
    {
        return this.month;
    }

    public void setTMax(double tMax)
    {
        this.tMax=tMax;
    }

    public double getTMax()
    {
        return this.tMax;
    }

    public void setTMin(double tMin)
    {
        this.tMin=tMin;
    }

    public double getTMin()
    {
        return this.tMin;
    }

    public void setDaysAirFrost(int af)
    {
        this.daysAirFrost=af;
    }

    public int getDaysAirFrost()
    {
        return this.daysAirFrost;
    }

    public void setRainfall(double rain)
    {
        this.rainFall=rain;
    }

    public double getRainfall()
    {
        return this.rainFall;
    }

    public void setSunshine(double sunshine)
    {
        this.sunshine=sunshine;
    }

    public double getSunshine()
    {
        return this.sunshine;
    }

    public void setProvisional(boolean provisional)
    {
        this.isProvisional=provisional;
    }

    public boolean isProvisional()
    {
        return this.isProvisional;
    }

    public double getTMean()
    {
        return (tMax+tMin)/2;
    }

    public String toString()
    {
        if(isProvisional)
        {
            return String.format("%8d %8d %8.1f %8.1f %8d %8.1f %8.1f Provisional",year,month,tMax,tMin,daysAirFrost,rainFall,sunshine);
        }
        else
        {
            return String.format("%8d %8d %8.1f %8.1f %8d %8.1f %8.1f",year,month,tMax,tMin,daysAirFrost,rainFall,sunshine);
        }
    }

    public void update(String observationLine)
    {
        int counter=0;
        String[] strings = observationLine.split(" ");
        for(String string:strings)
        {
            if(!string.equals(""))
            {
                if(counter==0)
                {
                    year = Integer.parseInt(string);
                    counter+=1;
                }
                else if(counter==1)
                {
                    month=Integer.parseInt(string);
                    counter+=1;
                }
                else if(counter==2)
                {
                    tMax = Double.parseDouble(string);
                    counter+=1;
                }
                else if(counter==3)
                {
                    tMin = Double.parseDouble(string);
                    counter+=1;
                }
                else if(counter==4)
                {
                    daysAirFrost = Integer.parseInt(string);
                    counter+=1;
                }
                else if(counter==5)
                {
                    rainFall = Double.parseDouble(string);
                    counter+=1;
                }
                else if(counter==6)
                {
                    sunshine = Double.parseDouble(string);
                    counter+=1;
                }
                else if(counter == 7)
                {
                    isProvisional = Boolean.parseBoolean(string);
                }
            }
        }
    }
}