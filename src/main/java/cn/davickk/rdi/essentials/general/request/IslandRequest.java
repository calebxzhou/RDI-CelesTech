package cn.davickk.rdi.essentials.general.request;

import cn.davickk.rdi.essentials.RDIEssentials;
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
import com.sk89q.worldedit.world.World;
import net.minecraft.entity.player.ServerPlayerEntity;
import javax.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static cn.davickk.rdi.essentials.RDIEssentials.SQL_CONN;

public class IslandRequest {
    private final ServerPlayerEntity player;
    private final int DIST=128;//每个空岛大小为256*256，中心位置128,128
    private final int HIGH=220;
    private final int BTWN=256;//每个空岛之间的间隔
    private final Connection sqlConn=SQL_CONN;
    private final String uuid;
    private final String playerName;
    private ResultSet allIslands;
    public IslandRequest(ServerPlayerEntity player) throws SQLException, ClassNotFoundException {
        RDIEssentials.createSQLConnection();
        this.player=player;
        this.uuid=player.getUniqueID().toString();
        this.playerName=player.getDisplayName().getString();
        this.allIslands=sqlConn.prepareStatement("SELECT * FROM island").executeQuery();
    }
    public boolean hasIsland() throws SQLException {
        //ResultSet rs=sqlConn.prepareStatement("SELECT x FROM island WHERE uuid='"+uuid+"'").executeQuery();
        while(allIslands.next()){
            if(allIslands.getString("uuid").equals(uuid))
                return true;
        }
        return false;
    }
    public boolean existIslandOnLocation(IslandLocation loca) throws SQLException{
        /*PreparedStatement psm =sqlConn.prepareStatement("SELECT COUNT(*) AS rowcount FROM island WHERE x=? AND y=? AND z=?");
        psm.setInt(1, loca.x);
        psm.setInt(2, loca.y);
        psm.setInt(3, loca.z);*/
        //ResultSet rs=psm.executeQuery();
        while (allIslands.next()){
            if(allIslands.getInt("x")==loca.x
            && allIslands.getInt("z")==loca.z)
                return true;
        }
        return false;
    }
    @Nullable
    public IslandLocation findAvailableIsland() throws SQLException{
       // for(int i=1;i<=15;i++){

            for(int j=1;j<39;++j){
                IslandLocation loca1=new IslandLocation(DIST*j,  HIGH,DIST*j);
                IslandLocation loca2=new IslandLocation(- (DIST*j),  HIGH,DIST*j);
                IslandLocation loca3=new IslandLocation(- (DIST * j), HIGH, -(DIST * j));
                IslandLocation loca4=new IslandLocation(DIST * j, HIGH, -(DIST * j));
                if(!existIslandOnLocation(loca1))
                    return loca1;
                else if(!existIslandOnLocation(loca2))
                    return loca2;
                else if(!existIslandOnLocation(loca3))
                    return loca3;
                else if(!existIslandOnLocation(loca4))
                    return loca4;
            }

        //}
        return null;
    }
    public void createIsland(IslandLocation loca) throws SQLException {
        PreparedStatement psm=sqlConn.prepareStatement("INSERT INTO island (uuid, playerName, x, y, z) VALUES (?,?,?,?,?)");
        psm.setString(1,uuid);
        psm.setString(2,playerName);
        psm.setInt(3, loca.x);
        psm.setInt(4, loca.y);
        psm.setInt(5, loca.z);
        psm.executeUpdate();
        /* INSERT INTO island (uuid,playerName,x,y,z) (?,?,?,?,?)
        i=1;
        Q1: dist*i,dist*i 有? 创:下一个
        Q2: -(dist*i),dist*i 有? 创:下一个
        Q3: -(dist*i),-(dist*i)  有？ 创:下一个
        Q4: dist*i,-(dist*i) 有？ 创 : i++,dist+=btwn

       if 256 0 has BEDROCK         256,0  -256,0  0,256  0,-256
                                    2d,0 -(2d),0  0,2d  0,-2d
         look 0 256
            look 0 -256
            look -256 0

       */
    }
    public void pasteSchematic(IslandLocation loc) throws IOException, WorldEditException {
            Clipboard clipboard;
            File file=new File("./islands/island.schem");
            ClipboardFormat format = ClipboardFormats.findByFile(file);
            ClipboardReader reader = format.getReader(new FileInputStream(file));
            clipboard = reader.read();
            try(EditSession session = WorldEdit.getInstance().getEditSessionFactory()
                    .getEditSession(ForgeAdapter.adapt(player.getServerWorld()), -1)){
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
    @Nullable
    public IslandLocation getIslandLocation() throws SQLException {
        while(this.allIslands.next()){
            if(allIslands.getString("uuid").equalsIgnoreCase(uuid))
                return new IslandLocation(allIslands.getInt("x"),allIslands.getInt("y"),
                        allIslands.getInt("z"));
        }
        return null;
    }
    public void refresh() throws SQLException {
        this.allIslands=sqlConn.prepareStatement("SELECT * FROM island").executeQuery();
    }
    public void backToIsland(){

    }
    public void checkPosition(){

    }
}
