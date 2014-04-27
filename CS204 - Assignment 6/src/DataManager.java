import java.io.*;
import java.util.*;


public class DataManager implements DataManagerInterface
{

    private FriendGraph friendList;
    
    public DataManager()
    {
	friendList = new FriendGraph();
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

    @Override
    public ArrayList<String> listFriends(String name)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ArrayList<String> listFriends(String fname, String lname,
	    String hometown)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ArrayList<String> listFriends(Friend f)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ArrayList<String> getProfile(String name)
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Vector<String> vectorOfParticipants()
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void addParticipant(String fName, String lName, String city)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void addFriend(String profile, String newFriend)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void addFriend(String profileFname, String profileLname,
	    String profileHometown, String newFriendFname,
	    String newFriendLName, String newFriendHometown)
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void populateParticipants(File participantsFile)
	    throws FileNotFoundException
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void populateFriends(File friendsFile) throws FileNotFoundException
    {
	// TODO Auto-generated method stub
	
    }

}
