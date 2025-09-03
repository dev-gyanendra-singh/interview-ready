package NeetCode150;

import WorkingModule.Main;

import java.util.Stack;

public class Stack_Valid_Parenthesis {
    public static void main(String[] args) {
        System.out.println(new Stack_Valid_Parenthesis().isValid("([{}])"));
    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();

                if (c == ')' && pop != '(' || pop != '{' && c == '}' || pop != '[' && c == ']') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
