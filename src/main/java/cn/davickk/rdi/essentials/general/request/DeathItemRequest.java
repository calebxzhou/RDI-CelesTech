package cn.davickk.rdi.essentials.general.request;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeathItemRequest {
    private final ServerPlayerEntity player;
    private final String playerName;
    private final String uuid;
    private final Connection conn= RDIEssentials.SQL_CONN;
    private List<ItemStack> itemStackList;
    public DeathItemRequest(ServerPlayerEntity player) throws SQLException, ClassNotFoundException {
        RDIEssentials.createSQLConnection();
        this.player=player;
        this.playerName=player.getDisplayName().getString();
        this.uuid=player.getUniqueID().toString();
    }
    private void storeItemStackList(List<ItemStack> stackList){
        this.itemStackList=stackList;
    }

    public void getAndDeleteRandomStackList(){
        List<ItemStack> stackList = new ArrayList<>();
        for(int i=0;i<16;i++){
            int ran= RandomUtils.generateRandomInt(1,35);
            ItemStack stack2Drop = player.inventory.getStackInSlot(ran);
            if(stack2Drop.isEmpty())
                continue;
            stackList.add(stack2Drop);
            player.inventory.deleteStack(stack2Drop);
            //String itemNbt = stack2Drop.serializeNBT().toString();
        }
        storeItemStackList(stackList);
        //return stackList;
    }

    public void uploadItemList() throws SQLException {
        ResultSet rs=conn.prepareStatement("SELECT COUNT(itemTag) AS items FROM death_item WHERE uuid='"+uuid+"'")
                .executeQuery();
        if(rs.next())
            if(rs.getInt("items")>36)
                conn.prepareStatement("DELETE FROM death_item WHERE uuid='"+uuid+"'")
                .executeUpdate();
        for(ItemStack oneStack:this.itemStackList)
        {
            PreparedStatement psm= conn.prepareStatement("INSERT INTO death_item (uuid, playerName, itemTag) VALUES (?,?,?)");
            psm.setString(1,uuid);
            psm.setString(2,playerName);
            psm.setString(3,oneStack.serializeNBT().toString());
            psm.executeUpdate();
        }
    }
    @Nullable
    public List<ItemStack> getItemList() throws SQLException, CommandSyntaxException {
        List<ItemStack> stackList = new ArrayList<>();
        PreparedStatement psm=conn.prepareStatement("SELECT * FROM death_item WHERE uuid=?");
        psm.setString(1,uuid);
        ResultSet stackSet=psm.executeQuery();
        while(stackSet.next()){
            CompoundNBT cnbt = JsonToNBT.getTagFromJson(stackSet.getString("itemTag"));
            ItemStack stack = ItemStack.read(cnbt);
            stackList.add(stack);
        }
            return stackList;
    }
    public void getAndGiveItemList() throws SQLException,CommandSyntaxException{
        List<ItemStack> stackList =getItemList();
        if(stackList==null||stackList.isEmpty()){
            TextUtils.sendChatMessage(player,"您没有物品可以取出。");
            return;
        }
        int stackSize=stackList.size();
        int invEmptySize= PlayerUtils.getEmptySlotsInventory(player);
        if(invEmptySize<stackSize) {
            TextUtils.sendChatMessage(player, "本操作需要您的背包中有(" + invEmptySize + "/" + stackSize + ")个空位，无法执行。");
            //if(stackSize<35)
                return;
            //else TextUtils.sendChatMessage(player,"您要取出的物品过多，正在尝试取出...");
        }
        int itemTaken=0;
        for(ItemStack oneStack:stackList){
                if(!player.inventory.addItemStackToInventory(oneStack))
                    break;
                ++itemTaken;
        }
        PreparedStatement psm=conn.prepareStatement("DELETE FROM death_item WHERE uuid=?");
        psm.setString(1,uuid);
        psm.executeUpdate();
        TextUtils.sendChatMessage(player,"成功取出"+itemTaken+"个物品。");
    }
    public void givePlayerItemList(List<ItemStack> stackList) {
        /*for(ItemStack oneStack:stackList){
            int stackSize=stackList.size();
            int invEmptySize=player.inventory.mainInventory.size();
            if(invSize<stackSize){
                TextUtils.sendChatMessage(player,"本操作需要"+invSize+"个背包空位，");
            }
            player.inventory.addItemStackToInventory(oneStack);
        }*/

    }
}
