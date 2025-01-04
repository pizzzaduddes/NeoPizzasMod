package com.pizzaduddes.neopizzasmod.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import com.pizzaduddes.neopizzasmod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;


@EventBusSubscriber(modid = NeoPizzasMod.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ModClientEvents {
    private static final ResourceLocation ABILITY_FRAME = NeoPizzasMod.resloc("textures/gui/abilities/ability_frame.png");

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null) return;
        GuiGraphics guiGraphics = event.getGuiGraphics();

        ItemStack mainHandItem = minecraft.player.getMainHandItem();
        ItemStack offHandItem = minecraft.player.getOffhandItem();

        boolean isHoldingItem = mainHandItem.getItem() == ModItems.FIXED_TEMP_STICK.get() || offHandItem.getItem() == ModItems.FIXED_TEMP_STICK.get();

        int screenWidth = event.getGuiGraphics().guiWidth();
        int screenHeight = event.getGuiGraphics().guiHeight();

        if (isHoldingItem) {
            renderSodAbility(screenWidth, screenHeight, guiGraphics);
        }
    }

    private static void renderSodAbility(int screenWidth, int screenHeight, GuiGraphics guiGraphics) {
        Minecraft minecraft = Minecraft.getInstance();
        int xpos = screenWidth / 64;
        int ypos = screenHeight / 2;


        guiGraphics.blit(ABILITY_FRAME, xpos, ypos - 12, 0, 0, 24, 24, 24, 24);




    }
}
