/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logout;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author Hmusallam
 */
public class LogOut extends AnchorPane {
    @FXML
    private Button slideButton;
    private double MinValue =0;
    private double MaxValue =0;
    private ImageView arrow =new ImageView(getClass().getResource("arrow.png").toString());
  //  private ImageView CardView =new ImageView(getClass().getResource("card.png").toString());
    private logedStats current_status;
    
    
    
   public static enum logedStats{
    
        yes,no
    
    }
//
//    public LogOut() {
//        this(viewType.list);
//    }
//    
    
    
    public LogOut() {
 
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logOut.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
       this.MinValue = slideButton.getLayoutX();
       this.MaxValue = getPrefWidth() -slideButton.getPrefWidth()-2; 
       this.current_status = logedStats.no; 
        
      // if(view == viewType.list){
       slideButton.layoutXProperty().set(MinValue);
       slideButton.setGraphic(arrow);
      // }else if(view == viewType.card){
     //  slideButton.layoutXProperty().setValue(MaxValue);
     //  slideButton.setGraphic(CardView);
     //  }
           //   Event.fireEvent(slideButton.getParent(),new ToggleEvent());

       
       
        slideButton.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
   
                double x =slideButton.localToParent(event.getX(),slideButton.getLayoutY()).getX()-(slideButton.getPrefWidth()/2);
                
                if(x < MinValue ){
                x= MinValue;
                }else if(x>MaxValue){
                x=MaxValue;
                }              
               slideButton.layoutXProperty().set(x);

            }
        } );
        
     
        
        slideButton.setOnMouseReleased(new EventHandler<MouseEvent>() {

              @Override
              public void handle(MouseEvent event) {
     
                  if(slideButton.getLayoutX()+slideButton.getPrefWidth()/2>getPrefWidth()/2){
                  
                      Animate(slideButton.layoutXProperty(), MaxValue);
                  }else{
                      Animate(slideButton.layoutXProperty(), MinValue);
                  }

              }
          });
        
        
        
       slideButton.layoutXProperty().addListener(new ChangeListener<Number>() {

           @Override
           public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        
//          if(newValue.doubleValue()+slideButton.getPrefWidth()/2 < getPrefWidth()/2){
//          
//          slideButton.setGraphic(ListView);
//          
//          }else{
//          slideButton.setGraphic(CardView);
//          
//          }
//          
          
          if(newValue.doubleValue() == MinValue ){
              current_status = logedStats.no;
              Event.fireEvent(slideButton.getParent(),new logout.LogOutEvent());
          }else if( newValue.doubleValue() == MaxValue){
          
           current_status = logedStats.yes;
           Event.fireEvent(slideButton.getParent(),new logout.LogOutEvent());
           }
               
               
           }
       });
       
       
       
       
        
    }
    
    
    
    
    public void Animate(DoubleProperty xvalue,Double toXValue){
    
       Platform.runLater(()->{
        Timeline timeline = new Timeline();
        KeyValue value = new KeyValue(xvalue,toXValue);
       timeline.getKeyFrames().add(new KeyFrame(Duration.millis(20), value));
       timeline.setCycleCount(1);
       timeline.setAutoReverse(false);
       timeline.play();
        });
    
    }
    
    
    
    public logedStats getSelectedView(){
    
    return current_status;
    }

    /**
     * Initializes the controller class.
     */
   
    
    
    
    
    
}
