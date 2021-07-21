package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.util.text.IFormattableTextComponent;

public class CreateIslandT extends Thread{
    private final ServerPlayerEntity player;
    public CreateIslandT(ServerPlayerEntity player){
        this.player=player;
    }
    public void run(){

        try {
            TextUtils.sendTitle(player,"请保持不动。", STitlePacket.Type.TITLE);
            PlayerUtils.randomTeleport(player,true);
            HomeRequest hreq=new HomeRequest(player);
            if(hreq.getHomeCounts()!=0){
                TextUtils.sendChatMessage(player, "您已经有一个空岛了，因此不能创建新的空岛。");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextUtils.sendChatMessage(player,"您觉得这个位置适合创建空岛吗？");
        TextUtils.sendChatMessage(player,"（以下选项可点击）（请尽量选择没有人的地方）");
        try {
            TextUtils.sendTitle(player,"请保持不动。", STitlePacket.Type.TITLE);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        IFormattableTextComponent t1=
                TextUtils.getClickableContentComp(
                        EColor.BRIGHT_GREEN.code+"[（点这里）可以，就在这创建空岛吧]","/createkdhere"," ");
        IFormattableTextComponent t2=
                TextUtils.getClickableContentComp(
                        EColor.RED.code+"[（点这里）不可以，重新寻找位置]","/createkd"," ");
        TextUtils.sendChatMessage(player,t1.append(t2));
    }
}
