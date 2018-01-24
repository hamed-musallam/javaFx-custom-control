/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logout;

import javafx.event.Event;
import static javafx.event.Event.ANY;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 *
 * @author Hmusallam
 */
public class LogOutEvent extends Event {
    
    public static final EventType<LogOutEvent>  logOut = new EventType(ANY, "logout");
    
    public LogOutEvent() {
        this(logOut);
    }
    
    public LogOutEvent(EventType<? extends Event> arg0) {
        super(arg0);
    }
    public LogOutEvent(Object arg0, EventTarget arg1, EventType<? extends Event> arg2) {
        super(arg0, arg1, arg2);
    }  
}