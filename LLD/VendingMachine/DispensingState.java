package LLD.VendingMachine;

// DispensingState.java
public class DispensingState implements State {
    private VendingMachineManager vm;
    public DispensingState(VendingMachineManager vm) {
        this.vm = vm;
    }

    public void insertCoin(Coin coin) {
        System.out.println("Currently dispensing. Please wait.");
    }

    public void selectItem(String itemName) {
        System.out.println("Already selected. Dispensing in progress.");
    }

    public void dispense() {
        Item item = vm.getSelectedItem();
        vm.getInventory().dispenseItem(item);
        vm.deductMoney(item.getPrice());
        System.out.println("Dispensed: " + item.getName());

        vm.setSelectedItem(null);
        vm.setState(vm.getIdleState());
    }
}

