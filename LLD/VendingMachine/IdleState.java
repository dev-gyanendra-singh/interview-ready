package LLD.VendingMachine;

// IdleState.java
public class IdleState implements State {
    private VendingMachineManager vm;
    public IdleState(VendingMachineManager vm) {
        this.vm = vm;
    }
    public void insertCoin(Coin coin) {
        vm.addMoney(coin.getValue());
        vm.setState(vm.getHasMoneyState());
        System.out.println("Inserted: " + coin.name());
    }
    public void selectItem(String itemName) {
        System.out.println("Insert coins first.");
    }
    public void dispense() {
        System.out.println("Insert coins first.");
    }
}

