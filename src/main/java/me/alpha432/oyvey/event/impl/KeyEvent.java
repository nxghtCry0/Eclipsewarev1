package me.nxght.eclipseware.event.impl;

import me.nxght.eclipseware.event.Event;

public class KeyEvent extends Event {
    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
