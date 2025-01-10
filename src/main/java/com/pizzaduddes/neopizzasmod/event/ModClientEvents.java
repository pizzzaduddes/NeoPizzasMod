package com.pizzaduddes.neopizzasmod.event;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.pizzaduddes.neopizzasmod.NeoPizzasMod;
import com.pizzaduddes.neopizzasmod.item.ModItems;
import com.pizzaduddes.neopizzasmod.item.custom.SwordOfDarknessItem;
import com.pizzaduddes.neopizzasmod.mob_effects.ModMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;


@EventBusSubscriber(modid = NeoPizzasMod.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ModClientEvents {
    private static final ResourceLocation ABILITY_FRAME = NeoPizzasMod.resloc("textures/gui/abilities/ability_frame.png");
    private static final ResourceLocation INTO_THE_SHADOWS_ABILITY = NeoPizzasMod.resloc("textures/gui/abilities/into_the_shadows.png");
    private static final ResourceLocation ITS_COOLDOWN = NeoPizzasMod.resloc("textures/gui/abilities/cooldowns/its_cooldown.png");

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (player == null) return;
        GuiGraphics guiGraphics = event.getGuiGraphics();

        ItemStack mainHandItem = minecraft.player.getMainHandItem();
        ItemStack offHandItem = minecraft.player.getOffhandItem();

        boolean isHoldingItem = mainHandItem.getItem() == ModItems.SWORD_OF_DARKNESS.get() || offHandItem.getItem() == ModItems.SWORD_OF_DARKNESS.get();

        int screenWidth = event.getGuiGraphics().guiWidth();
        int screenHeight = event.getGuiGraphics().guiHeight();

        if (isHoldingItem) {
            renderItsAbility(screenWidth, screenHeight, guiGraphics, player);
        }
    }

    private static void renderAbilityFrame(int yOffset, int screenWidth, int screenHeight, GuiGraphics guiGraphics) {
        int xpos = screenWidth / 64;
        int ypos = ((screenHeight / 2) - 12) + yOffset;

        guiGraphics.blit(ABILITY_FRAME, xpos, ypos, 0, 0, 24, 24, 24, 24);
    }

    private static void renderItsAbility(int screenWidth, int screenHeight, GuiGraphics guiGraphics, Player player) {
        Minecraft minecraft = Minecraft.getInstance();
        int xpos = screenWidth / 64;
        int ypos = (screenHeight / 2) - 12;

        renderAbilityFrame(0, screenWidth, screenHeight, guiGraphics);
        guiGraphics.blit(INTO_THE_SHADOWS_ABILITY, xpos, ypos, 0, 0, 24, 24, 24, 24);

        if (SwordOfDarknessItem.getCooldown()) {
            byte maxCooldownWidth = 20;
            byte maxCooldownHeight = 20;
            float remainingCooldown = SwordOfDarknessItem.getRemainingCooldown(player);
            byte cooldownTexWidth = (byte) ((maxCooldownWidth * (remainingCooldown)));
            byte cooldownTexHeight = (byte) ((maxCooldownHeight * (remainingCooldown)));
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderTexture(0, ITS_COOLDOWN);
            guiGraphics.blit(ITS_COOLDOWN, xpos + 2, ypos + 2, 0, 0, cooldownTexWidth, cooldownTexHeight, maxCooldownWidth, maxCooldownHeight);
        }
    }


    @SubscribeEvent
    public static void onRenderLiving(RenderLivingEvent.Pre<?, ?> event) {
        if (event.getEntity() instanceof Player player && player.hasEffect(ModMobEffects.INCONCEIVABLE)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onChangeAttackTarget(LivingChangeTargetEvent event) {
        LivingEntity mob = event.getEntity();
        LivingEntity target = event.getNewTarget();

        if (target instanceof Player player && player.hasEffect(ModMobEffects.INCONCEIVABLE)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onEffectChange(MobEffectEvent.Added event) {
        MobEffectInstance effect = event.getEffectInstance();
        if (effect.getEffect() == ModMobEffects.INCONCEIVABLE) {
            LivingEntity entity = event.getEntity();
            if (entity instanceof Player player) {
               Level level = player.level();
               clearTargetsFromMobs(level, player);
            }
        }
    }

    private static void clearTargetsFromMobs(Level level, Player player) {
        for (Mob mob : level.getEntitiesOfClass(Mob.class, player.getBoundingBox().inflate(50))) {
            if (mob.getTarget() == player) {
                mob.setTarget(null);
            }
        }
    }
}
