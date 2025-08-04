package me.nxght.eclipseware.features.modules.player;

import me.nxght.eclipseware.eclipseware;
import me.nxght.eclipseware.features.modules.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Module {
    public NoFall() {
        super("NoFall", "Removes fall damage", Category.PLAYER, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (!mc.player.isOnGround() && eclipseware.positionManager.getFallDistance() > 3) {
            boolean bl = mc.player.horizontalCollision;
            PlayerMoveC2SPacket.Full pakcet = new PlayerMoveC2SPacket.Full(mc.player.getX(), mc.player.getY() + 0.000000001, mc.player.getZ(),
                    mc.player.getYaw(), mc.player.getPitch(), false, bl);
            mc.player.networkHandler.sendPacket(pakcet);
        }
    }
}
