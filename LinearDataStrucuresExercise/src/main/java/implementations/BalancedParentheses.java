package implementations;

import interfaces.Deque;
import interfaces.Solvable;

public class BalancedParentheses implements Solvable {
    private String parentheses;
    private Deque<Character> stack;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
        this.stack = new ArrayDeque<>();
    }

    @Override
    public Boolean solve() {

        if (this.parentheses.length() % 2 != 0) {
            return false;
        }

        for (int i = 0; i < this.parentheses.length(); i++) {
            if (i < parentheses.length() / 2) {
                stack.push(parentheses.charAt(i));
            }else {
                Character pop = stack.pop();
                char c = parentheses.charAt(i);

                if (pop == '(') {
                    if (c != ')') {
                        return false;
                    }

                }else if (pop == '{') {

                    if (c != '}') {
                        return false;
                    }

                }else if (pop == '[') {
                    if (c != ']') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
