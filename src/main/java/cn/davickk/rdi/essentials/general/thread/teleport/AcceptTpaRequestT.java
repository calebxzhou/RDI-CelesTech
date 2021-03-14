package cn.davickk.rdi.essentials.general.thread.teleport;

import cn.davickk.rdi.essentials.general.request.TpaRequest;
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
        TpaRequest tpreq=new TpaRequest(null,toPlayer,reqid);
        ServerPlayerEntity fromPlayer=tpreq.getFromPlayer();

        if(fromPlayer==null){
            TextUtils.sendChatMessage(toPlayer,"没有找到对方，请确认对方是否在线");
            return;
        }

        if(tpreq.acceptRequest()) {
            TextUtils.sendChatMessage(toPlayer, "已经接受传送请求");
            TextUtils.sendChatMessage(fromPlayer, "对方已经接受您的传送请求");
            tpreq.deleteRequest();
        }
        else{
            TextUtils.sendChatMessage(toPlayer,"传送请求已经失效：①超过有效期②对方经验不足");
        }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
}
