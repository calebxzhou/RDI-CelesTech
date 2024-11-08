package cn.davickk.rdi.essentials.general.thread.player;

import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.STitlePacket;

import java.util.ArrayList;
import java.util.List;

public class TechRollT extends Thread {
    private final ServerPlayerEntity player;
    private final int rollCount;

    private final String MC="minecraft:";
    private final String MEK="mekanism:";
    private final String AE="appliedenergistics2:";
    public TechRollT(ServerPlayerEntity player,int rollCount) {
        this.rollCount=rollCount;
        this.player = player;
    }

    public void run() {

        try {
            double base=0.4;
            if(this.rollCount<3){
                base=0.2;
            }
            List<Items2Give> itemList=new ArrayList<>();
            itemList.add(new Items2Give("experience","[1����]",base/2.0,1));
            itemList.add(new Items2Give(MC+"dirt","[����]",base,8));
            itemList.add(new Items2Give(MC+"jungle_sapling","[��������]",base/4.0,2));
            itemList.add(new Items2Give(MC+"nether_quartz_ore","[����ʯӢ��]",base/8.0,4));
            itemList.add(new Items2Give(MC+"ancient_debris","[Զ�Ųк�]",base/8.0,2));
            itemList.add(new Items2Give("experience","[3����]",base/6.0,3));
            itemList.add(new Items2Give(AE+"sky_stone_block","[16��ʯ]",base/8.0,16));
            itemList.add(new Items2Give(AE+"charged_quartz_ore","[����ʯӢ]",base/8.0,2));
            itemList.add(new Items2Give(AE+"calculation_processor_press","[����ѹӡ��]",base/15.0,1));
            itemList.add(new Items2Give(AE+"silicon_press","[��ѹӡ��]",base/15.0,1));
            itemList.add(new Items2Give(AE+"name_press","[����ѹӡ��]",base/20.0,1));
            itemList.add(new Items2Give(AE+"logic_processor_press","[�߼�ѹӡ��]",base/20.0,1));
            itemList.add(new Items2Give(AE+"sky_stone_block","[8��ʯ]",base/8.0,8));
            itemList.add(new Items2Give(AE+"engineering_processor_press","[����ѹӡ��]",base/20.0,1));

            itemList.add(new Items2Give(MEK+"fluorite_ore","[��ʯ]",base/6.0,2));

            itemList.add(new Items2Give("experience","[5����]",base/15.0,5));
            itemList.add(new Items2Give("creeper","[���������]",base/8.0,1));
            itemList.add(new Items2Give(MC+"cow_spawn_egg","[ţ]",base/12.0,1));
            itemList.add(new Items2Give(MC+"sheep_spawn_egg","[��]",base/12.0,1));
            itemList.add(new Items2Give(MC+"chicken_spawn_egg","[��]",base/12.0,1));
            itemList.add(new Items2Give(MC+"prismarine_crystals","[����ɰʯ]",base/12.0,1));
            /*IFormattableTextComponent text=new StringTextComponent("");
        for (int i=0;i<itemList.size();++i) {
            Items2Give itm=itemList.get(i);
            text.append(new StringTextComponent(itm.itemDescription));
            if(i%5==0)
                text.append(new StringTextComponent("\n"));
        }
        TextUtils.sendChatMessage(player,text);*/
            //for(Items2Give itm:itemList){
            boolean getPrize=false;
            String itemsGet="=";
            StringBuilder itemListStr=new StringBuilder();
            for(int i=0;i<this.rollCount;++i){
                int jcount=0;
                for(Items2Give itm:itemList){
                    itemListStr.append(itm.itemDescription);
                    Thread.sleep(200);
                    if(jcount%2==0){
                        TextUtils.sendActionMessage(player,EColor.BRIGHT_GREEN.code+itm.itemDescription+" <-O");

                    }
                    else{
                        TextUtils.sendActionMessage(player,"O-> "+itm.itemDescription);

                    }
                    /*TextUtils.sendTitle(player,TextUtils.getMiddleString(itemListStr.toString()), STitlePacket.Type.TITLE);
                    TextUtils.sendTitle(player,"��", STitlePacket.Type.SUBTITLE);*/
                    if(RandomUtils.randomPercentage(itm.percent)){
                        //Thread.sleep(1000);
                        TextUtils.sendChatMessage(player,"��ϲ��������"+EColor.GOLD.code+itm.itemDescription);
                        itemsGet=itemsGet.concat(itm.itemDescription);
                        getPrize=true;
                        if(itm.percent<0.05)
                            TextUtils.sendGlobalChatMessage(player.getServer().getPlayerList(), EColor.GOLD.code+player.getDisplayName().getString()+"������"+itm.itemDescription+"!!!!");
                        if(itm.itemId.equals("experience"))
                            player.giveExperienceLevels(itm.count);
                        else if(itm.itemId.equals("creeper")){
                            player.getServer().getCommands().performCommand(player.getServer().createCommandSourceStack(),"summon creeper "+player.getX()+" "+player.getY()+" "+player.getZ());
                            player.getServer().getCommands().performCommand(player.getServer().createCommandSourceStack(),"summon lightning_bolt "+player.getX()+" "+player.getY()+" "+player.getZ());
                        }else
                            PlayerUtils.givePlayerItem(player,itm.itemId,itm.count);
                    }
                    ++jcount;
                }
            }
            if(getPrize){
                TextUtils.sendChatMessage(player,"��һ��������������Ʒ��");
            }else{
                TextUtils.sendChatMessage(player,"�ƺ���ʲô��û����....Ҫ����һ����");
                TextUtils.clickableContent2Send(player,EColor.ORANGE.code+"[����һ�Σ�]","/rroll"," ");
            }
            ServerUtils.startThread(new RollRecordT(player,rollCount,itemsGet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5���� sapling 5�½�ʯӢ�� nether_quartz_ore
        // 5��ʯ appliedenergistics2:sky_stone_block  5��������˹�� charged_quartz_ore
        //ѹӡģ�� appliedenergistics2:calculation_processor_press
        //engineering logic silicon name
    }
    public class Items2Give {
        public String itemId;
        public String itemDescription;
        public double percent;
        public int count;
        public Items2Give(String ItemId,String ItemDescription,double percent,int count){
            this.itemId=ItemId;
            this.itemDescription=ItemDescription;
            this.percent=percent;
            this.count=count;
        }
    }
}
