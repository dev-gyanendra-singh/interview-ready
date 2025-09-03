package NeetCode150;

public class ArrayAndHashing_IsPalindrome {
    public static void main(String[] args) {
        System.out.println(new ArrayAndHashing_IsPalindrome().isPalindrome("Was it a car or a cat I saw?")); // true
        System.out.println(new ArrayAndHashing_IsPalindrome().isPalindrome("tab a cat")); // false
    }

    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

        int l = 0, r = sb.length() - 1;
        while (l < r) {
            if(sb.charAt(l) != sb.charAt(r)) {
                return false;
            }
            l++; r--;
        }
        return true;
    }
}
