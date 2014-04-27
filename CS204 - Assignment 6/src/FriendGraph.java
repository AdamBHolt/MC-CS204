import java.util.*;

/**
 * This class is the data structure for a program that is a social network of Friends
 * @author Adam Holt
 * @date 4/27/14
 * @class CS204
 * @time 12:00 MW
 */
public class FriendGraph implements GraphInterface<Friend, Edge<Friend, Friend>>
{

    private TreeMap<Friend, TreeSet<Edge<Friend, Friend>>> adjacencyList;

    public FriendGraph()
    {
	adjacencyList = new TreeMap<>();
    }


    public Edge<Friend, Friend> getEdge(Friend sourceVertex, Friend targetVertex)
    {
	if(sourceVertex==null || targetVertex==null)
	    return null;

	if(containsVertex(sourceVertex))
	{
	    if(containsEdge(sourceVertex, targetVertex))
	    {
		Edge<Friend, Friend> tempEdge = new Edge<>(sourceVertex, targetVertex);

		for(Edge<Friend, Friend> e : adjacencyList.get(sourceVertex))
		    if(e.equals(tempEdge))
			return e;
	    }
	}
	return null;
    }

    public Edge<Friend, Friend> addEdge(Friend sourceVertex, Friend targetVertex)
    {
	Edge<Friend, Friend> e;

	if(sourceVertex==null || targetVertex==null)
	    throw new NullPointerException();

	if(containsVertex(sourceVertex) && containsVertex(targetVertex))
	{
	    if(!containsEdge(sourceVertex, targetVertex))
	    {
		e = new Edge<>(sourceVertex, targetVertex);
		adjacencyList.get(sourceVertex).add(e);
		adjacencyList.get(targetVertex).add(e);
		return e;
	    }
	}
	else
	    throw new IllegalArgumentException();

	return null;
    }

    /**
     * Add a Friend vertex to the graph
     * @param Friend vertex to add to the graph
     * @return True if the vertex was added, otherwise false
     */
    public boolean addVertex(Friend v)
    {
	//If the Friend is already in the graph return false
	if(containsVertex(v))
	    return false;

	//Otherwise add the Friend as a vertex and a new TreeSet
	//And return true
	adjacencyList.put(v, new TreeSet<Edge<Friend, Friend>>());
	return true;
    }

    public boolean containsEdge(Friend sourceVertex, Friend targetVertex)
    {
	if(containsVertex(sourceVertex))
	{
	    Edge<Friend, Friend> tempEdge = new Edge<>(sourceVertex, targetVertex);

	    for(Edge<Friend, Friend> e : adjacencyList.get(sourceVertex))
		if(e.equals(tempEdge))
		    return true;
	}
	return false;
    }

    public boolean containsVertex(Friend v)
    {
	return adjacencyList.containsKey(v);
    }

    public Set<Edge<Friend, Friend>> edgeSet()
    {
	Set<Friend> keys = adjacencyList.keySet();
	Set<Edge<Friend, Friend>> returnSet = new TreeSet<>();

	for(Friend f : keys)
	    for(Edge<Friend, Friend> e : adjacencyList.get(f))
		returnSet.add(e);

	return returnSet;
    }

    public Set<Edge<Friend, Friend>> edgesOf(Friend vertex)
    {
	return adjacencyList.get(vertex);
    }

    public Edge<Friend, Friend> removeEdge(Friend sourceVertex, Friend targetVertex)
    {
	if(containsEdge(sourceVertex, targetVertex))
	{
	    Edge<Friend, Friend> tempEdge = new Edge<>(sourceVertex, targetVertex);
	    
	    for(Edge<Friend, Friend> e : adjacencyList.get(sourceVertex))
		if(e.equals(tempEdge))
		    adjacencyList.get(sourceVertex).remove(e);
	    
	    for(Edge<Friend, Friend> e : adjacencyList.get(targetVertex))
		if(e.equals(tempEdge))
		    adjacencyList.get(targetVertex).remove(e);
	    
	    return tempEdge;
	}
	return null;
    }

    public boolean removeVertex(Friend v)
    {
	if(containsVertex(v))
	{
	    Set<Friend> keys = adjacencyList.keySet();

		for(Friend f : keys)
		{
		    Edge<Friend, Friend> tempEdge = new Edge<>(v, f);
		    for(Edge<Friend, Friend> e : adjacencyList.get(f))
			if(e.equals(tempEdge))
			    adjacencyList.get(f).remove(e);
		}
	    
	    adjacencyList.remove(v);
	    return true;
	}
	return false;
    }

    public Set<Friend> vertexSet()
    {
	return adjacencyList.keySet();
    }
    
    public String getHomeTown(String fName, String lName)
    {
	for(Friend f : vertexSet())
	    if(f.equals(fName + " " + lName))
		return f.getHomeTown();
	return null;
    }
}