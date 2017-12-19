package me.conblem.switchli;

import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.conblem.switchli.controller.NewHome;

public class NewMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        NewHome newHome = new NewHome();
        ScreenSwitch.loadView(newHome, Optional.empty());
        Scene scene = new Scene(newHome);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
