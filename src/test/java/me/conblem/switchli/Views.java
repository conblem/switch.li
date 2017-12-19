package me.conblem.switchli;

import me.conblem.switchli.Controller;
import me.conblem.switchli.ViewEnum;
import me.conblem.switchli.controller.Home;
import me.conblem.switchli.controller.Settings;

public enum Views implements ViewEnum {
    HOME(new Home()),
    SETTINGS(new Settings());

    private Controller controller;

    private Views(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

}
