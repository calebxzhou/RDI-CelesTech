package cn.davickk.rdi.essentials.general.thread.rinv;

import cn.davickk.rdi.essentials.general.enums.ERinv;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.text.TextComponent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static cn.davickk.rdi.essentials.general.util.SQLUtils.*;

public class RinvLsThread extends Thread {
    private ServerPlayerEntity player;
    private String args;

    public RinvLsThread(ServerPlayerEntity player) {
        this.player = player;
    }

    public RinvLsThread(ServerPlayerEntity player, String args) {
        this.player = player;
        this.args = args;
    }

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection(invdburl, USR, PWD);
            //UUID 横杠全换成下划线
            String u= PlayerUtils.getLegacyUUID(player);
            String stm = "SELECT * FROM " + u;
            ResultSet resultSet = c.prepareStatement(stm).executeQuery();
            if (!resultSet.next()) {
                TextUtils.sendChatMessage(player, "云仓库里什么都没有");
                return;
            } else
                resultSet.last();
            for (int i = 0; i <= 35; i++) {
                String nbtdata = resultSet.getString(1);
                CompoundNBT cnbt = JsonToNBT.getTagFromJson(nbtdata);
                ItemStack itemstk = ItemStack.read(cnbt);
                if (!nbtdata.startsWith("{id:\"minecraft:air\"")) {

                    if (this.args == null) {

                        TextComponent text = (TextComponent) itemstk.getTextComponent();
                        TextUtils.sendChatMessage(player, text);
                    } else
                        TextUtils.sendChatMessage(player, itemstk.getDisplayName().getString() + ":" + nbtdata);
                }
                if (!resultSet.previous())
                    break;
            }
            TextUtils.sendChatMessage(player, "以上为云仓库中的数据");
            TextUtils.clickableContent2Send(player, ERinv.DOWNLOAD_NOW.text, ERinv.DOWNLOAD_NOW.cmd);
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
