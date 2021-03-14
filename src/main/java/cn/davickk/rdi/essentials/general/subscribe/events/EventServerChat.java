package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.thread.player.ChatRecordT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventServerChat {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerChat(ServerChatEvent event) {
        ServerPlayerEntity player = event.getPlayer();
        String chat=event.getComponent().getString();
        ServerUtils.startThread(new ChatRecordT(player,chat));
    }

    /*@SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerChat2(ServerChatEvent event) {
        ServerPlayerEntity player = event.getPlayer();
        String chat=event.getComponent().getString();
        ServerUtils.startThread(new ChatRecordT(player,chat));
    }*/

}
