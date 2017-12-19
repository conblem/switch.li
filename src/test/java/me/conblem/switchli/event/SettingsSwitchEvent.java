package me.conblem.switchli.event;

import me.conblem.switchli.SwitchEvent;
import me.conblem.switchli.Views;

public class SettingsSwitchEvent implements SwitchEvent<Views> {

    @Override
    public Views getViewEnum() {
        return Views.SETTINGS;
    }

}
