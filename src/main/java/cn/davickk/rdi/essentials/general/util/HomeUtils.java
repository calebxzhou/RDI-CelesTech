package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.enums.EHomeText;
import net.minecraft.entity.player.ServerPlayerEntity;

public class HomeUtils {
    public static final int MAX_HOME = 3;
    public static final int ACTIV_REQUIRE_XP=5;

    public static void tellPlayerActivHome(ServerPlayerEntity player,String homeName){
        TextUtils.clickableContent2Send(player, EHomeText.ACTIVATE.text.replace("%s",homeName),
                EHomeText.ACTIVATE.cmd.replace("%s",homeName));
    }
    public static void tellPlayerListHome(ServerPlayerEntity player){
        TextUtils.clickableContent2Send(player, EHomeText.LISTHOME.text, EHomeText.LISTHOME.cmd);
    }
//��Ӧ�ķ������ж�Ӧ��home��
    /*public static String getHomeDBTable(ServerPlayerEntity player){
        return SQLUtils.getHomeTable(player.getServer().getServerPort());
    }*/

    //������Ƿ��Ѿ������ˣ��������

    //�Ƿ�ﵽ�������ü�������
    /*public static int getRequiredXp(double distance,boolean notOverworld){
        if(notOverworld)
            return 5;
        if(distance>12000)
            return 8;
        if(distance>8000)
            return 4;
        if(distance>4000)
            return 2;
        if(distance>1000)
            return 1;
        else return 0;
    }*/





}
