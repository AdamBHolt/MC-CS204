import java.util.*;

public class PersonComparator<T extends PersonInterface> implements Comparator<T>
{
    public int compare(T p1, T p2)
    {
	return p1.getPhone().compareTo(p2.getPhone());
    }
}