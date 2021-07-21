package cn.davickk.rdi.essentials.general.thread.server;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.TimerTask;

public class SummonDragonTask extends TimerTask {
    private World world;
    private Vector3d cord;
    public SummonDragonTask(World world, Vector3d cord){
        this.world=world;
        this.cord=cord;
    }
    @Override
    public void run() {
        Entity dragonEntity=EntityType.ENDER_DRAGON.create(world);
        dragonEntity.moveTo(cord.x(),cord.y()+30,cord.z());
        world.addFreshEntity(dragonEntity);
    }
}
