package cn.davickk.rdi.essentials.general.thread.teleport;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.model.PlayerTpaRequest;
import cn.davickk.rdi.essentials.general.request.TpaRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;

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
            PlayerTpaRequest preq=new PlayerTpaRequest(fromPlayer,toPlayer);
            TpaRequest.getReqMap().put(reqid.toString(),preq);
            TextUtils.sendChatMessage(fromPlayer,"�Ѿ����ʹ��������"+toPlayer.getDisplayName().getString()+", ����ID:"+reqid);
            TextUtils.sendChatMessage(this.toPlayer,EColor.ORANGE.getCode()+this.fromPlayer.getDisplayName().getString()+" ��Ҫ���͵������ߡ�");
            TextUtils.sendChatMessage(this.toPlayer,EColor.ORANGE.getCode()+"Ϊ��ֹ�����ƻ�����������ܴ�������");
            IFormattableTextComponent tpyes=TextUtils.getClickableContentComp(EColor.BRIGHT_GREEN.code+"[����]"+EColor.RESET.code,"/tpyes "+reqid," ");
            IFormattableTextComponent tpwait=TextUtils.getClickableContentComp(EColor.GOLD.code+"[����һ��]"+EColor.RESET.code,"�Ե�"," ");
            TextUtils.sendChatMessage(this.toPlayer,tpyes.append(tpwait));
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
}
