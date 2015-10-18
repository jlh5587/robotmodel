package coalition_Formation;

import java.util.ArrayList;

public class Coalition {

	private ArrayList<Agent> myAgents;	
	private ArrayList <Tasks> myTasks;
	private Agent leader;
	private int[][] discoveredMap;
	
	public Coalition()
	{
		myAgents = new ArrayList<Agent>();
		myTasks = new ArrayList<Tasks>();
	}
	
	public void addAgent(Agent newAgent)
	{
		myAgents.add(newAgent);
	}
	
	public ArrayList<Agent> getMyAgents()
	{
		return myAgents;
	}
	
	public void addTask(Tasks task)
	{
		myTasks.add(task);
	}
	
	public ArrayList<Tasks> getMyTasks()
	{
		return myTasks;
	}
	
	public void setLeader(Agent leader)
	{
		this.leader = leader;
	}
	
	public Agent getLeader()
	{
		return leader;
	}
	
	public int[][] getDiscoveredMap()
	{
		return discoveredMap;
	}
	
	public void setDiscoveredMap(int[][] discoveredMap)
	{
		this.discoveredMap = discoveredMap;
	}
	
	public void finished()
	{
		for(Agent a:myAgents)
		{
			if(! a.isLeader())
			{
				a.die();
			}
		}
	}
}
