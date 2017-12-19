package me.conblem.switchli;

import static com.google.common.base.Preconditions.checkNotNull;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * This Screenswitch controller can directly be set on a Scene
 * 
 * @param <ViewEnumType>
 *            Set as ?
 * @param <ContainerType>
 *            Set as ?
 */
public class ScreenSwitch<ViewEnumType extends Enum<ViewEnumType> & ViewEnum, ContainerType extends Node & Container & Controller>
    extends AnchorPane {
    private ContainerType container;
    private final double SPACEFROMANCHOR = 0.0;

    /**
     * @param views
     *            Enum Class that implements the ViewEnum Interface
     * @param container
     *            Node which receives Views, that implements the Container and Controller Interface
     * @param bus
     *            Custom Google Guava EventBus
     */
    public ScreenSwitch(Class<ViewEnumType> views, ContainerType container, EventBus bus, Optional<ResourceBundle> resourceBundle) {
        checkNotNull(views);
        checkNotNull(container);
        checkNotNull(bus);

        bus.register(this);
        this.container = container;
        this.container.setEventBus(bus);
        loadView(this.container, resourceBundle);
        bus.register(this.container);

        this.getChildren().add(this.container);
        AnchorPane.setBottomAnchor(this.container, SPACEFROMANCHOR);
        AnchorPane.setTopAnchor(this.container, SPACEFROMANCHOR);
        AnchorPane.setLeftAnchor(this.container, SPACEFROMANCHOR);
        AnchorPane.setRightAnchor(this.container, SPACEFROMANCHOR);

        Arrays.stream(views.getEnumConstants())
            .map(view -> view.getController())
            .peek(view -> view.setEventBus(bus))
            .peek(view -> loadView(view, resourceBundle))
            .forEach(bus::register);
    }

    /**
     * Loads a view using the FXMLLoader, uses getResource + getSimpleName as path
     * 
     * @param view
     */
    public static void loadView(Object view, Optional<ResourceBundle> resourceBundle) {
        URL filePath = view.getClass().getResource(view.getClass().getSimpleName() + ".fxml");
        FXMLLoader loader = new FXMLLoader(filePath);
        resourceBundle.ifPresent(loader::setResources);
        loader.setRoot(view);
        loader.setController(view);

        try {
            loader.load();
        } catch (IOException e) {
            System.err.println(view.getClass().getSimpleName() + " couldn't be loaded");
            e.printStackTrace();
        }
        catch (IllegalStateException e) {
            System.err.println(view.getClass().getSimpleName() + " controller file not found");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Subscribe
    public <ViewType extends Node & Controller> void screenSwitch(SwitchEvent<ViewEnumType> event) {
        Platform.runLater(() -> {
            try {
                this.container.setView((ViewType) event.getViewEnum().getController());
            } catch (ClassCastException e) {
                String viewName = event.getViewEnum().getController().getClass().getSimpleName();
                System.err.println(viewName + " does not extend Node or any of it's Sub Classes");
                e.printStackTrace();
            }
        });
    }
}
