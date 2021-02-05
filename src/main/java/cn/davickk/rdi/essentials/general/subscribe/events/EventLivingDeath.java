package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EBack;
import cn.davickk.rdi.essentials.general.enums.EDeathItemReq;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.thread.death.DeathItemT;
import cn.davickk.rdi.essentials.general.thread.home.DelhomeThread;
import cn.davickk.rdi.essentials.general.thread.player.BackThread;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventLivingDeath {

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) throws SQLException, ClassNotFoundException {
        if (!(event.getEntity() instanceof PlayerEntity))
            return;
        ServerPlayerEntity player=(ServerPlayerEntity) event.getEntity();
        //ServerUtils.startThread(new BackThread(player, EBack.RECORD));
        ServerUtils.startThread(new DeathItemT(player, EDeathItemReq.RECORD));

    }
}
