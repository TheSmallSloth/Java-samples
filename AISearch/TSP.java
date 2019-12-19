import java.io.*;
import java.util.ArrayList;

public class TSP
{
    private BufferedReader fileReader;

    private int[][] distances;

    private String fileName = "";

    public TSP(String filePath)
    { 
        fileName += filePath;
        try
        {
            this.fileReader = new BufferedReader(new FileReader(filePath));
        }
        catch(FileNotFoundException e)
        {

        }

        String file = "";
        String fileLine="";
        try
        {
            while((fileLine=fileReader.readLine())!=null)
            {
                file+=fileLine;
            }
        }
        catch(IOException e)
        {

        }

        int offset = 0;
        for(int i = 0; i<file.length()-3; ++i)
        {
            if(file.substring(i, i+4).equals("SIZE"))
            {
                offset = i;
                break;
            }
        }

        String currentNum = "";
        int numCities = 0;
        while(!(file.charAt(offset) >= 48 && file.charAt(offset)<=57))
        {
            offset+=1;
        }

        while(file.charAt(offset) >= 48 && file.charAt(offset)<=57)
        {
            currentNum = currentNum + file.charAt(offset);
            offset+=1;
        }
        numCities = Integer.parseInt(currentNum);

        this.distances = new int[numCities][numCities];

        currentNum = "";
        System.out.println(numCities + " cities");

        while(file.charAt(offset) != ',')
        {
            offset+=1;            
        }

        int i = 0;
        int j = 1;

        while(offset < file.length())
        {
            if(i==numCities-1)
            {
                break;
            }
            if(!(file.charAt(offset)>=48 && file.charAt(offset)<=57) && currentNum.length()>0)
            {
                distances[i][j] = Integer.parseInt(currentNum);
                distances[j][i] = distances[i][j];
                currentNum = "";
                j+=1;
                if(j == numCities)
                {
                    distances[i][i] = 0;
                    i+=1;
                    j=i+1;
                }
            }
            else if(file.charAt(offset)>=48 && file.charAt(offset)<=57)
            {
                currentNum += file.charAt(offset);
            }
            ++offset;
        }
        if(currentNum.length()>0)
        {
            distances[i][j] = Integer.parseInt(currentNum);
            distances[j][i] = distances[i][j];
        }

        /*ArrayList<Integer> testPath = new ArrayList<Integer>();
        testPath.add(1);
        testPath.add(2);
        testPath.add(3);
        testPath.add(4);
        testPath.add(5);
        testPath.add(6);
        testPath.add(7);
        testPath.add(1);
        Node test = new Node(testPath, this.distances);
        System.out.println(test.isGoalNode());*/
    }

    public void simulatedAnnealing()
    {
        int temperature = 1000000;

        int[] route = new int[distances.length];

        for(int i = 1; i<=distances.length; ++i)
        {
            route[i-1] = i;
        }

        for(int i = 0; i<1000; ++i)
        {
            int rand1 = (int)((Math.random()*(distances.length-1))+1);
            int rand2 = (int)((Math.random()*(distances.length-1))+1);

            int temp = route[rand1];
            route[rand1] = route[rand2];
            route[rand2] = temp;
        }

        while(temperature != 0)
        {
            int rand1 = (int)((Math.random()*(distances.length-1))+1);
            int rand2 = (int)((Math.random()*(distances.length-1))+1);

            int[] potentialSuccessor = new int[distances.length];

            for(int i = 0; i<distances.length; ++i)
            {
                potentialSuccessor[i] = route[i];
            }

            int temp = potentialSuccessor[rand1];
            potentialSuccessor[rand1] = potentialSuccessor[rand2];
            potentialSuccessor[rand2] = temp;

            int routeLength = 0;
            int successorLength = 0;

            for(int i = 0; i<route.length-1; ++i)
            {
                routeLength+=distances[route[i]-1][route[i+1]-1];
            }

            for(int i = 0; i<potentialSuccessor.length-1; ++i)
            {
                successorLength+=distances[potentialSuccessor[i]-1][potentialSuccessor[i+1]-1];
            }

            if(routeLength <= successorLength)
            {
                route = potentialSuccessor;
            }
            else
            {
                int deltaE = successorLength - routeLength;

                double probability = Math.pow(Math.E, deltaE/temperature);

                double rand = Math.random();

                if(rand > probability)
                {
                    int rand3 = (int)((Math.random()*(distances.length-1))+1);
                    int rand4 = (int)((Math.random()*(distances.length-1))+1);
                    int temp1= route[rand3];
                    route[rand3] = route[rand4];
                    route[rand4] = temp1;
                }
                else
                {
                    route = potentialSuccessor;
                }
            }
            temperature--;
        }

        for(int i = 0; i<route.length; ++i)
        {
            System.out.print(route[i] + " ");
        }
        System.out.println("");
        int routeLength = 0;
        for(int j = 0; j<route.length-1;++j)
        {
            routeLength+=distances[route[j]-1][route[j+1]-1];
        }
        System.out.println(routeLength);
    }

    public static void main(String[] args)
    {
        TSP tsp = new TSP("AISearchtestcase.txt");
        while(true)
        {
            tsp.simulatedAnnealing();
        }
    }
}
