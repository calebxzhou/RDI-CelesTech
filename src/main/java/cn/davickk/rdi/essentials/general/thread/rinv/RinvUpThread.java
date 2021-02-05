package cn.davickk.rdi.essentials.general.thread.rinv;

import cn.davickk.rdi.essentials.general.enums.ERinv;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;

import java.sql.*;

import static cn.davickk.rdi.essentials.general.util.SQLUtils.*;

public class RinvUpThread extends Thread {
    private ServerPlayerEntity player;

    public RinvUpThread(ServerPlayerEntity player) {
        this.player = player;
    }

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            PlayerInventory inventory = player.inventory;

            Connection c = DriverManager.getConnection(invdburl, USR, PWD);
            //UUID ���ȫ�����»���
            //String u= PlayerUtils.getUUID(player);
            String u= PlayerUtils.getLegacyUUID(player);
            DatabaseMetaData dbm = c.getMetaData();
            //String stmSlt =  "IF WHERE table_name like 'TEST2'";//"SELECT invdata FROM " + u + "";
            ResultSet rs = dbm.getTables(null, "rdirepo", u, null);
            int port=player.getServer().getServerPort();
            //TODO
            if(!(port== ServerUtils.MAIN_PORT || port==ServerUtils.REAL_PORT || port==-1))
            {
                TextUtils.sendChatMessage(player, "ֻ�������������ʵ����ʹ�ñ�ָ��");
                return;
            }
            if (!rs.next()) {
                TextUtils.sendChatMessage(player, "���ڳ�ʼ���Ʋֿ�.....");
                String stm1 = "CREATE TABLE " + u + " (invdata longtext);";
                //String stm2="INSERT INTO "+u+" (invdata, comment) VALUES (?, '')";
                System.out.println(stm1);
                //System.out.println(stm2);
                c.prepareStatement(stm1).executeUpdate();
                //c.prepareStatement(stm2).executeUpdate();
                TextUtils.sendChatMessage(player, "�ɹ���ʼ���Ʋֿ⡣");
            }
            String stm33="SELECT * FROM "+u;
            ResultSet rs2=c.prepareStatement(stm33).executeQuery();
            if(rs2.next())
            {
                TextUtils.sendChatMessage(player,"�����Ʋֿ�����Ȼ����Ʒ������ȡ�������ϴ���");
                TextUtils.clickableContent2Send(player, ERinv.LIST_LOOK.text,ERinv.LIST_LOOK.cmd);
                return;
            }
            //ArrayList<String> itemList = new ArrayList<>();
            for (int i = 0; i <= 35; i++)//MC����Ʒ�����У�9~17 / 18~26 / 27~35 / 0~8
            {
                //itemList.add(i,inventory.getStackInSlot(i).serializeNBT().toString());
                ItemStack stk = inventory.getStackInSlot(i);
                String itemNbt = stk.serializeNBT().toString();
                String stm = "INSERT INTO " + u + " (invdata) VALUES (?)";
                double percent = i / 35.0 * 100.0;
                int disSpd = RandomUtils.generateRandomInt(1000, 4000);
                TextUtils.sendActionMessage(player, "�����ϴ���(" + Math.round(percent) + "%) " + disSpd + " KB/s");
                //Thread.sleep(400);
                //if(itemNbt.startsWith("{id:\"minecraft:air\""))
                  //  continue;
                //System.out.println(stm);
                PreparedStatement pstm = c.prepareStatement(stm);
                pstm.setString(1, itemNbt);
                player.inventory.deleteStack(stk);
                pstm.execute();


            }



                /*ByteArrayOutputStream bAout = new ByteArrayOutputStream();
                ObjectOutputStream objOut = new ObjectOutputStream(bAout);
                //TODO cant serialize
                objOut.writeObject(itemList);
                objOut.flush();
                pstm.setInt(1,1);
                pstm.setBlob(1, new ByteArrayInputStream(bAout.toByteArray()));
                pstm.execute();
                pstm.close();
                objOut.close();
                bAout.close();*/
            TextUtils.sendChatMessage(player, "�ɹ��ϴ�������");
            //player.inventory.clear();

            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
