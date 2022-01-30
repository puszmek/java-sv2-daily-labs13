package day05;

public class TransferPerClient {

    private String clientId;
    private int sum;
    private int numberOfTransactions;

    public TransferPerClient(String clientId) {
        this.clientId = clientId;
    }

    public void increase(int amount){
        sum += amount;
        numberOfTransactions++;
    }

    public void decrease(int amount){
        sum -= amount;
        numberOfTransactions++;
    }

    public String getClientId() {
        return clientId;
    }

    public int getSum() {
        return sum;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }
}