package cn.davickk.rdi.essentials.general.thread.player;

import cn.davickk.rdi.essentials.general.enums.EBack;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.BackRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.sql.Connection;
import java.sql.DriverManager;

import static cn.davickk.rdi.essentials.general.util.SQLUtils.*;

public class BackThread extends Thread {
    private final ServerPlayerEntity player;
    private final EBack opr;
    public BackThread(ServerPlayerEntity player, EBack opration) {
        this.player = player;
        this.opr=opration;
    }

    public void run() {
        try {


            if (opr.equals(EBack.RECORD)) {
                //new BackRequest(c,player).record();
            } else if (opr.equals(EBack.READ)) {
                Location loca=new BackRequest(player).read();
                if(loca==null) {
                    TextUtils.sendChatMessage(player,"没找到上次的游戏人物死亡地点。");
                    return;
                }
                PlayerUtils.teleportPlayer(player,loca);
                TextUtils.sendChatMessage(player,"成功回到了上次的游戏人物死亡地点。");
            }
        }catch (Exception e)
        {e.printStackTrace();}

    }
}
