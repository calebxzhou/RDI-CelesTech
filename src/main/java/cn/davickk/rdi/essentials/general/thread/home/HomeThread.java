package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.enums.EHomeResult;
import cn.davickk.rdi.essentials.general.enums.EHomeText;
import cn.davickk.rdi.essentials.general.enums.EWorld;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.server.ServerWorld;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Locale;

import static cn.davickk.rdi.essentials.general.util.SQLUtils.*;

public class HomeThread extends Thread {
    private ServerPlayerEntity player;
    private String homeName;

    public HomeThread(ServerPlayerEntity player, String homeName) {
        this.player = player;
        this.homeName = homeName;
    }

    public void run() {
        try {
            HomeRequest hreq=new HomeRequest(player,homeName);
            EHomeResult result=hreq.goHome();
            if(result.equals(EHomeResult.OK)) {
                TextUtils.sendChatMessage(player, "成功到达" + homeName);

            }else if(result.equals(EHomeResult.HOME_NOT_EXIST)){

                TextUtils.sendChatMessage(player, "没有找到 "+homeName);
                TextUtils.clickableContent2Send(player, EHomeText.LISTHOME.text, EHomeText.LISTHOME.cmd);

            }else if(result.equals(EHomeResult.HOME_NOT_ACTIVE)){

                TextUtils.sendChatMessage(player, homeName + "还没有激活，因此无法传送。");
                TextUtils.clickableContent2Send(player, EHomeText.ACTIVATE.text.replace("%s",homeName),
                        EHomeText.ACTIVATE.cmd.replace("%s",homeName));
            }else if(result.equals(EHomeResult.NO_ENOUGH_XP)){
                TextUtils.sendChatMessage(player,"经验不足");
                TextUtils.clickableContent2Send(player,"[原因]","",
                        "跨世界传送：5\n非跨世界：\n距离>1000n: n，");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}