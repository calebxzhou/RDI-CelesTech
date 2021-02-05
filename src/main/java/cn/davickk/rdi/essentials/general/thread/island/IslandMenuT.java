package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.general.command.impl.island.IslandCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.request.IslandRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;

import java.sql.SQLException;

public class IslandMenuT extends Thread{
    private ServerPlayerEntity player;
    public IslandMenuT(ServerPlayerEntity player){this.player=player;}

    public void run(){
        try {
            IslandRequest ireq=new IslandRequest(player);
            String creS="";
            String bkS="";
            String ob2lav="";
            String water2ice="";
            String share="";
            if(ireq.hasIsland()){
                bkS= EColor.BRIGHT_GREEN.code+ "[�ص�]";
                ob2lav=EColor.PURPLE.code+"[������ʯ��Ϊ�ҽ�]";
                water2ice=EColor.AQUA.code+"[��ˮ���]";
            }
            else{
                creS=EColor.AQUA.code+"[�����յ�]";
            }

            IFormattableTextComponent createTxt=
                    TextUtils.getClickableContentComp(player,creS,"/createkd",
                            "����һ���µĿյ�");
            IFormattableTextComponent homeTxt=
                    TextUtils.getClickableContentComp(player,bkS,"/home island",
                            "�������Ŀյ�");
            IFormattableTextComponent obsi2LavaTxt=
                    TextUtils.getClickableContentComp(player,ob2lav,"/obsi2lava"," ");
            IFormattableTextComponent water2iceTxt=
                    TextUtils.getClickableContentComp(player,water2ice,"/water2ice"," ");
            IFormattableTextComponent shareTxt=
                    TextUtils.getClickableContentComp(player,share,"/sharehome","�����Ŀյ����������");

            TextUtils.sendChatMessage(player,createTxt.append(homeTxt).append(obsi2LavaTxt).append(water2iceTxt).append(shareTxt));


        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

    }
}
