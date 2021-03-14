package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.request.IslandRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;

public class CreateIslandT extends Thread{
    private final ServerPlayerEntity player;
    public CreateIslandT(ServerPlayerEntity player){
        this.player=player;
    }
    public void run(){

        try {
            IslandRequest req= new IslandRequest(player);
            if (req.hasIsland()) {
                TextUtils.sendChatMessage(player, "您已经有一个空岛了，因此不能创建新的空岛。");
                TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[立刻前往我的空岛]","/home island"," ");
                //TODO 回到我的空岛
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextUtils.sendChatMessage(player,"您觉得这个位置适合创建空岛吗？（以下选项可点击）（请尽量选择没有人的地方）");
        PlayerUtils.randomTeleport(player,true);
        IFormattableTextComponent t1=
                TextUtils.getClickableContentComp(player,
                        EColor.BRIGHT_GREEN.code+"[（点这里）可以，就在这创建空岛吧]","/createkdhere"," ");
        IFormattableTextComponent t2=
                TextUtils.getClickableContentComp(player,
                        EColor.RED.code+"[（点这里）不可以，重新寻找位置]","/createkd"," ");
        TextUtils.sendChatMessage(player,t1.append(t2));
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
