package cn.davickk.rdi.essentials.general.subscribe.events;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RDIEssentials.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventPlayerBlock {
    @SubscribeEvent
    public static void onPlayerPlace(BlockEvent.EntityPlaceEvent event){
        Entity entity =event.getEntity();
        if(entity instanceof PlayerEntity){
            PlayerEntity player=(PlayerEntity) entity;
            if(event.getPlacedBlock().getBlock().getRegistryName().toString().contains("crucible"))
                TextUtils.sendChatMessage(player,"������û����������ʲô�������ˡ�");
        }
    }
    @SubscribeEvent
    public static void onPlayerDestory(BlockEvent.BreakEvent event) {
        try {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
            BlockState blockS = event.getState();
            //System.out.println(blockS.toString());
            Block block=blockS.getBlock();

            //System.out.println(block.getRegistryNamed().toString());
            //System.out.println(block.getRegistryType().toString());

            if(block.getRegistryName().toString().contains("leaves")){
                //System.out.println(player.getHeldItemMainhand().getDisplayName().getString());
                if(player.getHeldItemMainhand().getItem() instanceof SwordItem){
                    int stack=player.inventory.getFirstEmptyStack();
                    if(PlayerUtils.hasInventorySpace(player))
                        PlayerUtils.givePlayerItem(player,"apple",1);
                        /*player.inventory.add(stack, new ItemStack(
                                new Item(new Item.Properties().food(Foods.APPLE))));*/
                }else{
                    int ran=RandomUtils.generateRandomInt(1,1000);
                    if(ran<100){
                        int stack=player.inventory.getFirstEmptyStack();
                        if(PlayerUtils.hasInventorySpace(player)){
                            TextUtils.sendChatMessage(player,"�����Ϸ�����һ��ƻ��~");
                            PlayerUtils.givePlayerItem(player,"apple",1);
                        }
                    }else
                    if(ran>995){
                        int stack=player.inventory.getFirstEmptyStack();
                        if(PlayerUtils.hasInventorySpace(player)){
                            TextUtils.sendChatMessage(player,"�����Ϸ�����һ����ƻ������");
                            PlayerUtils.givePlayerItem(player,"golden_apple",1);
                        }
                    }else
                    if(ran==666){
                        int stack=player.inventory.getFirstEmptyStack();
                        if(PlayerUtils.hasInventorySpace(player)){
                            TextUtils.sendGlobalChatMessage(player.getServer().getPlayerList(),
                                    EColor.GOLD.code+player.getDisplayName().getString()+"�����Ϸ�����һ����ħ��ƻ��������");
                            PlayerUtils.givePlayerItem(player,"enchanted_golden_apple",1);
                        }
                    }else
                        if(ran-100<50){
                            TextUtils.sendChatMessage(player,"�Ͳ�һ���Ϳ��Ի��ƻ���ˣ���");
                        }
                }
            }
            if(block.getRegistryName().toString().equalsIgnoreCase("stone")){
                int ran=RandomUtils.generateRandomInt(1,100);
                if(ran<5){
                    if(PlayerUtils.hasInventorySpace(player)) {
                        TextUtils.sendChatMessage(player, "��ʯͷ�﷢����һ����֪����ʯ�����������������ӡ�");
                        PlayerUtils.givePlayerItem(player, "appliedenergistics2:quartz", 1);
                    }
                }
                if(ran==66){
                    if(PlayerUtils.hasInventorySpace(player)) {
                        TextUtils.sendChatMessage(player, "��ʯͷ�﷢����һ����ɫ��Сʯͷ�����������������ӡ�");
                        PlayerUtils.givePlayerItem(player, "diamond", 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*System.out.println(bpos);
        int bright = event.getWorld()
                .getLightManager().getLightEngine(LightType.SKY)
                .getLightFor(bpos);
        System.out.println(bright);
        if (bright <= 3) {
            float hp = player.getHealth();
            player.setHealth(hp - 1.0f);
            // TextUtils.sendChatMessage( player,
            //   new StringTextComponent("һƬ��ڣ��㲻С�İ������õ���Ʒ����������"));
        }*/

    }
}
