package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.enums.EGameTime;
import cn.davickk.rdi.essentials.general.model.IslandLocation;
import cn.davickk.rdi.essentials.general.model.Location;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.forge.ForgeAdapter;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WorldUtils {
    public static void setWorldTime(ServerWorld world, EGameTime time)
    {
        world.setDayTime(time.time);
    }
    public static double getDistanceXZ(Location l1, Location l2){
        return getDistance(l1.getX(),0,l1.getZ(), l2.getX(),0,l2.getZ());
    }
    private static double getDistance(double x1,double y1,double z1,double x2,double y2,double z2)
    {
        Vector3d pos1=new Vector3d(x1,y1,z1);
        Vector3d pos2=new Vector3d(x2,y2,z2);
        return pos1.distanceTo(pos2);
    }
    public static boolean ifNearbySpawn(Location loca){
        double diffX=Math.abs(loca.getX()-ServerUtils.SPAWN_LOCA.getX());
        double diffZ=Math.abs(loca.getZ()-ServerUtils.SPAWN_LOCA.getZ());
        return diffX<20 && diffZ<20;
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
    public static void pasteTree(PlayerEntity player, BlockPos loc) throws IOException, WorldEditException {
        Clipboard clipboard;
        File file=new File("./islands/oaktree.schem");
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        ClipboardReader reader = format.getReader(new FileInputStream(file));
        clipboard = reader.read();
        try(EditSession session = WorldEdit.getInstance().getEditSessionFactory()
                .getEditSession(ForgeAdapter.adapt(player.getCommandSenderWorld()), -1)){
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(session)
                    .to(BlockVector3.at(loc.getX()-1, loc.getY(), loc.getZ()))
                    .copyEntities(false)
                    .ignoreAirBlocks(true)
                    // configure here
                    .build();
            Operations.complete(operation);
        }
    }
    public static void clearMob(PlayerEntity player,String mobName){
        player.getServer().getCommands().performCommand(player.getServer().createCommandSourceStack(),
                "kill @e[type="+mobName+"]");
    }
    public static void removeGround(ServerPlayerEntity player){
        String fillcmd="fill %x1 4 %z1 %x2 0 %z2 minecraft:air";

        IslandLocation iloc=new IslandLocation(player);
        fillcmd=fillcmd.replace("%x1",(iloc.x-39)+"")
                .replace("%z1",(iloc.z-40)+"")
                .replace("%x2",(iloc.x+40)+"")
                .replace("%z2",(iloc.z+40)+"");
        System.out.println(fillcmd);
        player.getServer().getCommands().performCommand(player.getServer().createCommandSourceStack(),fillcmd);

    }
    public static void pasteSchematic(PlayerEntity player,IslandLocation loc,String schem) throws IOException, WorldEditException {
        Clipboard clipboard;
        File file=new File("./islands/"+schem+".schem");
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        ClipboardReader reader = format.getReader(new FileInputStream(file));
        clipboard = reader.read();
        try(EditSession session = WorldEdit.getInstance().getEditSessionFactory()
                .getEditSession(ForgeAdapter.adapt(player.getCommandSenderWorld()), -1)){
            System.out.println(loc.x+" "+loc.y+" "+loc.z);
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(session)
                    .to(BlockVector3.at(loc.x, loc.y, loc.z))
                    .copyEntities(false)
                    .ignoreAirBlocks(true)
                    // configure here
                    .build();
            Operations.complete(operation);
        }


    }
    public static void pasteIslandSchematic(BlockPos bpos,ServerPlayerEntity player) throws IOException, WorldEditException {
        Clipboard clipboard;
        File file=new File("./islands/island.schem");
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        ClipboardReader reader = format.getReader(new FileInputStream(file));
        clipboard = reader.read();
        try(EditSession session = WorldEdit.getInstance().getEditSessionFactory()
                .getEditSession(ForgeAdapter.adapt(player.getCommandSenderWorld()), -1)){
            System.out.println(bpos.getX()+" "+bpos.getY()+" "+bpos.getZ());
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(session)
                    .to(BlockVector3.at(bpos.getX(), bpos.getY(), bpos.getZ()))
                    .copyEntities(false)
                    .ignoreAirBlocks(true)
                    // configure here
                    .build();
            Operations.complete(operation);
        }


    }
}
