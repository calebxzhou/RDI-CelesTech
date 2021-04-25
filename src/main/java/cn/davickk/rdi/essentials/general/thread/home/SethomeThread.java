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
                TextUtils.sendChatMessage(player,"���ֻ������"+HomeUtils.MAX_HOME+"�����͵㡣("+homes+"/"+HomeUtils.MAX_HOME+")");
                return;
            }
            Location loca=new Location(player);
            if(WorldUtils.ifNearbySpawn(loca)){
                TextUtils.sendChatMessage(player,"�����������������ô��͵㡣");
                return;
            }
            if(hreq.hasThisHome()){
                if(PlayerUtils.minusXPLvl(player,1))
                {
                    hreq.setNewLocation(loca);
                    TextUtils.sendChatMessage(player,"���Ѿ����ù�"+homeName+"�ˣ����ǳɹ�....");
                    return;
                }else{
                    TextUtils.sendChatMessage(player,"���Ǵ��͵���Ҫ1���飬���ľ��鲻�㡣");
                    return;
                }
            }
            hreq.setHome();
            TextUtils.sendChatMessage(player, "�ɹ����ô��͵�" + homeName);
            TextUtils.clickableContent2Send(player, EColor.YELLOW.code+"[�鿴����]","/home");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
