import implementations.ArrayDeque;
import implementations.BalancedParentheses;
import implementations.ReversedList;
import interfaces.Deque;

public class Main {
    public static void main(String[] args) {

        BalancedParentheses balancedParentheses = new BalancedParentheses("([{ }])");


        System.out.println(balancedParentheses.solve());


    }
}
