package threads;

import java.util.Date;

public class DatePrinter implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            Date date = new Date();
            System.out.println("DatePrinter: " + date);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
