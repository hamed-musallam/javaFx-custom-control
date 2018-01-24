/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputNumber;

import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hmusallam
 */
public final class Numbercontroller extends AnchorPane{
    @FXML
    private TextField field;
    @FXML
    private AnchorPane keyPanel;
    @FXML
    public Button Enter;
    @FXML
    private Button showKeybordBT;
    
  
    private Timeline time = new Timeline();
    
    public Numbercontroller() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("number.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        
        try{
        
        loader.load();
        }catch(IOException ex){throw new RuntimeException(ex);}
            
        
        
        
        showKeybordBT.setGraphic(new ImageView(new Image(getClass().getResource("keybord.png").toString())));
        showKeybordBT.setOnMousePressed((MouseEvent event)->{      
               if(time.statusProperty().getValue()!=Animation.Status.RUNNING){
                time.setCycleCount(1);                
                KeyValue value =null;
                if(((DisplacementMap)keyPanel.effectProperty().get()).offsetYProperty().get() == 1){
                value= new KeyValue(((DisplacementMap)keyPanel.effectProperty().get()).offsetYProperty(),0,Interpolator.SPLINE(0.8013, 0.0000, 0.2065, 1.0000));
                }else{
                    field.setStyle("-fx-text-fill:black;");
                    field.textProperty().bind(new SimpleStringProperty(""));
                    value= new KeyValue(((DisplacementMap)keyPanel.effectProperty().get()).offsetYProperty(),1,Interpolator.SPLINE(0.8013, 0.0000, 0.2065, 1.0000));
               
                
                }
                
                KeyFrame frame = new KeyFrame(Duration.millis(300), value);
                time.getKeyFrames().clear();
                time.getKeyFrames().add(frame);
                time.play();

               }
        });
        
        

        
//        field.setOnMousePressed((MouseEvent event) -> {
//     
//            }
//        });
//        
//        
//       Enter.setOnAction((ActionEvent event) -> {
//
//           if(time.statusProperty().getValue()!=Animation.Status.RUNNING){
//               KeyValue value = new KeyValue(((DisplacementMap)keyPanel.effectProperty().get()).offsetYProperty(),1,Interpolator.SPLINE(0.8013, 0.0000, 0.2065, 1.0000));
//               KeyFrame frame = new KeyFrame(Duration.millis(300), value);
//               time.getKeyFrames().clear();
//               time.getKeyFrames().add(frame);
//               time.play();   
//           }else{
//                       
//
//           }
//        });
       
       this.iniateEventHandler(field.textProperty());
      
       
       ((DisplacementMap)keyPanel.effectProperty().get()).offsetYProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
           
                if(newValue.intValue()==1){
                
                keyPanel.setVisible(false);
                }else{
               keyPanel.setVisible(true);

                }
                   
            }
        });
     
        
    }
    
    
    
    public void iniateEventHandler(StringProperty field){
    
     
        if(field != null){
        field.setValue("");
        ObservableList<Node> buttons =((AnchorPane)keyPanel.getChildren().get(0)).getChildren();

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
        if(field.textProperty().get().length() == 1){
          field.textProperty().bind(new SimpleStringProperty(""+0));
        }else{
        field.textProperty().bind(new SimpleStringProperty(field.textProperty().get().substring(0,field.textProperty().get().length()-1)));
        }
    }

    @FXML
    private void clearALLAction(ActionEvent event) {
       field.textProperty().bind(new SimpleStringProperty(""+0));
    }

    /**
     * Initializes the controller class.
     * @return 
     */
    
    public TextField getValue(){
    
    return  field;
    }

    
        
    
    public void hideKeybord(){
    
    
           if(time.statusProperty().getValue()!=Animation.Status.RUNNING){
               KeyValue value = new KeyValue(((DisplacementMap)keyPanel.effectProperty().get()).offsetYProperty(),1,Interpolator.SPLINE(0.8013, 0.0000, 0.2065, 1.0000));
               KeyFrame frame = new KeyFrame(Duration.millis(300), value);
               time.getKeyFrames().clear();
               time.getKeyFrames().add(frame);
               time.play();   
           }
            
    }
    
}
