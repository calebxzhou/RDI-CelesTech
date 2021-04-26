package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import com.sk89q.worldedit.WorldEditException;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventRightClick {
    /*@SubscribeEvent
    public static void onLeftClick(PlayerInteractEvent.LeftClickBlock event){
        PlayerEntity player = event.getPlayer();
        if(player.getEntityWorld().getBlockState(event.getPos()).getBlock().getRegistryName().toString().contains("sign")){
            TileEntity te=player.world.getTileEntity(event.getPos());
            if(te!=null && te instanceof SignTileEntity){
                try {
                    //ITextComponent signLine2CmdTxt = ((SignTileEntity) te).getText(1);
                    CompoundNBT cnbt = ((SignTileEntity) te).serializeNBT();
                    if(cnbt==null)
                        return;
                    //{ForgeData:{},Color:"black",x:10,Text4:'{"text":""}',y:4,Text3:'{"text":""}',z:-11,Text2:'{"text":"232323"}',id:"minecraft:sign",Text1:'{"text":"122121212"}'}
                    ServerUtils.startThread(new LoadCmdSignT(player,cnbt));

                }
                *//*if(signLine2CmdTxt== StringTextComponent.EMPTY)
                    return;
                //ServerUtils.startThread(new LoadCmdSignT(player,event.getPos()));
                if(player.getServer()==null)
                    return;
                else{
                    MinecraftServer serv=player.getServer();
                    serv.getCommandManager().handleCommand
                            (player.getCommandSource(),
                                    signLine2CmdTxt.getString());
                }
                TextUtils.sendChatMessage(player,"这个牌子内置了指令。正在读取....");}*//*
                catch (Exception e){e.printStackTrace();}
            }
        }
    }*/
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        Item heldItem = event.getItemStack().getItem();

        //System.out.println(heldItem.getRegistryName());
        if(heldItem == Items.BONE) {
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
        }


            /*if (heldItem == Items.COMPASS) {
                // TODO
            }*/

//            System.out.println(event.getItemStack());
//            System.out.println(vec3b.getX());
//            System.out.println(vec3b.getY());
//            System.out.println(vec3b.getZ());

    }
}
