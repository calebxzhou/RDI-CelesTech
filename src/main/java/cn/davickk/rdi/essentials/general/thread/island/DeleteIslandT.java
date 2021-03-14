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
                TextUtils.sendChatMessage(player, "��û�пյ�����˲���ɾ���յ���");
                return;
            }
            player.inventory.clear();
            player.setExperienceLevel(0);
            player.setHealth(0.1F);
            hre.delAllHome();
            req.deleteIsland();
            PlayerUtils.teleportPlayer(player, ServerUtils.SPAWN_LOCA);
            TextUtils.sendChatMessage(player,"�ɹ�ɾ���յ�");
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
            TextUtils.sendGlobalChatMessage(pli,name+"���ڴ����յ�....");
            IslandRequest req = new IslandRequest(player);
            if (req.hasIsland()) {
                TextUtils.sendChatMessage(player, "���Ѿ���һ���յ��ˣ���˲��ܴ����µĿյ���");
                TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[����ǰ���ҵĿյ�]","/home island"," ");
                    //TODO �ص��ҵĿյ�
                return;
            }
            req.refresh();
            IslandLocation iloc = req.findAvailableIsland();
            if (iloc == null) {
                TextUtils.sendChatMessage(player, "�޷���ȡ�µĿյ�λ�ã�����ѯ����");
                return;
            }
            req.createIsland(iloc);
            req.pasteSchematic(iloc);
            req.refresh();
            TextUtils.sendChatMessage(player, "�ɹ������˿յ�");
            HomeRequest hreq=new HomeRequest(player);
            Location islandLoca=new Location(iloc.x, iloc.y-5,iloc.z,0.0f,0.0f,"minecraft:overworld");
            hreq.setHomeWithLocation(islandLoca,"island",true);
            TextUtils.clickableContent2Send(player,EColor.GOLD.code+"���˳������������µ�¼��鿴�յ�",""," ");
                //TODO �ص��ҵĿյ�




        }catch (Exception e){e.printStackTrace();}*/
    }
}
