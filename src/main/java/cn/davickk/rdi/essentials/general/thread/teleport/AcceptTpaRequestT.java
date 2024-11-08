package cn.davickk.rdi.essentials.general.thread.teleport;

import cn.davickk.rdi.essentials.general.model.Location;
import cn.davickk.rdi.essentials.general.model.PlayerTpaRequest;
import cn.davickk.rdi.essentials.general.request.TpaRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

public class AcceptTpaRequestT extends Thread{
    private final ServerPlayerEntity toPlayer;
    private final String reqid;
    public AcceptTpaRequestT(ServerPlayerEntity toPlayer,String reqid){
        this.toPlayer=toPlayer;
        this.reqid=reqid;
    }
    public void run(){
        try {
            PlayerTpaRequest tpaRequest=TpaRequest.getReqMap().get(reqid);
            if(tpaRequest==null){
                TextUtils.sendChatMessage(toPlayer,"没有找到此请求。请确认1.对方是否在线2.是否已经接受了此请求");
                TextUtils.sendChatMessage(toPlayer,"请求ID:"+reqid);
                return;
            }
            TextUtils.sendChatMessage(toPlayer,"正在传送..");
            TextUtils.sendChatMessage(tpaRequest.getFromPlayer(),"正在传送..");
            PlayerUtils.teleportPlayer(tpaRequest.getFromPlayer(),new Location(toPlayer));
            TpaRequest.getReqMap().remove(reqid);
        //TpaRequest tpreq=new TpaRequest(null,toPlayer,reqid);
        /*ServerPlayerEntity fromPlayer=tpreq.getFromPlayer();

        if(fromPlayer==null){

        }

        if(tpreq.acceptRequest()) {
            TextUtils.sendChatMessage(toPlayer, "已经接受传送请求");
            TextUtils.sendChatMessage(fromPlayer, "对方已经接受您的传送请求");
            tpreq.deleteRequest();
        }
        else{
            TextUtils.sendChatMessage(toPlayer,"传送请求已经失效：①超过有效期②对方经验不足");
        }*/
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
}
