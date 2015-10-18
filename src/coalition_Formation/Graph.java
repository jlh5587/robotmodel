package coalition_Formation;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Graph is an object-oriented representation of a mathematical graph.
 *
 * @author David Woolbright
 * @version 2011
 * 
 * Taken from an undergrad assignment we worked on in programming languages. We were given 
 * the skeleton of this graph and had to add dijkstra's pathing funcionality to it.
 */

public class Graph
{
	private int[][] edgeDistance;  //the length of the edge from vertex i to vertex j, -1 if no edge
	private int[][] shortestDistance;
	private int noVertices;

	//stuff i added to Dr. Woolbright's code
	private int [][]prev;
	private Node [] myNodes;

	/**
	 * Constructor for objects of class Graph
	 */
	public Graph(int [][] discoveredMap)	
	{
		noVertices = discoveredMap.length;

		shortestDistance = new int[noVertices][noVertices]; 
		prev = new int [noVertices][noVertices];

		edgeDistance= discoveredMap;     

		myNodes = new Node [noVertices];
		for (int i =0 ;i<noVertices;i++){
			myNodes[i] = new Node(i);  			
		}

		dyjkstra();
	}

	/**
	 * Resets visited variable to false in all nodes
	 * and distance to -1 and prev to -1
	 */
	public void clear(){

		for(int i =0;i<noVertices;i++) {
			myNodes[i].visited = false;
			myNodes[i].distance=-1;
			myNodes[i].prev=-1;
		}
	}

	public int getNoVertices()

	{
		return noVertices;
	}

	// edgeDistance - returns the length of the edge from vertex i to vertex j

	//                returns -1 if there is no edge from vertex i to vertex j

	public int edgeDistance(int i, int j)
	{
		return edgeDistance[i][j];
	}

	public ArrayList<Integer> adjacentVertices(int u)
	{
		ArrayList<Integer> al = new ArrayList<Integer>();

		for (int i = 0; i < noVertices; i++)
		{
			if (edgeDistance(u,i) > 0)
			{
				al.add(i);
			}
		}

		return al;
	}

	public int shortestDistance(int i, int j)
	{
		return shortestDistance[i][j];
	}

	/**
	 * Populates shortestDistance array by calling
	 * dyjstra's algorithm on each vertex of the graph
	 */
	public void dyjkstra(){

		for (int i=0;i<noVertices;i++){
			shortestPath(i);

			for(int j=0;j<noVertices;j++){
				shortestDistance[i][j]=myNodes[j].distance;
				prev[i][j]=myNodes[j].prev;
			}
			clear();
		}
	}


	/**
	 * Given a starting vertex calculates
	 * the shortest path to all other vertices 
	 * in a given graph
	 * @param int x (starting vertex)
	 */
	public void shortestPath(int x){
		PriorityQueue <Node> pq = new PriorityQueue<Node>();

		myNodes[x].distance=0;
		myNodes[x].prev=x;

		pq.add(myNodes[x]);

		while(!pq.isEmpty()){

			Node temp = pq.poll();
			temp.visited=true;

			ArrayList<Integer> t = adjacentVertices(temp.id);

			for(int i = 0;i<t.size();i++){
				Node tempFor = myNodes[t.get(i)];
				if(!tempFor.visited){
					if(tempFor.distance==-1){
						tempFor.distance = temp.distance + edgeDistance[temp.id][tempFor.id];
						tempFor.prev=temp.id;
						pq.add(tempFor);
					}
					else if(  (temp.distance+edgeDistance[temp.id][tempFor.id]) < tempFor.distance){
						tempFor.distance = temp.distance + edgeDistance[temp.id][tempFor.id];
						tempFor.prev=temp.id;
						pq.add(tempFor);
					}
				}
			}
		}


	}

	public String toString()
	{
		String temp = "Vertices:";
		
		for(int i = 0; i < noVertices - 1; i++)
		{
			temp = temp + i + ",";
		}

		temp = temp + (noVertices - 1);
		temp = temp + "\nEdges:";

		for(int i = 0; i < noVertices; i++)
		{
			for (int j = i+1; j < noVertices; j++)
			{
				if (edgeDistance(i,j) > 0)
				{
					temp = temp + "\n(" + i + "," + j + ") weight: " + edgeDistance(i,j);
				}
			}
		}
		return temp;
	}

	public int previousArray(int i, int j){
		return prev[i][j];
	}

	/**
	 * Private support class Nodes
	 */
	private class Node implements Comparable{
		boolean visited = false;
		int distance=-1;
		int id;
		int prev=-1;

		public Node(int id){
			this.id = id;
		}

		@Override
		public int compareTo(Object arg0) {
			Node temp = (Node)arg0;
			return distance - temp.distance;
		}
	}



}