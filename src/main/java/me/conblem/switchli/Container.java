package me.conblem.switchli;

import javafx.scene.Node;

public interface Container {
    public <T extends Node & Controller> void setView(T controller);
}
