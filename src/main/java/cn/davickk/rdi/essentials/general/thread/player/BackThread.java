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
                    TextUtils.sendChatMessage(player,"û�ҵ��ϴε���Ϸ���������ص㡣");
                    return;
                }
                PlayerUtils.teleportPlayer(player,loca);
                TextUtils.sendChatMessage(player,"�ɹ��ص����ϴε���Ϸ���������ص㡣");
            }
        }catch (Exception e)
        {e.printStackTrace();}

    }
}
