package LLD.DesignPatterns.Structural.FlyWeight;

import java.util.Arrays;
import java.util.List;

public class FlyweightApp {
    public static void main(String[] args) {
        CssStyleFactory factory = new CssStyleFactory();

        List<String> textList = Arrays.asList("myName", "yourName");
        int index = 0;
        for (String ch : textList) {
            CssDetailsFlyWeight glyph = factory.getGlyph(ch);  // shared
            glyph.style();   // extrinsic
        }
    }
}
