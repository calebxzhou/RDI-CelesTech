package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import com.sk89q.worldedit.WorldEditException;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventRightClick {

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        Item heldItem = event.getItemStack().getItem();

        //System.out.println(heldItem.getRegistryName());
        if(!(heldItem == Items.BONE))
            return;
        BlockPos lookingAt=event.getPos();
        BlockState blockS=player.getEntityWorld().getBlockState(lookingAt);
        //player.getEntityWorld().setBlockState(lookingAt, Blocks.AIR.getDefaultState());
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
        player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount()-1);

            /*if (heldItem == Items.COMPASS) {
                // TODO
            }*/

//            System.out.println(event.getItemStack());
//            System.out.println(vec3b.getX());
//            System.out.println(vec3b.getY());
//            System.out.println(vec3b.getZ());

    }
}
