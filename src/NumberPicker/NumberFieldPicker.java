/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumberPicker;

import java.io.IOException;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import minikeybord.MinikeybordController;

/**
 * FXML Controller class
 *
 * @author hmusallam
 */
public class NumberFieldPicker extends AnchorPane {

    @FXML
    private TextField field;
    @FXML
    private Button showKeybordBT;
    private Popup pop;
    
    private MinikeybordController keybord;

    public NumberFieldPicker() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("number.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        // loader.setClassLoader(getClass().getClassLoader());
        try {

            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        showKeybordBT.setGraphic(new ImageView(new Image(getClass().getResource("keybord.png").toString())));
        keybord = new MinikeybordController(field.textProperty(), 0, MinikeybordController.keyBordType.Float);

        showKeybordBT.setOnMousePressed((MouseEvent event) -> {

            pop = new Popup();
            pop.setAutoFix(true);
            pop.setAutoHide(true);
            pop.setHideOnEscape(true);
            pop.getContent().add(keybord);

            Point2D point = localToScene(0.0, 0.0);
            double x = point.getX() + getScene().getX() + getScene().getWindow().getX() - ((keybord.getPrefWidth() - getPrefWidth()) / 2);

            double y = point.getY() + getScene().getY() + getScene().getWindow().getY() + getHeight();
            pop.show(NumberFieldPicker.this, x, y);

        });

        keybord.Enter.setOnAction((ActionEvent event) -> {
            pop.hide();
        });
    }

    public String getField() {
        return field.getText();
    }

    public void setField(String field) {
        this.field.setText(field);
    }
    
    
    
    
    

    /**
     * Initializes the controller class.
     */
}
