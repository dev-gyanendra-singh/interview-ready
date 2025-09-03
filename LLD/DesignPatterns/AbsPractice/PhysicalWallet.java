package LLD.DesignPatterns.AbsPractice;

public class PhysicalWallet implements Wallet {
    @Override
    public void deposit(Money amount) {
        System.out.println("deposit " + amount);
    }
}
