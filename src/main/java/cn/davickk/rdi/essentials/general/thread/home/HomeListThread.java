package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.enums.EHomeText;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.enums.EWorld;
import cn.davickk.rdi.essentials.general.lib.HomeLocation;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.HomeUtils;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.HashMap;
import java.util.Map;

public class HomeListThread extends Thread {
    private final ServerPlayerEntity player;

    public HomeListThread(ServerPlayerEntity player) {
        this.player = player;
    }

    public void run() {
        String uuid= PlayerUtils.getUUID(player);
        int serverPort = player.getServer().getServerPort();
        try {

            HomeRequest hreq=new HomeRequest(player);
            if (hreq.getHomeCounts()==0) {
                TextUtils.sendChatMessage(player, "您还未设置过家，现在就设置一个吗？");
                TextUtils.clickableContent2Send(player, EHomeText.SETHOME_DEFAULT.text,EHomeText.SETHOME_DEFAULT.cmd);
                TextUtils.clickableContent2Send(player, EHomeText.IMPORT.text, EHomeText.IMPORT.cmd);
                return;
            }
            TextUtils.sendChatMessage(player, "家 ("+hreq.getHomeCounts()+"/"+ HomeUtils.MAX_HOME+") (点击->操作)");
            HashMap<String, HomeLocation> homeMap= hreq.getHomeList();
            if(homeMap==null){
                TextUtils.sendChatMessage(player,"无法获取家列表，请咨询腐竹");
                return;
            }
            IFormattableTextComponent txt2s=new StringTextComponent("");
            for(Map.Entry<String ,HomeLocation> entry : homeMap.entrySet()){
                String hname=entry.getKey();
                String hnameC="";
                HomeLocation hloc=entry.getValue();
                int x=(int)hloc.x,  y=(int)hloc.y,  z=(int)hloc.z;
                String dims=hloc.dims;
                if(hloc.isActive)
                {
                    hnameC=EColor.BRIGHT_GREEN.code+EColor.BOLD.code+hname;
                    if(dims.equalsIgnoreCase(EWorld.THE_NETHER.dim))
                        hnameC=EColor.DARK_RED.code+EColor.BOLD.code+hname;
                    if(dims.equalsIgnoreCase(EWorld.THE_END.dim))
                        hnameC=EColor.DARK_GREEN.code+EColor.BOLD.code+hname;
                }else hnameC=hname;
                String hlocs="(" +dims+", "+ x + ", " + y + ", " + z + ")";
                String hnames=" 【"+hnameC+EColor.RESET.code+"】 ";
                txt2s.append(TextUtils.getClickableContentComp(player,hnames,
                        "/actions4home "+hname,hlocs));
            }

            TextUtils.sendChatMessage(player,  txt2s);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
