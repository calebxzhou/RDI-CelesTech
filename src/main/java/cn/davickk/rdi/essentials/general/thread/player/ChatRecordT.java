package cn.davickk.rdi.essentials.general.thread.player;

import net.minecraft.entity.player.PlayerEntity;

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
        /*try {

            Timestamp param = DateTimeUtils.getTimestampNow();
            PreparedStatement psm=RDIEssentials.getSQLUtils().getSqlSession().getConnection().prepareStatement
                    ("INSERT INTO chat (Date, playerName, chat) VALUES (?,?,?)");
            psm.setTimestamp(1, param);
            psm.setString(2,pName);
            psm.setString(3,chat);
            psm.executeUpdate();
            RDIEssentials.getSQLUtils().getSqlSession().commit();
        }catch (Exception e)
        {e.printStackTrace();}*/

    }
}
