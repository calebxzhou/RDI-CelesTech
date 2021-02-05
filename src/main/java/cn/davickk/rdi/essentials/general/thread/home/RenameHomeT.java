package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.HomeUtils;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;

import static cn.davickk.rdi.essentials.general.util.SQLUtils.*;

public class RenameHomeT extends Thread{
    private final ServerPlayerEntity player;
    private final String oldName;
    private final String newName;
    public RenameHomeT(ServerPlayerEntity player, String oldName, String newName){
        this.player=player;
        this.oldName=oldName;
        this.newName=newName;
    }
    public void run(){
        try {
            HomeRequest hreq=new HomeRequest(player,oldName);
            if(hreq.renameHome(newName)){
                TextUtils.sendChatMessage(player,"改名成功");
            }else TextUtils.sendChatMessage(player,"改名错误，请咨询腐竹");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //.getDisplayName().getString().equalsIgnoreCase(player2Share)
    }
}
