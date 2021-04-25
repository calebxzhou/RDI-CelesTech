package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.*;
import com.sk89q.worldedit.world.World;
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
            Location loca=new Location(player);
            if(WorldUtils.ifNearbySpawn(loca)){
                TextUtils.sendChatMessage(player,"您不可以在主城设置传送点。");
                return;
            }
            if(hreq.hasThisHome()){
                if(PlayerUtils.minusXPLvl(player,1))
                {
                    hreq.setNewLocation(loca);
                    TextUtils.sendChatMessage(player,"您已经设置过"+homeName+"了，覆盖成功....");
                    return;
                }else{
                    TextUtils.sendChatMessage(player,"覆盖传送点需要1经验，您的经验不足。");
                    return;
                }
            }
            hreq.setHome();
            TextUtils.sendChatMessage(player, "成功设置传送点" + homeName);
            TextUtils.clickableContent2Send(player, EColor.YELLOW.code+"[查看所有]","/home");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
