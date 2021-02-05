package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.command.impl.island.IslandCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.request.IslandRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;

import java.sql.SQLException;

public class IslandMenuT extends Thread{
    private ServerPlayerEntity player;
    public IslandMenuT(ServerPlayerEntity player){
        RDIEssentials.createSQLConnection();
        this.player=player;}

    public void run(){
        try {
            IslandRequest ireq=new IslandRequest(player);
            String creS="";
            String bkS="";
            String ob2lav="";
            String water2ice="";
            String share="";
            String coverTp="";
            if(ireq.hasIsland()){
                bkS= EColor.BRIGHT_GREEN.code+ "[<==返回空岛==>]";
                ob2lav=EColor.PURPLE.code+"[将黑曜石熔为岩浆]";
                water2ice=EColor.AQUA.code+"[让水结冰]";
                share=EColor.GOLD.code+"[分享空岛]";
                coverTp=EColor.PINK.code+"[更改空岛传送点（这将会覆盖原有的传送点，无法恢复）]";
            }
            else{
                creS=EColor.AQUA.code+"[创建空岛]";
            }

            IFormattableTextComponent createTxt=
                    TextUtils.getClickableContentComp(player,creS,"/createkd",
                            "创建一个新的空岛");
            IFormattableTextComponent homeTxt=
                    TextUtils.getClickableContentComp(player,bkS,"/home island",
                            "返回您的空岛");
            IFormattableTextComponent obsi2LavaTxt=
                    TextUtils.getClickableContentComp(player,ob2lav,"/obsi2lava"," ");
            IFormattableTextComponent water2iceTxt=
                    TextUtils.getClickableContentComp(player,water2ice,"/water2ice"," ");
            IFormattableTextComponent shareTxt=
                    TextUtils.getClickableContentComp(player,share,"/sharehome","把您的空岛分享给朋友");
            IFormattableTextComponent coverTxt=
                    TextUtils.getClickableContentComp(player,coverTp,"/sethome island","将当前您所在的位置设置为空岛的传送点");
            TextUtils.sendChatMessage(player,createTxt.append(homeTxt).append(obsi2LavaTxt).append(water2iceTxt).append(shareTxt).append(coverTxt));


        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

    }
}
