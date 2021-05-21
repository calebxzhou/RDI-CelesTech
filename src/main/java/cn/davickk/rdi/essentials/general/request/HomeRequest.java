package cn.davickk.rdi.essentials.general.request;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.dao.IHomeDaoMapper;
import cn.davickk.rdi.essentials.general.model.Location;
import cn.davickk.rdi.essentials.general.model.Home;
import cn.davickk.rdi.essentials.general.util.HomeUtils;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import org.apache.ibatis.session.SqlSession;

import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HomeRequest{
    private final SqlSession SQL_SESSION =RDIEssentials.getSQLUtils().getSqlSession();
    private IHomeDaoMapper HOME_MAPPER;
    private final ServerPlayerEntity PLAYER;
    private final String UUID;
    private int PORT;
    private String HOMENAME;
    private String newHomeName;
    private Location location2Set;

    //正常情况
    public HomeRequest(ServerPlayerEntity player) throws SQLException, ClassNotFoundException {

        HOME_MAPPER = SQL_SESSION.getMapper(IHomeDaoMapper.class);
        this.PLAYER = player;
        this.UUID = PlayerUtils.getUUID(player);
        this.PORT =player.getServer().getPort();
    }
    public HomeRequest(ServerPlayerEntity player, String homeName) throws SQLException, ClassNotFoundException {
        HOME_MAPPER = SQL_SESSION.getMapper(IHomeDaoMapper.class);
        this.PLAYER = player;
        this.HOMENAME = homeName;
        this.UUID = PlayerUtils.getUUID(player);
        this.PORT =player.getServer().getPort();
    }
    private void setHome(boolean isWithLocation, Location hloc, boolean useAnotherHomeName, String anotherHomeName, boolean isActiv,String comment) {
        String playerName= PLAYER.getDisplayName().getString();
        String homeName;
        Location loca;

        if(isWithLocation)
            loca=hloc;
        else
            loca = new Location(PLAYER);

        if(useAnotherHomeName)
            homeName=anotherHomeName;
        else
            homeName=this.HOMENAME;

        double x = loca.getX();
        double y = loca.getY();
        double z = loca.getZ();
        float w = loca.getYaw();
        float p = loca.getPitch();
        String dims=loca.getDims().toString();
        Home home=new Home(UUID,playerName,homeName,dims,PORT,x,y,z,w,p,isActiv?1:0);
        home.setComment(comment);
        HOME_MAPPER.insertRecord(home);
        SQL_SESSION.commit();
        /*String st = "INSERT INTO home (uuid, playerName, homeName, port, dims, x, y, z, w, p, activ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = SQL_SESSION.prepareStatement(st);
        stmt.setString(1, UUID);
        stmt.setString(2, playerName);
        stmt.setString(3, homeName);
        stmt.setInt(4, PORT);
        stmt.setString(5, dims);
        stmt.setDouble(6, x);
        stmt.setDouble(7, y);
        stmt.setDouble(8, z);
        stmt.setDouble(9, w);
        stmt.setDouble(10, p);
        stmt.setBoolean(11, isActiv);*/
    }
    private void updateHome(boolean updName, String newHomeName,boolean updLocation,Location newLocation)  {
        if(updName){
            HOME_MAPPER.rename(UUID,HOMENAME,newHomeName);
            /*stm="UPDATE home SET homeName = ? WHERE port=? AND uuid = ? AND homeName = ?";
            pstm= SQL_SESSION.getConnection().prepareStatement(stm);
            pstm.setString(1,newHomeName);
            pstm.setInt(2, PORT);
            pstm.setString(3, UUID);
            pstm.setString(4, HOMENAME);*/
        }
        if(updLocation){
            HOME_MAPPER.locate(UUID,HOMENAME, newLocation.getX(), newLocation.getY(), newLocation.getZ());
            /*stm="UPDATE home SET x=?,y=?,z=? WHERE port=? AND uuid=? AND homeName=?";
            pstm= SQL_SESSION.getConnection().prepareStatement(stm);
            pstm.setDouble(1,newLocation.getX());
            pstm.setDouble(2,newLocation.getY());
            pstm.setDouble(3,newLocation.getZ());
            pstm.setInt(4, PORT);
            pstm.setString(5, UUID);
            pstm.setString(6, HOMENAME);*/
        }
        SQL_SESSION.commit();
    }
    public void setHome()  {
        setHome(false,null,false,null,false,"");
    }
    public void setHomeWithLocation(Location location2Set,String anotherHomeName, boolean isActiv,String comment) {
        setHome(true,location2Set,true,anotherHomeName,isActiv,comment);
    }
    public void renameHome(String newHomeName)  {
        updateHome(true,newHomeName,false,null);
    }
    public void setNewLocation(Location newLoca)  {
        updateHome(false,null,true, newLoca);
    }
    //玩家一共设置过几个家？
    public int getHomeCounts(){
        return HOME_MAPPER.getRecordAmount(UUID);

        /*String stm="SELECT COUNT(*) AS rowcount FROM home WHERE uuid=? AND port=?;";
        PreparedStatement ps2= SQL_SESSION.prepareStatement(stm);
        ps2.setString(1, UUID);
        ps2.setInt(2, PORT);
        ResultSet rs2 = ps2.executeQuery();
        rs2.next();
        return rs2.getInt("rowcount");*/
    }
    public boolean hasThisHome() {
        return HOME_MAPPER.exists(UUID, HOMENAME) == 1;
        /*ResultSet rs3 = SQL_SESSION.prepareStatement(
                "SELECT * FROM home WHERE uuid= '"+ UUID +"' AND homeName='"+ HOMENAME +"' AND port='"+ PORT +"'").executeQuery();
        return rs3.next();*/
    }
    @Nullable
    public Location getHomeLocation() {
        return HOME_MAPPER.getLocation(UUID,HOMENAME);
        /*ResultSet rs3 = SQL_SESSION.getConnection().prepareStatement(
                "SELECT * FROM home WHERE uuid= '"+ UUID +"' AND homeName='"+ HOMENAME +"' AND port='"+ PORT +"'").executeQuery();
        if(!rs3.next())
            return null;
        String dims=rs3.getString("dims");
        double x = rs3.getDouble("x");
        double y = rs3.getDouble("y");
        double z = rs3.getDouble("z");
        float w = rs3.getFloat("w");
        float p = rs3.getFloat("p");
        return new Location(x,y,z,w,p,dims);*/
    }
    public boolean isReachedMax() {
        return getHomeCounts() > HomeUtils.MAX_HOME;
    }
    public void delHome() {
        HOME_MAPPER.delete(UUID,HOMENAME);
        SQL_SESSION.commit();
        /*String selectSQL = "DELETE FROM home WHERE uuid ='" + UUID + "' AND homeName='"+ HOMENAME +"' AND port='"+ PORT +"'";
        int rowsEff = SQL_SESSION.prepareStatement(selectSQL).executeUpdate();
        return rowsEff!=0;*/
    }
    public void delAllHome() {
        HOME_MAPPER.delAllByUuid(UUID);
        SQL_SESSION.commit();
        /*String selectSQL = "DELETE FROM home WHERE uuid ='" + UUID + "' AND port='"+ PORT +"'";
        int rowsEff = SQL_SESSION.prepareStatement(selectSQL).executeUpdate();
        return rowsEff!=0;*/
    }
    public boolean hasActiveHome() {
        return HOME_MAPPER.hasActive(UUID) == 1;
        /*ResultSet rs4 = SQL_SESSION.prepareStatement("SELECT * FROM home WHERE uuid= '"+ UUID +"' AND activ='1' AND port='"+ PORT +"'").executeQuery();
        return rs4.next();*/
    }
    public boolean isActive(){
        return HOME_MAPPER.isActive(UUID,HOMENAME) == 1;
        /*ResultSet rs3 = SQL_SESSION.prepareStatement(
                "SELECT * FROM home WHERE uuid= '"+ UUID +"' AND homeName='"+ HOMENAME +"' AND activ='1' AND port='"+ PORT +"'").executeQuery();
        return rs3.next();*/
    }
    public void setActive(boolean activ) {
        HOME_MAPPER.setActive(UUID,HOMENAME,activ?1:0);
        SQL_SESSION.commit();
        /*int a;
        if(activ)
            a=1;
        else
            a=0;
        return SQL_SESSION.prepareStatement("UPDATE home SET activ = '"+a+"' WHERE uuid='"+ UUID +"' AND homeName='"+ HOMENAME +"' AND port='"+ PORT +"'")
                .executeUpdate()>0;*/
    }
    public void goHome() throws SQLException{
        Location loc=getHomeLocation();
        PlayerUtils.teleportPlayer(PLAYER, loc);
    }
    public void addComment(String comment){
        HOME_MAPPER.addComment(UUID,HOMENAME,comment);
        SQL_SESSION.commit();
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
    public List<Home> getHomeList() throws SQLException{
        List<Home> homeList=SQL_SESSION.selectList("getByPlayerUuid",UUID);
        return homeList;
        /*ResultSet rs = SQL_SESSION.getConnection().prepareStatement(
                "SELECT * FROM home WHERE uuid= '"+ UUID +"'").executeQuery();
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
        return homeMap;*/
    }

}
