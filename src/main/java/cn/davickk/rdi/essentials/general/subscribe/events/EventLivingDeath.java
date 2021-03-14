package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.sql.SQLException;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventLivingDeath {

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) throws SQLException, ClassNotFoundException {
        if (!(event.getEntity() instanceof PlayerEntity))
            return;
        PlayerEntity player=(PlayerEntity) event.getEntity();
        //ServerUtils.startThread(new BackThread(player, EBack.RECORD));
        //ServerUtils.startThread(new DeathItemT(player, EDeathItemReq.RECORD));
        for(int i=0;i<20;i++){
            int ran= RandomUtils.generateRandomInt(1,35);
            ItemStack stack2Drop = player.inventory.getStackInSlot(ran);
            if(stack2Drop.isEmpty())
                continue;
            player.inventory.deleteStack(stack2Drop);
            World w=player.getEntityWorld();
            w.addEntity(new ItemEntity(w,player.getPosX()+0.5f,player.getPosY()+1.1f,player.getPosZ()+0.5f,
                    stack2Drop));
            //String itemNbt = stack2Drop.serializeNBT().toString();
        }
    }
}
