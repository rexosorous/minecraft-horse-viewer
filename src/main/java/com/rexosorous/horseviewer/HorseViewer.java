package com.rexosorous.horseviewer;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.InputEvent;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraftforge.fml.client.registry.ClientRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("horse-viewer")
public class HorseViewer
{
    public KeyBinding mainKey = new KeyBinding("Main Key", 75, "Horse Viewer");

    public HorseViewer() {
        // Register ourselves for server and other game events we are interested in
        ClientRegistry.registerKeyBinding(mainKey);
        MinecraftForge.EVENT_BUS.register(this);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void getHorseStats(InputEvent.KeyInputEvent event) {
        if (mainKey.isPressed())
        {
            Minecraft mc = Minecraft.getInstance();
            ClientPlayerEntity player = mc.player;
            Entity genericEntity = mc.getRenderManager().pointedEntity;
            if (genericEntity instanceof HorseEntity)
            {
                HorseEntity horse = (HorseEntity) genericEntity;
                double health = horse.getMaxHealth() / 2;
                double speed = horse.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
                double jump = horse.getHorseJumpStrength();
                jump = -0.1817584952 * Math.pow(jump, 3) + 3.689713992 * Math.pow(jump, 2) + 2.128599134 * jump - 0.343930367; // https://minecraft.gamepedia.com/Horse
                speed = speed * 43;
                StringTextComponent msg = new StringTextComponent(String.format("Health: %.0f  Speed: %.1f  Jump: %.1f", health, speed, jump));
                player.sendStatusMessage(msg, true);
            }
        }
    }
}