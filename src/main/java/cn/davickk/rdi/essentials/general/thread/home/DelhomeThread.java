package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.enums.EHomeText;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

public class DelhomeThread extends Thread {
    private ServerPlayerEntity player;
    private String homeName;

    public DelhomeThread(ServerPlayerEntity player, String homeName) {
        this.player = player;
        this.homeName = homeName;
    }

    public void run() {
        try {
            if(homeName.equals("island")){
                TextUtils.sendChatMessage(player,"不可以删除空岛传送点");
                return;
            }
            HomeRequest hreq=new HomeRequest(player,homeName);
            if(!hreq.hasThisHome()) {
                TextUtils.sendChatMessage(player,"没有找到"+homeName);
                TextUtils.clickableContent2Send(player, EHomeText.LISTHOME.text, EHomeText.LISTHOME.cmd);
                return;
            }
            if(hreq.delHome())
                TextUtils.sendChatMessage(player, "成功删除了" + homeName);
            else
                TextUtils.sendChatMessage(player,"删除失败，请咨询腐竹");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}