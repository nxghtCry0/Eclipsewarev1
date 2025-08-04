package me.nxght.eclipseware.manager;

import com.google.common.eventbus.Subscribe;
import me.nxght.eclipseware.eclipseware;
import me.nxght.eclipseware.event.Stage;
import me.nxght.eclipseware.event.impl.*;
import me.nxght.eclipseware.features.Feature;
import me.nxght.eclipseware.features.commands.Command;
import me.nxght.eclipseware.util.models.Timer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.Formatting;

public class EventManager extends Feature {
    private final Timer logoutTimer = new Timer();

    public void init() {
        EVENT_BUS.register(this);
    }

    public void onUnload() {
        EVENT_BUS.unregister(this);
    }

    @Subscribe
    public void onUpdate(UpdateEvent event) {
        mc.getWindow().setTitle("eclipseware 0.0.3");
        if (!fullNullCheck()) {
//            eclipseware.inventoryManager.update();
            eclipseware.moduleManager.onUpdate();
            eclipseware.moduleManager.sortModules(true);
            onTick();
//            if ((HUD.getInstance()).renderingMode.getValue() == HUD.RenderingMode.Length) {
//                eclipseware.moduleManager.sortModules(true);
//            } else {
//                eclipseware.moduleManager.sortModulesABC();
//            }
        }
    }

    public void onTick() {
        if (fullNullCheck())
            return;
        eclipseware.moduleManager.onTick();
        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == null || player.getHealth() > 0.0F)
                continue;
            EVENT_BUS.post(new DeathEvent(player));
//            PopCounter.getInstance().onDeath(player);
        }
    }

    @Subscribe
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
        if (fullNullCheck())
            return;
        if (event.getStage() == Stage.PRE) {
            eclipseware.speedManager.updateValues();
            eclipseware.rotationManager.updateRotations();
            eclipseware.positionManager.updatePosition();
        }
        if (event.getStage() == Stage.POST) {
            eclipseware.rotationManager.restoreRotations();
            eclipseware.positionManager.restorePosition();
        }
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        eclipseware.serverManager.onPacketReceived();
        if (event.getPacket() instanceof WorldTimeUpdateS2CPacket)
            eclipseware.serverManager.update();
    }

    @Subscribe
    public void onWorldRender(Render3DEvent event) {
        eclipseware.moduleManager.onRender3D(event);
    }

    @Subscribe
    public void onRenderGameOverlayEvent(Render2DEvent event) {
        eclipseware.moduleManager.onRender2D(event);
    }

    @Subscribe
    public void onKeyInput(KeyEvent event) {
        eclipseware.moduleManager.onKeyPressed(event.getKey());
    }

    @Subscribe
    public void onChatSent(ChatEvent event) {
        if (event.getMessage().startsWith(Command.getCommandPrefix())) {
            event.cancel();
            try {
                if (event.getMessage().length() > 1) {
                    eclipseware.commandManager.executeCommand(event.getMessage().substring(Command.getCommandPrefix().length() - 1));
                } else {
                    Command.sendMessage("Please enter a command.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendMessage(Formatting.RED + "An error occurred while running this command. Check the log!");
            }
        }
    }
}