package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.enums.EHomeText;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

public class HomeActiveThread extends Thread {
    private final ServerPlayerEntity player;
    private final String homeName;

    public HomeActiveThread(ServerPlayerEntity player, String homeName) {
        this.player = player;
        this.homeName = homeName;
    }


    public void run() {
        try {
            HomeRequest hreq=new HomeRequest(player,homeName);
            if(!hreq.hasThisHome()){
                TextUtils.sendChatMessage(player,"û���ҵ�"+homeName);
                return;
            }
            /*if (hreq.hasActiveHome()) {
                //TextUtils.sendChatMessage(player, "���Ѿ������һ�����ˣ���������" +homeName+"��Ҫ��Լ7������ȼ���");
                if(!PlayerUtils.minusXPLvl(player,2)) {
                    TextUtils.sendChatMessage(player,"���ľ���ȼ����㣬�޷�����"+homeName);
                }else{
                    hreq.setActive(true);
                    TextUtils.sendChatMessage(player, "�ɹ�����" + homeName + "��");
                    TextUtils.clickableContent2Send(player, EHomeText.HOME.text.replace("%s", homeName), EHomeText.HOME.cmd.replace("%s", homeName));

                }
            }else{*/
                    hreq.setActive(true);
                    TextUtils.sendChatMessage(player, "�ɹ�����" + homeName + "�����ڿ��Դ����ˡ�");
                    TextUtils.clickableContent2Send(player, EHomeText.HOME.text.replace("%s",homeName),EHomeText.HOME.cmd.replace("%s",homeName));

           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
