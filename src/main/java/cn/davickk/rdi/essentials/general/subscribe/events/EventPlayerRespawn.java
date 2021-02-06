package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventPlayerRespawn {

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        PlayerUtils.teleportPlayer(player, ServerUtils.SPAWN_LOCA);
        //TextUtils.sendChatMessage(player,"���ղſ��ܵ�����һЩ��Ʒ��");
        //TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[�ָ���Ʒ]","/getfromvoid","����1���������лָ���Ʒ��");
        //�������ó����㵽����
        player.func_242111_a(player.world.getDimensionKey(), ServerUtils.SPAWN_BLKPS, 0f, true, false);
        /*if (ModConfig.spawn_force_on_death) {

            Location location = DataManager.getWorld().getSpawn();
           //TeleportUtils.doTeleport(player, location, true, false);
        }*/
    }
}
