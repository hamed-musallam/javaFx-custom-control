/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toggleButton;

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
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hmusallam
 */
public class ToggleController extends AnchorPane{

    
    @FXML
    private Button slideButton;
    private double MinValue =0;
    private double MaxValue =0;
    private ImageView ListView =new ImageView(getClass().getResource("list.png").toString());
    private ImageView CardView =new ImageView(getClass().getResource("card.png").toString());
    private viewType current_view;
    private EventHandler eventDrage ;
    private EventHandler eventRelease ;
    private EventHandler event;
    
   public static enum viewType{
    
        card,list
    
    }

    public ToggleController() {
        this(viewType.list);
    }
    
    
    
    public ToggleController(viewType view) {
 
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("toggle.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
       this.MinValue = slideButton.getLayoutX();
       this.MaxValue = getPrefWidth() -slideButton.getPrefWidth()-2; 
       this.current_view = view; 
        
       if(view == viewType.list){
       slideButton.layoutXProperty().set(MinValue);
       slideButton.setGraphic(ListView);
       }else if(view == viewType.card){
       slideButton.layoutXProperty().setValue(MaxValue);
       slideButton.setGraphic(CardView);
       }
           //   Event.fireEvent(slideButton.getParent(),new ToggleEvent());

       
       
       
       
        event = new EventHandler<Event>() {

              @Override
              public void handle(Event event) {
               
                         double x   =0 ;
                  
               if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
               
               MouseEvent mevent = (MouseEvent)event;
              x= mevent.getX();
               }else if(event.getEventType() == TouchEvent.TOUCH_MOVED){
               
               
               TouchEvent tevent = (TouchEvent)event;
              
              x=tevent.getTouchPoint().getX();
                
               
               }
               
               
               if(x > getPrefWidth()/2){
               
                   Animate(slideButton.layoutXProperty(), MaxValue);
               
               }else{
                   Animate(slideButton.layoutXProperty(), MinValue);
               }
               
                  
                  
              }
          };
       
        
        setOnMousePressed(event);
        
       eventDrage = new EventHandler() {

              @Override
              public void handle(Event event) {
        
                   double x   =0 ;
                  
               if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
               
               MouseEvent mevent = (MouseEvent)event;
              
              x=slideButton.localToParent(mevent.getX(),slideButton.getLayoutY()).getX()-(slideButton.getPrefWidth()/2);
                
               }else if(event.getEventType() == TouchEvent.TOUCH_MOVED){
               
               
               TouchEvent tevent = (TouchEvent)event;
              
              x=slideButton.localToParent(tevent.getTouchPoint().getX(),slideButton.getLayoutY()).getX()-(slideButton.getPrefWidth()/2);
                
               
               }   
               
                if(x < MinValue ){
                x= MinValue;
                }else if(x>MaxValue){
                x=MaxValue;
                }              
               slideButton.layoutXProperty().set(x);

                  
                  
              }
          };
       
       
       eventRelease = new EventHandler() {

              @Override
              public void handle(Event event) {
                  if(slideButton.getLayoutX()+slideButton.getPrefWidth()/2>getPrefWidth()/2){
                  
                      Animate(slideButton.layoutXProperty(), MaxValue);
                  }else{
                      Animate(slideButton.layoutXProperty(), MinValue);
                  }

              }
          };
       
       
        slideButton.setOnMouseDragged(eventDrage);
        slideButton.setOnTouchMoved(eventDrage);        
        slideButton.setOnMouseReleased(eventRelease);
        slideButton.setOnTouchReleased(eventDrage);
                
        
        
       slideButton.layoutXProperty().addListener(new ChangeListener<Number>() {

           @Override
           public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        
          if(newValue.doubleValue()+slideButton.getPrefWidth()/2 < getPrefWidth()/2){
          
          slideButton.setGraphic(ListView);
          
          }else{
          slideButton.setGraphic(CardView);
          
          }
          
          
          if(newValue.doubleValue() == MinValue ){
              current_view = viewType.list;
              Event.fireEvent(slideButton.getParent(),new ToggleEvent());
          }else if( newValue.doubleValue() == MaxValue){
          
           current_view = viewType.card;
           Event.fireEvent(slideButton.getParent(),new ToggleEvent());
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
    
    
    
    public viewType getSelectedView(){
    
    return current_view;
    }

    /**
     * Initializes the controller class.
     */
   
    
    
    
    
    
    
}
