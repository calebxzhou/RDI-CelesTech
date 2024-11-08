package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;

public class IslandMenuT extends Thread{
    private ServerPlayerEntity player;
    public IslandMenuT(ServerPlayerEntity player){

        this.player=player;}

    public void run(){
        try {
            HomeRequest homereq=new HomeRequest(player,"island");
            //OpenScreenUtils.openInv(player,"RDI CelesTech Menu 2.0");
            //todo
            String bkS= EColor.BRIGHT_GREEN.code+ "返回空岛";
            String share=EColor.AQUA.code+        "邀请他人";
            String changeTp=EColor.RED.code+      "改传送点";
            String ut=EColor.ORANGE.code+         "实用工具";
            String roll=EColor.GOLD.code+           "科技抽奖";
            if(homereq.getHomeCounts()==0){
                TextUtils.clickableContent2Send(player,EColor.AQUA.code+"创建空岛（点这里）","/createkd","创建一个新的空岛");
                return;
            }
            IFormattableTextComponent homeTxt;
            /*if(otherIsland)
                homeTxt=TextUtils.getClickableContentComp(bkS,"/home island_other", "返回您的空岛");
            else*/
                homeTxt= TextUtils.getClickableContentComp(bkS,"/home island", "返回您的空岛");
            IFormattableTextComponent space=
                    new StringTextComponent("   ");
            IFormattableTextComponent coverTxt=
                    TextUtils.getClickableContentComp(changeTp,"/updatehome island --locate here","将当前您所在的位置设置为空岛的传送点\n（这将会覆盖原有的传送点，无法恢复）");
            IFormattableTextComponent utTxt=
                    TextUtils.getClickableContentComp(ut,"/rdi","实用工具大合集");
            IFormattableTextComponent rollTxt=
                    TextUtils.getClickableContentComp(roll, "/rroll","花费经验启动大转盘。");
            TextUtils.sendChatMessage(player,homeTxt.append(space).append(coverTxt));
            TextUtils.sendChatMessage(player,space);
            TextUtils.sendChatMessage(player,utTxt.append(space).append(rollTxt));
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

    }
}
