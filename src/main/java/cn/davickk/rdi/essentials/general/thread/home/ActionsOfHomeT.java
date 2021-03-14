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
        //3x3x3��Χ��� ��û����� û��->����=��ɫ
        try {

            HomeRequest hreq=new HomeRequest(player,homeName);
            List<PlayerEntity> playerEntityList=
                    PlayerUtils.getNearbyPlayersInRange(player,3);
            //[����]  [������] [ɾ��] [����]    [����]
            String tpCont="[<==����==>]";
            String tpHover="�ص������";
            String renameCont="[������]";
            String delCont="[ɾ��]";
            String activCont="[����]";
            String activHover="��������ң�ʹ��ɴ���";
            String shareCont="[����]";
            String shareHover="������ҷ����";
            String green= EColor.BRIGHT_GREEN.code;
            String gray=EColor.GRAY.code;
            String red=EColor.RED.code;
            String gold=EColor.GOLD.code;
            String blk=EColor.BLACK.code;
            if(hreq.isActive()) {
                tpCont=green.concat(tpCont);
                activCont=" ";
                activHover="�Ѿ�������";
            }else{
                tpCont=gray.concat(tpCont);
                tpHover="δ����޷�����";
                int level=player.experienceLevel;
                if(level<5) {
                    if(hreq.getHomeCounts()==0)
                        activCont=green.concat(activCont);
                    else{
                        activHover="���鲻��5�����޷����������";
                        activCont=gray.concat(activCont);
                    }
                }else
                    activCont=green.concat(activCont);

            }
                shareCont=gold.concat(shareCont);


            //[����]  [������] [ɾ��] [����]    [����]
            IFormattableTextComponent tpHomeT=
                    TextUtils.getClickableContentComp(player,tpCont,
                            EHomeText.HOME.cmd.replace("%s",homeName),
                            tpHover);
            IFormattableTextComponent renameT=
                    TextUtils.getClickableContentComp(player,EColor.AQUA.code+renameCont
                            , "/renamehome","�ı�ҵ�����");
            IFormattableTextComponent delHomeT=
                    TextUtils.getClickableContentComp(player,red+delCont,
                            EHomeText.DELETE.cmd.replace("%s",homeName), "ɾ�������");
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
