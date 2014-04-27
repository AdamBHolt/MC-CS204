import java.io.*;
import java.util.*;


public class DataManager implements DataManagerInterface
{

    private FriendGraph friendGraph;

    public DataManager()
    {
	friendGraph = new FriendGraph();
    }

    @Override
    public ArrayList<String> friendsOfFriends(String name)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ArrayList<String> friendsOfFriends(String fname, String lname,
	    String hometown)
	    {
	// TODO Auto-generated method stub
	return null;
	    }


    public ArrayList<String> listFriends(String name)
    {
	return listFriends(new Friend(name));
    }

    public ArrayList<String> listFriends(String fname, String lname,String hometown)
    {

	return listFriends(new Friend(fname, lname, hometown));
    }

    public ArrayList<String> listFriends(Friend f)
    {
	ArrayList<String> returnList = new ArrayList<>();
	Set<Edge<Friend, Friend>> friendSet = friendGraph.edgesOf(f);

	for(Edge<Friend, Friend> e : friendSet)
	{
	    if(e.getV1().equals(f))
		returnList.add(e.getV2().toString());
	    else
		returnList.add(e.getV1().toString());
	}

	return returnList;
    }

    public ArrayList<String> getProfile(String name)
    {
	Friend tempFriend = new Friend(name);
	ArrayList<String> returnList = new ArrayList<>();



	return returnList;
    }

    public Vector<String> vectorOfParticipants()
    {
	Vector<String> returnVector = new Vector<>();
	Set<Friend> friendSet = friendGraph.vertexSet();

	for(Friend f : friendSet)
	    returnVector.add(f.toString());

	return returnVector;
    }

    public void addParticipant(String fName, String lName, String city)
    {
	friendGraph.addVertex(new Friend(fName, lName, city));
    }

    public void addFriend(String profile, String newFriend)
    {
	friendGraph.addEdge(new Friend(profile),  new Friend(newFriend));
    }

    public void addFriend(String profileFname, String profileLname,
	    String profileHometown, String newFriendFname,
	    String newFriendLName, String newFriendHometown)
    {
	friendGraph.addEdge(new Friend(profileFname, profileLname, profileHometown),  new Friend(newFriendFname, newFriendLName, newFriendHometown));
    }

    public void populateParticipants(File participantsFile)throws FileNotFoundException
    {
	// TODO Auto-generated method stub

    }

    public void populateFriends(File friendsFile) throws FileNotFoundException
    {
	// TODO Auto-generated method stub

    }

}
