package cn.davickk.rdi.essentials.general.thread.player;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EBack;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.BackRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class CreateCmdSignT extends Thread {
    private final PlayerEntity player;
    private final String cmd;
    private final BlockPos bpos;
    public CreateCmdSignT(PlayerEntity player, String cmd, BlockPos bpos) {
        this.player = player;
        this.cmd=cmd;
        this.bpos=bpos;
    }

    public void run() {
        try {
            RDIEssentials.createSQLConnection();
            Date date=new Date(System.currentTimeMillis());
            PreparedStatement psm=RDIEssentials.SQL_CONN.prepareStatement
                    ("INSERT INTO cmd_sign (createDate, x, y, z, cmd, playerName) VALUES (?,?,?,?,?,?)");
            psm.setDate(1, date);
            psm.setInt(2,bpos.getX());
            psm.setInt(3,bpos.getY());
            psm.setInt(4,bpos.getZ());
            psm.setString(5,cmd);
            psm.setString(6,player.getDisplayName().getString());
            psm.executeUpdate();
        }catch (Exception e)
        {e.printStackTrace();}

    }
}
