package coalition_Formation;

public class Obstacle {
	
	private Location myLocation;
	private int size;
	
	public Obstacle()
	{
		myLocation = new Location();
		size = 1;
	}
	
	public Obstacle(int x, int y, int size)
	{
		myLocation = new Location(x,y);
		this.size = size;
	}
	
	public Location getLocation()
	{
		return myLocation;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void setLocation(int x, int y)
	{
		myLocation.setLocation(x, y);
	}
	
	public void setSize(int size)
	{
		this.size = size;
	}
	
	
}
