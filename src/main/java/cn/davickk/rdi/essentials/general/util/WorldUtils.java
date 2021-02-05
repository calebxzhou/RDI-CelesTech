package cn.davickk.rdi.essentials.general.util;

import cn.davickk.rdi.essentials.general.enums.EGameTime;
import cn.davickk.rdi.essentials.general.lib.IslandLocation;
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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    public static void pasteTree(PlayerEntity player, BlockPos loc) throws IOException, WorldEditException {
        Clipboard clipboard;
        File file=new File("./islands/oaktree.schem");
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        ClipboardReader reader = format.getReader(new FileInputStream(file));
        clipboard = reader.read();
        try(EditSession session = WorldEdit.getInstance().getEditSessionFactory()
                .getEditSession(ForgeAdapter.adapt(player.world), -1)){
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
}
