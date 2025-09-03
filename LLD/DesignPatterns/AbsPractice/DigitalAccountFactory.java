package LLD.DesignPatterns.AbsPractice;

public class DigitalAccountFactory implements AccountFactory {
    @Override
    public Money getMoney() {
        return new DigitalMoney();
    }

    @Override
    public Wallet getWallet() {
        return new DigitalWallet();
    }
}
