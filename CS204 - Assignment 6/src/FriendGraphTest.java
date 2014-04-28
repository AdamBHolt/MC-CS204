import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FriendGraphTest
{

    private FriendGraph graph;
    Edge<Friend, Friend> e1, e2, e3;
    Friend f1, f2, f3, f4;
    
    @Before
    public void setUp() throws Exception
    {
	graph = new FriendGraph();
	f1 = new Friend("Ford", "Prefect", "London, EN");
	f2 = new Friend("Arthur", "Dent", "Surrey, EN");
	f3 = new Friend("Zaphod", "Beeblebrox", "Hear of Gold");
	f4 = new Friend("Trisha", "McMillian", "New York, NY");
	
	graph.addVertex(f1);
	graph.addVertex(f2);
	graph.addVertex(f3);
	graph.addVertex(f4);
	
	e1 = new Edge<>(f1, f2);
	e2 = new Edge<>(f2, f3);
	e3 = new Edge<>(f3, f4);
    }

    @After
    public void tearDown() throws Exception
    {
	graph = null;
	e1=e2=e3=null;
	f1=f2=f3=f4=null;
    }

    @Test
    public void testGetEdge()
    {
	graph.addEdge(f1, f2);
	graph.addEdge(f2, f3);
	graph.addEdge(f3, f4);
	
	assertTrue(e1.equals(graph.getEdge(f1, f2)));
	assertTrue(e2.equals(graph.getEdge(f2, f3)));
	assertTrue(e3.equals(graph.getEdge(f3, f4)));
    }

    @Test
    public void testAddEdge()
    {
	graph.addEdge(f1, f2);
	graph.addEdge(f2, f3);
	graph.addEdge(f3, f4);
	
	assertTrue(e1.equals(graph.getEdge(f1, f2)));
	assertTrue(e2.equals(graph.getEdge(f2, f3)));
	assertTrue(e3.equals(graph.getEdge(f3, f4)));
    }

    @Test
    public void testAddVertex()
    {
	Friend f5 = new Friend("Bob", "Jones", "Boulder, CO");
	Friend f6 = new Friend("Susie", "Smith", "College Park, MD");
	Friend f7 = new Friend("Doug", "Funny", "Somewhere, USA");
	
	assertFalse(graph.containsVertex(f5));
	assertFalse(graph.containsVertex(f6));
	assertFalse(graph.containsVertex(f7));
	
	graph.addVertex(f5);
	graph.addVertex(f6);
	graph.addVertex(f7);
	
	assertTrue(graph.containsVertex(f5));
	assertTrue(graph.containsVertex(f6));
	assertTrue(graph.containsVertex(f7));
    }

    @Test
    public void testContainsEdge()
    {
	assertFalse(graph.containsEdge(f1, f2));
	assertFalse(graph.containsEdge(f2, f3));
	assertFalse(graph.containsEdge(f3, f4));
	
	graph.addEdge(f1, f2);
	graph.addEdge(f2, f3);
	graph.addEdge(f3, f4);
	
	assertTrue(graph.containsEdge(f1, f2));
	assertTrue(graph.containsEdge(f2, f3));
	assertTrue(graph.containsEdge(f3, f4));
    }

    @Test
    public void testContainsVertex()
    {
	Friend f5 = new Friend("Bob", "Jones", "Boulder, CO");
	Friend f6 = new Friend("Susie", "Smith", "College Park, MD");
	Friend f7 = new Friend("Doug", "Funny", "Somewhere, USA");
	
	assertFalse(graph.containsVertex(f5));
	assertFalse(graph.containsVertex(f6));
	assertFalse(graph.containsVertex(f7));
	
	graph.addVertex(f5);
	graph.addVertex(f6);
	graph.addVertex(f7);
	
	assertTrue(graph.containsVertex(f5));
	assertTrue(graph.containsVertex(f6));
	assertTrue(graph.containsVertex(f7));
    }

    @Test
    public void testEdgeSet()
    {
	Set<Edge<Friend, Friend>> edgeSet = graph.edgeSet();
	
	
	
	assertFalse(edgeSet.contains(e1));
	assertFalse(edgeSet.contains(e2));
	assertFalse(edgeSet.contains(e3));
	
	graph.addEdge(f1, f2);
	graph.addEdge(f2, f3);
	graph.addEdge(f3, f4);
	
	edgeSet = graph.edgeSet();
	
	assertTrue(edgeSet.contains(e1));
	assertTrue(edgeSet.contains(e2));
	assertTrue(edgeSet.contains(e3));
    }

    @Test
    public void testEdgesOf()
    {
	Set<Edge<Friend, Friend>> edges = graph.edgesOf(f1);
	
	assertFalse(edges.contains(e1));
	assertFalse(edges.contains(e2));
	assertFalse(edges.contains(e3));
	
	graph.addEdge(f1, f2);
	graph.addEdge(f2, f3);
	graph.addEdge(f3, f4);
	
	edges = graph.edgesOf(f1);
	
	assertTrue(edges.contains(e1));
	
    }

    @Test
    public void testRemoveEdge()
    {
	Edge<Friend, Friend> e4, e5, e6;
	
	graph.addEdge(f1, f2);
	graph.addEdge(f2, f3);
	graph.addEdge(f3, f4);
	
	assertTrue(graph.containsEdge(f1, f2));
	assertTrue(graph.containsEdge(f2, f3));
	assertTrue(graph.containsEdge(f3, f4));
	
	e4 = graph.removeEdge(f1, f2);
	e5 = graph.removeEdge(f2, f3);
	e6 = graph.removeEdge(f3, f4);
	
	assertTrue(e1.equals(e4));
	assertTrue(e2.equals(e5));
	assertTrue(e3.equals(e6));
	
	assertFalse(graph.containsEdge(f1, f2));
	assertFalse(graph.containsEdge(f2, f3));
	assertFalse(graph.containsEdge(f3, f4));
    }

    @Test
    public void testRemoveVertex()
    {
	assertTrue(graph.removeVertex(f1));
	assertTrue(graph.removeVertex(f2));
	assertTrue(graph.removeVertex(f3));
	assertTrue(graph.removeVertex(f4));
	assertFalse(graph.removeVertex(f1));
	assertFalse(graph.removeVertex(f2));
	assertFalse(graph.removeVertex(f3));
    }

    @Test
    public void testVertexSet()
    {
	Set<Friend> vertices = graph.vertexSet();
	
	assertTrue(vertices.contains(f1));
	assertTrue(vertices.contains(f2));
	assertTrue(vertices.contains(f3));
	assertTrue(vertices.contains(f4));
	
	graph.removeVertex(f1);
	graph.removeVertex(f2);
	graph.removeVertex(f3);
	graph.removeVertex(f4);
	
	vertices = graph.vertexSet();
	
	assertFalse(vertices.contains(f1));
	assertFalse(vertices.contains(f2));
	assertFalse(vertices.contains(f3));
	assertFalse(vertices.contains(f4));
    }

    @Test
    public void testGetHomeTown()
    {
	assertEquals(graph.getHomeTown("Ford", "Prefect"), "London, EN");
	assertEquals(graph.getHomeTown("Arthur", "Dent"), "Surrey, EN");
	assertEquals(graph.getHomeTown("Zaphod", "Beeblebrox"), "Hear of Gold");
	assertEquals(graph.getHomeTown("Trisha", "McMillian"), "New York, NY");

    }

}
