package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
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
        if(event.getEntityLiving().getType().equals(EntityType.PLAYER))
            return;
        if(event.getEntityLiving().getType().equals(EntityType.SLIME)
                || event.getEntityLiving().getDisplayName().getString().contains("slime"))
            event.setCanceled(true);
        if(event.getEntityLiving().getEntity().getPosY()< PlayerUtils.LOWEST_LIMIT){
            event.setCanceled(true);
        }

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
                            "�ճ�����С��������̫���ˣ����ٴγ���"));
                }else{
                    event.getChild().onKillCommand();
                    TextUtils.sendChatMessage(player, new StringTextComponent(
                            "�ճ�����" + animalType + "����̫���ˣ����ٴγ���"));
                }
            } else if (ran < 7 && ran >= 4) {
                event.setCanceled(true);
                TextUtils.sendChatMessage(player, new StringTextComponent(
                        "����ֻ" + animalType + "��ʱû�����������Ժ�����"));
            } else
                player.setExperienceLevel(player.experienceLevel+1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}