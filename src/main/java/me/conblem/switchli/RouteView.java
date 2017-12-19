package me.conblem.switchli;

import org.bigtesting.routd.Route;
import javafx.beans.DefaultProperty;
import javafx.beans.NamedArg;
import javafx.scene.Node;

@DefaultProperty("view")
public class RouteView<ViewType extends Node & Controller> {
    private Route route;

    public Route getRoute() {
        return route;
    }

    private ViewType view;

    public ViewType getView() {
        return view;
    }

    public void setView(ViewType view) {
        this.view = view;
    }

    public RouteView(@NamedArg("url") String url) {
        this.route = new Route(url);
    }
}
