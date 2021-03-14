package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.enums.EHomeText;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;

import java.util.List;

public class ActionsOfHomeT extends Thread{
    private ServerPlayerEntity player;
    private String homeName;
    public ActionsOfHomeT(ServerPlayerEntity player,String homeName)
    {
        this.player=player;
        this.homeName=homeName;
    }
    public void run(){
        RDIEssentials.createSQLConnection();
        //3x3x3范围检测 有没有玩家 没有->分享=灰色
        try {

            HomeRequest hreq=new HomeRequest(player,homeName);
            List<PlayerEntity> playerEntityList=
                    PlayerUtils.getNearbyPlayersInRange(player,3);
            //[传送]  [重命名] [删除] [激活]    [分享]
            String tpCont="[<==传送==>]";
            String tpHover="回到这个家";
            String renameCont="[重命名]";
            String delCont="[删除]";
            String activCont="[激活]";
            String activHover="激活这个家，使其可传送";
            String shareCont="[分享]";
            String shareHover="把这个家分享给";
            String green= EColor.BRIGHT_GREEN.code;
            String gray=EColor.GRAY.code;
            String red=EColor.RED.code;
            String gold=EColor.GOLD.code;
            String blk=EColor.BLACK.code;
            if(hreq.isActive()) {
                tpCont=green.concat(tpCont);
                activCont=" ";
                activHover="已经激活了";
            }else{
                tpCont=gray.concat(tpCont);
                tpHover="未激活，无法传送";
                int level=player.experienceLevel;
                if(level<5) {
                    if(hreq.getHomeCounts()==0)
                        activCont=green.concat(activCont);
                    else{
                        activHover="经验不足5级，无法激活这个家";
                        activCont=gray.concat(activCont);
                    }
                }else
                    activCont=green.concat(activCont);

            }
                shareCont=gold.concat(shareCont);


            //[传送]  [重命名] [删除] [激活]    [分享]
            IFormattableTextComponent tpHomeT=
                    TextUtils.getClickableContentComp(player,tpCont,
                            EHomeText.HOME.cmd.replace("%s",homeName),
                            tpHover);
            IFormattableTextComponent renameT=
                    TextUtils.getClickableContentComp(player,EColor.AQUA.code+renameCont
                            , "/renamehome","改变家的名称");
            IFormattableTextComponent delHomeT=
                    TextUtils.getClickableContentComp(player,red+delCont,
                            EHomeText.DELETE.cmd.replace("%s",homeName), "删除这个家");
            IFormattableTextComponent activHomeT=
                    TextUtils.getClickableContentComp(player,activCont,
                            EHomeText.ACTIVATE.cmd.replace("%s",homeName),
                            activHover);
            IFormattableTextComponent shareHomeT=
                    TextUtils.getClickableContentComp(player,shareCont,
                            EHomeText.SHARE.cmd.replace("%s","")
                                    .replace("%p",""),shareHover);
            TextUtils.sendChatMessage(player,TextUtils.appendTwoComp(homeName+" -> ",
                    tpHomeT.append(renameT).append(shareHomeT).append(activHomeT).append(delHomeT)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
