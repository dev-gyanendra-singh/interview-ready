package LLD.DesignPatterns.AbstractFactory;

public class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public CheckBox createCheckbox() {
        return new MacCheckbox();
    }
}

