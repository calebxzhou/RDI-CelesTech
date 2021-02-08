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
                TextUtils.sendChatMessage(player,"������ɾ���յ����͵�");
                return;
            }
            HomeRequest hreq=new HomeRequest(player,homeName);
            if(!hreq.hasThisHome()) {
                TextUtils.sendChatMessage(player,"û���ҵ�"+homeName);
                TextUtils.clickableContent2Send(player, EHomeText.LISTHOME.text, EHomeText.LISTHOME.cmd);
                return;
            }
            if(hreq.delHome())
                TextUtils.sendChatMessage(player, "�ɹ�ɾ����" + homeName);
            else
                TextUtils.sendChatMessage(player,"ɾ��ʧ�ܣ�����ѯ����");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}