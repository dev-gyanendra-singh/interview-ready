package LLD.DesignPatterns.Structural.FlyWeight;

public class StyleUsingFlyWeight implements CssDetailsFlyWeight{

    String text;

    StyleUsingFlyWeight(String text){
        this.text = text;
    }

    @Override
    public void style() {
        System.out.println(text);
    }
}
