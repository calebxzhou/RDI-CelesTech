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
                bkS= EColor.BRIGHT_GREEN.code+ "[<====返回空岛====>]";
                ob2lav=EColor.PURPLE.code+"[黑曜石->岩浆]";
                if(PlayerUtils.hasEnoughXPLvl(player,10))
                    water2ice=EColor.AQUA.code+"[水->冰]";
                share=EColor.GOLD.code+"[分享]";
                coverTp=EColor.PINK.code+"[改传送点]";
                clearPhantom=EColor.DARK_BLUE.code+"[清幻翼]";
                removeGround=EColor.PINK.code+"[移除草地]";
                if(PlayerUtils.hasEnoughXPLvl(player,5))
                    roll=EColor.AQUA.code+"[大科技转转转]";
            }
            else{
                creS=EColor.AQUA.code+"[创建空岛]";
            }

            IFormattableTextComponent createTxt=
                    TextUtils.getClickableContentComp(player,creS,"/createkd",
                            "创建一个新的空岛");
            IFormattableTextComponent homeTxt;
            if(otherIsland)
                homeTxt=TextUtils.getClickableContentComp(player,bkS,"/home island_other",
                        "返回您的空岛");
            else
                homeTxt= TextUtils.getClickableContentComp(player,bkS,"/home island",
                        "返回您的空岛");
            IFormattableTextComponent obsi2LavaTxt=
                    TextUtils.getClickableContentComp(player,ob2lav,"/obsi2lava","将黑曜石熔为岩浆");
            IFormattableTextComponent water2iceTxt=
                    TextUtils.getClickableContentComp(player,water2ice,"/water2ice","让水结冰。（需要经验）");
            IFormattableTextComponent shareTxt=
                    TextUtils.getClickableContentComp(player,share,"/sharehome","把您的空岛分享给朋友");
            IFormattableTextComponent coverTxt=
                    TextUtils.getClickableContentComp(player,coverTp,"/sethome island","将当前您所在的位置设置为空岛的传送点\n（这将会覆盖原有的传送点，无法恢复）");
            IFormattableTextComponent clearPhanTxt=
                    TextUtils.getClickableContentComp(player,clearPhantom,
                            "/clearphantom","花费："+(int)player.experienceLevel*0.5+"经验\n立刻清除幻翼。");
            IFormattableTextComponent rollTxt=
                    TextUtils.getClickableContentComp(player,roll,
                            "/rroll","花费5经验启动大转盘。");
            TextUtils.sendChatMessage(player,createTxt.append(homeTxt));
            TextUtils.sendChatMessage(player,obsi2LavaTxt.append(water2iceTxt).append(shareTxt).append(rollTxt).append(coverTxt).append(clearPhanTxt));
            TextUtils.clickableContent2Send(player,removeGround,"/removeground"," ");
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }

    }
}
