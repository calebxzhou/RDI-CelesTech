package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;

public class CreateIslandHereT extends Thread{
    private final ServerPlayerEntity player;
    public CreateIslandHereT(ServerPlayerEntity player){
        this.player=player;
    }
    public void run(){
        TextUtils.sendChatMessage(player,"���ڴ����յ�...�벻Ҫ�ƶ�...");
        try{
            //IslandRequest req = new IslandRequest(player);
            //IslandLocation iloc=new IslandLocation(player);
            HomeRequest hreq=new HomeRequest(player);
            BlockPos bpos=player.getPosition();
            if (hreq.getHomeCounts()>0) {
                TextUtils.sendChatMessage(player, "���Ѿ���һ���յ��ˣ���˲��ܴ����µĿյ���");
                return;
            }
            hreq.setHomeWithLocation(new Location(player),"island",true);
            WorldUtils.pasteIslandSchematic(bpos,player);
            TextUtils.sendChatMessage(player, "�ɹ������˿յ�");
            TextUtils.clickableContent2Send(player,EColor.GOLD.code+"�������������ˡ����Ķ�Ⱥ�ļ����ֲᡣ",""," ");
                //TODO �ص��ҵĿյ�




        }catch (Exception e){e.printStackTrace();}
    }
}
