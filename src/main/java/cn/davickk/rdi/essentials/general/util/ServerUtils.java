package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.lib.IslandLocation;
import cn.davickk.rdi.essentials.general.lib.Location;
import net.minecraft.util.math.BlockPos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerUtils {
    public static final int MAIN_PORT=29477;
    public static final int REAL_PORT=37138;
    public static final int CRET_PORT=28527;
    public static final Location SPAWN_LOCA =new Location(-3,224,-2,0,0,"minecraft:overworld");
    public static final BlockPos SPAWN_BLKPS=new BlockPos(-3,224,-2);
    public static void startThread(Runnable task){
        ExecutorService exe = Executors.newCachedThreadPool();
        exe.execute(task);
    }
}
