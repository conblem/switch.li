package me.conblem.switchli;

import com.google.common.eventbus.EventBus;

public interface Controller {
    // Will be called before Controller gets loaded
    public void setEventBus(EventBus eventBus);
}
