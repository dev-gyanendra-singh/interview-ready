package NeetCode150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayAndHashing_EncodeAndDecodeString {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("neet", "code", "love", "you");
        ArrayAndHashing_EncodeAndDecodeString main = new ArrayAndHashing_EncodeAndDecodeString();
        String encoded = main.encode(input);
        System.out.println(encoded);
        List<String> output = main.decode(encoded);
        System.out.println(Arrays.toString(output.toArray()));
    }

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append("#").append(str);
        }
        return sb.toString();
    }

    public List<String> decode(String str) {

        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length();) {
            int j = i;
            while(j < str.length() && str.charAt(j) != '#') {
                j++;
            }
            int len = Integer.parseInt(str.substring(i, j));
            result.add(str.substring(j+1, j+1 + len));
            i = j+len+1;
        }
        return result;
    }
}
