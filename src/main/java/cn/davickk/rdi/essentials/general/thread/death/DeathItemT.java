package cn.davickk.rdi.essentials.general.thread.death;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EDeathItemReq;
import cn.davickk.rdi.essentials.general.request.DeathItemRequest;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.sql.SQLException;

public class DeathItemT extends Thread{
    private final ServerPlayerEntity player;
    private final String playerName;
    private final String uuid;
    private final EDeathItemReq ereq;
    public DeathItemT(ServerPlayerEntity player, EDeathItemReq ereq) throws SQLException, ClassNotFoundException {

        this.player=player;
        this.playerName=player.getDisplayName().getString();
        this.uuid=player.getUniqueID().toString();
        this.ereq=ereq;
    }
    public void run(){
        RDIEssentials.createSQLConnection();
        if(ereq.equals(EDeathItemReq.RECORD)){


            try {
                DeathItemRequest req=new DeathItemRequest(player);
                req.getAndDeleteRandomStackList();
                req.uploadItemList();
            } catch (SQLException | ClassNotFoundException sqlException) {
                sqlException.printStackTrace();
            }
        }else if(ereq.equals(EDeathItemReq.READ)){

            try {DeathItemRequest req=new DeathItemRequest(player);
                req.getAndGiveItemList();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
