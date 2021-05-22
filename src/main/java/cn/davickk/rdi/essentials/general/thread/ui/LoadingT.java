package cn.davickk.rdi.essentials.general.thread.ui;

import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

public class LoadingT extends Thread{
    private final PlayerEntity player;
    private final String a1="[";
    private final String a2="]";
    private final String b1="=";
    private final String b2=">";
    private final String b3=" ";
    private final String c1="%";
    private String toSend;
    private final int totalTime;
    //时间单位是毫秒
    public LoadingT(PlayerEntity player, int totalTime) {
        this.player = player;
        this.totalTime=totalTime;
    }

    public void run() {
        toSend= toSend+a1;
        String b1s = "",b3s="";
        for(int i=0;i<=50;++i){
            b1s+=b1;
            //for(int j=0;j<30-i;++j)
                //b3s+=b3;
            toSend=b1s+b2+b3s+a2+i*2+c1;
            try {
                Thread.sleep((long)(totalTime/50));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(toSend);
            TextUtils.sendActionMessage(player,toSend);
            //正在读取[=================>               ] 25%
        }
    }
}
