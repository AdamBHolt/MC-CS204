
public class Driver
{

    
    public static void main(String[] args)
    {
	Friend f1 = new Friend("Adam", "Holt", "Here");
	Friend f2 = new Friend("Bill", "Bailey", "There");
	Friend f3 = new Friend("Chris", "Frisk", "Everywhere");
	Friend f4 = new Friend("Doug", "Funny", "Allover");
	
	Edge<Friend, Friend> e1 = new Edge<>(f1,f4);
	Edge<Friend, Friend> e2 = new Edge<>(f3,f2);
	Edge<Friend, Friend> e3 = new Edge<>(f3,f4);
	Edge<Friend, Friend> e4 = new Edge<>(f4,f1);
	
	System.out.println(e1);
	System.out.println(e2);
	System.out.println(e3);
	System.out.println(e4);
	
	System.out.println(e2.compareTo(e1));
	
	

    }

}
