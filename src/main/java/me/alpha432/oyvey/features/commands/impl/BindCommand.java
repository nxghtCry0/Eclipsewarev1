package me.nxght.eclipseware.features.commands.impl;

import com.google.common.eventbus.Subscribe;
import me.nxght.eclipseware.eclipseware;
import me.nxght.eclipseware.event.impl.KeyEvent;
import me.nxght.eclipseware.features.commands.Command;
import me.nxght.eclipseware.features.modules.Module;
import me.nxght.eclipseware.features.settings.Bind;
import me.nxght.eclipseware.util.KeyboardUtil;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class BindCommand
        extends Command {
    private boolean listening;
    private Module module;

    public BindCommand() {
        super("bind", new String[]{"<module>"});
        EVENT_BUS.register(this);
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            sendMessage("Please specify a module.");
            return;
        }
        String moduleName = commands[0];
        Module module = eclipseware.moduleManager.getModuleByName(moduleName);
        if (module == null) {
            sendMessage("Unknown module '%s'!", moduleName);
            return;
        }

        sendMessage("{gray} Press a key.");
        listening = true;
        this.module = module;
    }

    @Subscribe
    private void onKey(KeyEvent event) {
        if (nullCheck() || !listening) return;
        listening = false;
        if (event.getKey() == GLFW.GLFW_KEY_ESCAPE) {
            sendMessage(Formatting.GRAY + "Operation cancelled.");
            return;
        }

        String key = KeyboardUtil.getKeyName(event.getKey());
        sendMessage("Bind for {green} %s {} set to {gray} %s", module.getName(), key);
        module.bind.setValue(new Bind(event.getKey()));
    }

}