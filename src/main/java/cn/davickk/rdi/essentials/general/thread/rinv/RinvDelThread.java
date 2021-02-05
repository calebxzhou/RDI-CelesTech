package cn.davickk.rdi.essentials.general.thread.rinv;

import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static cn.davickk.rdi.essentials.general.util.SQLUtils.*;

public class RinvDelThread extends Thread {
    private ServerPlayerEntity player;

    public RinvDelThread(ServerPlayerEntity player) {
        this.player = player;
    }

    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection(invdburl, USR, PWD);
            //UUID 横杠全换成下划线
            String u= PlayerUtils.getLegacyUUID(player);
            DatabaseMetaData dbm = c.getMetaData();
            ResultSet rs = dbm.getTables(null, "rdirepo", u, null);
            if (!rs.next()) {
                TextUtils.sendChatMessage(player, "您的云仓库中，没有任何数据。");
                return;
            } else {
                String selectSQL = "DELETE FROM " + u;
                c.prepareStatement(selectSQL).executeUpdate();
                c.close();
                TextUtils.sendChatMessage(player, "成功删除了云仓库数据。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
