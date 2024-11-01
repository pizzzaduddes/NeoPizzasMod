package com.pizzaduddes.neopizzasmod.shaders.renderer;

import com.google.common.eventbus.Subscribe;
import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import net.minecraft.client.renderer.ShaderInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

@EventBusSubscriber(modid = NeoPizzasMod.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ExpandingCubeRenderer {

    @Subscribe
    public static void onRegisterShaderEvent(RegisterShadersEvent event) {
        event.registerShader();
    }

}
