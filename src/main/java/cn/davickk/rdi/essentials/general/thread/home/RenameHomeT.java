package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

public class RenameHomeT extends Thread{
    private final ServerPlayerEntity player;
    private final String oldName;
    private final String newName;
    public RenameHomeT(ServerPlayerEntity player, String oldName, String newName){
        this.player=player;
        this.oldName=oldName;
        this.newName=newName;
    }
    public void run(){
        /*try {
            HomeRequest hreq=new HomeRequest(player,oldName);
            if(hreq.renameHome(newName)){
                TextUtils.sendChatMessage(player,"�����ɹ�");
            }else TextUtils.sendChatMessage(player,"������������ѯ����");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //.getDisplayName().getString().equalsIgnoreCase(player2Share)
    }
}
