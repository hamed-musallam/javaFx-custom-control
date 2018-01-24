/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toggleButton;

import javafx.event.EventHandler;

/**
 *
 * @author Hmusallam
 */
public abstract class ToggleEventHandler implements EventHandler<ToggleEvent> {
      
    @Override
    public abstract void handle(ToggleEvent event); //{
        
//        if (event.getEventType().equals(ToggleEvent.viewChange)) {
//            System.out.println("MyEvent 2");
//        }
//    }
}