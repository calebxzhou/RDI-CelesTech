package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.WeatherUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.GameType;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventPlayerLoggedIn {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        PlayerUtils.teleportPlayer(player, ServerUtils.SPAWN_LOCA);
        String ip = player.getPlayerIP();
        PlayerUtils.sayHello(player);
        WeatherUtils.sendWeatherToPlayer(ip, player);
        player.setGameType(GameType.SURVIVAL);
        //TextUtils.sendChatMessage(player,new StringTextComponent(WeatherUtils.getFormattedWeatherFromIP(ip,1)));
        //重新设置出生点到主城
        player.func_242111_a(player.world.OVERWORLD, ServerUtils.SPAWN_BLKPS, 0f, true, false);
        /*EssentialPlayer eslPlayer = DataManager.newPlayer(player);
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //Date date= Calendar.getInstance().getTime();

        if (eslPlayer != null) {
            LogUtils.debug("New player " + player.getDisplayName().getString() + " joined");

            //if (player.getServer().isDedicatedServer()) {
                // Add variable spawn force new
                //Location spawnLocation = DataManager.getWorld().getSpawn();
               // if (spawnLocation != null) {
                    //TeleportUtils.doTeleport(player, spawnLocation, true, false);
                //}
          //  }

            if (ModConfig.kits_starting) {
                Kit kit = DataManager.getKit().getKit(ModConfig.kits_starting_name);
                if (ModUtils.giveKit(player, kit)) {
                    TextUtils.sendChatMessage(player,new StringTextComponent("新人礼包已发送"));
                    //BankUtils.createAccount(player);
                    eslPlayer.getUsage().setKitUssage(ModConfig.kits_starting_name);
                    eslPlayer.saveData();
                }
            }
        } else {
            EssentialPlayer eslPlayerExisted = DataManager.getPlayer(player);
            PlayerRestriction ban = eslPlayerExisted.getRestrictions().getBan();
            if (ban != null) {
                if (ban.getTime() == -1) {
                    player.connection.disconnect(new TranslationTextComponent("tempban.rdi-essentials.success.perm.target", player.getDisplayName(), ban.getReason()));
                } else {
                   if (ban.getTime() > currentTimestamp()) {
                       String displayTime = TimeUtils.formatDate(ban.getTime() - currentTimestamp());
                       player.connection.disconnect(new TranslationTextComponent("tempban.rdi-essentials.success.target", player.getDisplayName(), displayTime, ban.getReason()));
                   }
                }
            }

            PlayerData playerData = eslPlayerExisted.getData();
            //PlayerUtils.setFlying(player, playerData.getFlyEnabled());
            //PlayerUtils.setGod(player, playerData.getGodEnabled());

            LogUtils.debug("Player " + player.getDisplayName().getString() + " joined");
        }

        EssentialPlayer eslPlayerNew = DataManager.getPlayer(player);
        eslPlayerNew.setUsername(player.getDisplayName().getString());
        eslPlayerNew.saveData();*/
    }
}