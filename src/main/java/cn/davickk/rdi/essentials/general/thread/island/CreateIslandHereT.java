package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.IslandLocation;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.request.IslandRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import com.sk89q.worldedit.world.World;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.management.PlayerList;

public class CreateIslandHereT extends Thread{
    private final ServerPlayerEntity player;
    public CreateIslandHereT(ServerPlayerEntity player){
        this.player=player;
    }
    public void run(){
        TextUtils.sendChatMessage(player,"���ڴ����յ�...");
       String name= player.getDisplayName().getString();
        int serverPort = player.getServer().getServerPort();
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            //Connection c = DriverManager.getConnection(DB_URL, USR, PWD);
            //TextUtils.sendGlobalChatMessage(pli,name+"���ڴ����յ�....");
            IslandRequest req = new IslandRequest(player);
            IslandLocation iloc=new IslandLocation(player);
            if (req.hasIsland()) {
                TextUtils.sendChatMessage(player, "���Ѿ���һ���յ��ˣ���˲��ܴ����µĿյ���");
                TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[����ǰ���ҵĿյ�]","/home island"," ");
                    //TODO �ص��ҵĿյ�
                return;
            }
            req.refresh();
            /*IslandLocation iloc = req.findAvailableIsland();
            if (iloc == null) {
                TextUtils.sendChatMessage(player, "�޷���ȡ�µĿյ�λ�ã�����ѯ����");
                return;
            }*/
            req.createIsland(iloc);
            req.pasteSchematic(iloc);
            req.refresh();
            TextUtils.sendChatMessage(player, "�ɹ������˿յ�");
            WorldUtils.removeGround(player);
            HomeRequest hreq=new HomeRequest(player);
            Location islandLoca=new Location(iloc.x, iloc.y-5,iloc.z,0.0f,0.0f,"minecraft:overworld");
            hreq.setHomeWithLocation(islandLoca,"island",true);
            TextUtils.clickableContent2Send(player,EColor.GOLD.code+"�������������ˡ�",""," ");
                //TODO �ص��ҵĿյ�




        }catch (Exception e){e.printStackTrace();}
    }
}
