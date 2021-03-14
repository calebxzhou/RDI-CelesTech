package cn.davickk.rdi.essentials.general.thread.spawn;

import net.minecraft.entity.player.ServerPlayerEntity;

public class ChangeSpawnPointT extends Thread {
    private ServerPlayerEntity player;
    private String opr;
    public static final String RESPAWN_OPR="respawn";
    public static final String JOIN_OPR="join";
    public ChangeSpawnPointT(ServerPlayerEntity player, String opr) {
        this.player = player;
        this.opr = opr;
    }

    public void run() {
        try {
            if(opr.equals(RESPAWN_OPR)){

            }else if(opr.equals(JOIN_OPR)){

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}