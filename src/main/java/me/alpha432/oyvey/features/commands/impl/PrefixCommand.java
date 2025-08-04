package me.nxght.eclipseware.features.commands.impl;

import me.nxght.eclipseware.eclipseware;
import me.nxght.eclipseware.features.commands.Command;
import net.minecraft.util.Formatting;

public class PrefixCommand
        extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage("{green} Current prefix is %s ", eclipseware.commandManager.getPrefix());
            return;
        }
        eclipseware.commandManager.setPrefix(commands[0]);
        Command.sendMessage("Prefix changed to {gray} %s", commands[0]);
    }
}