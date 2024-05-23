package com.nursoft.toccess.views.impl;

import com.nursoft.toccess.views.interfaces.IControlView;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class ControlView<T extends Pane> extends Pane implements IControlView {

    private T pane;

    public ControlView(T pane) {
        super();
        this.pane = pane;
    }

    public void setPane(T pane) {
        this.pane = pane;
    }

    public T getPane() {
        return pane;
    }

    public void addNode(Node node) {
        pane.getChildren().add(node);
    }

    public void addNodes(Node ...nodes) {
        pane.getChildren().addAll(nodes);
    }
}
