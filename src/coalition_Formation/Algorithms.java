package coalition_Formation;

import java.util.ArrayList;

public class Algorithms {

	/* This section of the code is for formation algorithms. If you wish to add a formation algorithm, 
	  This is the place to do it.*/
	
	/** Algorithm 1
	 * 
	 * @param param1
	 * @param param2
	 * @param param3
	 */
	public static ArrayList<Agent> coalition_formation(Tasks task, ArrayList<Agent> robotPool)
	{
		ArrayList<Agent> tempRobotPool = new ArrayList<Agent>();
		
		for(Agent x: robotPool)
		{
			
			ArrayList<Capabilities> temp = x.getMyCapabilities();
			for(Capabilities y: task.getRequiredCapabilities())
			{
				if(!temp.contains(y)) break;
			}
			tempRobotPool.add(x);
		}
		
		return tempRobotPool;
	}
	
	public static void SelectLeader(Coalition coalition)
	{
		ArrayList<Agent>members = coalition.getMyAgents();
		
		int mostCapable = 0;
		Agent currMostCapable = members.get(0);
		
		for(Agent x: members)
		{
			int value = 0;
			
			//Checks each tasks capabilities to find the agent with the largest
			//capability coefficient
			for(Tasks tempTask: coalition.getMyTasks())
			{
				//Creates a summation of all capability proficiencies
				for(Capabilities c: tempTask.getRequiredCapabilities())
				{
					//Gets the index of the capability
					int index = x.getMyCapabilities().indexOf(c);
					//Gets the proficiency of the capability and adds to previous sum
					value += x.getMyProficieny().get(index);
				}
				
				//Checks to see if this agents capability is greater than the current maximum
				if(value > mostCapable)
				{
					mostCapable = value;
					currMostCapable = x;
				}
				
			}	
		}
		
		//Sets the leader in the coalition
		coalition.setLeader(currMostCapable);
		
		//Gets the index of the agent in the coalition
		int i = coalition.getMyAgents().indexOf(currMostCapable);
		
		//Sets the flag in the new leader to true
		coalition.getMyAgents().get(i).setLeader(true);
		coalition.getMyAgents().get(i).setLead(new Leader(coalition.getDiscoveredMap()));
		
	}
	
	
	
	
	/* This section of the code is for pathing algorithms */
	
	public static void how_to_search(int param1, int param2)
	{
		
	}
	

}
