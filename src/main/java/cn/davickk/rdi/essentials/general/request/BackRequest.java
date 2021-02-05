package cn.davickk.rdi.essentials.general.request;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.util.HomeUtils;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BackRequest {
    private final ServerPlayerEntity player;
    private final Connection sqlConn;
    private final int port;
    private final String uuid;
    public BackRequest (ServerPlayerEntity player){
        this.player=player;
        this.sqlConn= RDIEssentials.SQL_CONN;
        this.uuid= PlayerUtils.getUUID(player);
        this.port=player.getServer().getServerPort();
    }
    public boolean record() throws SQLException {
        PreparedStatement ps= sqlConn.prepareStatement("INSERT INTO back (uuid,dims,port,x,y,z,w,p) VALUES (?,?,?,?,?,?,?,?)");
        Location loca=new Location(player);
        ps.setString(1,uuid);
        ps.setString(2,loca.dims.toString());
        ps.setInt(3,port);
        ps.setDouble(4, loca.x);
        ps.setDouble(5,loca.y);
        ps.setDouble(6,loca.z);
        ps.setFloat(7,loca.rotationYaw);
        ps.setFloat(8,loca.rotationPitch);
        return ps.executeUpdate()>0;
    }
    @Nullable
    public Location read() throws SQLException{
        ResultSet rs3=sqlConn.prepareStatement("SELECT * FROM back WHERE uuid='"+uuid+"' AND port='"+port+"'").executeQuery();
        if(!rs3.next()) return null;
        String dims=rs3.getString("dims");
        double x = rs3.getDouble("x");
        double y = rs3.getDouble("y");
        double z = rs3.getDouble("z");
        float w = rs3.getFloat("w");
        float p = rs3.getFloat("p");
        sqlConn.prepareStatement("DELETE FROM back WHERE uuid='"+uuid+"'").executeUpdate();
        return new Location(x,y,z,w,p,dims);
    }
}
