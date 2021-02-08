package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.thread.player.ChatRecordT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
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
        String pname=player.getDisplayName().getString();
        ServerUtils.startThread(new ChatRecordT(player,chat));
        /*EssentialPlayer eslPlayer = DataManager.getPlayer(player);
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
