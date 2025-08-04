package me.nxght.eclipseware.features.commands.impl;

import me.nxght.eclipseware.eclipseware;
import me.nxght.eclipseware.features.commands.Command;
import me.nxght.eclipseware.features.modules.Module;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("toggle", new String[]{"<module>"});
    }

    @Override
    public void execute(String[] var1) {
        if (var1.length < 1 || var1[0] == null) {
            notFound();
            return;
        }
        Module mod = eclipseware.moduleManager.getModuleByName(var1[0]);
        if (mod == null) {
            notFound();
            return;
        }
        mod.toggle();
    }

    private void notFound() {
        sendMessage("Module is not found.");
    }
}
