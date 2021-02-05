package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.enums.EGameTime;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class WorldUtils {
    public static void setWorldTime(ServerWorld world, EGameTime time)
    {
        world.setDayTime(time.time);
    }
    public static double getDistance(double x1,double y1,double z1,double x2,double y2,double z2)
    {
        Vector3d pos1=new Vector3d(x1,y1,z1);
        Vector3d pos2=new Vector3d(x2,y2,z2);
        return pos1.distanceTo(pos2);
    }
    public static String translateDimensionName(String dims){
        if(dims.equalsIgnoreCase("minecraft:overworld"))
            return "主世界";
        else if(dims.equalsIgnoreCase("minecraft:the_nether"))
            return "地狱";
        else if(dims.equalsIgnoreCase("minecraft:the_end"))
            return "末地";
        else return dims;
    }
}
