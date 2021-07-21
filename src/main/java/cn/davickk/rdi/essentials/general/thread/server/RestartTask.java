package cn.davickk.rdi.essentials.general.thread.server;

import net.minecraft.server.MinecraftServer;

import java.util.TimerTask;

public class RestartTask extends TimerTask {
    private MinecraftServer server;
    public RestartTask(MinecraftServer server){
        this.server=server;
    }
    @Override
    public void run() {
        server.close();
    }
}
