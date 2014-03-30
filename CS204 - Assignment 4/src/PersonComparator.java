import java.util.*;

/**
 * This class allows objects that implement PersonInterface to be compared
 * based on the String representation of their phone number member
 * @author Adam Holt
 * @date 3/30/14
 * @class CS204
 * @time 12:00 MW
 */
public class PersonComparator<T extends PersonInterface> implements Comparator<T>
{
    /**
     * Compare two PersonInterface objects based on their phone number
     * @param p1 PersonInterface object 1
     * @param p2 PersonInterface object 2
     * @return String comparison of the phone numbers of p1 to p2
     */
    public int compare(T p1, T p2)
    {
	//Return the comparison of the phone numbers
	return p1.getPhone().compareTo(p2.getPhone());
    }
}