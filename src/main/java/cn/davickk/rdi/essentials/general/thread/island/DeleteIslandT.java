package cn.davickk.rdi.essentials.general.thread.island;

import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

public class DeleteIslandT extends Thread{
    private final ServerPlayerEntity player;
    public DeleteIslandT(ServerPlayerEntity player){
        this.player=player;
    }
    public void run(){

        try {
            HomeRequest hre=new HomeRequest(player);
            if (hre.getHomeCounts()==0) {
                TextUtils.sendChatMessage(player, "��û�пյ�����˲���ɾ���յ���");
                return;
            }
            player.inventory.clearContent();
            player.setExperienceLevels(0);
            player.setHealth(0.1F);
            hre.delAllHome();
            PlayerUtils.teleportPlayer(player, ServerUtils.SPAWN_LOCA);
            TextUtils.sendChatMessage(player,"�ɹ�ɾ���յ�");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
