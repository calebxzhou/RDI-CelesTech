package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.request.IslandRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;

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
            String removeGround="";
            String roll="";
            boolean otherIsland=ireq.isJoinedOthersIsland();
            if(ireq.hasIsland()||homereq.hasThisHome()||otherIsland){
                bkS= EColor.BRIGHT_GREEN.code+ "[<====���ؿյ�====>]";
                ob2lav=EColor.PURPLE.code+"[����ʯ->�ҽ�]";
                if(PlayerUtils.hasEnoughXPLvl(player,10))
                    water2ice=EColor.AQUA.code+"[ˮ->��]";
                share=EColor.GOLD.code+"[����]";
                coverTp=EColor.PINK.code+"[�Ĵ��͵�]";
                clearPhantom=EColor.DARK_BLUE.code+"[�����]";
                removeGround=EColor.PINK.code+"[�Ƴ��ݵ�]";
                if(PlayerUtils.hasEnoughXPLvl(player,5))
                    roll=EColor.AQUA.code+"[��Ƽ�תתת]";
            }
            else{
                creS=EColor.AQUA.code+"[�����յ�]";
            }

            IFormattableTextComponent createTxt=
                    TextUtils.getClickableContentComp(player,creS,"/createkd",
                            "����һ���µĿյ�");
            IFormattableTextComponent homeTxt;
            if(otherIsland)
                homeTxt=TextUtils.getClickableContentComp(player,bkS,"/home island_other",
                        "�������Ŀյ�");
            else
                homeTxt= TextUtils.getClickableContentComp(player,bkS,"/home island",
                        "�������Ŀյ�");
            IFormattableTextComponent obsi2LavaTxt=
                    TextUtils.getClickableContentComp(player,ob2lav,"/obsi2lava","������ʯ��Ϊ�ҽ�");
            IFormattableTextComponent water2iceTxt=
                    TextUtils.getClickableContentComp(player,water2ice,"/water2ice","��ˮ���������Ҫ���飩");
            IFormattableTextComponent shareTxt=
                    TextUtils.getClickableContentComp(player,share,"/sharehome","�����Ŀյ����������");
            IFormattableTextComponent coverTxt=
                    TextUtils.getClickableContentComp(player,coverTp,"/sethome island","����ǰ�����ڵ�λ������Ϊ�յ��Ĵ��͵�\n���⽫�Ḳ��ԭ�еĴ��͵㣬�޷��ָ���");
            IFormattableTextComponent clearPhanTxt=
                    TextUtils.getClickableContentComp(player,clearPhantom,
                            "/clearphantom","���ѣ�"+(int)player.experienceLevel*0.5+"����\n�����������");
            IFormattableTextComponent rollTxt=
                    TextUtils.getClickableContentComp(player,roll,
                            "/rroll","����5����������ת�̡�");
            TextUtils.sendChatMessage(player,createTxt.append(homeTxt));
            TextUtils.sendChatMessage(player,obsi2LavaTxt.append(water2iceTxt).append(shareTxt).append(rollTxt).append(coverTxt).append(clearPhanTxt));
            TextUtils.clickableContent2Send(player,removeGround,"/removeground"," ");
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

    }
}
