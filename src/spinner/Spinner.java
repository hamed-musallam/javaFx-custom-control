/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spinner;

import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import minikeybord.MinikeybordController;

/**
 * FXML Controller class
 *
 * @author pdx
 */
public class Spinner extends HBox {

    @FXML
    private Button decrementbt;
    @FXML
    private Label amount_tf;
    @FXML
    private Button incrementbt;
    private boolean flage;
    private float MinValue;
    private Popup pop;
    private minikeybord.MinikeybordController keybord;

  

    /**
     * Initializes the controller class.
     */
    public Spinner() {
        this(0);
    }

    public Spinner(float MinValue) {
        this.MinValue = MinValue;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("custome_spinner.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        decrementbt.getStylesheets().add("/spinner/custome_style.css");
        incrementbt.getStylesheets().add("/spinner/custome_style.css");

        pop = new Popup();
        keybord = new MinikeybordController(getValue(),getMinValue(),MinikeybordController.keyBordType.Float);
        pop.setAutoFix(true);
        pop.setAutoHide(true);
        pop.setHideOnEscape(true);
        pop.getContent().add(keybord);

        amount_tf.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                Point2D point = localToScene(0.0, 0.0);
                double x = point.getX() + getScene().getX() + getScene().getWindow().getX() - ((keybord.getPrefWidth() - getPrefWidth()) / 2);

                double y = point.getY() + getScene().getY() + getScene().getWindow().getY() + getHeight();
                pop.show(Spinner.this, x, y);

            }
        });

        keybord.Enter.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                pop.hide();
            }
        });

        this.amount_tf.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                flage = Float.parseFloat(newValue) <= getMinValue();
                decrementbt.setDisable(flage);

            }
        });
        // return the button for work
    }

    @FXML
    private void do_decrement(ActionEvent event) {

        getValue().bind(new SimpleStringProperty(Float.parseFloat(getValue().get()) - 1 + ""));

    }

    @FXML
    private void do_increment(ActionEvent event) {

        getValue().bind(new SimpleStringProperty(Float.parseFloat(getValue().get()) + 1 + ""));

    }

    
    
      public StringProperty getValue() {
        return amount_tf.textProperty();
    }

    public void setMinValue(float value) {
        this.MinValue = value;
    }

    public float getMinValue() {
        return MinValue;
    }
}
