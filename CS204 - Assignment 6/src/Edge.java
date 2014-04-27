/**
 * This class represents an edge in a graph and is used to show connections
 * in a program that is a social network of Friends
 * @author Adam Holt
 * @date 4/27/14
 * @class CS204
 * @time 12:00 MW
 */
public class Edge<T extends Comparable<T>, S extends Comparable<S>> implements Comparable<Edge<T,S>>
{
    //First vertex
    private T vertex1;
    //Second vertex
    private S vertex2;
    
    //Default constructor
    public Edge()
    {}
    
    /**
     * Constructor that takes two objects to represent the vertices of the edge
     * @param v1 Vertex 1
     * @param v2 Vertex 2
     */
    public Edge(T v1, S v2)
    {
	//Set the vertices
	setV1(v1);
	setV2(v2);
    }
    
    /**
     * Set vertex 1
     * @param v1 Vertex 1
     */
    public void setV1(T v1)
    {
	vertex1 = v1;
    }
    
    /**
     * Get vertex 1
     * @return Vertex 1
     */
    public T getV1()
    {
	return vertex1;
    }
    
    /**
     * Set vertex 2
     * @param v2 Vertex 2
     */
    public void setV2(S v2)
    {
	vertex2 = v2;
    }
    
    /**
     * Get vertex 2
     * @return Vertex 2
     */
    public S getV2()
    {
	return vertex2;
    }
    
    /**
     * Compare this Edge to another Edge
     * @param otherEdge Edge object to be compared
     * @return String comparison of the two edges
     */
    public int compareTo(Edge<T, S> otherEdge)
    {
	return toString().compareTo(otherEdge.toString());
    }
    
    /**
     * Determine whether two Edge objects are equal
     * @param otherEdge Edge object to be compared
     * @return True if the edges are equal, otherwise false
     */
    public boolean equals(Edge<T, S> otherEdge)
    {
	//If the vertices of each edge are the same, regardless of order, return true
	if((getV1().equals(otherEdge.getV1()) && getV2().equals(otherEdge.getV2())) ||
	   (getV1().equals(otherEdge.getV2()) && getV2().equals(otherEdge.getV1())))
	    return true;
	return false;
    }
    
    /**
     * Return a String representation of the Edge
     * @return String representation of the Edge
     */
    public String toString()
    {
	//Return the String representation of each vertex with the smallest String first
	if(getV1().toString().compareTo(getV2().toString())<0)
	    return getV1() + " - " + getV2();
	else
	    return getV2() + " - " + getV1();
    }
}