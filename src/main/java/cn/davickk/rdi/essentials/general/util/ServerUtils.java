package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.lib.IslandLocation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerUtils {
    public static final int MAIN_PORT=29477;
    public static final int REAL_PORT=37138;
    public static final int CRET_PORT=28527;
    public static final IslandLocation VOID_LOCA =new IslandLocation(-3,-2,-2);
    public static final IslandLocation SPAWN_LOCA =new IslandLocation(-3,224,-2);
    public static void startThread(Runnable task){
        ExecutorService exe = Executors.newCachedThreadPool();
        exe.execute(task);
    }
}
