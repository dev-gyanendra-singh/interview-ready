package LLD.DesignPatterns.Structural.FlyWeight;

import java.util.HashMap;
import java.util.Map;

public class CssStyleFactory {
    private final Map<String, CssDetailsFlyWeight> glyphs = new HashMap<>();

    public CssDetailsFlyWeight getGlyph(String symbol) {
        glyphs.putIfAbsent(symbol, new StyleUsingFlyWeight(symbol));
        return glyphs.get(symbol);
    }

}
