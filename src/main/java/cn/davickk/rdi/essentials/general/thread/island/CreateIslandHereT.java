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
        TextUtils.sendChatMessage(player,"���ڴ����յ�...");
        try{
            IslandRequest req = new IslandRequest(player);
            IslandLocation iloc=new IslandLocation(player);
            BlockPos bpos=player.getPosition();
            if (req.hasIsland()) {
                TextUtils.sendChatMessage(player, "���Ѿ���һ���յ��ˣ���˲��ܴ����µĿյ���");
                TextUtils.clickableContent2Send(player, EColor.GOLD.code+"[����ǰ���ҵĿյ�]","/home island"," ");
                    //TODO �ص��ҵĿյ�
                return;
            }
            req.createIsland(bpos);
            req.pasteSchematic(bpos);
            //req.refresh();
            TextUtils.sendChatMessage(player, "�ɹ������˿յ�");
            //WorldUtils.removeGround(player);
            HomeRequest hreq=new HomeRequest(player);
            Location islandLoca=new Location(iloc.x, iloc.y-5,iloc.z,0.0f,0.0f,"minecraft:overworld");
            hreq.setHomeWithLocation(islandLoca,"island",true);
            TextUtils.clickableContent2Send(player,EColor.GOLD.code+"�������������ˡ��������Ķ�Ⱥ�ļ����ֲᡣ",""," ");
                //TODO �ص��ҵĿյ�




        }catch (Exception e){e.printStackTrace();}
    }
}
