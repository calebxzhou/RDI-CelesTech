package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.model.Location;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerUtils {
    private static HashMap<String, PlayerEntity> playerInspectingMap=new HashMap<>();
    public static final Location SPAWN_LOCA =new Location(-3,224,-2,0,0,"minecraft:overworld");
    public static final BlockPos SPAWN_BLKPS=new BlockPos(-3,224,-2);
    public static HashMap<String,PlayerEntity> getPlayerInspectingMap(){
        return playerInspectingMap;
    }
    public static void startThread(Runnable task){
        ExecutorService exe = Executors.newCachedThreadPool();
        exe.execute(task);
    }
}
