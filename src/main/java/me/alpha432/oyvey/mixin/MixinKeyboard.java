package me.nxght.eclipseware.mixin;

import me.nxght.eclipseware.event.impl.KeyEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.nxght.eclipseware.util.traits.Util.EVENT_BUS;

@Mixin(Keyboard.class)
public class MixinKeyboard {
    @Inject(method = "onKey", at = @At("TAIL"), cancellable = true)
    private void onKey(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
        if (action != 1) return;
        KeyEvent event = new KeyEvent(key);
        EVENT_BUS.post(event);
        if (event.isCancelled()) ci.cancel();
    }
}
