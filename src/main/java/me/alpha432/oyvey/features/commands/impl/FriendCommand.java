package me.nxght.eclipseware.features.commands.impl;

import me.nxght.eclipseware.eclipseware;
import me.nxght.eclipseware.features.commands.Command;
import net.minecraft.util.Formatting;

public class FriendCommand
        extends Command {
    public FriendCommand() {
        super("friend", new String[]{"<add/del/name/clear>", "<name>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            if (eclipseware.friendManager.getFriends().isEmpty()) {
                FriendCommand.sendMessage("Friend list empty D:.");
            } else {
                StringBuilder f = new StringBuilder("Friends: ");
                for (String friend : eclipseware.friendManager.getFriends()) {
                    try {
                        f.append(friend).append(", ");
                    } catch (Exception exception) {
                    }
                }
                FriendCommand.sendMessage(f.toString());
            }
            return;
        }
        if (commands.length == 2) {
            if (commands[0].equals("reset")) {
                eclipseware.friendManager.getFriends().clear();
                FriendCommand.sendMessage("Friends got reset.");
                return;
            }
            FriendCommand.sendMessage(commands[0] + (eclipseware.friendManager.isFriend(commands[0]) ? " is friended." : " isn't friended."));
            return;
        }
        if (commands.length >= 2) {
            switch (commands[0]) {
                case "add" -> {
                    eclipseware.friendManager.addFriend(commands[1]);
                    FriendCommand.sendMessage("{aqua} %s has been friended", commands[1]);
                    return;
                }
                case "del", "remove" -> {
                    eclipseware.friendManager.removeFriend(commands[1]);
                    FriendCommand.sendMessage("{red} %s has been unfriended", commands[1]);
                    return;
                }
            }
            FriendCommand.sendMessage("Unknown Command, try friend add/del (name)");
        }
    }
}