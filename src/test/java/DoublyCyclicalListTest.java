import collections.DoublyCyclicalList;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class DoublyCyclicalListTest {
    private static DoublyCyclicalList<Integer> list;

    @BeforeClass
    public static void print(){
        list = new DoublyCyclicalList<>(1, 3, 5, 6, 7, 8);
        System.out.println("Size: " + list.size() + "; values: " + list);
    }

    @Before
    public void initialization(){
        list = new DoublyCyclicalList<>(1, 3, 5, 6, 7, 8);
    }

    @Test
    public void contains() {
        boolean expected = list.contains(3);
        Assert.assertTrue(expected);

        expected = list.contains(10);
        Assert.assertFalse(expected);
    }

    @Test
    public void add(){
        list.add(10);

        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(7);
        actual.add(8);
        actual.add(10);

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);

        list.add(3, 1000);
        actual.add(3, 1000);

        expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    @Test
    public void addFirst(){
        list.addFirst(101);

        List<Integer> actual = new ArrayList<>();
        actual.add(101);
        actual.add(1);
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(7);
        actual.add(8);

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    @Test
    public void remove(){
        boolean expected = list.remove(new Integer(3));
        Assert.assertTrue(expected);

        expected = list.remove(new Integer(200));
        Assert.assertFalse(expected);

        int expected1 = list.remove(1);
        Assert.assertEquals(expected1, 5);
    }

    @Test
    public void removeFirst(){
        list.removeFirst();

        List<Integer> actual = new ArrayList<>();
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(7);
        actual.add(8);

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    @Test
    public void removeLast(){
        list.removeLast();

        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(7);

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    @Test
    public void containsAll(){
        List<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(3);
        collection.add(5);
        collection.add(6);
        collection.add(7);
        collection.add(8);

        boolean expected = list.containsAll(collection);
        Assert.assertTrue(expected);
    }

    @Test
    public void addAll(){
        list.addAll(30, 50);

        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(7);
        actual.add(8);
        actual.add(30);
        actual.add(50);

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    @Test
    public void removeAll(){
        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(3);
        actual.add(5);
        actual.add(6);
        actual.add(7);
        actual.add(8);

        list.removeAll(actual);
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    public void clear(){
        list.clear();
        Assert.assertEquals(list.size(), 0);
    }

    @Test
    public void get(){
        int expected = list.get(list.size() - 1);
        Assert.assertEquals(expected, 8);
    }

    @Test
    public void set(){
        list.set(1, 1000);

        List<Integer> actual = new ArrayList<>();
        actual.add(1);
        actual.add(1000);
        actual.add(5);
        actual.add(6);
        actual.add(7);
        actual.add(8);

        boolean expected = checkEquals(actual);
        Assert.assertTrue(expected);
    }

    @Test
    public void indexOf(){
        int expected = list.indexOf(5);
        Assert.assertEquals(expected, 2);

        expected = list.indexOf(500);
        Assert.assertEquals(expected, -1);
    }

    @Test
    public void lastIndexOf(){
        list.addAll(8, 7);

        int expected = list.lastIndexOf(8);
        Assert.assertEquals(expected, 6);
    }

    private boolean checkEquals(List<Integer> actual){
        boolean expected = true;
        for (int i = 0; i < list.size(); i++) {
            if(!list.get(i).equals(actual.get(i))){
                expected = false;
                break;
            }
        }
        return expected;
    }
}
