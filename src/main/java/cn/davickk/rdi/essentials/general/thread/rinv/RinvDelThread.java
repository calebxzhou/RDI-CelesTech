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
            //UUID ���ȫ�����»���
            String u= PlayerUtils.getLegacyUUID(player);
            DatabaseMetaData dbm = c.getMetaData();
            ResultSet rs = dbm.getTables(null, "rdirepo", u, null);
            if (!rs.next()) {
                TextUtils.sendChatMessage(player, "�����Ʋֿ��У�û���κ����ݡ�");
                return;
            } else {
                String selectSQL = "DELETE FROM " + u;
                c.prepareStatement(selectSQL).executeUpdate();
                c.close();
                TextUtils.sendChatMessage(player, "�ɹ�ɾ�����Ʋֿ����ݡ�");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
