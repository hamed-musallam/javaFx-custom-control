/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customecontroller;

import NumberPicker.NumberFieldPicker;
import inputNumber.Numbercontroller;
import toggleButton.ToggleEvent;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logout.LogOut;
import logout.LogOutEvent;
import logout.LogOutEventHandler;
import productlist.ProductListController;
import toggleButton.ToggleController;

/**
 *
 * @author pdx
 */
public class CustomeController extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       // Parent root = FXMLLoader.load(getClass().getResource("custome_spinner.fxml"));
        
        //Custome_spinnerController hh = new Custome_spinnerController();
    //    NumberTextField field = new NumberTextField();
       TextField field = new TextField();
  //  keybord.Keybord gg = new keybord.Keybord (field.textProperty());
   //     Numbercontroller jj = new  Numbercontroller();
    // newkeybord.newMiniKeybord ggg = new newkeybord.newMiniKeybord(field.textProperty());
   //  productcard.PrductUIController ff = new PrductUIController();
     
       
//       productlist.productList kk = new productList();
       
//     ggg.Enter.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                JOptionPane.showMessageDialog(null, "do job");
//            }
//        });
        
        //SubScene jjjj = new SubScene(jj, 300,500);
        //jjjj.autosize();
        
   //    kLabel.klabel kk = new klabel();
    // VBox box = new VBox(field,kk);
       
       
       ToggleController jj = new ToggleController(ToggleController.viewType.card);
       logout.LogOut log = new LogOut();
       inputNumber.Numbercontroller ll = new Numbercontroller();
       NumberFieldPicker ww = new NumberFieldPicker();
//       ww.setEditEvent(new EventHandler() {
//
//           @Override
//           public void handle(Event event) {
//               System.out.println("sss");
//           }
//       });
               jj.setLayoutX(100);
       log.setLayoutX(100);
       log.setLayoutY(100);
       ll.setLayoutY(100);
       
       ww.setLayoutY(200);
       
       log.addEventHandler(LogOutEvent.logOut, new EventHandler<LogOutEvent>() {

           @Override
           public void handle(LogOutEvent event) {

           if(log.getSelectedView()== LogOut.logedStats.no){
                  System.out.println("not loged");
              }else{
                  System.out.println("loged");
              }   
                    }
       });
       
       
       
       jj.addEventHandler(ToggleEvent.viewChange,new EventHandler<ToggleEvent>() {

           @Override
           public void handle(ToggleEvent event) {

               if(jj.getSelectedView() == ToggleController.viewType.list){
                  System.out.println("list");
              }else{
                  System.out.println("card");
              }
              // ((AnchorPane)event.getSource()).getUserData()
               
           }
       });
       AnchorPane kk = new AnchorPane(jj,log,ll,ww);
     Scene scene = new Scene(kk);
     
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
