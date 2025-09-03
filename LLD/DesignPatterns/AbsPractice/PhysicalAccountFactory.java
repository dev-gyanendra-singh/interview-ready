package LLD.DesignPatterns.AbsPractice;

public class PhysicalAccountFactory implements AccountFactory{
    @Override
    public Money getMoney() {
        return new CashMoney();
    }

    @Override
    public Wallet getWallet() {
       return new PhysicalWallet();
    }
}
