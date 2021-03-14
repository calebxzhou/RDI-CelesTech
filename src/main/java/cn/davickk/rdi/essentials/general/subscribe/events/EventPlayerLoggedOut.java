package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventPlayerLoggedOut {

    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        event.getPlayer().getServer().save(false, false, true);
        /*ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        EssentialPlayer eslPlayer = DataManager.getPlayer(player);
        eslPlayer.cleanTemp();
        LogUtils.debug("Player " + player.getDisplayName().getString() + " leave");*/
    }
}
