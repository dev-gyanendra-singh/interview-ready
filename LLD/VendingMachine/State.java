package LLD.VendingMachine;

public interface State {
    void insertCoin(Coin coin);
    void selectItem(String itemName);
    void dispense();
}

