package me.conblem.switchli;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.loadui.testfx.controls.impl.VisibleNodesMatcher.visible;
import java.util.Optional;
import java.util.concurrent.Executors;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import com.google.common.eventbus.AsyncEventBus;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import me.conblem.switchli.ScreenSwitch;
import me.conblem.switchli.controller.LoadMe;
import me.conblem.switchli.controller.MainContainer;
import me.conblem.switchli.event.HomeSwitchEvent;
import me.conblem.switchli.event.SettingsSwitchEvent;

public class ScreenSwitchTest extends GuiTest {
    private AsyncEventBus eventBus = new AsyncEventBus(Executors.newSingleThreadExecutor());

    @Override
    protected Parent getRootNode() {
        return new ScreenSwitch<>(Views.class, new MainContainer(), eventBus, Optional.empty());
    }

    @Test
    public void shouldSwitchBetweenViews() {
        eventBus.post(new HomeSwitchEvent());
        waitUntil("#lblHome", is(visible()));
        Label lblHome = find("#lblHome");
        assertEquals("Home", lblHome.getText());

        eventBus.post(new SettingsSwitchEvent());
        waitUntil("#lblSettings", is(visible()));
        Label lblSettings = find("#lblSettings");
        assertEquals("Settings", lblSettings.getText());
    }

    @Test
    public void shouldHaveMainContainer() {
        Label lblMainContainer = find("#lblMainContainer");
        assertEquals("This is the MainContainer", lblMainContainer.getText());
    }

    @Test
    public void shouldLoadView() {
        LoadMe loadMe = new LoadMe();
        ScreenSwitch.loadView(loadMe, Optional.empty());

        Label lblLoadMe = (Label) loadMe.getChildren().get(0);
        assertEquals("Has Loaded", lblLoadMe.getText());
    }
}
