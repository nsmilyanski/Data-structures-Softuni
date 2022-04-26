import implementations.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {

        SinglyLinkedList<String> myList = new SinglyLinkedList<>();

        for (int i = 0; i < 20; i++) {
            myList.addLast(String.valueOf(i));
        }

        System.out.println();

        myList.forEach(System.out::println);



    }
}
