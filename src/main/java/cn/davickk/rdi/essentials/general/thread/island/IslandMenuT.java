package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.command.impl.island.IslandCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.request.IslandRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;

import java.sql.SQLException;

public class IslandMenuT extends Thread{
    private ServerPlayerEntity player;
    public IslandMenuT(ServerPlayerEntity player){

        this.player=player;}

    public void run(){
        try {
            RDIEssentials.createSQLConnection();
            IslandRequest ireq=new IslandRequest(player);
            HomeRequest homereq=new HomeRequest(player,"island");
            String creS="";
            String bkS="";
            String ob2lav="";
            String water2ice="";
            String share="";
            String coverTp="";
            String clearPhantom="";
            if(ireq.hasIsland()||homereq.hasHome()){
                bkS= EColor.BRIGHT_GREEN.code+ "[<====���ؿյ�====>]";
                ob2lav=EColor.PURPLE.code+"[����ʯ->�ҽ�]";
                water2ice=EColor.AQUA.code+"[ˮ->��]";
                share=EColor.GOLD.code+"[����]";
                coverTp=EColor.PINK.code+"[�Ĵ��͵�]";
                clearPhantom=EColor.DARK_BLUE.code+"[�����]";
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
                    TextUtils.getClickableContentComp(player,ob2lav,"/obsi2lava","������ʯ��Ϊ�ҽ�");
            IFormattableTextComponent water2iceTxt=
                    TextUtils.getClickableContentComp(player,water2ice,"/water2ice","��ˮ���������Ҫ1���飩");
            IFormattableTextComponent shareTxt=
                    TextUtils.getClickableContentComp(player,share,"/sharehome","�����Ŀյ����������");
            IFormattableTextComponent coverTxt=
                    TextUtils.getClickableContentComp(player,coverTp,"/sethome island","���ѣ�1����\n����ǰ�����ڵ�λ������Ϊ�յ��Ĵ��͵�\n���⽫�Ḳ��ԭ�еĴ��͵㣬�޷��ָ���");
            IFormattableTextComponent clearPhanTxt=
                    TextUtils.getClickableContentComp(player,clearPhantom,
                            "/clearphantom","���ѣ�2����\n�����������");

            TextUtils.sendChatMessage(player,createTxt.append(homeTxt));
            TextUtils.sendChatMessage(player,obsi2LavaTxt.append(water2iceTxt).append(shareTxt).append(coverTxt).append(clearPhanTxt));

        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

    }
}
