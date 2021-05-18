package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.model.Location;
import net.minecraft.util.math.BlockPos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerUtils {

    public static final Location SPAWN_LOCA =new Location(-3,224,-2,0,0,"minecraft:overworld");
    public static final BlockPos SPAWN_BLKPS=new BlockPos(-3,224,-2);
    public static void startThread(Runnable task){
        ExecutorService exe = Executors.newCachedThreadPool();
        exe.execute(task);
    }
}
