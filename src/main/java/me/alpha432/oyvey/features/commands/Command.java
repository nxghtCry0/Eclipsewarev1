package me.nxght.eclipseware.features.commands;

import me.nxght.eclipseware.eclipseware;
import me.nxght.eclipseware.features.Feature;
import me.nxght.eclipseware.util.TextUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


public abstract class Command
        extends Feature {
    protected String name;
    protected String[] commands;

    public Command(String name) {
        super(name);
        this.name = name;
        this.commands = new String[]{""};
    }

    public Command(String name, String[] commands) {
        super(name);
        this.name = name;
        this.commands = commands;
    }

    public static void sendMessage(String message, Object... obj) {
        sendMessage(TextUtil.text(message, obj));
    }

    public static void sendMessage(Text message) {
        MutableText text = Text.empty();
        text.append(eclipseware.commandManager.getClientMessage() + " " + Formatting.GRAY);
        text.append(message);
        Command.sendSilentMessage(text);
    }

    public static void sendSilentMessage(Text message) {
        if (Command.nullCheck()) {
            return;
        }
        // TODO add silent support ig
        mc.inGameHud.getChatHud().addMessage(message);
    }

    public static String getCommandPrefix() {
        return eclipseware.commandManager.getPrefix();
    }

    public abstract void execute(String[] var1);

    @Override
    public String getName() {
        return this.name;
    }

    public String[] getCommands() {
        return this.commands;
    }
}