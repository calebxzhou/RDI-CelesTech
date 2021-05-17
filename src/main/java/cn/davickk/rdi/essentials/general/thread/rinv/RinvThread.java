package cn.davickk.rdi.essentials.general.thread.rinv;

import cn.davickk.rdi.essentials.general.enums.ERinv;
import cn.davickk.rdi.essentials.general.model.Rinv;
import cn.davickk.rdi.essentials.general.request.RinvRequest;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.text.TextComponent;

import java.util.List;

public class RinvThread extends Thread {
    private ServerPlayerEntity player;
    private int arg;
    public static final int PUSH=1,LIST=2,GET=4;

    public RinvThread(ServerPlayerEntity player, int arg) {
        this.player = player;
        this.arg = arg;
    }

    public void run() {
        try {
            RinvRequest rinvreq=new RinvRequest(player);
            List<Rinv> invdata=rinvreq.get(player.getUniqueID().toString());
            if(this.arg==LIST){
                if(invdata.isEmpty()||invdata==null){
                    TextUtils.sendChatMessage(player,"云仓库里什么都没有。");
                    return;
                }
                TextUtils.sendChatMessage(player,"您的仓库中有：");
                for (Rinv singleInvdata:invdata) {
                    ItemStack itemstk = this.getStackFromNbt(singleInvdata.getSerializedNbt());
                    if (!singleInvdata.getSerializedNbt().startsWith("{id:\"minecraft:air\"")) {
                        TextComponent text = (TextComponent) itemstk.getTextComponent();
                        TextUtils.sendChatMessage(player, text);
                    }
                }
                TextUtils.clickableContent2Send(player, ERinv.DOWNLOAD_NOW.text, ERinv.DOWNLOAD_NOW.cmd);
            }else if(this.arg==PUSH){
                if(!invdata.isEmpty()){
                    TextUtils.sendChatMessage(player,"云仓库里有物品，请先取出后才可上传。");
                    return;
                }

                for (int i = 0; i <= 35; i++)//MC的物品栏排列：9~17 / 18~26 / 27~35 / 0~8
                {
                    //itemList.add(i,inventory.getStackInSlot(i).serializeNBT().toString());
                    ItemStack stk = player.inventory.getStackInSlot(i);
                    rinvreq.put(player.getUniqueID().toString(),player.getDisplayName().getString(),stk.serializeNBT().toString());

                    double percent = i / 35.0 * 100.0;
                    int disSpd = RandomUtils.generateRandomInt(1000, 4000);
                    TextUtils.sendActionMessage(player, "正在上传：(" + Math.round(percent) + "%) " + disSpd + " KB/s");
                    player.inventory.deleteStack(stk);


                }
                TextUtils.sendChatMessage(player,"上传完成。");
            }else if(this.arg==GET){
                if(invdata.isEmpty()){
                    TextUtils.sendChatMessage(player,"您的云仓库里什么都没有。");
                    return;
                }
                if(!player.inventory.isEmpty()){
                    TextUtils.sendChatMessage(player,"请清空您的背包，包括身上的穿戴品，才能开始取出。");
                    return;
                }
                for (int i = 0; i <= 35; i++)//MC的物品栏排列：9~17 / 18~26 / 27~35 / 0~8
                {
                    Rinv itemNbt = invdata.get(i);
                    double percent = i / 35.0 * 100.0;
                    int disSpd = RandomUtils.generateRandomInt(1000, 4000);
                    TextUtils.sendActionMessage(player, "正在下载：(" + Math.round(percent) + "%) " + disSpd + " KB/s");
                    CompoundNBT cnbt = JsonToNBT.getTagFromJson(itemNbt.getSerializedNbt());
                    ItemStack itemstk = ItemStack.read(cnbt);
                    player.inventory.add(i, itemstk);

                }
                rinvreq.delete(player.getUniqueID().toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private ItemStack getStackFromNbt(String serializedNbt) throws CommandSyntaxException {
        CompoundNBT cnbt = JsonToNBT.getTagFromJson(serializedNbt);
        return ItemStack.read(cnbt);
    }
}
