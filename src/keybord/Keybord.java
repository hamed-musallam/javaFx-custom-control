/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keybord;

import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hmusallam
 */

public class Keybord extends AnchorPane {

    
     @FXML
    public Button Enter;
     
    private StringProperty field ;

    public String getField() {
        return field.get();
    }

    public void setField(String value) {
        field.set(value);
    }

    public StringProperty fieldProperty() {
        return field;
    }
   
    public void setFieldProperty(StringProperty field){
    this.field=field;
    this.iniateEventHandler(field);
    }

    public Keybord() {
        this(null);
    }
    
    
    public Keybord(StringProperty field) {
        this.field =field;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("keybord.fxml"));
       
        loader.setRoot(this);
        loader.setController(this);

        try {

            loader.load();
        } catch (IOException exp) {
            throw new RuntimeException(exp);
        }
        this.iniateEventHandler(field);
       
    }

    
    
    public void iniateEventHandler(StringProperty field){
    
     
        if(field != null){
        field.setValue("");
        ObservableList<Node> buttons = this.getChildren();

        for (int i = 0; i < buttons.size(); i++) {
            Button bt = (Button) buttons.get(i);
           if(this.isNumber(bt.getText())){
            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(field.get().equals("0") || field.get().equals("00")){
                    if(field.get().length() == 1 || field.get().length() == 2){
                    field.bind(new SimpleStringProperty(bt.getText()));
                    }
                    }else{
                    field.bind(new SimpleStringProperty(field.getValue() + bt.getText()));
                    }
                   
                    }
            });
           }
        }

        
        
        }
    }
    
    public boolean isNumber(String value){
      return value.matches("[0-9]|00");
    
    }
    
    @FXML
    private void ClearLastNumberAction(ActionEvent event) {
        if(field.get().length() == 1){
          field.bind(new SimpleStringProperty(""+0));
        }else{
        field.bind(new SimpleStringProperty(field.get().substring(0,field.get().length()-1)));
        }
    }

    @FXML
    private void clearALLAction(ActionEvent event) {
       field.bind(new SimpleStringProperty(""+0));
    }
    /**
     * Initializes the controller class.
     */
}
