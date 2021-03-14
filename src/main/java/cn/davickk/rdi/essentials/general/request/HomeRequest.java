package cn.davickk.rdi.essentials.general.request;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.lib.HomeLocation;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.util.HomeUtils;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class HomeRequest{
    private final Connection sqlConn;
    private final ServerPlayerEntity player;
    private final String uuid;
    private int port;
    private String homeName;
    private String newHomeName;
    private Location location2Set;
    //正常情况
    public HomeRequest(ServerPlayerEntity player) throws SQLException, ClassNotFoundException {
        RDIEssentials.createSQLConnection();
        this.sqlConn = RDIEssentials.SQL_CONN;
        this.player = player;
        this.uuid= PlayerUtils.getUUID(player);
        this.port=player.getServer().getServerPort();
    }
    public HomeRequest(ServerPlayerEntity player, String homeName) throws SQLException, ClassNotFoundException {
        RDIEssentials.createSQLConnection();
        this.sqlConn = RDIEssentials.SQL_CONN;
        this.player = player;
        this.homeName = homeName;
        this.uuid= PlayerUtils.getUUID(player);
        this.port=player.getServer().getServerPort();
    }
    private boolean setHome(boolean isWithLocation, Location hloc, boolean useAnotherHomeName, String anotherHomeName, boolean isActiv) throws SQLException{
        String playerName=player.getDisplayName().getString();
        String homeName;
        Location loca;

        if(isWithLocation)
            loca=hloc;
        else
            loca = new Location(player);

        if(useAnotherHomeName)
            homeName=anotherHomeName;
        else
            homeName=this.homeName;

        double x = loca.getX();
        double y = loca.getY();
        double z = loca.getZ();
        float w = loca.getYaw();
        float p = loca.getPitch();
        String dims=loca.getDims().toString();
        String st = "INSERT INTO home (uuid, playerName, homeName, port, dims, x, y, z, w, p, activ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = sqlConn.prepareStatement(st);
        stmt.setString(1, uuid);
        stmt.setString(2, playerName);
        stmt.setString(3, homeName);
        stmt.setInt(4,port);
        stmt.setString(5, dims);
        stmt.setDouble(6, x);
        stmt.setDouble(7, y);
        stmt.setDouble(8, z);
        stmt.setDouble(9, w);
        stmt.setDouble(10, p);
        stmt.setBoolean(11, isActiv);
        return stmt.executeUpdate()>0;
    }
    private boolean updateHome(boolean updName, String newHomeName,boolean updLocation,Location newLocation) throws SQLException {
        String stm;
        PreparedStatement pstm = null;
        if(updName){
            stm="UPDATE home SET homeName = ? WHERE port=? AND uuid = ? AND homeName = ?";
            pstm=sqlConn.prepareStatement(stm);
            pstm.setString(1,newHomeName);
            pstm.setInt(2,port);
            pstm.setString(3,uuid);
            pstm.setString(4,homeName);
        }
        if(updLocation){
            stm="UPDATE home SET x=?,y=?,z=? WHERE port=? AND uuid=? AND homeName=?";
            pstm=sqlConn.prepareStatement(stm);
            pstm.setDouble(1,newLocation.getX());
            pstm.setDouble(2,newLocation.getY());
            pstm.setDouble(3,newLocation.getZ());
            pstm.setInt(4,port);
            pstm.setString(5,uuid);
            pstm.setString(6,homeName);
        }
        return pstm.executeUpdate()>0;
    }
    public boolean setHome() throws SQLException {
        return setHome(false,null,false,null,false);
    }
    public boolean setHomeWithLocation(Location location2Set,String anotherHomeName, boolean isActiv) throws SQLException{
        return setHome(true,location2Set,true,anotherHomeName,isActiv);
    }
    public boolean renameHome(String newHomeName) throws SQLException {
        return updateHome(true,newHomeName,false,null);
    }
    public boolean setNewLocation(Location newLoca) throws SQLException {
        return updateHome(false,null,true, newLoca);
    }
    //玩家一共设置过几个家？
    public int getHomeCounts() throws SQLException {
        String stm="SELECT COUNT(*) AS rowcount FROM home WHERE uuid=? AND port=?;";
        PreparedStatement ps2=sqlConn.prepareStatement(stm);
        ps2.setString(1, uuid);
        ps2.setInt(2,port);
        ResultSet rs2 = ps2.executeQuery();
        rs2.next();
        return rs2.getInt("rowcount");
    }
    public boolean hasThisHome() throws SQLException {
        ResultSet rs3 = sqlConn.prepareStatement(
                "SELECT * FROM home WHERE uuid= '"+uuid+"' AND homeName='"+homeName+"' AND port='"+port+"'").executeQuery();
        return rs3.next();
    }
    @Nullable
    public Location getHomeLocation() throws SQLException{
        ResultSet rs3 = sqlConn.prepareStatement(
                "SELECT * FROM home WHERE uuid= '"+uuid+"' AND homeName='"+homeName+"' AND port='"+port+"'").executeQuery();
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
        return getHomeCounts() > HomeUtils.MAX_HOME;
    }
    public boolean delHome() throws SQLException {
        String selectSQL = "DELETE FROM home WHERE uuid ='" + uuid + "' AND homeName='"+homeName+"' AND port='"+port+"'";
        int rowsEff = sqlConn.prepareStatement(selectSQL).executeUpdate();
        return rowsEff!=0;
    }
    public boolean delAllHome() throws SQLException {
        String selectSQL = "DELETE FROM home WHERE uuid ='" + uuid + "' AND port='"+port+"'";
        int rowsEff = sqlConn.prepareStatement(selectSQL).executeUpdate();
        return rowsEff!=0;
    }
    public boolean hasActiveHome() throws SQLException{
        ResultSet rs4 = sqlConn.prepareStatement("SELECT * FROM home WHERE uuid= '"+uuid+"' AND activ='1' AND port='"+port+"'").executeQuery();
        return rs4.next();
    }
    public boolean isActive() throws SQLException {
        ResultSet rs3 = sqlConn.prepareStatement(
                "SELECT * FROM home WHERE uuid= '"+uuid+"' AND homeName='"+homeName+"' AND activ='1' AND port='"+port+"'").executeQuery();
        return rs3.next();
    }
    public boolean setActive(boolean activ) throws SQLException {
        int a;
        if(activ)
            a=1;
        else
            a=0;
        return sqlConn.prepareStatement("UPDATE home SET activ = '"+a+"' WHERE uuid='"+uuid+"' AND homeName='"+homeName+"' AND port='"+port+"'")
                .executeUpdate()>0;
    }
    public void goHome() throws SQLException{
        Location loc=getHomeLocation();
        PlayerUtils.teleportPlayer(player, loc);
    }
    /*public EHomeResult goHome() throws SQLException{
        ResultSet rs3 = sqlConn.prepareStatement(
                "SELECT * FROM home WHERE uuid= '"+uuid+"' AND homeName='"+homeName+"' AND port='"+port+"'").executeQuery();
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
            PlayerUtils.teleportPlayer(player, loc);
            return EHomeResult.OK;
        }
        else return EHomeResult.NO_ENOUGH_XP;

    }*/
    @Nullable
    public HashMap<String, HomeLocation> getHomeList() throws SQLException{
        ResultSet rs = sqlConn.prepareStatement(
                "SELECT * FROM home WHERE uuid= '"+uuid+"'").executeQuery();
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
