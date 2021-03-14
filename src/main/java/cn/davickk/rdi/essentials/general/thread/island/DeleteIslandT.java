package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.request.IslandRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;

public class DeleteIslandT extends Thread{
    private final ServerPlayerEntity player;
    public DeleteIslandT(ServerPlayerEntity player){
        this.player=player;
    }
    public void run(){

        try {
            IslandRequest req= new IslandRequest(player);
            HomeRequest hre=new HomeRequest(player);
            if (!req.hasIsland()) {
                TextUtils.sendChatMessage(player, "您没有空岛，因此不能删除空岛。");
                return;
            }
            player.inventory.clear();
            player.setExperienceLevel(0);
            player.setHealth(0.1F);
            hre.delAllHome();
            req.deleteIsland();
            PlayerUtils.teleportPlayer(player, ServerUtils.SPAWN_LOCA);
            TextUtils.sendChatMessage(player,"成功删除空岛");
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* String name= player.getDisplayName().getString();
        int serverPort = player.getServer().getServerPort();
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            //Connection c = DriverManager.getConnection(DB_URL, USR, PWD);
            PlayerList pli=player.getServer().getPlayerList();
            pli.removePlayer(player);
            TextUtils.sendGlobalChatMessage(pli,name+"正在创建空岛....");
            IslandRequest req = new IslandRequest(player);
            if (req.hasIsland()) {
                TextUtils.sendChatMessage(player, "您已经有一个空岛了，因此不能创建新的空岛。");
                TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[立刻前往我的空岛]","/home island"," ");
                    //TODO 回到我的空岛
                return;
            }
            req.refresh();
            IslandLocation iloc = req.findAvailableIsland();
            if (iloc == null) {
                TextUtils.sendChatMessage(player, "无法获取新的空岛位置，请咨询腐竹");
                return;
            }
            req.createIsland(iloc);
            req.pasteSchematic(iloc);
            req.refresh();
            TextUtils.sendChatMessage(player, "成功创建了空岛");
            HomeRequest hreq=new HomeRequest(player);
            Location islandLoca=new Location(iloc.x, iloc.y-5,iloc.z,0.0f,0.0f,"minecraft:overworld");
            hreq.setHomeWithLocation(islandLoca,"island",true);
            TextUtils.clickableContent2Send(player,EColor.GOLD.code+"请退出服务器，重新登录后查看空岛",""," ");
                //TODO 回到我的空岛




        }catch (Exception e){e.printStackTrace();}*/
    }
}
