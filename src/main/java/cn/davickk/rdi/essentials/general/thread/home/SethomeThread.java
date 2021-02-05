package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.*;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.sql.*;

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
            int homes=hreq.getHomeCount();
            if(hreq.isReachedMax()) {
                TextUtils.sendChatMessage(player,"���ֻ������"+HomeUtils.MAX_HOME+"�����͵㡣("+homes+"/"+HomeUtils.MAX_HOME+")");
                return;
            }
            if(hreq.existsHome()){
                TextUtils.sendChatMessage(player,"���Ѿ����ù�"+homeName+"�ˣ��������ְɡ�");
                return;
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
