package LLD.VendingMachine;

// HasMoneyState.java
public class HasMoneyState implements State {
    private VendingMachineManager vm;

    public HasMoneyState(VendingMachineManager vm) {
        this.vm = vm;
    }

    public void insertCoin(Coin coin) {
        vm.addMoney(coin.getValue());
        System.out.println("Inserted: " + coin.name());
    }
    public void selectItem(String itemName) {
        Item item = vm.getItemByName(itemName);
        if (item == null) {
            System.out.println("Invalid selection.");
            return;
        }
        if (!vm.getInventory().hasItem(item)) {
            System.out.println("Item out of stock.");
            return;
        }

        if (vm.getCurrentBalance() >= item.getPrice()) {
            vm.setSelectedItem(item);
            vm.setState(vm.getDispensingState());
            vm.dispense();
        } else {
            System.out.println("Not enough balance.");
        }
    }

    public void dispense() {
        System.out.println("Select item first.");
    }
}

