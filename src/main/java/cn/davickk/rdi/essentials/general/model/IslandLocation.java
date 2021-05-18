package cn.davickk.rdi.essentials.general.model;

import net.minecraft.entity.player.PlayerEntity;

public class IslandLocation{
    public int x, y, z;
    private PlayerEntity playerEntity;

    public IslandLocation(int x,int y,int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public IslandLocation(PlayerEntity playerEntity){
        this.x= (int) playerEntity.getX();
        this.y= (int) playerEntity.getY();
        this.z= (int) playerEntity.getZ();
    }

    @Override
    public String toString(){
        return "("+x+","+y+","+z+")";
    }



}
