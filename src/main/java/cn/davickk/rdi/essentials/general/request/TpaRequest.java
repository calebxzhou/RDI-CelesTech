package cn.davickk.rdi.essentials.general.request;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import cn.davickk.rdi.essentials.general.util.TimeUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;

import java.sql.*;
import java.util.UUID;

public class TpaRequest {
    private ServerPlayerEntity fromPlayer;
    private final ServerPlayerEntity toPlayer;
    private String fromPlayerName;
    private final String toPlayerName;
    private final Connection sql;
    private final String reqid;
    public TpaRequest(ServerPlayerEntity fromPlayer,ServerPlayerEntity toPlayer,String reqid) throws SQLException {
        RDIEssentials.createSQLConnection();
        this.sql=RDIEssentials.SQL_CONN;
        this.fromPlayer=fromPlayer;
        this.toPlayer=toPlayer;
        if(fromPlayer!=null)
            this.fromPlayerName=fromPlayer.getDisplayName().getString();
        this.toPlayerName=toPlayer.getDisplayName().getString();
        this.reqid=reqid;
    }
    public ServerPlayerEntity getFromPlayer() throws SQLException{
        PreparedStatement ps=sql.prepareStatement("SELECT * FROM tpa WHERE reqid=?");
        ps.setString(1,reqid);
        ResultSet rs=ps.executeQuery();
        if(!rs.next())
            return null;
        this.fromPlayerName=rs.getString("fromPlayer");
        this.fromPlayer=toPlayer.getServer().getPlayerList().getPlayerByUsername(this.fromPlayerName);
        return fromPlayer;
    }
    public void sendRequest() throws SQLException {
        PreparedStatement ps=sql.prepareStatement("INSERT INTO tpa (reqid, reqtime, fromPlayer, toPlayer) VALUES (?,?,?,?)");
        ps.setString(1,reqid);
        ps.setTimestamp(2, TimeUtils.getTimestampNow());
        ps.setString(3,this.fromPlayerName);
        ps.setString(4,this.toPlayerName);
        ps.executeUpdate();
        TextUtils.sendChatMessage(this.toPlayer,this.fromPlayerName+"想要传送到你的身边。");
        IFormattableTextComponent tpyes=TextUtils.getClickableContentComp(this.toPlayer, EColor.BRIGHT_GREEN.code+"[接受]"+EColor.RESET.code,"/tpyes "+reqid," ");
        IFormattableTextComponent tpwait=TextUtils.getClickableContentComp(this.toPlayer, EColor.GOLD.code+"[等我一下]"+EColor.RESET.code,"稍等"," ");
        TextUtils.sendChatMessage(this.toPlayer,tpyes.append(tpwait));
    }
    public boolean acceptRequest() throws SQLException {
        PreparedStatement ps=sql.prepareStatement("SELECT * FROM tpa WHERE reqid=?");
        ps.setString(1,reqid);
        ResultSet rs=ps.executeQuery();
        if(!rs.next())
            return false;
        Timestamp timesReq=rs.getTimestamp("reqtime");
        Timestamp timesNow=TimeUtils.getTimestampNow();
        if((timesNow.getTime()-timesReq.getTime()) > 60*1000)
            return false;
        teleportPlayer1to2();
        return true;
    }
    private void teleportPlayer1to2(){
        PlayerUtils.teleportPlayer(fromPlayer,new Location(toPlayer));
    }
    public void denyRequest(){

    }
    public void deleteRequest(){

    }
}
