package model;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class InstantViewer extends ObjectBinding<Image> {
    private StringProperty imageName = new SimpleStringProperty();

    public InstantViewer() {}

    public InstantViewer(StringProperty imageName) {
        super.bind(imageName);
        this.imageName = imageName;
    }

    @Override
    protected Image computeValue() {
        Image image = null;

        switch (imageName.get()) {
            case "boy":
                image = new Image("media/boy.jpg");
                break;
            case "girl":
                image = new Image("media/girl.jpg");
                break;
            case "oldman":
                image = new Image("media/oldman.jpg");
                break;
            case "palmtree":
                image = new Image("media/palmtree.jpg");
                break;
            case "street.jpg":
                image = new Image("media/street.jpg");
                break;
            default:
                break;
        }

        return image;
    }

    public String getImageName() {
        return imageName.get();
    }

    public StringProperty imageNameProperty() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName.set(imageName);
    }
}
