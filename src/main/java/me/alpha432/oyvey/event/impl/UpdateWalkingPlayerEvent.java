package me.nxght.eclipseware.event.impl;

import me.nxght.eclipseware.event.Event;
import me.nxght.eclipseware.event.Stage;

public class UpdateWalkingPlayerEvent extends Event {
    private final Stage stage;

    public UpdateWalkingPlayerEvent(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
