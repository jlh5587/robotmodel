package coalition_Formation;

import java.util.ArrayList;

public class Tasks {
	
	ArrayList<Capabilities>requiredCapabilities;	
	
	public Tasks()
	{
		requiredCapabilities = new ArrayList<Capabilities>();
	}
	
	public Tasks(ArrayList<Capabilities>requirements)
	{
		requiredCapabilities = requirements;
	}
	
	public void addRequirement(Capabilities newRequirement)
	{
		requiredCapabilities.add(newRequirement);
	}
	
	public ArrayList<Capabilities> getRequiredCapabilities()
	{
		return requiredCapabilities;
	}
	
	

}
