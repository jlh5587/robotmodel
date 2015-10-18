package coalition_Formation;

public class Map {
	private static int map[][];

	public static int getLocationContent(int x, int y, int dir){
		
		//0 = Up (y+1), 1 = Down (y-1), 2 = Right (x+1), 3 = Left (x-1)
		if(dir == 0)
		{
			if(y+1 < map[0].length)
			{
				return map[x][y+1]; 
			}
		}
		else if(dir == 1)
		{
			if(y-1 >= 0)
			{
				return map[x][y-1];
			}
		}
		else if(dir == 2)
		{
			if(x+1 < map.length)
			{
				return map[x+1][y];
			}
			
		}
		else if(dir == 3)
		{
			if(x-1 >= 0)
			{
				return map[x-1][y];
			}
		}
		
		return -1;
	}

}
