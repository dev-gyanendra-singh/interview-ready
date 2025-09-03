package NeetCode150;

import java.util.Stack;

public class Stack_ReversePolishNotation {
    public static void main(String[] args) {
        Stack_ReversePolishNotation main = new Stack_ReversePolishNotation();
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(main.evalRPN(tokens));
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+": {
                    stack.push(stack.pop() + stack.pop());
                }
                break;
                case "-": {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b - a);
                }
                break;
                case "*": {
                    stack.push(stack.pop() * stack.pop());
                }
                break;
                case "/": {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b / a);
                }
                break;
                default: {
                    stack.push(Integer.parseInt(token));
                }
            }
        }
        return stack.peek();
    }
}
