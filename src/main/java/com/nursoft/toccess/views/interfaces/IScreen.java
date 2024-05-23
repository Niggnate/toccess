package com.nursoft.toccess.views.interfaces;

import javafx.event.ActionEvent;


/**
 * This interface handles all actions in all subclasses
 *
 * @author Prince Khanyile
 * @version 1.0.1
 *
 *
 */
public interface IScreen {

    /**
     * Build global layout design for all screens
     * <p>This method needs to be overridden in sub-screens</p>
     */
    void buildLayout();

    /**
     * Build global styles for layout screens
     * <p>This method needs to be overridden in sub-screens</p>
     */
    void cascadeSetters();
    void actionEventSetters();
    void actionEventListeners(ActionEvent event);

}
