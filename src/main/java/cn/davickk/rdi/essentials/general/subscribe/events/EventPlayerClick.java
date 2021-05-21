package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.thread.blockrec.ReadRecThread;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import com.sk89q.worldedit.WorldEditException;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventPlayerClick {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLeftClick(PlayerInteractEvent.LeftClickBlock event){
        if(event.getHand()==Hand.OFF_HAND)
            return;
        if(event.getHand()==Hand.MAIN_HAND){
            PlayerEntity player = event.getPlayer();
            if(player.level.isClientSide())
                return;
            if(ServerUtils.getPlayerInspectingMap().containsKey(player.getStringUUID())){
                event.setCanceled(true);
                TextUtils.sendChatMessage(player,"正在读取此位置的方块记录....");
                ServerUtils.startThread(new ReadRecThread(player,event.getPos()));
            }
        }
    }
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        Item heldItem = event.getItemStack().getItem();

        //System.out.println(heldItem.getRegistryName());
        if(heldItem == Items.BONE) {
            BlockPos lookingAt=event.getPos();
            BlockState blockS=player.getCommandSenderWorld().getBlockState(lookingAt);
            //player.getCommandSenderWorld().setBlockState(lookingAt, Blocks.AIR.getDefaultState());
            //System.out.println(blockS.getBlock().getRegistryName().toString());
            if(!blockS.getBlock().getRegistryName().toString().contains("sapling"))
                return;
            if(PlayerUtils.hasEnoughXPLvl(player,1)){
                TextUtils.sendChatMessage(player,"您的能力太强了，超出了本功能的范围");
                return;
            }
            try {
                WorldUtils.pasteTree(player,lookingAt);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WorldEditException e) {
                e.printStackTrace();
            }
            player.getMainHandItem().setCount(player.getMainHandItem().getCount()-1);
        }

    }
}
