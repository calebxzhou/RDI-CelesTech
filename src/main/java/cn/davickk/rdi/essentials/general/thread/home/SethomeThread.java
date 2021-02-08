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
                TextUtils.sendChatMessage(player,"���ֻ������"+HomeUtils.MAX_HOME+"�����͵㡣("+homes+"/"+HomeUtils.MAX_HOME+")");
                return;
            }
            if(hreq.hasThisHome()){
                if(PlayerUtils.minusXPLvl(player,1))
                {
                    Location loca=new Location(player);
                    double diff1=Math.abs(loca.x-ServerUtils.SPAWN_LOCA.x);
                    double diff2=Math.abs(loca.z-ServerUtils.SPAWN_LOCA.z);
                    if(loca.x<20 && loca.z<20)
                        TextUtils.sendChatMessage(player,"�����������������ô��͵㡣");
                    else{
                    hreq.setNewLocation(loca);
                    TextUtils.sendChatMessage(player,"���Ѿ����ù�"+homeName+"�ˣ����ǳɹ�....");
                    }
                    return;
                }else{
                    TextUtils.sendChatMessage(player,"���Ǵ��͵���Ҫ1���飬���ľ��鲻�㡣");
                    return;
                }


            }
            if(hreq.setHome()){
            TextUtils.sendChatMessage(player, "�ɹ����ô��͵�" + homeName);
            TextUtils.clickableContent2Send(player, EColor.YELLOW.code+"[�鿴����]","/home");
            }else
                TextUtils.sendChatMessage(player,"�޷����üң�����ѯ����");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
