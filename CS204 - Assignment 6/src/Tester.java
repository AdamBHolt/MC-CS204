import java.util.Set;


public class Tester
{

    public Tester()
    {
	FriendGraph graph = new FriendGraph();
	DataManager manager = new DataManager();

	Friend f1, f2, f3, f4;

	f1 = new Friend("Adam Holt of Anytown");
	f2 = new Friend("Bob Smith of Everytown");
	f3 = new Friend("Chris Jone of Onlytown");
	f4 = new Friend("Don Baker of Notown");

	/**
	graph.addVertex(f1);
	graph.addVertex(f2);
	graph.addVertex(f3);
	graph.addVertex(f4);

	//System.out.println(graph.containsVertex(f1));
	//System.out.println(graph.containsVertex(f2));
	//System.out.println(graph.containsVertex(f3));
	//System.out.println(graph.containsVertex(f4));

	//for(Friend f : graph.vertexSet())
	 //   System.out.println(f);

	graph.addEdge(f1, f2);
	graph.addEdge(f2, f3);
	graph.addEdge(f3, f4);
	graph.addEdge(f4, f1);

	//for(Edge<Friend, Friend> e : graph.edgeSet())
	 //   System.out.println(e);
	
	Set<Edge<Friend, Friend>> edges = graph.edgesOf(f4);
	
	for(Edge<Friend, Friend> e : edges)
	    System.out.println(e);
	 */

	
	manager.addParticipant("Bob", "Smith", "Everytown");
	manager.addParticipant("Don", "Baker", "Notown");
	manager.addParticipant("Adam", "Holt", "Anytown");
	manager.addParticipant("Chris", "Jones", "Onlytown");
	
	manager.addFriend("Adam Holt of Anytown", "Bob Smith of Everytown");
	manager.addFriend("Adam Holt of Anytown", "Don Baker of Notown");

	//for(String s : manager.vectorOfParticipants())
	 //   System.out.println(s);

	for(String s : manager.listFriends(f1))
		System.out.println(s);
		
	System.out.println(manager.listFriends(f1).get(0));
	System.out.println(manager.listFriends(f1).get(1));
    }


    public static void main(String[] args)
    {
	new Tester();
    }
}