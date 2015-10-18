package coalition_Formation;
import java.util.ArrayList;


public class Leader{

	int[][] discoveryMap;
	private ArrayList<ArrayList<Agent>> agentLocations;

	
	public Leader(int[][] map)
	{	
		discoveryMap = map;
	}
	
	public int[] whereNext(Agent agent)
	{
		int[] lastMove = agent.getLastMove();
		
		int x = lastMove[0];
		int y = lastMove[1];
		int dir = lastMove[2];
		
		
		
		//lastMove[0] = x
		//lastMove[1] = y
		//lastMove[2] = dir
		
		//0 = Up (y+1), 1 = Down (y-1), 2 = Right (x+1), 3 = Left (x-1)
		
		if(dir == 2)
		{
			if(x+1 < discoveryMap.length)
			{
				x = x+1;
			}
			else
			{
				dir = 0;
			}
		}
		else if(dir == 3)
		{
			if(x-1 >= 0)
			{
				x = x-1;
			}
			else
			{
				dir = 0;
			}
		}
		else if(dir == 0)
		{
			dir = 2;
		}
		else 
		{
			dir = 2;
		}
		

		int[] move = {x,y,dir};
		return move;
	}
	
	public void discovered(int x, int y, int discovery)
	{
		discoveryMap[x][y] = discovery;
	}
	
	public int shortestPath(int start, int end)
	{
		int[][] adjacencyMap = new int[discoveryMap.length][discoveryMap[0].length];
		
		for(int i = 0; i <discoveryMap.length; i++)
		{
			for(int j = 0; j < discoveryMap[0].length; i++)
			{
				if(i == j)
				{
					adjacencyMap[i][j] = 0;
				}
				else if(discoveryMap[i][j] == 0)
				{
					adjacencyMap[i][j] = -1;
				}
				else
				{
					adjacencyMap[i][j] = discoveryMap[i][j];
				}
			}
		}
		
		
		
		
		Graph g = new Graph(adjacencyMap);
		return g.shortestDistance(start,end);
	}
	
	public boolean allDiscovered()
	{
		for(int i = 0; i < discoveryMap.length; i++)
		{
			for(int j= 0; j<discoveryMap[0].length; i++)
			{
				if(discoveryMap[i][j] == -1)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public int[][] getDiscoveredMap()
	{
		return discoveryMap;
	}
	
	private boolean isRowDiscovered(int row)
	{
		for(int i=0; i<discoveryMap[0].length;i++)
		{
			if(discoveryMap[row][i] == -1)
			{
				return false;
			}
		}
		
		return true;
	}
}
