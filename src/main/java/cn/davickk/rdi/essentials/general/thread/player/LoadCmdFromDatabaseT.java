package cn.davickk.rdi.essentials.general.thread.player;

import cn.davickk.rdi.essentials.RDIEssentials;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoadCmdFromDatabaseT extends Thread {
    private final PlayerEntity player;
    private final BlockPos bpos;
    public LoadCmdFromDatabaseT(PlayerEntity player, BlockPos bpos) {
        this.player = player;
        this.bpos=bpos;
    }

    public void run() {
        try {
            RDIEssentials.createSQLConnection();
            PreparedStatement psm=RDIEssentials.SQL_CONN.prepareStatement
                    ("SELECT * FROM cmd_sign WHERE x=? AND y=? AND z=?");
            psm.setInt(1,bpos.getX());
            psm.setInt(2,bpos.getY());
            psm.setInt(3,bpos.getZ());
            ResultSet rset=psm.executeQuery();
            String cmd=rset.getString("cmd");
            player.getServer().getCommandManager().handleCommand(player.getCommandSource(), cmd);
        }catch (Exception e)
        {e.printStackTrace();}

    }
}
