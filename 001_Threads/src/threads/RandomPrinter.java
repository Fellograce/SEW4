package threads;

public class RandomPrinter implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            int random = (int) Math.floor(Math.random()*(3)+1);
            randomColour(random);
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void randomColour(int random) {
        if (random == 1) {
            System.out.println("RandomPrinter: Rot");
        } else if (random == 2) {
            System.out.println("RandomPrinter: Grün");
        } else {
            System.out.println("RandomPrinter: Blau");
        }
    }
}
