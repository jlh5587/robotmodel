package robotmodel;

import java.util.List;

import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.SimUtilities;

public class Robot {

	private Grid<Object> map;
	private ContinuousSpace <Object > space;

	//Adds a map to the robot.
    public Robot(Grid<Object> map, ContinuousSpace <Object > space) {
            this.map = map;
            this.space = space;
    }
    
    //change or reset a map... 
    public void addMap(Grid<Object> map){
    	this.map = map;
    }
    
    //Allows the robot to step, adapted from repast zombie example
    public void step(){
    	//get the gridpoint of the robot
    	GridPoint pt = map.getLocation(this);
    	
    	
    	GridCellNgh<Robot> nghCreator = new GridCellNgh<Robot>(map,pt,Robot.class,1,1);
    	List<GridCell<Robot>> gridCells = nghCreator.getNeighborhood(true);
    	SimUtilities.shuffle(gridCells, RandomHelper.getUniform());
    	
    	GridPoint pointWithRobots = null; 
    	int maxCount = -1;
    	
    	for(GridCell <Robot > cell : gridCells) { 
    		if (cell.size() > maxCount) {
	    		pointWithRobots = cell.getPoint();
	    		maxCount = cell.size(); 
    		}
    	}
    	moveTowards(pointWithRobots);
    }
    
    //Moves forward, adapted from repast zombie example
    public void moveTowards(GridPoint pt){
    	if(!pt.equals(map.getLocation(this))){
    		NdPoint thisPoint = space.getLocation(this);
    		NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
    		double angle = SpatialMath.calcAngleFor2DMovement(space, thisPoint , otherPoint);
    		space.moveByVector(this, 1, angle, 0);
    		thisPoint = space.getLocation(this);
    		map.moveTo(this, (int)thisPoint.getX(), (int)thisPoint.getY());
    	}
    }
    
}
