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
                        TextUtils.sendChatMessage(player,"成功回到"+homeName);
                    }else{
                        TextUtils.sendChatMessage(player,"一股强大的阻力让您在原地动弹不得.....");
                    }
                }else {
                    if(PlayerUtils.hasEnoughXPLvl(player,HomeUtils.ACTIV_REQUIRE_XP)){
                        TextUtils.sendChatMessage(player,"这个家还没有激活，现在就激活吗？");
                        HomeUtils.tellPlayerActivHome(player,homeName);
                    }else{
                        TextUtils.sendChatMessage(player,"这个家没有激活，并且您的经验不足，因此无法传送。");
                    }
                }
            }else{
                TextUtils.sendChatMessage(player,"没有找到这个家。");
                HomeUtils.tellPlayerListHome(player);
            }
            /*EHomeResult result=hreq.goHome();
            if(result.equals(EHomeResult.OK)) {
                TextUtils.sendChatMessage(player, "成功到达" + homeName);

            }else if(result.equals(EHomeResult.HOME_NOT_EXIST)){

                TextUtils.sendChatMessage(player, "没有找到 "+homeName);
                TextUtils.clickableContent2Send(player, EHomeText.LISTHOME.text, EHomeText.LISTHOME.cmd);

            }else if(result.equals(EHomeResult.HOME_NOT_ACTIVE)){

                TextUtils.sendChatMessage(player, homeName + "还没有激活，因此无法传送。");
                TextUtils.clickableContent2Send(player, EHomeText.ACTIVATE.text.replace("%s",homeName),
                        EHomeText.ACTIVATE.cmd.replace("%s",homeName));
            }else if(result.equals(EHomeResult.NO_ENOUGH_XP)){
                TextUtils.sendChatMessage(player,"经验不足");
                TextUtils.clickableContent2Send(player,"[原因]","",
                        "跨世界传送：5\n非跨世界：\n距离>1000n: n，");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}