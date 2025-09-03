package LLD.VendingMachine;

// VendingMachine.java
import java.util.*;

public class VendingMachineManager {
    private State idleState;
    private State hasMoneyState;
    private State dispensingState;
    private State currentState;
    private Inventory inventory;
    private int currentBalance;
    private Item selectedItem;
    public VendingMachineManager() {this.inventory = new Inventory();
        this.idleState = new IdleState(this);
        this.hasMoneyState = new HasMoneyState(this);
        this.dispensingState = new DispensingState(this);
        this.currentState = idleState;
    }
    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }
    public void selectItem(String itemName) {
        currentState.selectItem(itemName);
    }
    public void dispense() {
        currentState.dispense();
    }
    public void addMoney(int amount) { currentBalance += amount; }
    public void deductMoney(int amount) { currentBalance -= amount; }
    public int getCurrentBalance() { return currentBalance; }
    public void setSelectedItem(Item item) { this.selectedItem = item; }
    public Item getSelectedItem() { return selectedItem; }
    public Inventory getInventory() { return inventory; }
    public Item getItemByName(String name) {
        for (Item item : inventory.getAllItems()) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
    // State transitions
    public void setState(State state) { this.currentState = state; }
    public State getIdleState() { return idleState; }
    public State getHasMoneyState() { return hasMoneyState; }
    public State getDispensingState() { return dispensingState; }
}

