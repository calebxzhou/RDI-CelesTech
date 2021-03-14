package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.HomeUtils;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class HomeThread extends Thread {
    private ServerPlayerEntity player;
    private String homeName;

    public HomeThread(ServerPlayerEntity player, String homeName) {
        this.player = player;
        this.homeName = homeName;
    }

    public void run() {
        try {
            HomeRequest hreq=new HomeRequest(player,homeName);
            if(hreq.hasThisHome()) {
                if (hreq.isActive()){
                    if(hreq.getHomeLocation().getDims().equals(new ResourceLocation("minecraft:overworld"))){
                        hreq.goHome();
                        TextUtils.sendChatMessage(player,"�ɹ��ص�"+homeName);
                    }else{
                        TextUtils.sendChatMessage(player,"һ��ǿ�������������ԭ�ض�������.....");
                    }
                }else {
                    if(PlayerUtils.hasEnoughXPLvl(player,HomeUtils.ACTIV_REQUIRE_XP)){
                        TextUtils.sendChatMessage(player,"����һ�û�м�����ھͼ�����");
                        HomeUtils.tellPlayerActivHome(player,homeName);
                    }else{
                        TextUtils.sendChatMessage(player,"�����û�м���������ľ��鲻�㣬����޷����͡�");
                    }
                }
            }else{
                TextUtils.sendChatMessage(player,"û���ҵ�����ҡ�");
                HomeUtils.tellPlayerListHome(player);
            }
            /*EHomeResult result=hreq.goHome();
            if(result.equals(EHomeResult.OK)) {
                TextUtils.sendChatMessage(player, "�ɹ�����" + homeName);

            }else if(result.equals(EHomeResult.HOME_NOT_EXIST)){

                TextUtils.sendChatMessage(player, "û���ҵ� "+homeName);
                TextUtils.clickableContent2Send(player, EHomeText.LISTHOME.text, EHomeText.LISTHOME.cmd);

            }else if(result.equals(EHomeResult.HOME_NOT_ACTIVE)){

                TextUtils.sendChatMessage(player, homeName + "��û�м������޷����͡�");
                TextUtils.clickableContent2Send(player, EHomeText.ACTIVATE.text.replace("%s",homeName),
                        EHomeText.ACTIVATE.cmd.replace("%s",homeName));
            }else if(result.equals(EHomeResult.NO_ENOUGH_XP)){
                TextUtils.sendChatMessage(player,"���鲻��");
                TextUtils.clickableContent2Send(player,"[ԭ��]","",
                        "�����紫�ͣ�5\n�ǿ����磺\n����>1000n: n��");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}