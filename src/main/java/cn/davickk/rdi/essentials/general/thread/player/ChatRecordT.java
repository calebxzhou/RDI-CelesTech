package cn.davickk.rdi.essentials.general.thread.player;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.util.TimeUtils;
import net.minecraft.entity.player.PlayerEntity;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class ChatRecordT extends Thread {
    private final PlayerEntity player;
    private final String chat;
    private final String pName;
    public ChatRecordT(PlayerEntity player, String chat) {
        this.player = player;
        this.chat=chat;
        pName=player.getDisplayName().getString();
    }

    public void run() {
        try {
            RDIEssentials.createSQLConnection();
            Timestamp param = TimeUtils.getTimestampNow();
            PreparedStatement psm=RDIEssentials.SQL_CONN.prepareStatement
                    ("INSERT INTO chat (Date, playerName, chat) VALUES (?,?,?)");
            psm.setTimestamp(1, param);
            psm.setString(2,pName);
            psm.setString(3,chat);
            psm.executeUpdate();
        }catch (Exception e)
        {e.printStackTrace();}

    }
}
