package cn.davickk.rdi.essentials.general.thread.player;

import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public class LoadCmdSignT extends Thread {
    private final PlayerEntity player;
    private final CompoundNBT cnbt;
    public LoadCmdSignT(PlayerEntity player, CompoundNBT cnbt) {
        this.player = player;
        this.cnbt=cnbt;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            /*RDIEssentials.createSQLConnection();
            PreparedStatement psm=RDIEssentials.SQL_CONN.prepareStatement
                    ("SELECT * FROM cmd_sign WHERE x=? AND y=? AND z=?");
            psm.setInt(1,bpos.getX());
            psm.setInt(2,bpos.getY());
            psm.setInt(3,bpos.getZ());
            ResultSet rset=psm.executeQuery();
            String cmd=rset.getString("cmd");*/
            //TextUtils.sendChatMessage(player,cnbt.toString());
            String lastLine=cnbt.getString("Text4");
            if(lastLine.equals("") || lastLine==null)
                return;
            String processedLine= lastLine.split(":")[1].replace("}","")
                    .replace("\"","");
            if(processedLine.length()<3 || processedLine==null){
                TextUtils.sendChatMessage(player,"这个牌子上并没有指令可供读取");
                return;
            }
            TextUtils.sendChatMessage(player,"正在执行牌子上的指令："+processedLine);
            player.getServer().getCommandManager().handleCommand(player.getCommandSource(), processedLine);
        }catch (Exception e)
        {e.printStackTrace();}

    }
}
