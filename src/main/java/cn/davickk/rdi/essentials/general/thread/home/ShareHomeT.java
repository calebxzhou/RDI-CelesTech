package cn.davickk.rdi.essentials.general.thread.home;

import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.HomeRequest;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ShareHomeT extends Thread{
    private final ServerPlayerEntity player;
    private final String homeName;
    private final String player2Share;
    public ShareHomeT(ServerPlayerEntity player,String homeName,String player2Share){
        this.player=player;
        this.homeName=homeName;
        this.player2Share=player2Share;
    }
    public void run(){
        /*try {
            ServerPlayerEntity player2=player.getServer().getPlayerList().getPlayerByUsername(player2Share);
            HomeRequest hreq=new HomeRequest(player,homeName);
            HomeRequest hreq2=new HomeRequest(player2,homeName);
            if(player2==null) {
                TextUtils.sendChatMessage(player, player2Share + "�����ߡ�");
                return;
            }
            *//*Iterator<PlayerEntity> nearby=PlayerUtils.getNearbyPlayersInRange(player,5).iterator();
            if(!nearby.hasNext()){
                TextUtils.sendChatMessage(player,"�Է�������ĸ���");
                return;
            }
            if(!nearby.next().getDisplayName().getString().equalsIgnoreCase(player2Share))
                TextUtils.sendChatMessage(player,"���㸽�����˲�����"+player2Share);*//*
            Location loca=hreq.getHomeLocation();
            if(loca==null) {
                TextUtils.sendChatMessage(player,"�޷���ȡλ�ã�ȷ��"+homeName+"������");
                return;
            }
            if(hreq2.hasThisHome()){
                //TextUtils.sendChatMessage(player,"�Է��Ѿ���"+homeName+"������ˣ��볢�Ը�������");

                hreq2.setHomeWithLocation(loca,homeName+"_other",true);
                TextUtils.sendChatMessage(player,"�ɹ������Ŀյ��������"+player2Share);
                TextUtils.sendChatMessage(player2,player.getDisplayName().getString()+"�����Ŀյ����������");
                TextUtils.clickableContent2Send(player2,"[���̴���]","/home "+homeName+"_other"," ");
                return;
            }
            if(hreq2.setHomeWithLocation(loca,homeName,true))
            {
                TextUtils.sendChatMessage(player,"�ɹ���"+homeName+"�������"+player2Share);
                TextUtils.sendChatMessage(player2,player.getDisplayName().getString()+"������"+homeName+"���������");
                TextUtils.clickableContent2Send(player2,"[���̴���]","/home "+homeName," ");


                return;
            }else TextUtils.sendChatMessage(player,"�����������ѯ����");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //.getDisplayName().getString().equalsIgnoreCase(player2Share)
    }
}
