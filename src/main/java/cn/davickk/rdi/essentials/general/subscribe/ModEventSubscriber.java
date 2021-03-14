package cn.davickk.rdi.essentials.general.subscribe;

import net.minecraftforge.fml.config.ModConfig;

//@EventBusSubscriber(modid = RDIEssentials.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {

    //@SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
        /*final ModConfig config = event.getConfig();
        ConfigHelper.bake(config);
        Log.debug("Baked config");*/
    }

}
