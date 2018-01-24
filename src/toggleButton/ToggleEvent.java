/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toggleButton;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 *
 * @author Hmusallam
 */
public class ToggleEvent extends Event {
    
    public static final EventType<ToggleEvent>  viewChange = new EventType(ANY, "viewChange");
    
    public ToggleEvent() {
        this(viewChange);
    }
    
    public ToggleEvent(EventType<? extends Event> arg0) {
        super(arg0);
    }
    public ToggleEvent(Object arg0, EventTarget arg1, EventType<? extends Event> arg2) {
        super(arg0, arg1, arg2);
    }  
}