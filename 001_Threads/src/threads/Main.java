package threads;

public class Main {
    public static void main(String[] args) {
        Thread countPrinterThread = new Thread(new CountPrinter());
        countPrinterThread.start();

        Thread randomPrinterThread = new Thread(new RandomPrinter());
        randomPrinterThread.start();

        Thread datePrinterThread = new Thread(new DatePrinter());
        datePrinterThread.start();
    }
}
