package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.enums.EHomeText;
import cn.davickk.rdi.essentials.general.enums.EWorld;
import cn.davickk.rdi.essentials.general.lib.HomeLocation;
import cn.davickk.rdi.essentials.general.model.Home;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.HomeUtils;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeListThread extends Thread {
    private final ServerPlayerEntity player;

    public HomeListThread(ServerPlayerEntity player) {
        this.player = player;
    }

    public void run() {
        try {

            HomeRequest hreq=new HomeRequest(player);
            if (hreq.getHomeCounts()==0) {
                TextUtils.sendChatMessage(player, EColor.RED.code+"您 未拥有自己的空岛 或者 未加入他人的空岛，因此不能使用此指令。");
                //TextUtils.clickableContent2Send(player, EHomeText.SETHOME_DEFAULT.text,EHomeText.SETHOME_DEFAULT.cmd);
                //TextUtils.clickableContent2Send(player, EHomeText.IMPORT.text, EHomeText.IMPORT.cmd);
                return;
            }
            TextUtils.sendChatMessage(player, "家 ("+hreq.getHomeCounts()+"/256) (点击->操作)");
            List<Home> homeList= hreq.getHomeList();
            if(homeList==null){
                TextUtils.sendChatMessage(player,"无法获取家列表，请咨询腐竹");
                return;
            }
            IFormattableTextComponent txt2s=new StringTextComponent("");
            int index=1;
            for(Home home:homeList){
                //家名前面的颜色 激活了是绿色
                String color_homeName="";
                if(home.getActiv()==1)
                    color_homeName=EColor.BRIGHT_GREEN.getCode();
                //完整信息
                String fullMsg=String.format("[%d] %s%s%s (%d, %d, %d) [%s]   %s// %s",
                        index,
                        color_homeName,
                        home.getHomeName(),
                        EColor.RESET.code,
                        (int)home.getX(),(int)home.getY(),(int)home.getZ(),
                        home.getDims(),
                        EColor.GRAY.code,
                        home.getComment()
                );
                txt2s.append(TextUtils.getClickableContentComp(player,fullMsg,"/actions4home "+home.getHomeName(),""));
                TextUtils.sendChatMessage(player,txt2s);
                /*String hname=entry.getKey();
                String hnameC="";
                HomeLocation hloc=entry.getValue();
                int x=(int)hloc.getX(),  y=(int)hloc.getY(),  z=(int)hloc.getZ();
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
                String hnames=" >"+hnameC+EColor.RESET.code+"< ";
                txt2s.append(TextUtils.getClickableContentComp(player,hnames,
                        "/actions4home "+hname,hlocs));*/
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
