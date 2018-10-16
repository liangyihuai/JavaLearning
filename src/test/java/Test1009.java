import java.util.Comparator;

class Person implements Comparable<Person>{

    private String name;
    private int age;

    public Person(int age){
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return age - o.age;
    }

    public int getAge(){
        return age;
    }


}

public class Test1009 {

    public static void main(String[] args) {
//        Integer arr[] = {5, 3, 6, 1};
//        Sorter sorter = new SorterImpl();
//        sorter.sort(arr, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//
//        for(int i = 0; i < arr.length; i++){
//            System.out.println(arr[i]);
//        }

        Person ps [] = {new Person(5), new Person(3), new Person(6), new Person(1)};

        Sorter sorter = new SorterImpl();

        sorter.sort(ps);

        for(Person p: ps){
            System.out.println(p.getAge());
        }
    }
}
