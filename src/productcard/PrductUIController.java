/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productcard;

import customecontroller.ProductControllerInterface;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hmusallam
 */
public class PrductUIController extends AnchorPane implements ProductControllerInterface{

    @FXML
    private Label priceLabel;
    @FXML
    private Label nameLabel; 
     @FXML
    private ImageView productImage;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    
    private int id; 
    
    public void setID(int id){
    this.id=id;
    }

    public void setPrice(String priceLabel) {
        this.priceLabel.setText(priceLabel);
    }


    public void setName(String nameLabel) {
        this.nameLabel.setText(nameLabel);
    }

    public void setProduct(Image productImage) {
        this.productImage.setImage(productImage);
    }


    
    public PrductUIController(String price, String name, Image image,boolean visibleFlage,EventHandler addEvent , EventHandler editEvent) {
        
        this();
        this.priceLabel.setText(price);
        this.nameLabel.setText(name);
        
        if(image == null){
        this.productImage.setImage(new Image(getClass().getResource("tea.png").toString()));
        }else{
        this.productImage.setImage(image);
        }
        addButton.setOnMousePressed(addEvent);
        addButton.setOnTouchPressed(addEvent);
         
        
        editButton.setVisible(visibleFlage);
        
        if(editButton.isVisible()){
        editButton.setOnMousePressed(editEvent);
        editButton.setOnTouchPressed(editEvent);
        }
    }
    
    
    
    
    
    public PrductUIController() {
    
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("productUI.fxml"));
        
        loader.setRoot(this);
        loader.setController(this);
        
        try{
        
           Parent parent= loader.load();
           parent.setUserData(loader.getController());
           
        }catch (IOException ex) {
           throw new RuntimeException(ex); 
        }
   
    
    }
    
    
    
    
    public void setAddEvent(EventHandler event){

        addButton.setOnMousePressed(event);
        addButton.setOnTouchPressed(event);
        
    }
    public void setEditEvent(EventHandler event){
    
        editButton.setVisible(true);
        editButton.setOnMousePressed(event);
        editButton.setOnTouchPressed(event);
        
    }

    @Override
    public int getProductID() {

    return id;
    }

    /**
     * Initializes the controller class.
     */
  
    
    
    
}
