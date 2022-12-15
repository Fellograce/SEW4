fpackage controller;

import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Cylinder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CylinderC {
  @FXML
  private Text txHoehe;
  
  @FXML
  private Slider slHoehe;
  
  @FXML
  private ImageView ivCylinder;
  
  @FXML
  private Text txRadius;
  
  @FXML
  private Slider slRadius;
  
  @FXML
  private Text txVolumen;
  
  @FXML
  private Text txOberflaeche;
  
  @FXML
  private Text txUmfang;
  
  // Model
  Cylinder cylinder = new Cylinder();
  
  public static void show(Stage stage) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(CylinderC.class.getResource("../view/cylinderV.fxml"));
      Parent root = loader.load();
      
      //stage.setResizable(false);
      stage.setTitle("Zylinder");
      stage.setScene(new Scene(root));
      stage.show();
    }
    catch (IOException ex) {
      Logger.getLogger(CylinderC.class.getName()).log(Level.SEVERE, null, ex);
      System.err.println("Something wrong with simpleBindingV.fxml!");
      ex.printStackTrace(System.err);
      Platform.exit();
    }
    
  }
  
  @FXML
  void initialize() {
    // Bind Model to Slider
    cylinder.hoeheProperty().bind(slHoehe.valueProperty());
    cylinder.radiusProperty().bind(slRadius.valueProperty());
    
    // Bind Text to Model
    txHoehe.textProperty().bind(cylinder.hoeheProperty().asString("%.2f"));
    txRadius.textProperty().bind(cylinder.radiusProperty().asString("%.2f"));
    
    // Bind Calculated Values to Model
    txVolumen.textProperty().bind(cylinder.volumenProperty().asString("%.2f"));
    txUmfang.textProperty().bind(cylinder.umfangProperty().asString("%.2f"));
    txOberflaeche.textProperty().bind(cylinder.oberflaecheProperty().asString("%.2f"));
    
    // Bind size of ImageView to Slider
    ivCylinder.setPreserveRatio(false);
    ivCylinder.fitHeightProperty().bind(slHoehe.valueProperty().multiply(12));
    ivCylinder.fitWidthProperty().bind(slRadius.valueProperty().multiply(15));
  }
}
