package cn.davickk.rdi.essentials.general.thread.tick;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;

import java.util.TimerTask;

public class TickTask extends TimerTask {
    private PlayerEntity player;
    public TickTask(PlayerEntity player){
        this.player=player;
    }
    @Override
    public void run() {
        TextUtils.sendActionMessage(player, EColor.GOLD.code+"���Ѿ������¼��ѯģʽ���ٴ�����/rbrx inspect���˳���");
    }
}
