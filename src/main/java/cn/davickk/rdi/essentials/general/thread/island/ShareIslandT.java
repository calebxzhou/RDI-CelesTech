package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ShareIslandT extends Thread{
    private final ServerPlayerEntity player;
    private final String homeName="island";
    private final String player2Share;
    public ShareIslandT(ServerPlayerEntity player, String player2Share){
        this.player=player;
        this.player2Share=player2Share;
    }
    public void run(){
        try {
            ServerPlayerEntity player2=player.getServer().getPlayerList().getPlayerByUsername(player2Share);
            HomeRequest hreq=new HomeRequest(player,homeName);
            HomeRequest hreq2=new HomeRequest(player2,homeName);
            if(player2==null) {
                TextUtils.sendChatMessage(player, player2Share + "不在线，请您等待对方上线后再分享。");
                return;
            }
            /*Iterator<PlayerEntity> nearby=PlayerUtils.getNearbyPlayersInRange(player,5).iterator();
            if(!nearby.hasNext()){
                TextUtils.sendChatMessage(player,"对方不在你的附近");
                return;
            }
            if(!nearby.next().getDisplayName().getString().equalsIgnoreCase(player2Share))
                TextUtils.sendChatMessage(player,"在你附近的人并不是"+player2Share);*/
            Location loca=hreq.getHomeLocation();
            if(loca==null) {
                TextUtils.sendChatMessage(player,"无法读取位置，确定您的岛存在吗？");
                return;
            }
            if(hreq2.hasThisHome()){
                TextUtils.sendChatMessage(player,"提示：对方已经有岛了。");
                hreq2.setHomeWithLocation(loca,homeName+"_other",true);
                TextUtils.sendChatMessage(player,"成功把您的空岛分享给了"+player2Share);
                TextUtils.sendChatMessage(player2,player.getDisplayName().getString()+"把他的空岛分享给你了");
                TextUtils.clickableContent2Send(player2,"[立刻传送]","/home "+homeName+"_other"," ");
                return;
            }
            if(hreq2.setHomeWithLocation(loca,homeName,true))
            {
                TextUtils.sendChatMessage(player,"成功把您的空岛分享给了"+player2Share);
                TextUtils.sendChatMessage(player2,player.getDisplayName().getString()+"把他的空岛分享给你了");
                TextUtils.clickableContent2Send(player2,"[立刻传送]","/home "+homeName," ");
                return;
            }else TextUtils.sendChatMessage(player,"分享错误，请咨询腐竹");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //.getDisplayName().getString().equalsIgnoreCase(player2Share)
    }
}
