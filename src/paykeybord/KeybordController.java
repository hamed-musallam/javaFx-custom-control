/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paykeybord;

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
public class KeybordController extends AnchorPane{
  
     @FXML
    public Button Enter;
    
    
   // public StringProperty insertVal;
   // public StringProperty totalVal;
   // public StringProperty returnVal;
    
   // private final SimpleValidator format;
    private StringProperty insertVal ;
    private StringProperty totalVal ;
    private StringProperty returnVal ;

    public String getReturnVal() {
        return returnVal.get();
    }

    public void setReturnVal(StringProperty value) {
        returnVal=value;
    }

    public StringProperty returnValProperty() {
        return returnVal;
    }

    public String getTotalVal() {
        return totalVal.get();
    }

    public void setTotalVal(StringProperty value) {
        totalVal=value;
    }

    public StringProperty totalValProperty() {
        return totalVal;
    }
   
    public String getInsertVal() {
        return insertVal.get();
    }

    public void setInsertVal(StringProperty value) {
        insertVal=value;
    }

    public StringProperty insertValProperty() {
        return insertVal;
    }

    
    
    public void setValueProperty(StringProperty insertVal,StringProperty totalVal,StringProperty returnVal){
    
    this.insertVal =insertVal;
    this.totalVal = totalVal;
    this.returnVal = returnVal;
        
        iniateAction(insertVal);
    
    }
    
    
    
    public KeybordController() {
        
        
      //  insertVal = new SimpleStringProperty(0+"");
     //   totalVal = new SimpleStringProperty(0+"");
      //  returnVal = new SimpleStringProperty(0+"");
                
      //  this.field = null;
    //    this(new SimpleStringProperty(0+""),new SimpleStringProperty(0+""),new SimpleStringProperty(0+""));
  ////  }
    
    
    
    
    //public KeybordController(StringProperty totalVal,StringProperty insertVal,StringProperty returnVal) {
    //    this.insertVal = insertVal;
     //   this.totalVal =totalVal;
      //  this.returnVal = returnVal;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("keybord.fxml"));
       
        loader.setRoot(this);
        loader.setController(this);

        try {

            loader.load();
        } catch (IOException exp) {
            throw new RuntimeException(exp);
        }

        iniateAction(insertVal);
    
    }

    
    
    public void iniateAction(StringProperty insertVal){
        
        if(insertVal != null){
        ObservableList<Node> buttons = this.getChildren();

        for (int i = 0; i < buttons.size(); i++) {
            Button bt = (Button) buttons.get(i);
           if(this.isNumber(bt.getText())){
            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(insertVal.get().equals("0")){
                    if(insertVal.get().length() == 1){
                    
                    
                    insertVal.bind(new SimpleStringProperty(bt.getText()));
                    }
                    }else{
                    
                    insertVal.bind(new SimpleStringProperty(insertVal.getValue() + bt.getText()));
                    }
                   
                    }
            });
           }
        }

        this.insertVal.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
           returnValProperty().bind(new SimpleStringProperty(Float.parseFloat(getTotalVal()) - Float.parseFloat(getInsertVal())+""));

                if (!isValid(newValue)) {
                  //  System.out.println(newValue);
                    insertValProperty().bind(new SimpleStringProperty(oldValue));
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
            return Double.parseDouble(insertVal.get());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing double (" + insertVal.get() + ") from field.");
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
        
        if(insertVal.get().length() == 1){
        
          insertVal.bind(new SimpleStringProperty(""+0));
        }else{
        insertVal.bind(new SimpleStringProperty(insertVal.get().substring(0,insertVal.get().length()-1)));
        }
    }

    @FXML
    private void clearALLAction(ActionEvent event) {
   
       insertVal.bind(new SimpleStringProperty(""+0));
    
    }
}
