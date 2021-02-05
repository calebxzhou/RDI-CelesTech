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
                bkS= EColor.BRIGHT_GREEN.code+ "[回岛]";
                ob2lav=EColor.PURPLE.code+"[将黑曜石熔为岩浆]";
                water2ice=EColor.AQUA.code+"[让水结冰]";
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

            TextUtils.sendChatMessage(player,createTxt.append(homeTxt).append(obsi2LavaTxt).append(water2iceTxt).append(shareTxt));


        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

    }
}
