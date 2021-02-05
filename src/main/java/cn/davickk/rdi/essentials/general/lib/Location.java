package cn.davickk.rdi.essentials.general.lib;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class Location {
    public double x, y, z;
    public float rotationYaw, rotationPitch;
    public ResourceLocation dims;
    private PlayerEntity playerEntity;
    /*public Location(int posX, int posY, int posZ, ResourceLocation dimension) {
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.rotationYaw = 0;
        this.rotationPitch = 0;
        this.dimension = dimension;
    }*/

    public Location(double posX, double posY, double posZ, float rotationYaw, float rotationPitch, String dims) {
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.dims=new ResourceLocation(dims);
    }

    public Location(ServerPlayerEntity player) {
        this.x = player.getPosX();
        this.y = player.getPosY();
        this.z = player.getPosZ();
        this.rotationYaw = player.rotationYaw;
        this.rotationPitch = player.rotationPitch;
        this.dims=player.getEntityWorld().getDimensionKey().getLocation();
    }

    public Location(PlayerEntity player) {
        this.x = player.getPosX();
        this.y = player.getPosY();
        this.z = player.getPosZ();
        this.rotationYaw = player.rotationYaw;
        this.rotationPitch = player.rotationPitch;
        this.dims=player.getEntityWorld().getDimensionKey().getLocation();
        this.playerEntity = player;
    }



    public Biome getBiome() {
        BlockPos pos = new BlockPos(x, y, z);
        return playerEntity.getEntityWorld().getBiomeManager().getBiome(pos);
    }

    public float getTemperature() {
        BlockPos pos = new BlockPos(x, y, z);
        return playerEntity.getEntityWorld().getBiomeManager().getBiome(pos).getTemperature(pos);
    }
}
