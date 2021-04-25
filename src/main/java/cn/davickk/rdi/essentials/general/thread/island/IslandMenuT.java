package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.request.IslandRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
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

            PlayerUtils.sendLoading(player);
            IslandRequest ireq=new IslandRequest(player);
            HomeRequest homereq=new HomeRequest(player,"island");
            //OpenScreenUtils.openInv(player,"RDI CelesTech Menu 2.0");
            //todo
            String bkS= EColor.BRIGHT_GREEN.code+ "���ؿյ�";;
            String share=EColor.AQUA.code+        "����յ�";
            String changeTp=EColor.RED.code+      "�Ĵ��͵�";
            String ut=EColor.ORANGE.code+         "ʵ�ù���";
            String roll=EColor.GOLD.code+EColor.BOLD.code+           "������Ƽ�תתת����";
            boolean otherIsland=ireq.isJoinedOthersIsland();
            if(!(ireq.hasIsland()||homereq.hasThisHome()||otherIsland)){
                TextUtils.clickableContent2Send(player,EColor.AQUA.code+"�����յ��������","/createkd","����һ���µĿյ�");
                return;
            }
            IFormattableTextComponent homeTxt;
            if(otherIsland)
                homeTxt=TextUtils.getClickableContentComp(player,bkS,"/home island_other", "�������Ŀյ�");
            else
                homeTxt= TextUtils.getClickableContentComp(player,bkS,"/home island", "�������Ŀյ�");
            IFormattableTextComponent space=
                    new StringTextComponent("   ");
            IFormattableTextComponent shareTxt=
                    TextUtils.getClickableContentComp(player,share,"/sharehome","�����Ŀյ����������");
            IFormattableTextComponent coverTxt=
                    TextUtils.getClickableContentComp(player,changeTp,"/sethome island","����ǰ�����ڵ�λ������Ϊ�յ��Ĵ��͵�\n���⽫�Ḳ��ԭ�еĴ��͵㣬�޷��ָ���");
            IFormattableTextComponent utTxt=
                    TextUtils.getClickableContentComp(player,ut,"/rdi","ʵ�ù��ߴ�ϼ�");
            IFormattableTextComponent rollTxt=
                    TextUtils.getClickableContentComp(player,roll, "/rroll","���Ѿ���������ת�̡�");
            TextUtils.sendChatMessage(player,homeTxt.append(space).append(coverTxt));
            TextUtils.sendChatMessage(player,space);
            TextUtils.sendChatMessage(player,utTxt.append(space).append(shareTxt).append(space).append(rollTxt));
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

    }
}
