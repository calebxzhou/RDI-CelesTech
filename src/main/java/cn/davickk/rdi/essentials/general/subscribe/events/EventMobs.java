package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventMobs {
    @SubscribeEvent
    public static void mobSpawn(LivingSpawnEvent.SpecialSpawn event){
        if(event.getEntityLiving().getType().equals(EntityType.SLIME)
        || event.getEntityLiving().getDisplayName().getString().contains("slime"))
            if(!(event.getEntityLiving().getType().equals(EntityType.PLAYER)))
            event.setCanceled(true);
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
