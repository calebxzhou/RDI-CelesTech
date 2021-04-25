package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.IslandLocation;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.request.IslandRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class CreateIslandHereT extends Thread{
    private final ServerPlayerEntity player;
    public CreateIslandHereT(ServerPlayerEntity player){
        this.player=player;
    }
    public void run(){
        TextUtils.sendChatMessage(player,"正在创建空岛...");
        try{
            IslandRequest req = new IslandRequest(player);
            IslandLocation iloc=new IslandLocation(player);
            BlockPos bpos=player.getPosition();
            if (req.hasIsland()) {
                TextUtils.sendChatMessage(player, "您已经有一个空岛了，因此不能创建新的空岛。");
                TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[立刻前往我的空岛]","/home island"," ");
                    //TODO 回到我的空岛
                return;
            }
            req.createIsland(bpos);
            req.pasteSchematic(bpos);
            //req.refresh();
            TextUtils.sendChatMessage(player, "成功创建了空岛");
            //WorldUtils.removeGround(player);
            HomeRequest hreq=new HomeRequest(player);
            Location islandLoca=new Location(iloc.x, iloc.y-5,iloc.z,0.0f,0.0f,"minecraft:overworld");
            hreq.setHomeWithLocation(islandLoca,"island",true);
            TextUtils.clickableContent2Send(player,EColor.GOLD.code+"可以正常游玩了。别忘了阅读群文件的手册。",""," ");
                //TODO 回到我的空岛




        }catch (Exception e){e.printStackTrace();}
    }
}
