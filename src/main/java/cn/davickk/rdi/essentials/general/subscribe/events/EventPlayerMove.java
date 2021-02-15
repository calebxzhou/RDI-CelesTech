package cn.davickk.rdi.essentials.general.subscribe.events;


import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.IslandLocation;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.DimensionType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventPlayerMove {
    private static int tickCounter = 0;
    private static float bodyTemp = 36.5f;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onMove(TickEvent.PlayerTickEvent event) {
        PlayerEntity player=event.player;
        if(!player.isCreative())
            if(player.getPosY()<PlayerUtils.LOWEST_LIMIT){
            //if(PlayerUtils.minusXPLvl(player,1)){
                //IslandLocation loca=new IslandLocation(player);
                //loca.y+=200;
            PlayerUtils.teleportPlayer(player,ServerUtils.SPAWN_LOCA);
            TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[我卡住了]","/spawn","修复卡住的问题。");
            //}

             //player.onKillCommand();
            }

        /*try {
            PlayerEntity player = event.player;
            Location loca = new Location(player);
            boolean inWater = player.isWet();
            Entity entity = player.getRidingEntity();
            boolean onBoat = (EntityType.BOAT.equals(entity));
            //System.out.println(onBoat);
            if (tickCounter == 20 * 10) {
                //System.out.println(tickCounter);
                if (onBoat || inWater) {
                    bodyTemp -= 0.05f;
                    TextUtils.sendChatMessage(player, "体温" + bodyTemp);
                    TextUtils.sendChatMessage(player, "群系" + loca.getBiome().getCategory().getString());
                    TextUtils.sendChatMessage(player, "温度1-" + loca.getTemperature());
                    TextUtils.sendChatMessage(player, "温度2-" + loca.getTemperature() * 30);
                    tickCounter = 0;
                } else if (bodyTemp < 36.5)
                    bodyTemp += 0.02f;
                tickCounter = 0;
                //else tickCounter=0;
            } else
                tickCounter++;
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*ServerBossInfo info = new ServerBossInfo
                ( new StringTextComponent("体温"),
                BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20);
        info.addPlayer(player);*/


    }
}
