package me.conblem.switchli;

import org.bigtesting.routd.TreeRouter;
import javafx.beans.DefaultProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

@DefaultProperty("container")
public class NewScreenSwitch<ContainerType extends Node & Container & Controller> extends AnchorPane {
    private final TreeRouter router = new TreeRouter();

    private final ObservableList<RouteView<?>> routes = FXCollections.observableArrayList();

    public ObservableList<RouteView<?>> getRoutes() {
        return routes;
    }

    private ContainerType container;

    public ContainerType getContainer() {
        return container;
    }

    public void setContainer(ContainerType container) {
        System.out.println("test");
        System.out.println(container);
        this.container = container;
    }

    private ListChangeListener<RouteView<?>> routesChangeListener = event -> {
        event.getAddedSubList()
            .stream()
            .map(routerView -> routerView.getRoute())
            .forEach(router::add);
    };

    public NewScreenSwitch() {
        routes.addListener(routesChangeListener);
    }
}
