/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputField;

import java.io.IOException;
import java.net.URL;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Hmusallam
 */
public class InputField extends StackPane{
    @FXML
    private TextField input_tf;
    @FXML
    private Button clear_bt;
    @FXML
    private ImageView imageIcon;
    private FadeTransition trans;

    
    
    public void setValue(String val){
        
        
        
        if(val.isEmpty()){
                  
                      if(clear_bt.opacityProperty().get()==1){
                  hideButton();
                      }
                      
                  }else{
                      if(clear_bt.opacityProperty().get()==0){
                  showButton();
                      }
                  }
    input_tf.setText(val);
    }
    
    public String getValue(){
    
    return input_tf.getText();
    }
    
    
    public void setPromptValue(String value){
    input_tf.setPromptText(value);
    }

    public InputField() {
        
        this(null);
    }
    
    
    
    public InputField(String image_url) {
   
        
        
          trans = new FadeTransition(Duration.millis(100));

   FXMLLoader loader = new FXMLLoader(getClass().getResource("input_field.fxml"));

  loader.setRoot(this);
  loader.setController(this);
  
  try{
  
  loader.load();
  
  }catch(IOException expection){  throw new RuntimeException(expection);}
  
  
  
  if(input_tf.getText().isEmpty()){
  hideButton();
  }
  
  
  input_tf.textProperty().addListener(new ChangeListener<String>() {

              @Override
              public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    
                  
                  if(newValue.isEmpty()){
                  
                      if(clear_bt.opacityProperty().get()==1){
                  hideButton();
                      }
                      
                  }else{
                      if(clear_bt.opacityProperty().get()==0){
                  showButton();
                      }
                  }
                  
                  
              }
              
              
          });
  
  
  
  if(image_url ==null || image_url.isEmpty()){
  
  imageIcon.setImage(new Image(getClass().getResource("/inputField/defaultImage.png").toString()));
  }else{
    imageIcon.setImage(new Image(image_url));

  }
    

    }
   
     
    
    
    /**
     * Initializes the controller class.
     */
    
    private void showButton(){
    trans.setNode(clear_bt);
    trans.setFromValue(0);
    trans.setToValue(1);
    trans.setCycleCount(1);
    trans.setAutoReverse(false);
    
    //hide  image icon
    imageIcon.setVisible(false);
   // imageIcon.toBack();
//show  clear button
   // clear_bt.toFront();
    trans.play();
    clear_bt.setDisable(false);
    
    }
 
    
       private void hideButton(){
    trans.setNode(clear_bt);
    trans.setFromValue(1);
    trans.setToValue(0);
    trans.setCycleCount(1);
    trans.setAutoReverse(false);
    
    // show image icon
    imageIcon.setVisible(true);
    
    //hide clear button
    trans.play();
    clear_bt.setDisable(true);
 
    }
 
    
       
       
    @FXML
    private void clearAction(ActionEvent event) {
    
        input_tf.setText("");
    
    }
    
}
