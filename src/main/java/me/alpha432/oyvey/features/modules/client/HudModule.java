package me.nxght.eclipseware.features.modules.client;

import me.nxght.eclipseware.eclipseware;
import me.nxght.eclipseware.event.impl.Render2DEvent;
import me.nxght.eclipseware.features.modules.Module;
import me.nxght.eclipseware.util.TextUtil;

public class HudModule extends Module {
    public HudModule() {
        super("Hud", "hud", Category.CLIENT, true, false, false);
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                TextUtil.text("{global} %s {} %s", eclipseware.NAME, eclipseware.VERSION),
                2, 2,
                -1
        );
    }
}
