package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.model.Location;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import com.sk89q.worldedit.world.World;
import net.minecraft.entity.player.ServerPlayerEntity;

public class UpdateHomeT extends Thread{
    private final ServerPlayerEntity player;
    private int opr;
    private String homeName,argument;
    public static final int RENAME=1;
    public static final int LOCATE=2,COMMENT=3;
    public UpdateHomeT(ServerPlayerEntity player, String homeName,int opr,String argument){
        this.player=player;
        this.opr=opr;
        this.homeName=homeName;
        this.argument=argument;
    }
    public void run(){
        try {
            HomeRequest hreq=new HomeRequest(player,homeName);
            if(!hreq.hasThisHome()){
                TextUtils.sendChatMessage(player,"没有找到传送点"+homeName);
                return;
            }
            if(opr==RENAME){
                hreq.renameHome(argument);
                TextUtils.sendChatMessage(player,"成功把传送点"+homeName+"更名为"+argument);
            }else if(opr==LOCATE){
                if(WorldUtils.ifNearbySpawn(new Location(player))){
                    TextUtils.sendChatMessage(player,"不可以把传送点更改到主城附近");
                    return;
                }
                hreq.setNewLocation(new Location(player));
                TextUtils.sendChatMessage(player,"成功更改了"+homeName+"的位置");
            }else if(opr==COMMENT){
                hreq.addComment(argument);
                TextUtils.sendChatMessage(player,"成功将注释"+argument+"添加到"+homeName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //.getDisplayName().getString().equalsIgnoreCase(player2Share)
    }
}
