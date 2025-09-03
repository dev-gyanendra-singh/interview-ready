package NeetCode150;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Stack_GenerateParenthesis {
    public static void main(String[] args) {
        Stack_GenerateParenthesis main = new Stack_GenerateParenthesis();
        System.out.println(main.generateParenthesis(3));
        System.out.println(main.generateParenthesisUsingStack(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backTrack(result, "", 0, 0, n);
        return result;
    }

    private void backTrack(List<String> result, String validParenthesi, int open, int close, int n) {
        if(validParenthesi.length() == 2*n) {
            result.add(validParenthesi);
            return;
        }

        if(open < n) {
            backTrack(result, validParenthesi + '(', open+1, close, n);
        }

        if(close < open) {
            backTrack(result, validParenthesi + ')', open, close +1, n);
        }
    }

    public List<String> generateParenthesisUsingStack(int n) {
        List<java.lang.String> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        stack.push(new Node("", 0, 0));

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            java.lang.String curr = node.str;
            int open = node.open;
            int close = node.close;

            if (curr.length() == n * 2) {
                result.add(curr);
            }

            if (open < n) {
                stack.push(new Node(curr + "(", open + 1, close));
            }

            if (close < open) {
                stack.push(new Node(curr + ")", open, close + 1));
            }
        }

        return result;
    }

    class Node {
        String str;
        int open;
        int close;

        Node(String str, int open, int close) {
            this.str = str;
            this.open = open;
            this.close = close;
        }
    }
}
