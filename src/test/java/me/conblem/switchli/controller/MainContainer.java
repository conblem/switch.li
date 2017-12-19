package me.conblem.switchli.controller;

import com.google.common.eventbus.EventBus;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import me.conblem.switchli.Container;
import me.conblem.switchli.Controller;

public class MainContainer extends AnchorPane implements Container, Controller {
    @Override
    public <T extends Node & Controller> void setView(T controller) {
        this.getChildren().set(1, controller);
    }

    @Override
    public void setEventBus(EventBus eventBus) {
    }
}
