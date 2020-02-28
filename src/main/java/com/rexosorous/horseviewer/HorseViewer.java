package com.rexosorous.horseviewer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.util.util.math.EntityRayTraceResult;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("horse-viewer")
public class HorseViewer
{
    public HorseViewer() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void getHorseStats(InputEvent.KeyInputEvent event) {
        if (event.getKey() == 220)
        {
            genericEntity = EntityRayTraceResult.getEntity()
            if (genericEntity instanceof HorseEntity)
            {
                ClientPlayerEntity player = Minecraft.getInstance().player;
                HorseEntity horse = genericEntity
                double health = horse.getModifiedMaxHealth();
                double speed = horse.getModifiedMovementSpeed();
                double jump = horse.getModifiedJumpStrength();
                // jump = -0.1817584952 * Math.pow(jump, 3) + 3.689713992 * Math.pow(jump, 2) + 2.128599134 * jump - 0.343930367; // https://minecraft.gamepedia.com/Horse
                // speed = speed * 43;
                StringTextComponent msg = StringTextComponent(string.format("Health: %.0f Speed: %.1f Jump: %.1f", health, speed, jump));
                player.sendStatusMessage(msg, true);
            }
        }
    }
}