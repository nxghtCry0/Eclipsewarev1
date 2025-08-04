package me.nxght.eclipseware.event.impl;

import me.nxght.eclipseware.event.Event;

public class ChatEvent extends Event {
    private final String content;

    public ChatEvent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return content;
    }
}
