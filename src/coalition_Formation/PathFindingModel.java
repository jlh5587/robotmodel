package coalition_Formation;

import java.util.ArrayList;

import repast.simphony.context.Context;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.grid.BouncyBorders;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.RandomGridAdder;

public class PathFindingModel implements ContextBuilder<Object>{

	@Override
	public Context build(Context<Object> context) {
		
		Parameters p = RunEnvironment.getInstance().getParameters();
		int numAgents = (Integer)p.getValue("initialNumAgents");
		int height = (Integer)p.getValue("worldHeight");
		int width = (Integer)p.getValue("worldWidth");
		int [][] theMap = 
		{
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1},
				{1,1,1,1,1}
				
		};
		height = theMap.length;
		width = theMap[0].length;
		
		GridFactoryFinder.createGridFactory(null).createGrid("Grid", context, 
				new GridBuilderParameters<Object>(new BouncyBorders(), 
						new RandomGridAdder<Object>(), false, width, height));
		
		int totalAgents = 1;
		for(int i=0; i < width;i++)
		{
			if(theMap[height-1][i] == 1) totalAgents++;
		}
		
		
		ArrayList<Agent>robotPool = new ArrayList<Agent>();		
		for(int i=0;i<totalAgents;i++)
		{
			Agent agent = new Agent("Robot-"+i);
			
			agent.addCapability(Capabilities.COMMUNICATE, (int)Math.random() * 10);
			agent.addCapability(Capabilities.MOVE, (int)Math.random() * 10);
			agent.addCapability(Capabilities.SENSE_OBSTACLE, (int)Math.random()*10);
		
			robotPool.add(agent);
		}
		
		ArrayList<Capabilities>requiredCapabilities = new ArrayList<Capabilities>();
		requiredCapabilities.add(Capabilities.COMMUNICATE);
		requiredCapabilities.add(Capabilities.MOVE);
		requiredCapabilities.add(Capabilities.SENSE_OBSTACLE);
		
		Tasks searchMap = new Tasks(requiredCapabilities);
		
		ArrayList<Agent>coalitionRobots = Algorithms.coalition_formation(searchMap, robotPool);
		
		Coalition coalition = new Coalition();
		
		for(Agent x: coalitionRobots)
		{
			coalition.addAgent(x);
			x.addCoalition(coalition);
		}
		
		coalition.addTask(searchMap);
		
		Algorithms.SelectLeader(coalition);
		
		for(Agent x: coalition.getMyAgents())
		{
			if(!x.isLeader())
				context.add(x);
		}
		
		return context;
	}

}
