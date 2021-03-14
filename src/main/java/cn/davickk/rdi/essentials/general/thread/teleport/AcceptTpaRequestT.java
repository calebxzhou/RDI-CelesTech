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
            TextUtils.sendChatMessage(toPlayer,"û���ҵ��Է�����ȷ�϶Է��Ƿ�����");
            return;
        }

        if(tpreq.acceptRequest()) {
            TextUtils.sendChatMessage(toPlayer, "�Ѿ����ܴ�������");
            TextUtils.sendChatMessage(fromPlayer, "�Է��Ѿ��������Ĵ�������");
            tpreq.deleteRequest();
        }
        else{
            TextUtils.sendChatMessage(toPlayer,"���������Ѿ�ʧЧ���ٳ�����Ч�ڢڶԷ����鲻��");
        }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
}
