package cn.davickk.rdi.essentials.general.subscribe;

import cn.davickk.rdi.essentials.RDIEssentials;
//import cn.davickk.rdi.essentials.general.config.ConfigHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import org.jline.utils.Log;

//@EventBusSubscriber(modid = RDIEssentials.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {

    //@SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
        /*final ModConfig config = event.getConfig();
        ConfigHelper.bake(config);
        Log.debug("Baked config");*/
    }

}
