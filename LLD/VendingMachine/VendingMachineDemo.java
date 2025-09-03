package LLD.VendingMachine;

public class VendingMachineDemo {

    public static void main(String[] args) {
        VendingMachineManager vm = new VendingMachineManager();
        Item chips = new Item("Chips", 25);
        Item soda = new Item("Soda", 35);

        vm.getInventory().addItem(chips, 5);
        vm.getInventory().addItem(soda, 3);

        vm.insertCoin(Coin.QUARTER);
        vm.selectItem("Chips");  // Should dispense

        vm.insertCoin(Coin.DIME);
        vm.insertCoin(Coin.QUARTER);
        vm.selectItem("Soda");   // Not enough balance

        vm.insertCoin(Coin.DIME);
        vm.selectItem("Soda");   // Should dispense
    }
}
