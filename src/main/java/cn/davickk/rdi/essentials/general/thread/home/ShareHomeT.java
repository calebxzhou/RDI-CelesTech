package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ShareHomeT extends Thread{
    private final ServerPlayerEntity player;
    private final String homeName;
    private final String player2Share;
    public ShareHomeT(ServerPlayerEntity player,String homeName,String player2Share){
        this.player=player;
        this.homeName=homeName;
        this.player2Share=player2Share;
    }
    public void run(){
        /*try {
            ServerPlayerEntity player2=player.getServer().getPlayerList().getPlayerByUsername(player2Share);
            HomeRequest hreq=new HomeRequest(player,homeName);
            HomeRequest hreq2=new HomeRequest(player2,homeName);
            if(player2==null) {
                TextUtils.sendChatMessage(player, player2Share + "不在线。");
                return;
            }
            *//*Iterator<PlayerEntity> nearby=PlayerUtils.getNearbyPlayersInRange(player,5).iterator();
            if(!nearby.hasNext()){
                TextUtils.sendChatMessage(player,"对方不在你的附近");
                return;
            }
            if(!nearby.next().getDisplayName().getString().equalsIgnoreCase(player2Share))
                TextUtils.sendChatMessage(player,"在你附近的人并不是"+player2Share);*//*
            Location loca=hreq.getHomeLocation();
            if(loca==null) {
                TextUtils.sendChatMessage(player,"无法读取位置，确定"+homeName+"存在吗？");
                return;
            }
            if(hreq2.hasThisHome()){
                //TextUtils.sendChatMessage(player,"对方已经有"+homeName+"这个家了，请尝试更换名称");

                hreq2.setHomeWithLocation(loca,homeName+"_other",true);
                TextUtils.sendChatMessage(player,"成功把您的空岛分享给了"+player2Share);
                TextUtils.sendChatMessage(player2,player.getDisplayName().getString()+"把他的空岛分享给你了");
                TextUtils.clickableContent2Send(player2,"[立刻传送]","/home "+homeName+"_other"," ");
                return;
            }
            if(hreq2.setHomeWithLocation(loca,homeName,true))
            {
                TextUtils.sendChatMessage(player,"成功把"+homeName+"分享给了"+player2Share);
                TextUtils.sendChatMessage(player2,player.getDisplayName().getString()+"把他的"+homeName+"分享给你了");
                TextUtils.clickableContent2Send(player2,"[立刻传送]","/home "+homeName," ");


                return;
            }else TextUtils.sendChatMessage(player,"分享错误，请咨询腐竹");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //.getDisplayName().getString().equalsIgnoreCase(player2Share)
    }
}
