import collections.CyclicalList;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class CyclicalListTest {
    private static CyclicalList<Integer> list;

    @BeforeClass
    public static void initializationAndPrint() {
        list = new CyclicalList<>(1, 3, 5, 6, 7, 8);
        System.out.println("Size: " + list.size() + "; values: " + list);
    }

    @Before
    public void initialization() {
        list = new CyclicalList<>(1, 3, 5, 6, 7, 8);
    }

    @Test
    public void addFirst() {
        list.addFirst(101);

        List<Integer> actual = new ArrayList<>();
        actual.add(101);
        actual.add(1);
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(7);
        actual.add(8);

        System.out.println("Expected:" + list);
        System.out.println("Actual:" + actual.toString());

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    @Test
    public void addLast() {
        list.addLast(101);

        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(7);
        actual.add(8);
        actual.add(101);

        System.out.println("Expected:" + list);
        System.out.println("Actual:" + actual.toString());

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    @Test
    public void add() {
        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(7);
        actual.add(8);


        list.add(3, 1000);
        System.out.println("Expected:" + list);
        actual.add(3, 1000);
        System.out.println("Actual:" + actual.toString());

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    @Test
    public void remove() {
        list.remove(new Integer(7));
        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(8);

        System.out.println("Expected:" + list);
        System.out.println("Actual:" + actual.toString());

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);

        list.remove(new Integer(3));
        System.out.println("Expected:" + list);
        actual.remove(new Integer(3));
        System.out.println("Actual:" + actual.toString());

        expected = checkEquals(actual);
        Assert.assertTrue(expected);

        list.remove(2);
        System.out.println("Expected:" + list);
        actual.remove(2);
        System.out.println("Actual:" + actual.toString());

        expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    @Test
    public void removeFirst() {
        list.removeFirst();

        List<Integer> actual = new ArrayList<>();
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(7);
        actual.add(8);

        System.out.println("Expected:" + list);
        System.out.println("Actual:" + actual.toString());

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    @Test
    public void removeLast() {
        list.removeLast();

        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(7);

        System.out.println("Expected:" + list);
        System.out.println("Actual:" + actual.toString());

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);

        list.removeLast();
        System.out.println("Expected:" + list);
        actual.remove(actual.size() - 1);
        System.out.println("Actual:" + actual.toString());

        expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    private boolean checkEquals(List<Integer> actual) {
        boolean expected = true;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(actual.get(i))) {
                expected = false;
                break;
            }
        }
        return expected;
    }
}
