package cn.davickk.rdi.essentials.general.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
public class Location {

    private double x, y, z;
    private float yaw, pitch;
    private ResourceLocation dims;
    //private transient PlayerEntity playerEntity;
    /*public Location(int posX, int posY, int posZ, ResourceLocation dimension) {
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.rotationYaw = 0;
        this.rotationPitch = 0;
        this.dimension = dimension;
    }*/

    public Location(double posX, double posY, double posZ, float yaw, float pitch, String dims) {
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.yaw = yaw;
        this.pitch = pitch;
        this.dims=new ResourceLocation(dims);
    }

    public Location(ServerPlayerEntity player) {
        this.x = player.getPosX();
        this.y = player.getPosY();
        this.z = player.getPosZ();
        this.yaw = player.rotationYaw;
        this.pitch = player.rotationPitch;
        this.dims=player.getEntityWorld().getDimensionKey().getLocation();
    }

    public Location(PlayerEntity player) {
        this.x = player.getPosX();
        this.y = player.getPosY();
        this.z = player.getPosZ();
        this.yaw = player.rotationYaw;
        this.pitch = player.rotationPitch;
        this.dims=player.getEntityWorld().getDimensionKey().getLocation();
        //this.playerEntity = player;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public ResourceLocation getDims() {
        return dims;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setDims(ResourceLocation dims) {
        this.dims = dims;
    }

    /*public Biome getBiome() {
                                    BlockPos pos = new BlockPos(x, y, z);
                                    return playerEntity.getEntityWorld().getBiomeManager().getBiome(pos);
                                }

                                public float getTemperature() {
                                    BlockPos pos = new BlockPos(x, y, z);
                                    return playerEntity.getEntityWorld().getBiomeManager().getBiome(pos).getTemperature(pos);
                                }*/
    @Override
    public String toString(){
        Gson gs=new GsonBuilder().setPrettyPrinting().create();
        return gs.toJson(this);
    }

    public Location fromString(String json){
        return new Gson().fromJson(json,Location.class);
    }
}
