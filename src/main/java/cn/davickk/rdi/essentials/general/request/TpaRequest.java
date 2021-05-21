package cn.davickk.rdi.essentials.general.request;

import cn.davickk.rdi.essentials.general.model.PlayerTpaRequest;

import java.util.HashMap;
import java.util.Map;

public class TpaRequest {
    private static Map<String,PlayerTpaRequest> REQ_MAP=new HashMap<>();
   /* private PlayerTpaRequest playerTpaRequest;
    private PlayerEntity fromPlayer;
    private PlayerEntity toPlayer;*/
    /*private String fromPlayerName;
    private String toPlayerName;
    private Connection sql;
    private SqlSession sqlSession;
    private String reqid;*/
    /*public TpaRequest(ServerPlayerEntity fromPlayer,ServerPlayerEntity toPlayer,String reqid) throws SQLException, ClassNotFoundException {
        sqlSession=RDIEssentials.getSQLUtils().getSqlSession();
        this.sql=sqlSession.getConnection();
        this.fromPlayer=fromPlayer;
        this.toPlayer=toPlayer;
        if(fromPlayer!=null)
            this.fromPlayerName=fromPlayer.getDisplayName().getString();
        this.toPlayerName=toPlayer.getDisplayName().getString();
        this.reqid=reqid;
    }*/
    /*public TpaRequest(PlayerTpaRequest ptrq){
        playerTpaRequest=ptrq;
        fromPlayer=ptrq.getFromPlayer();
        toPlayer=ptrq.getToPlayer();
    }*/
    public static Map<String,PlayerTpaRequest> getReqMap(){
        return REQ_MAP;
    }
    /*public ServerPlayerEntity getFromPlayer() throws SQLException{
        *//*PreparedStatement ps=sql.prepareStatement("SELECT * FROM tpa WHERE reqid=?");
        ps.setString(1,reqid);
        ResultSet rs=ps.executeQuery();
        if(!rs.next())
            return null;
        this.fromPlayerName=rs.getString("fromPlayer");
        this.fromPlayer=toPlayer.getServer().getPlayerList().getPlayerByUsername(this.fromPlayerName);
        return fromPlayer;*//*
    }*/
    /*public void sendRequest() throws SQLException {
        PreparedStatement ps=sql.prepareStatement("INSERT INTO tpa (reqid, reqtime, fromPlayer, toPlayer) VALUES (?,?,?,?)");
        ps.setString(1,reqid);
        ps.setTimestamp(2, DateTimeUtils.getTimestampNow());
        ps.setString(3,this.fromPlayerName);
        ps.setString(4,this.toPlayerName);
        ps.executeUpdate();
        sqlSession.commit();

        TextUtils.sendChatMessage(this.toPlayer,this.fromPlayer.getDisplayName().getString()+"想要传送到你的身边。");
        IFormattableTextComponent tpyes=TextUtils.getClickableContentComp(this.toPlayer, EColor.BRIGHT_GREEN.code+"[接受]"+EColor.RESET.code,"/tpyes "+playerTpaRequest.getReqId()," ");
        IFormattableTextComponent tpwait=TextUtils.getClickableContentComp(this.toPlayer, EColor.GOLD.code+"[等我一下]"+EColor.RESET.code,"稍等"," ");
        TextUtils.sendChatMessage(this.toPlayer,tpyes.append(tpwait));
        reqList.add(playerTpaRequest);
    }*/
    /*public boolean acceptRequest() throws SQLException {
        PreparedStatement ps=sql.prepareStatement("SELECT * FROM tpa WHERE reqid=?");
        ps.setString(1,reqid);
        ResultSet rs=ps.executeQuery();
        if(!rs.next())
            return false;
        Timestamp timesReq=rs.getTimestamp("reqtime");
        Timestamp timesNow=DateTimeUtils.getTimestampNow();
        if((timesNow.getTime()-timesReq.getTime()) > 60*1000)
            return false;
        double dis= WorldUtils.getDistanceXZ(new Location(fromPlayer),new Location(toPlayer));
        if(fromPlayer.experienceLevel<1 && toPlayer.experienceLevel<1){
            teleportPlayer1to2();
            return true;
        }
        int xp=0;
        if(dis>5000)
            xp=20;
        else if(dis>3000)
            xp=15;
        else if(dis>1000)
            xp=5;
        else if(dis>500)
            xp=3;
        else if(dis>200)
            xp=1;
        if(PlayerUtils.minusXPLvl(fromPlayer,xp)) {
            teleportPlayer1to2();
            return true;
        }else{
            TextUtils.sendChatMessage(fromPlayer,"本功能需要"+xp+"点经验，您的经验不足。");
            return false;
        }

    }*/
    private void teleportPlayer1to2(){
        //PlayerUtils.teleportPlayer(fromPlayer,new Location(toPlayer));
    }
    /*public void deleteRequest(PlayerTpaRequest ptpr){
        reqList.remove(ptpr);
        PreparedStatement ps=sql.prepareStatement("UPDATE tpa SET reqid='done' WHERE reqid=?");
        ps.setString(1,reqid);
        ps.executeUpdate();
        sqlSession.commit();
    }*/
}
