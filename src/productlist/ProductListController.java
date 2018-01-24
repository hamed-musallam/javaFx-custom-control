/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productlist;

import customecontroller.ProductControllerInterface;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;



/**
 * FXML Controller class
 *
 * @author hmusallam
 */
public class ProductListController extends AnchorPane implements ProductControllerInterface{
    @FXML
    private Label Counter;
    @FXML
    private Label Product_number ;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private HBox productBox;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    
    private int Id;
    
    
   
    
    public ProductListController() {
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productList.fxml"));
    fxmlLoader.setController(this);
    fxmlLoader.setRoot(this);
        
    try {
        Parent parent=   fxmlLoader.load();
        parent.setUserData(fxmlLoader.getController());
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    
    
    
    
    
    
    
}
    
    public void setAddEvent(EventHandler event){
    
      addButton.setOnMousePressed(event);
      addButton.setOnTouchPressed(event);
        
    } 
    
      public void setEditEvent(EventHandler event){
    
          
       editButton.setVisible(true);
       editButton.setPrefWidth(40);
       setRightAnchor(productBox,40.0);
       
      editButton.setOnMousePressed(event);
      editButton.setOnTouchPressed(event);
        
    } 
    
    
    

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
    
    
    
    public void setCounter(String counter){
    
    this.Counter.setText(counter);
    }
    
    public void setProductNumber(String number){
    this.Product_number.setText(number);
    }
    
    
    public void setID(int id){
    
     this.Id = id;
    }
    
    
    public void setName(String name){
    
    this.name.setText(name);
    }
    
    public void setPrice(String price){
    
    this.price.setText(price);
    }
    
    
    public HBox getProductBox(){
    return productBox;
    }
    
    

    @Override
    public int getProductID() {

    return Id;
    
    }
    
    
    


    
    
}
