package cn.davickk.rdi.essentials.general.thread.teleport;

import cn.davickk.rdi.essentials.general.request.TpaRequest;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.UUID;

public class SendTpaRequestT extends Thread{
    private final ServerPlayerEntity fromPlayer;
    private final ServerPlayerEntity toPlayer;
    public SendTpaRequestT(ServerPlayerEntity fromPlayer, ServerPlayerEntity toPlayer){
        this.fromPlayer=fromPlayer;
        this.toPlayer=toPlayer;
    }
    public void run(){
        try {
        UUID reqid= UUID.randomUUID();
        TpaRequest tpreq=new TpaRequest(fromPlayer,toPlayer,reqid.toString());
        tpreq.sendRequest();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
}
