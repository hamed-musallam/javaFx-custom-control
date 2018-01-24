/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newkeybord;

import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class newMiniKeybord extends AnchorPane {

    
     @FXML
    public  Button Enter;
    
     
     
     
    private final StringProperty field;
   // private final SimpleValidator format;

    
    
    
    public newMiniKeybord() {

        this(null);
    
    }
    
    
    
    
    public newMiniKeybord(StringProperty field) {
        this.field = field;
        
//format = NumberFormat.getInstance();
       // format.setMaximumFractionDigits(2);
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("newkeybord.fxml"));
       
        loader.setRoot(this);
        loader.setController(this);

        try {

            loader.load();
        } catch (IOException exp) {
            throw new RuntimeException(exp);
        }
if(field != null){
        ObservableList<Node> buttons = this.getChildren();

        for (int i = 0; i < buttons.size(); i++) {
            Button bt = (Button) buttons.get(i);
           if(this.isNumber(bt.getText())){
            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(field.get().equals("0")){
                    if(field.get().length() == 1){
                    
                    
                    field.bind(new SimpleStringProperty(bt.getText()));
                    }
                    }else{
                    
                    field.bind(new SimpleStringProperty(field.getValue() + bt.getText()));
                    }
                   
                    }
            });
           }
        }

        field.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!isValid(newValue)) {
                  //  System.out.println(newValue);
                    field.bind(new SimpleStringProperty(oldValue));
                }

            }
        });
        
}
    }

    
    
    public boolean isNumber(String value){
      return value.matches("[0-9]|[\\.]");
    
    }
    
    

    private boolean isValid(final String value) {

        if (this.countMatches(value, ".") > 1) {
            return false;
        }

        if (value.endsWith(".")) {
            return true;
        }

        try {
            Double.parseDouble(value);
           } catch (NumberFormatException ex) {
            return false;
        }

    
        
       return value.matches("[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?[0-9]?(\\.[0-9][0-9]?)?") ;
        
    }

    public double getDouble() {
        try {
            return Double.parseDouble(field.get());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing double (" + field.get() + ") from field.");
            return 0;
        }
    }

    public int countMatches(String value, String match) {

        int counter = 0;
        for (int i = 0; i < value.length(); i++) {
            if (String.valueOf(value.charAt(i)).equals(match)) {
                counter++;
            }
        }
        return counter;
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
