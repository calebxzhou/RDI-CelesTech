package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.thread.server.SQLReconnT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventWorldTick {

    private static int tickCounter = 0;

    @SubscribeEvent
    public static void worldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
           /*if(tickCounter==10*60*20)//10min
           {
               List<TileEntity> loadedTickEntityList = event.world.loadedTileEntityList;
               List<TileEntity> tickableTileEntities = event.world.tickableTileEntities;
               event.world.enti
               tickCounter=0;
           }else tickCounter++;*/
            int min20 = 25 * 60 * 20 * 5;//about 22 minutes
            int sec30 = 30 * 20 * 5;
            int min5 = 25*60*5*5;
            int min10 = 25*60*5*5*2;
            //int sec10=10*20;
            if(tickCounter==min5){
                PlayerList playerList = event.world.getServer().getPlayerList();
                TextUtils.sendGlobalChatMessage(playerList, "提示：机械动力(Create)可以实现前期大多数自动化，这是1.16的独有特性。");
                ServerUtils.startThread(new SQLReconnT());
            }else tickCounter++;
            if (tickCounter == min10) {
                PlayerList playerList = event.world.getServer().getPlayerList();
                TextUtils.sendGlobalChatMessage(playerList, "提示：中期您可以选择[机械动力(Create)]作为额外分支。");
            } else {
                tickCounter++;
            }
            if (tickCounter == min20) {
                PlayerList playerList = event.world.getServer().getPlayerList();
                TextUtils.sendGlobalChatMessage(playerList, "15秒后清除掉落物，请拾起重要物品");
            } else {
                tickCounter++;
            }
            if (tickCounter == min20 + sec30) {
                PlayerList playerList = event.world.getServer().getPlayerList();
                TextUtils.sendGlobalChatMessage(playerList, "已清理掉落物");
                TextUtils.sendGlobalChatMessage(playerList, "提示：输入指令/SAVE可以手动存档，避免回档");
                MinecraftServer mcs = event.world.getServer();
                mcs.getCommandManager().handleCommand(mcs.getCommandSource(), "kill @e[type=item]");
                mcs.save(false, false, true);
                tickCounter = 0;
            } else {
                tickCounter++;
            }

        }
        /*if (event.phase == TickEvent.Phase.END){

        }*/

    }
/*
    public static void checkAfk(PlayerList players) {
        try {
            if (players != null && players.getPlayers().size() > 0) {
                for (int i = players.getPlayers().size() - 1; i >= 0; i--) {
                    ServerPlayerEntity player = players.getPlayers().get(i);
                    if (player != null) {
                        EssentialPlayer eslPlayer = DataManager.getPlayer(player);
                        Location playerLocation = new Location(player);
                        Location lastLocation = eslPlayer.getTemp().getLocation();
                        if (lastLocation != null && checkDetailedLocation(playerLocation, lastLocation)) {
                            if (!eslPlayer.getTemp().isAfk()) {
                                if (currentTimestamp() - ModConfig.afk_auto_time >= eslPlayer.getTemp().getLastMoveTime()) {
                                    TextUtils.sendGlobalChatMessage(players, "afk.rdi-essentials.afk.true", player.getDisplayName());
                                    eslPlayer.getTemp().setAfk(true);
                                }
                            } else {
                                if (ModConfig.afk_auto_kick != 0 && currentTimestamp() - ModConfig.afk_auto_kick >= eslPlayer.getTemp().getLastMoveTime()) {
//                                    kickPlayer(player, new TranslationTextComponent("kick.rdi-essentials.server"), new TranslationTextComponent("kick.rdi-essentials.player").getFormattedText());
                                    kickPlayer(player, new TranslationTextComponent("kick.rdi-essentials.server"), new TranslationTextComponent("kick.rdi-essentials.player").getString());
                                }
                            }
                        } else {
                            if (eslPlayer.getTemp().isAfk()) {
                                //TextUtils.sendGlobalChatMessage(players, "afk.rdi-essentials.afk.false", player.getDisplayName());
                                eslPlayer.getTemp().setAfk(false);
                            }
                            eslPlayer.getTemp().setLocation(playerLocation);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("checkAfk error:");
            System.out.println(e);
        }
    }*/
}
