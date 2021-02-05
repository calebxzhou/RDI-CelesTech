package cn.davickk.rdi.essentials.general.thread.rinv;

import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;

import java.sql.*;

import static cn.davickk.rdi.essentials.general.util.SQLUtils.*;

public class RinvDownThread extends Thread {
    private ServerPlayerEntity player;

    public RinvDownThread(ServerPlayerEntity player) {
        this.player = player;
    }

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection(invdburl, USR, PWD);
            //Connection c2 = DriverManager.getConnection(invdburl, USR, PWD);
            //UUID 横杠全换成下划线
            String u= PlayerUtils.getLegacyUUID(player);
            DatabaseMetaData dbm = c.getMetaData();
            ResultSet rs = dbm.getTables(null, "rdirepo", u, null);
            int port=player.getServer().getServerPort();
            //TODO
            if(!(port== ServerUtils.MAIN_PORT || port==ServerUtils.REAL_PORT || port==-1))
            {
                TextUtils.sendChatMessage(player, "只能在主世界和真实世界使用本指令");
                return;
            }
            if (!rs.next()) {
                TextUtils.sendChatMessage(player, "您之前未上传过背包。");
                rs.close();
                c.close();
            } else {
                String stm = "SELECT * FROM " + u;
                PreparedStatement pstm = c.prepareStatement(stm);
                ResultSet rs2 = pstm.executeQuery();
                //int row = rs2.getRow();
                double percent;
                if (!rs2.next()) {
                    TextUtils.sendChatMessage(player, "云仓库里什么都没有");
                    return;
                }
                rs2.first();
                if(!player.inventory.isEmpty()){
                    TextUtils.sendChatMessage(player,"请清空您的背包，包括身上的穿戴品，才能开始取出。");
                    return;
                }
                //ResultSet resultSet = c.prepareStatement("SELECT invdata FROM " + u).executeQuery();
                for (int i = 0; i <= 35; i++)//MC的物品栏排列：9~17 / 18~26 / 27~35 / 0~8
                //for(int i=0;rs2.next();i++)
                {

                    //itemList.add(i,inventory.getStackInSlot(i).serializeNBT().toString());
                    //ItemStack stk=inventory.getStackInSlot(i);
                    //String itemNbt=stk.serializeNBT().toString();

                    String itemNbt = rs2.getString(1);
                    percent = i / 35.0 * 100.0;
                    int disSpd = RandomUtils.generateRandomInt(1000, 4000);
                    TextUtils.sendActionMessage(player, "正在下载：(" + Math.round(percent) + "%) " + disSpd + " KB/s");
                    //System.out.println(stm);
                    //TextUtils.sendChatMessage(player,"请不要与背包交互，否则【背包有可能丢失】");
                    CompoundNBT cnbt = JsonToNBT.getTagFromJson(itemNbt);
                    ItemStack itemstk = ItemStack.read(cnbt);
                    player.inventory.add(i, itemstk);
                    rs2.next();

                }
                //if(resultSet.next() ){
                            /*Blob plyInv = resultSet.getBlob(1);
                            ByteArrayInputStream bIn = new ByteArrayInputStream(plyInv.getBytes(1, (int) plyInv.length()));
                            ObjectInputStream objIn = new ObjectInputStream(bIn);
                            ArrayList itemList = (ArrayList) objIn.readObject();
                            for (int i = 0; i <= 35; i++)//MC的物品栏排列：9~17 / 18~26 / 27~35 / 0~8
                            {
                                //itemList.add(i,inventory.getStackInSlot(i).serializeNBT().toString());
                                String nbt= (String) itemList.get(i);
                                System.out.println(nbt);
                                CompoundNBT nbt1=new CompoundNBT();
                                nbt1.
                                ItemStack item = ItemStack.read(nbt1);
                                player.replaceItemInInventory(i,item);
                                //inventory.add(i,item);
                            }
                            //player.inventory.copyInventory(inventory);
                            objIn.close();
                            bIn.close();*/

                TextUtils.sendChatMessage(player, "成功下载背包");

                String selectSQL = "DELETE FROM " + u;
                c.prepareStatement(selectSQL).executeUpdate();
                c.close();
                //}

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
