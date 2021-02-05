package cn.davickk.rdi.essentials.general.request;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EHomeResult;
import cn.davickk.rdi.essentials.general.enums.EWorld;
import cn.davickk.rdi.essentials.general.lib.HomeLocation;
import cn.davickk.rdi.essentials.general.lib.IslandLocation;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.util.HomeUtils;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.WorldUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class HomeRequest {
    private final Connection sqlConn;
    private final ServerPlayerEntity player;
    private final String table;
    private final String uuid;
    private String homeName;
    private String newHomeName;
    private Location location2Set;
    //正常情况
    public HomeRequest(ServerPlayerEntity player) throws SQLException, ClassNotFoundException {
        RDIEssentials.createSQLConnection();
        this.sqlConn = RDIEssentials.SQL_CONN;
        this.player = player;
        this.table= HomeUtils.getHomeDBTable(player);
        this.uuid= PlayerUtils.getUUID(player);
    }
    public HomeRequest(ServerPlayerEntity player, String homeName) {
        this.sqlConn = RDIEssentials.SQL_CONN;
        this.player = player;
        this.homeName = homeName;
        this.table= HomeUtils.getHomeDBTable(player);
        this.uuid= PlayerUtils.getUUID(player);
    }

    public boolean renameHome(String newHomeName) throws SQLException {
        String stm="UPDATE "+table+" SET homeName = ? WHERE uuid = ? AND homeName = ?";
        PreparedStatement pstm=sqlConn.prepareStatement(stm);
        pstm.setString(1,newHomeName);
        pstm.setString(2,uuid);
        pstm.setString(3,homeName);
        return pstm.executeUpdate()>0;
    }
    //玩家一共设置过几个家？
    public int getHomeCount() throws SQLException {
        String stm="SELECT COUNT(*) AS rowcount FROM "+table+" WHERE uuid=?;";
        PreparedStatement ps2=sqlConn.prepareStatement(stm);
        ps2.setString(1, uuid);
        ResultSet rs2 = ps2.executeQuery();
        rs2.next();
        return rs2.getInt("rowcount");
    }
    public boolean existsHome() throws SQLException {
        ResultSet rs3 = sqlConn.prepareStatement(
                "SELECT * FROM "+table+" WHERE uuid= '"+uuid+"' AND homeName='"+homeName+"'").executeQuery();
        return rs3.next();
    }
    @Nullable
    public Location getHomeLocation() throws SQLException{
        ResultSet rs3 = sqlConn.prepareStatement(
                "SELECT * FROM "+table+" WHERE uuid= '"+uuid+"' AND homeName='"+homeName+"'").executeQuery();
        if(!rs3.next())
            return null;
        String dims=rs3.getString("dims");
        double x = rs3.getDouble("x");
        double y = rs3.getDouble("y");
        double z = rs3.getDouble("z");
        float w = rs3.getFloat("w");
        float p = rs3.getFloat("p");
        return new Location(x,y,z,w,p,dims);
    }
    public boolean isReachedMax() throws SQLException {
        return getHomeCount() > HomeUtils.MAX_HOME;
    }
    public boolean setHome() throws SQLException {
        String playerName=player.getDisplayName().getString();
        Location loca = new Location(player);
        double x = loca.x;
        double y = loca.y;
        double z = loca.z;
        float w = loca.rotationYaw;
        float p = loca.rotationPitch;
        String dims=loca.dims.toString();
        String st = "INSERT INTO " + table + " (uuid, playerName, homeName, dims, x, y, z, w, p, activ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = sqlConn.prepareStatement(st);
        stmt.setString(1, uuid);
        stmt.setString(2, playerName);
        stmt.setString(3, homeName);
        stmt.setString(4, dims);
        stmt.setDouble(5, x);
        stmt.setDouble(6, y);
        stmt.setDouble(7, z);
        stmt.setDouble(8, w);
        stmt.setDouble(9, p);
        stmt.setBoolean(10, false);
        return stmt.executeUpdate()>0;
    }
    public boolean setHomeWithLocation(Location location2Set,String newHomeName,boolean activ) throws SQLException{
        String playerName=player.getDisplayName().getString();
        double x = location2Set.x;
        double y = location2Set.y;
        double z = location2Set.z;
        float w = location2Set.rotationYaw;
        float p = location2Set.rotationPitch;
        String dims=location2Set.dims.toString();
        String st = "INSERT INTO " + table + " (uuid, playerName, homeName, dims, x, y, z, w, p, activ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = sqlConn.prepareStatement(st);
        stmt.setString(1, uuid);
        stmt.setString(2, playerName);
        stmt.setString(3, newHomeName);
        stmt.setString(4, dims);
        stmt.setDouble(5, x);
        stmt.setDouble(6, y);
        stmt.setDouble(7, z);
        stmt.setDouble(8, w);
        stmt.setDouble(9, p);
        stmt.setBoolean(10, activ);
        return stmt.executeUpdate()>0;
    }
    public boolean delHome() throws SQLException {
        String selectSQL = "DELETE FROM "+table+" WHERE uuid ='" + uuid + "' AND homeName='"+homeName+"'";
        int rowsEff = sqlConn.prepareStatement(selectSQL).executeUpdate();
        return rowsEff!=0;
    }
    public boolean hasActiveHome() throws SQLException{
        ResultSet rs4 = sqlConn.prepareStatement("SELECT * FROM "+table+" WHERE uuid= '"+uuid+"' AND activ=true").executeQuery();
        return rs4.next();
    }
    public boolean isActive() throws SQLException {
        ResultSet rs3 = sqlConn.prepareStatement(
                "SELECT * FROM "+table+" WHERE uuid= '"+uuid+"' AND homeName='"+homeName+"' AND activ='1';").executeQuery();
        return rs3.next();
    }
    public boolean setActive(boolean activ) throws SQLException {
        return sqlConn.prepareStatement("UPDATE "+table+" SET activ = '"+activ+"' WHERE uuid='"+uuid+"' AND homeName='"+homeName+"'")
                .executeUpdate()>0;
    }
    public EHomeResult goHome() throws SQLException{
        ResultSet rs3 = sqlConn.prepareStatement(
                "SELECT * FROM "+table+" WHERE uuid= '"+uuid+"' AND homeName='"+homeName+"'").executeQuery();
        if(!rs3.next())
            return EHomeResult.HOME_NOT_EXIST;
        boolean activ = rs3.getBoolean("activ");
        if(!activ)
            return EHomeResult.HOME_NOT_ACTIVE;
        String dims=rs3.getString("dims");
        double x = rs3.getDouble("x");
        double y = rs3.getDouble("y");
        double z = rs3.getDouble("z");
        float w = rs3.getFloat("w");
        float p = rs3.getFloat("p");
        Location loc=getHomeLocation();
        double distance= WorldUtils.getDistance(loc.x,loc.y,loc.z,x,y,z);
        int xp;
        //如果不在主世界
        if(!loc.dims.equals(new ResourceLocation(EWorld.OVERWORLD.dim))){
            xp=HomeUtils.getRequiredXp(114514,true);
        }else{
            xp=HomeUtils.getRequiredXp(distance,false);
        }
        if(PlayerUtils.minusXPLvl(player,xp)) {
            PlayerUtils.teleportPlayer(player, new IslandLocation((int)x, (int)y, (int)z));
            return EHomeResult.OK;
        }
        else return EHomeResult.NO_ENOUGH_XP;

    }
    @Nullable
    public HashMap<String, HomeLocation> getHomeList() throws SQLException{
        ResultSet rs = sqlConn.prepareStatement(
                "SELECT * FROM "+table+" WHERE uuid= '"+uuid+"'").executeQuery();
        if(!rs.next())
            return null;
        HashMap<String, HomeLocation> homeMap=new HashMap<>();
        rs.beforeFirst();
        String dims,homeName;
        boolean activ;
        double x,y,z;
        float w=0,p=0;
        while(rs.next()){
            homeName = rs.getString("homeName");
            dims=rs.getString("dims");
            x = rs.getDouble("x");
            y = rs.getDouble("y");
            z = rs.getDouble("z");
            w= rs.getFloat("w");
            p= rs.getFloat("p");
            activ= rs.getBoolean("activ");
            homeMap.put(homeName,new HomeLocation(dims,x,y,z,w,p,activ));
        }
        return homeMap;
    }

}
