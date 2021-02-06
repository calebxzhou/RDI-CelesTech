package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.IslandLocation;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.request.IslandRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.forge.ForgeAdapter;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.world.World;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static cn.davickk.rdi.essentials.RDIEssentials.SQL_CONN;
import static cn.davickk.rdi.essentials.general.util.SQLUtils.*;

public class CreateIslandT extends Thread{
    private final ServerPlayerEntity player;
    public CreateIslandT(ServerPlayerEntity player){
        this.player=player;
    }
    public void run(){
        IslandRequest req = null;
        try {
            req = new IslandRequest(player);
            if (req.hasIsland()) {
                TextUtils.sendChatMessage(player, "���Ѿ���һ���յ��ˣ���˲��ܴ����µĿյ���");
                TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[����ǰ���ҵĿյ�]","/home island"," ");
                //TODO �ص��ҵĿյ�
                return;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        TextUtils.sendChatMessage(player,"׼�������յ�...���������λ�ÿ����𣿣��뾡��ѡ��û���˵ĵط���");
        PlayerUtils.randomTeleport(player,true);
        IFormattableTextComponent t1=
                TextUtils.getClickableContentComp(player,
                        EColor.BRIGHT_GREEN.code+"[���ԣ������ⴴ���յ���]","/createkdhere"," ");
        IFormattableTextComponent t2=
                TextUtils.getClickableContentComp(player,
                        EColor.RED.code+"[�����ԣ�����Ѱ��λ��]","/createkd"," ");
        TextUtils.sendChatMessage(player,t1.append(t2));
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
