import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {

    @Test
    public void operations() {
        TreeSet<String> stringTreeSet = new TreeSet<String>();
        stringTreeSet.add("robbie");
        stringTreeSet.add("escoffier");
        stringTreeSet.add("jack");
        SortedSet<String> sortedSet = Collections.synchronizedSortedSet(stringTreeSet);

        assertEquals(3, stringTreeSet.size());
    }


}
