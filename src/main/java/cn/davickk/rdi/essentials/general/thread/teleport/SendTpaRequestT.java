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
            TextUtils.sendChatMessage(fromPlayer,"已经发送传送请求给"+toPlayer.getDisplayName().getString()+", 请求ID:"+reqid);
            TextUtils.sendChatMessage(this.toPlayer,EColor.ORANGE.getCode()+this.fromPlayer.getDisplayName().getString()+" 想要传送到你的身边。");
            TextUtils.sendChatMessage(this.toPlayer,EColor.ORANGE.getCode()+"为防止恶意破坏，请谨慎接受传送请求。");
            IFormattableTextComponent tpyes=TextUtils.getClickableContentComp(EColor.BRIGHT_GREEN.code+"[接受]"+EColor.RESET.code,"/tpyes "+reqid," ");
            IFormattableTextComponent tpwait=TextUtils.getClickableContentComp(EColor.GOLD.code+"[等我一下]"+EColor.RESET.code,"稍等"," ");
            TextUtils.sendChatMessage(this.toPlayer,tpyes.append(tpwait));
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
}
