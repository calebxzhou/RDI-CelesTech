package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventMobs {
    @SubscribeEvent(priority = EventPriority.HIGHEST)//LivingSpawnEvent.AllowDespawn
    public static void mobSpawn(EntityJoinWorldEvent event){
        if(event.getEntity().getType().equals(EntityType.PLAYER))
            return;
        if(event.getEntity().getEntity().getPosY()< PlayerUtils.LOWEST_LIMIT){
            event.setCanceled(true);
            return;
            //event.setResult(Event.Result.ALLOW);
            //event.getEntity().remove(false);
        }
        /*Entity entity=event.getEntity();
        if(entity.getEntityWorld().getDimensionKey().getRegistryName().getPath().contains("nether"))
        if(entity.getType().getTranslationKey().equals("entity.minecraft.item")) {
            ItemEntity itemEntity=(ItemEntity) entity;
            System.out.println(itemEntity.getItem().getTranslationKey());
            if(itemEntity.getItem().getTranslationKey().contains("netherrack"))
                entity.remove();
        }*/
        /*if(event.getEntityLiving().getType().equals(EntityType.SLIME)
                || event.getEntityLiving().getDisplayName().getString().contains("slime"))
            event.setCanceled(true);*/

    }
    @SubscribeEvent
    public static void babySpawn(BabyEntitySpawnEvent event) {
        try {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getCausedByPlayer();
            int ran = RandomUtils.generateRandomInt(0, 9);
            if(event.getParentA()==null || event.getParentB()==null)
            {
                event.setCanceled(true);
                return;
            }
            String animalType = event.getParentA().getDisplayName().getString();
            if(animalType==null) return;
            if (ran >= 7) {
                if (event.getChild() == null) {
                    TextUtils.sendChatMessage(player, new StringTextComponent(
                            "刚出生的小动物身体太弱了，请再次尝试"));
                }else{
                    event.getChild().onKillCommand();
                    TextUtils.sendChatMessage(player, new StringTextComponent(
                            "刚出生的" + animalType + "身体太弱了，请再次尝试"));
                }
            } else if (ran < 7 && ran >= 4) {
                event.setCanceled(true);
                TextUtils.sendChatMessage(player, new StringTextComponent(
                        "这两只" + animalType + "暂时没有性欲，请稍后再试"));
            } else
                player.setExperienceLevel(player.experienceLevel+1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
