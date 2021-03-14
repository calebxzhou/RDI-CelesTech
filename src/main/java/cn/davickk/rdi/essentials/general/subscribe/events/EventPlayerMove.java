package cn.davickk.rdi.essentials.general.subscribe.events;


import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventPlayerMove {
    private static int tickCounter = 0;
    private static float bodyTemp = 36.5f;
    @SubscribeEvent
    public static void onNether(PlayerEvent.PlayerChangedDimensionEvent event){

        PlayerEntity player=event.getPlayer();
        RegistryKey<World> world=event.getTo();
        String locaw=world.getLocation().toString();
        System.out.println("前往"+ locaw);
        if(locaw.contains("nether")){
            player.setGameType(GameType.ADVENTURE);
            TextUtils.sendChatMessage(player,"感觉身体沉甸甸的，做什么都没有力气.....");
        }else if(locaw.contains("overworld")){
            player.setGameType(GameType.SURVIVAL);
        }
    }
    @SubscribeEvent
    public static void onMove(TickEvent.PlayerTickEvent event) {
        PlayerEntity player=event.player;
        if(!player.isCreative()){
            if(player.getPosY()<PlayerUtils.LOWEST_LIMIT){
            //if(PlayerUtils.minusXPLvl(player,1)){
                //IslandLocation loca=new IslandLocation(player);
                //loca.y+=200;
                Location loca=new Location(player);
                loca.setY(240);
                TextUtils.sendChatMessage(player,"感觉身体轻飘飘的.....");
                PlayerUtils.teleportPlayer(player,loca);
                player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING,10*20,2));
                TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[我卡住了]","/spawn","修复卡住的问题。");
            //}

             //player.onKillCommand();
            }
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
