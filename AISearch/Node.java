import java.util.*;
public class Node
{
    private ArrayList<Integer> path;
    
    private int costToReach;
    
    private int heuristic;
    
    private boolean isGoalState;
    
    private boolean containsAllCities;
    
    public Node(ArrayList<Integer> path, int[][] costs)
    {
        this.path = path;
        this.costToReach = 0;
        for(int i = 0; i<path.size()-1;++i)
        {
            this.costToReach += costs[path.get(i)-1][path.get(i+1)-1];
        }
        
        this.heuristic = 0;
        boolean[] visited = new boolean[costs.length];
        int unvisited = 0;
        
        for(int i = 0; i<visited.length; ++i)
        {
            for(int city:path)
            {
                if(city == i+1)
                {
                    visited[i]=true;
                }
                else
                {
                    visited[i]=false;
                    unvisited = i;
                }
            }
        }
        
        int closestUnvisited = costs[path.get(path.size()-1)-1][unvisited];
        int closestUnvisitedIndex = unvisited;
        for(int i = 0; i<costs.length; ++i)
        {
            if(!visited[i])
            {
                if(costs[path.get(path.size()-1)-1][i] < closestUnvisited)
                {
                    closestUnvisited = costs[path.get(path.size()-1)-1][i];
                    closestUnvisitedIndex = i;
                }
            }
        }
        
        int restTotal = 0;
        for(int i = 0; i<costs.length; ++i)
        {
            if(!visited[i] && i!=closestUnvisitedIndex)
            {
                restTotal+=costs[0][i];
            }
        }
        
        this.heuristic+=(restTotal+closestUnvisited);
        
        if(this.path.size() == costs.length+1 && this.path.get(0) == this.path.get(this.path.size()-1))
        {
            this.isGoalState = true;
        }
        else
        {
            this.isGoalState = false;
        }
        
        if(this.path.size() >= costs.length)
        {
            this.containsAllCities = true;
        }
    }
    
    public int getCostToReach()
    {
        return this.costToReach;
    }
    
    public int getHeuristic()
    {
        return this.heuristic;
    }
    
    public boolean isGoalNode()
    {
        return this.isGoalState;
    }
    
    public boolean containsCity(int city)
    {
        for(int i = 0; i<this.path.size(); ++i)
        {
            if(this.path.get(i) == city)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean getContainsAllCities()
    {
        return this.containsAllCities;
    }
    
    public ArrayList<Integer> getPath()
    {
        return this.path;
    }
}
