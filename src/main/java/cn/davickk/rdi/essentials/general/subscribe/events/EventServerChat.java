package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.ServerChatEvent;


//@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventServerChat {

    //@SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerChat(ServerChatEvent event) {
        /*ServerPlayerEntity player = event.getPlayer();
        EssentialPlayer eslPlayer = DataManager.getPlayer(player);
        PlayerRestriction mute = eslPlayer.getRestrictions().getMute();
        if (mute != null) {
            if (mute.getTime() == -1 || mute.getTime() > currentTimestamp()) {
                if (mute.getTime() == -1) {
                    TextUtils.sendChatMessage(player, "mute.rdi-essentials.success.perm.target");
                } else {
                    String displayTime = TimeUtils.formatDate(mute.getTime() - currentTimestamp());
                    TextUtils.sendChatMessage(player, "mute.rdi-essentials.success.target", displayTime);
                }
                TextUtils.sendChatMessage(player, "mute.rdi-essentials.success.target.reason", mute.getReason());
                event.setCanceled(true);
            }
        }*/
    }
}
