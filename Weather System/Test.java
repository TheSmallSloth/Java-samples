public class Test
{
    private MetOffice metOffice;
    private Station station1 = new Station("Station 1");
    private Station station2 = new Station("Station 2");
    private Station station3 = new Station("Station 3");
    private Position position1 = new Position(100,120);
    private Position position2 = new Position(50,60);
    private Position position3 = new Position(200,300);
    private Observation observation1 = new Observation(1999,4);
    private Observation observation2 = new Observation(1999,4);
    private Observation observation3 = new Observation(1999,4);
    private Observation observation4 = new Observation(1980, 3);
    public Test()
    {
        metOffice = new MetOffice();
        station1.setPosition(position1);
        station2.setPosition(position2);
        station3.setPosition(position3);
        observation1.update("1111111111 111111111 50.0 10.0 30 5.0 100.0 true");
        observation2.update("2005 7 6.0 5.0 20 100.0 90.0 false");
        observation3.update("2008 9 4.0 3.0 2 1.0 0.0 false");
        observation4.update("1980 4 4.0 3.0 2 4.0 20.0 false");
        station1.addObservation(observation1);
        station2.addObservation(observation2);
        station3.addObservation(observation3);
        station1.addObservation(observation4);
        metOffice.addStation(station1);
        metOffice.addStation(station2);
        metOffice.addStation(station3);
        System.out.println(observation1.toString());
        System.out.println(observation2.toString());
        System.out.println(observation3.toString());
        System.out.println(observation4.toString());
    }
}