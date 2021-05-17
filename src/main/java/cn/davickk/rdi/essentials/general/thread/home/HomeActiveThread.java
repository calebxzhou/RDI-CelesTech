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
                TextUtils.sendChatMessage(player,"没有找到"+homeName);
                return;
            }
            /*if (hreq.hasActiveHome()) {
                //TextUtils.sendChatMessage(player, "您已经激活过一个家了，继续激活" +homeName+"需要大约7个经验等级。");
                if(!PlayerUtils.minusXPLvl(player,2)) {
                    TextUtils.sendChatMessage(player,"您的经验等级不足，无法激活"+homeName);
                }else{
                    hreq.setActive(true);
                    TextUtils.sendChatMessage(player, "成功激活" + homeName + "。");
                    TextUtils.clickableContent2Send(player, EHomeText.HOME.text.replace("%s", homeName), EHomeText.HOME.cmd.replace("%s", homeName));

                }
            }else{*/
                    hreq.setActive(true);
                    TextUtils.sendChatMessage(player, "成功激活" + homeName + "，现在可以传送了。");
                    TextUtils.clickableContent2Send(player, EHomeText.HOME.text.replace("%s",homeName),EHomeText.HOME.cmd.replace("%s",homeName));

           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
