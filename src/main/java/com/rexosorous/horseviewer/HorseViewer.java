package com.rexosorous.horseviewer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.InputEvent;

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
            
        }
    }
}
