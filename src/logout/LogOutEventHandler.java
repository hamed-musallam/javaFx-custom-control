/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logout;

import javafx.event.EventHandler;

/**
 *
 * @author Hmusallam
 */
public abstract class LogOutEventHandler implements EventHandler<LogOutEvent> {
      
    @Override
    public abstract void handle(LogOutEvent event); //{
        
}