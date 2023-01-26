package controllerView;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Mandelbrot;

import java.io.IOException;

public class MandelbrotC {
    @FXML
    private Button btStart;
    @FXML
    private ProgressBar pbBuild;
    @FXML
    private Canvas cvMandelbrot;

    @FXML
    private Label lbCalc;

    @FXML
    private VBox root;

    private Stage stage;
    private Scene scene;

    private Service<int[][]> svMandelbrot;

    @FXML
    private void btStartOnAction(ActionEvent actionEvent) {
        if (!svMandelbrot.isRunning()) {
            pbBuild.setVisible(true);
            btStart.setText("Cancel");
            svMandelbrot.restart();
        } else {
            svMandelbrot.cancel();
            pbBuild.setVisible(false);
            btStart.setText("Start");
        }
    }


    public static void show(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(MandelbrotC.class.getResource("mandelbrotV.fxml"));
            Parent parent = loader.load();

            MandelbrotC mandelbrotC = loader.getController();
            mandelbrotC.stage = stage;
            mandelbrotC.initialize();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Mandelbrot-Menge");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    private void initialize() {
        // Größe & Auflösung: Später mit mehr Flexibilität?!
        cvMandelbrot.heightProperty().bind(stage.heightProperty().subtract(root.getInsets().getBottom()*6));
        cvMandelbrot.widthProperty().bind(root.widthProperty().subtract(root.getInsets().getRight()*2));

        // Service und Task
        svMandelbrot = new Service() {
            @Override
            protected Task createTask() {
                return new Mandelbrot(0, 0, (int) cvMandelbrot.getWidth(),
                        (int) cvMandelbrot.getHeight(), (int) cvMandelbrot.getWidth() * 10);
            }
        };

        // Progressbar
        pbBuild.minWidthProperty().bind(cvMandelbrot.widthProperty().divide(2));
        pbBuild.progressProperty().bind(svMandelbrot.progressProperty());
        pbBuild.setVisible(false);

        // Ergebnis abholen und darstellen
        svMandelbrot.setOnSucceeded((WorkerStateEvent e) -> {
            // Ergebnis abholen
            int[][] mm = svMandelbrot.getValue();

            // Farben
            Color[] colors = new Color[(int)(cvMandelbrot.getWidth() * 10) + 1];
            for (int i = 0; i < (int)(cvMandelbrot.getWidth() * 10); i++) {
                colors[i] = Color.hsb(i / 256f, 1.0, i / (i + 8f));
            }
            colors[(int)(cvMandelbrot.getWidth() * 10)] = Color.BLACK;

            // Image für Mandelbrot-Baum
            WritableImage imMandelbrot = new WritableImage((int) cvMandelbrot.getWidth(), (int) cvMandelbrot.getHeight());
            PixelWriter pw = imMandelbrot.getPixelWriter();

            // Umsetzung in Farben
            for (int col = 0; col < (int) cvMandelbrot.getWidth(); col++) {
                for (int row = 0; row < (int) cvMandelbrot.getHeight(); row++) {
                    pw.setColor(col, row, colors[mm[col][row]]);
                }
            }

            // Mandelbrot-Baum zeichnen
            cvMandelbrot.getGraphicsContext2D().drawImage(imMandelbrot, 0, 0);

            // Button toggeln

            pbBuild.setVisible(false);
        });

        svMandelbrot.setOnCancelled((WorkerStateEvent e) -> {
            // Button toggeln
            btStart.setText("Start");
        });
    }
}
