import collections.CyclicalList;
import collections.DoublyCyclicalList;
import collections.ICyclicalList;

public class Main {

    public static void main(String[] args) {
        ICyclicalList<Integer> list = new DoublyCyclicalList<>(1, 3, 5, 6, 7, 8);

//        int i = 0;
//        for (Integer obj : list) {
//            if (i == 10) {
//                break;
//            }
//            System.out.print(obj + "  ");
//            i++;
//        }
//
//        list.forEach(System.out::println);
//        int sum = list.stream().mapToInt(Integer::intValue).sum();
//        System.out.println("Sum: " + sum);

        CyclicalList<Integer> list1 = new CyclicalList<>(1, 2);

        for (Integer num : list1) {
            System.out.println(num);
        }
    }

}
