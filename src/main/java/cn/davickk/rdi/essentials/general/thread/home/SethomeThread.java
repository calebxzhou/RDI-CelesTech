package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.*;
import net.minecraft.entity.player.ServerPlayerEntity;

public class SethomeThread extends Thread {
    private ServerPlayerEntity player;
    private String homeName;



    public SethomeThread(ServerPlayerEntity player, String homeName) {
        this.player = player;
        this.homeName = homeName;
    }

    public void run() {
        try {
            HomeRequest hreq=new HomeRequest(player,homeName);
            int homes=hreq.getHomeCounts();
            if(hreq.isReachedMax()) {
                TextUtils.sendChatMessage(player,"最多只能设置"+HomeUtils.MAX_HOME+"个传送点。("+homes+"/"+HomeUtils.MAX_HOME+")");
                return;
            }
            if(hreq.hasHome()){
                TextUtils.sendChatMessage(player,"您已经设置过"+homeName+"了，正在覆盖....");
                return;
            }
            if(hreq.setHome()){
            TextUtils.sendChatMessage(player, "成功设置传送点" + homeName);
            TextUtils.clickableContent2Send(player, EColor.YELLOW.code+"[查看所有]","/home");
            }else
                TextUtils.sendChatMessage(player,"无法设置家，请咨询腐竹");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
