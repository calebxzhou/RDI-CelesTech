package cn.davickk.rdi.essentials.general.thread.teleport;

import cn.davickk.rdi.essentials.general.request.TpaRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.UUID;

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
        if(!tpreq.acceptRequest() || fromPlayer==null)
            TextUtils.sendChatMessage(toPlayer,"û���ҵ������������....����Է����·��͡�");
        else{
            TextUtils.sendChatMessage(toPlayer,"OK");
            TextUtils.sendChatMessage(fromPlayer,"�Է��Ѿ��������Ĵ�������");
        }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
}
