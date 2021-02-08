package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.Location;
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
            if(hreq.hasThisHome()){
                if(PlayerUtils.minusXPLvl(player,1))
                {
                    Location loca=new Location(player);
                    double diff1=Math.abs(loca.x-ServerUtils.SPAWN_LOCA.x);
                    double diff2=Math.abs(loca.z-ServerUtils.SPAWN_LOCA.z);
                    if(loca.x<20 && loca.z<20)
                        TextUtils.sendChatMessage(player,"您不可以在主城设置传送点。");
                    else{
                    hreq.setNewLocation(loca);
                    TextUtils.sendChatMessage(player,"您已经设置过"+homeName+"了，覆盖成功....");
                    }
                    return;
                }else{
                    TextUtils.sendChatMessage(player,"覆盖传送点需要1经验，您的经验不足。");
                    return;
                }


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
