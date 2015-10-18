package coalition_Formation;

import java.util.ArrayList;

import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.parameter.Parameters;
import repast.simphony.query.space.grid.VNQuery;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.util.ContextUtils;

public class Agent {

	private Location myLocation;
	private ArrayList <Coalition>myCoalitions = new ArrayList<Coalition>();
	private ArrayList <Location> myPath = new ArrayList <Location>();
	private ArrayList <ArrayList<Location>> allPaths = new ArrayList <ArrayList<Location>>();
	private String id;
	private ArrayList <Capabilities> myCapabilities = new ArrayList <Capabilities>();
	private ArrayList <Integer> myProficiency = new ArrayList <Integer>();
	private boolean isLeader;
	private int formation_algorithm;
	private int search_algorithm;
	private int[] lastMove;
	private Leader lead;

	
	public Agent(String id) {

		this.id = id;
		
	}

  //Schedule the step method for agents.  The method is scheduled starting at 
	// tick one with an interval of 1 tick.  Specifically, the step starts at 0, and
	// and recurs at 1,2,3,...etc
	@ScheduledMethod(start=0,interval=1)
	public void step() {
		
		if(isLeader)
		{
			myCoalitions.get(0).setDiscoveredMap(lead.getDiscoveredMap());
			
			if(lead.allDiscovered())
			{
				myCoalitions.get(0).finished();
				//Add structure to present final path
				
				
				//int[][] shortestPath = lead.shortestPath(start, end)
			}
		}
		else
		{
		
		  // Get the context in which the agent is residing
			Context<Agent> context = (Context)ContextUtils.getContext(this);
			
		  // Get the grid from the context
			Grid<Agent> grid = (Grid)context.getProjection("Grid");
	
			// Agent will search entire map until it discovers a path from one end to the other.
	
			
			//Agent asks leader where to go
			
			
			//Agent Moves to where leader sends
			Agent leader = myCoalitions.get(0).getLeader();
			
			int[] whereNext = leader.getLead().whereNext(this);
			
			
			//int width  = grid.getDimensions().getWidth();
			//int height = grid.getDimensions().getHeight();
			int x= whereNext[0];
			int y= whereNext[1];
			
			lastMove = whereNext;
			
			// Move to new location
			grid.moveTo(this, x,y);
			
			
			
			
			//After moving Agent Reports to leader what's in the spot his looking at
			
			int discovery = Map.getLocationContent(lastMove[0], lastMove[1], lastMove[2]);
			leader.lead.discovered(x, y, discovery);
		}
	}



	//Kill the agent
	public void die(){
		// Get the context in which the agent resides.
		Context<Agent> context = (Context)ContextUtils.getContext(this);

		// Remove the agent from the context 
		context.remove(this);   

	}

	public Location getMyLocation()
	{
		Context<Agent> context = (Context)ContextUtils.getContext(this);
		
		// Get the grid from the context
		Grid<Agent> grid = (Grid)context.getProjection("Grid");
		
		myLocation.setLocation(grid.getLocation(this).getX(), grid.getLocation(this).getY());
		return myLocation;
	}
	
	public void setMyLocation(int x, int y)
	{
		myLocation.setLocation(x, y);
	}
	
	public void addCoalition(Coalition newCoalition)
	{
		myCoalitions.add(newCoalition);
	}
	
	public boolean removeCoalition(Coalition removeThis)
	{
		return myCoalitions.remove(removeThis);
	}
	
	public void clearCoalitions()
	{
		myCoalitions = new ArrayList<Coalition>();
	}
	
	public void addToPath(Location newLocation)
	{
		myPath.add(newLocation);
	}
	
	public ArrayList<Location> getSinglePath()
	{
		return myPath;
	}
	
	public void clearSinglePath()
	{
		myPath = new ArrayList<Location>();
	}
	
	public void clearAllPaths()
	{
		allPaths = new ArrayList<ArrayList<Location>>();
	}
	
	public void updateAllPaths()
	{
		for(Coalition x: myCoalitions)
		{
			ArrayList<Agent> temp = x.getMyAgents();
			for(Agent y: temp)
			{
				allPaths.add(y.myPath);
			}
			
		}
	}
	
	public ArrayList<Capabilities> getMyCapabilities()
	{
		return myCapabilities;
	}
	
	public void addCapability(Capabilities cap, int proficiency)
	{
		myCapabilities.add(cap);
		myProficiency.add(proficiency);
	}
	
	public String getId() {
		return id;
	}
	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	public String toString(){
		return this.id;
	}
	
	public ArrayList<Integer> getMyProficieny()
	{
		return myProficiency;
		
	}
	
	public int[] getLastMove()
	{
		return lastMove;
	}
	
	public void setLastMove(int[] move)
	{
		lastMove = move;
	}
	
	public void setLead(Leader lead)
	{
		this.lead = lead;
	}
	
	public Leader getLead()
	{
		return lead;
	}
	
}
