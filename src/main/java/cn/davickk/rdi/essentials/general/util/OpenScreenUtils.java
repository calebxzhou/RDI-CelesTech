package cn.davickk.rdi.essentials.general.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nullable;

public class OpenScreenUtils {
    public static void openInv(ServerPlayerEntity player,String title){
        INamedContainerProvider conp=new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new StringTextComponent(title);
            }

            @Nullable
            public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
                Container conta=ChestContainer.createGeneric9X1(p_createMenu_1_, p_createMenu_2_);
                conta.putStackInSlot(0,new ItemStack(Items.BEACON));
                conta.putStackInSlot(1,new ItemStack(Items.OBSIDIAN));
                conta.putStackInSlot(2,new ItemStack(Items.REDSTONE));
                conta.putStackInSlot(3,new ItemStack(Items.ICE));
                conta.putStackInSlot(4,new ItemStack(Items.ACACIA_BOAT));
                conta.putStackInSlot(5,new ItemStack(Items.ACACIA_BOAT));
                conta.putStackInSlot(6,new ItemStack(Items.ACACIA_BOAT));
                conta.putStackInSlot(7,new ItemStack(Items.ACACIA_BOAT));
                conta.putStackInSlot(8,new ItemStack(Items.ACACIA_BOAT));
                conta.addListener(new IContainerListener() {
                    @Override
                    public void sendAllContents(Container containerToSend, NonNullList<ItemStack> itemsList) { }

                    @Override
                    public void sendSlotContents(Container containerToSend, int slotInd, ItemStack stack) {
                       player.connection.sendPacket(new SSetSlotPacket(p_createMenu_1_,slotInd,stack));
                    }

                    @Override
                    public void sendWindowProperty(Container containerIn, int varToUpdate, int newValue) {

                    }
                });
                return conta;
            }
        };

        /*Container cont=ChestContainer.createGeneric9X6(1, player.inventory);
        cont.putStackInSlot(1,new ItemStack(Items.ACACIA_BOAT));*/
        player.openContainer(conp);
    }

}
