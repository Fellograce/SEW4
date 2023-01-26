package model;

import javafx.concurrent.Task;

public class Mandelbrot extends Task<int[][]> {
    private int startcol;
    private int startrow;
    private int width;
    private int height;
    private int maxiterations;

    public Mandelbrot(int startcol, int startrow, int width, int height, int maxiterations) {
        this.startcol = startcol;
        this.startrow = startrow;
        this.width = width;
        this.height = height;
        this.maxiterations = maxiterations;
    }


    @Override
    protected int[][] call() throws Exception {
        int[][] mm = new int[width - startcol][height - startrow];

        // Mandelbrot-Menge berechnen
        // Alle geforderten Bildpunkte durchgehen
        for (int col = startcol; col < width; col++) {
            // Fortschritt traken
            updateProgress(col, width);

            for (int row = startrow; row < height; row++) {
                // Complexe Zahl C(col/row) - oder fast zumindest:
                // C = cr + ci*i
                double cr = (col - width / 2) * 4.0 / width; // Translation & Streckung
                double ci = (row - height / 2) * 4.0 / width;

                // Complexe Zahl A(0/0):
                // A = ar + ai*i
                double ar = 0, ai = 0;

                int iteration = 0;
                while (ar * ar + ai * ai < 4 && iteration < maxiterations) {
                    // ANeu Complex gerechnet:
                    // ANeu = A**2 + C =
                    //        (ar + ai*i)**2 + (cr + ci*i) =
                    //        (ar**2 + 2*ar*ai*i + (ai*i)**2) + (cr + ci*i) =
                    //        ar**2 + 2*ar*ai*i - ai**2 + cr + ci*i

                    // Realteil:
                    // arNeu = ar**2 - ai**2 + cr
                    double arNeu = ar * ar - ai * ai + cr;
                    // Imaginärteil:
                    // aiNeu = 2*ar*ai + ci
                    ai = 2 * ar * ai + ci;

                    ar = arNeu;

                    iteration++;
                }


                mm[col][row] = iteration;
            }
        }

        return mm;
    }
}
