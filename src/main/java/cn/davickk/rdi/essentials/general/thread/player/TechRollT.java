package cn.davickk.rdi.essentials.general.thread.player;

import cn.davickk.rdi.essentials.general.enums.EBack;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.BackRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TechRollT extends Thread {
    private final ServerPlayerEntity player;
    private final int rollCount;

    private final String MC="minecraft:";
    private final String AE="appliedenergistics2:";
    private final String MEK="mekanism:";

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
            itemList.add(new Items2Give("experience","[1经验]",base/2.0,1));
            itemList.add(new Items2Give(MC+"dirt","[泥土]",base,8));
            itemList.add(new Items2Give(MC+"jungle_sapling","[丛林树苗]",base/4.0,2));
            itemList.add(new Items2Give(MC+"nether_quartz_ore","[地狱石英矿]",base/8.0,4));
            itemList.add(new Items2Give(MC+"ancient_debris","[远古残骸]",base/8.0,2));
            itemList.add(new Items2Give("experience","[3经验]",base/6.0,3));
            itemList.add(new Items2Give(AE+"sky_stone_block","[16陨石]",base/8.0,16));
            itemList.add(new Items2Give(AE+"charged_quartz_ore","[充能石英]",base/8.0,2));
            itemList.add(new Items2Give(AE+"calculation_processor_press","[运算压印板]",base/15.0,1));
            itemList.add(new Items2Give(AE+"silicon_press","[硅压印板]",base/15.0,1));
            itemList.add(new Items2Give(AE+"name_press","[名称压印板]",base/20.0,1));
            itemList.add(new Items2Give(AE+"logic_processor_press","[逻辑压印板]",base/20.0,1));
            itemList.add(new Items2Give(AE+"engineering_processor_press","[工程压印板]",base/20.0,1));
            itemList.add(new Items2Give(MEK+"fluorite_ore","[氟石]",base/4.0,2));
            itemList.add(new Items2Give(AE+"sky_stone_block","[8陨石]",base/8.0,8));
            itemList.add(new Items2Give("experience","[5经验]",base/15.0,5));
            itemList.add(new Items2Give("creeper","[闪电苦力怕]",base/12.0,1));
        /*itemList.add(new Items2Give(MC+"cow_spawn_egg","[牛]",0.02));
        itemList.add(new Items2Give(MC+"sheep_spawn_egg","[羊]",0.02));
        itemList.add(new Items2Give(MC+"slime_spawn_egg","[史莱姆]",0.02));*/
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
            for(int i=0;i<this.rollCount;++i){
                int jcount=0;
                for(Items2Give itm:itemList){
                    Thread.sleep(200);
                    if(jcount%2==0)
                        TextUtils.sendActionMessage(player,EColor.BRIGHT_GREEN.code+itm.itemDescription+" <-O");
                    else
                        TextUtils.sendActionMessage(player,"O-> "+itm.itemDescription);
                    if(RandomUtils.randomPercentage(itm.percent)){
                        //Thread.sleep(1000);
                        TextUtils.sendChatMessage(player,"恭喜您抽中了"+EColor.GOLD.code+itm.itemDescription);
                        itemsGet=itemsGet.concat(itm.itemDescription);
                        getPrize=true;
                        if(itm.percent<0.05)
                            TextUtils.sendGlobalChatMessage(player.getServer().getPlayerList(), EColor.GOLD.code+player.getDisplayName().getString()+"抽中了"+itm.itemDescription+"!!!!");
                        if(itm.itemId.equals("experience"))
                            player.addExperienceLevel(itm.count);
                        else if(itm.itemId.equals("creeper")){
                            player.getServer().getCommandManager().handleCommand(player.getServer().getCommandSource(),"summon creeper "+player.getPosX()+" "+player.getPosY()+" "+player.getPosZ());
                            player.getServer().getCommandManager().handleCommand(player.getServer().getCommandSource(),"summon lightning_bolt "+player.getPosX()+" "+player.getPosY()+" "+player.getPosZ());
                        }else
                            PlayerUtils.givePlayerItem(player,itm.itemId,itm.count);
                    }
                    ++jcount;
                }
            }
            if(getPrize){
                TextUtils.sendChatMessage(player,"您一共抽中了以上物品。");
            }else{
                TextUtils.sendChatMessage(player,"似乎您什么都没抽中....要再试一次吗？");
                TextUtils.clickableContent2Send(player,EColor.ORANGE.code+"[再试一次！]","/rroll"," ");
            }
            ServerUtils.startThread(new RollRecordT(player,rollCount,itemsGet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5树苗 sapling 5下界石英矿 nether_quartz_ore
        // 5陨石 appliedenergistics2:sky_stone_block  5充能赛特斯矿 charged_quartz_ore
        //压印模板 appliedenergistics2:calculation_processor_press
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
