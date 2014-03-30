import java.util.*;

public class PersonComparator implements Comparator<Person>
{

    public int compare(Person p1, Person p2)
    {
	return p1.getPhone().compareTo(p2.getPhone());
    }
}