package cn.davickk.rdi.essentials.general.thread.player;

import cn.davickk.rdi.essentials.RDIEssentials;
import net.minecraft.entity.player.PlayerEntity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class RollRecordT extends Thread {
    private final PlayerEntity player;
    private final int counts;
    private final String pName;
    private final String items;
    public RollRecordT(PlayerEntity player, int counts,String items) {
        this.player = player;
        this.counts=counts;
        this.items=items;
        pName=player.getDisplayName().getString();
    }

    public void run() {
       /* try {

            java.util.Date date = new Date(System.currentTimeMillis());
            Timestamp param = new Timestamp(date.getTime());
            PreparedStatement psm=RDIEssentials.getSQLUtils().getSqlSession().getConnection().prepareStatement
                    ("INSERT INTO rolls (Date, playerName, counts, itemsGet) VALUES (?,?,?,?)");
            psm.setTimestamp(1, param);
            psm.setString(2,pName);
            psm.setInt(3, counts);
            psm.setString(4, items);
            psm.executeUpdate();
            RDIEssentials.getSQLUtils().getSqlSession().commit();
        }catch (Exception e)
        {e.printStackTrace();}*/

    }
}
