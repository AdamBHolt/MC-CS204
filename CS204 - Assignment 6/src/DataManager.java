import java.io.*;
import java.util.*;


public class DataManager implements DataManagerInterface
{

    private FriendGraph friendGraph;

    public DataManager()
    {
	friendGraph = new FriendGraph();
    }

    public ArrayList<String> friendsOfFriends(String name)
    {
	ArrayList<String> friends = listFriends(name);
	ArrayList<String> returnList = new ArrayList<>();
	Set<String> friendSet = new TreeSet<>();

	for(String currentFriend : friends)
	    for(String friend : listFriends(currentFriend))
		if(!friend.toString().equals(name) && !friends.contains(friend))
		    friendSet.add(friend);

	
	for(String s : friendSet)
	    returnList.add(s);

	return returnList;
    }

    public ArrayList<String> friendsOfFriends(String fname, String lname, String hometown)
    {
	return friendsOfFriends(fname + " " + lname + " of " + hometown);
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
	ArrayList<String> returnList = new ArrayList<>();
	Scanner scan = new Scanner(name);

	returnList.add(scan.next());
	returnList.add(scan.next());
	scan.next();
	returnList.add(scan.nextLine().trim());

	scan.close();

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
	Friend f = new Friend(fName, lName, city);
	if(!friendGraph.containsVertex(f))
	    friendGraph.addVertex(f);
    }

    public void addFriend(String profile, String newFriend)
    {
	Friend f1 = new Friend(profile);
	Friend f2 = new Friend(newFriend);

	if(!f1.equals(f2) && !friendGraph.containsEdge(f1, f2))
	    friendGraph.addEdge(f1, f2);
    }

    public void addFriend(String profileFname, String profileLname,
	    String profileHometown, String newFriendFname,
	    String newFriendLName, String newFriendHometown)
    {
	friendGraph.addEdge(new Friend(profileFname, profileLname, profileHometown),  new Friend(newFriendFname, newFriendLName, newFriendHometown));
    }

    public void populateParticipants(File participantsFile)throws FileNotFoundException
    {
	//Scanner to read from the selected file
	Scanner inputFile = null;

	StringTokenizer tokens = null;

	//Try to create a new Scanner
	try
	{
	    inputFile = new Scanner(participantsFile);
	}
	catch (FileNotFoundException e)
	{
	    e.printStackTrace();
	}

	//Read each line in the text file
	while(inputFile.hasNext())
	{
	    tokens = new StringTokenizer(inputFile.nextLine(), ":");

	    while(tokens.hasMoreTokens())
	    {
		addParticipant(tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
	    }
	}

	//Close the file
	inputFile.close();
    }

    public void populateFriends(File friendsFile) throws FileNotFoundException
    {
	//Scanner to read from the selected file
	Scanner inputFile = null;
	StringTokenizer tokens = null;

	String fName, lName, homeTown, f, l, h;
	int friends;

	//Try to create a new Scanner
	try
	{
	    inputFile = new Scanner(friendsFile);
	}
	catch (FileNotFoundException e)
	{
	    e.printStackTrace();
	}

	//Read each line in the text file
	while(inputFile.hasNextLine())
	{
	    tokens = new StringTokenizer(inputFile.nextLine(), ":");

	    fName = tokens.nextToken();
	    lName = tokens.nextToken();
	    homeTown = friendGraph.getHomeTown(fName, lName);
	    friends = Integer.parseInt(tokens.nextToken());

	    for(int i=0; i<friends; i++)
	    {
		f = tokens.nextToken();
		l = tokens.nextToken();
		h = friendGraph.getHomeTown(f, l);
		addFriend(fName, lName, homeTown, f, l, h);
	    }
	}

	//Close the file
	inputFile.close();

    }
}