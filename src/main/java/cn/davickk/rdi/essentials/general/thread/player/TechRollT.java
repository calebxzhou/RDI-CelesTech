package cn.davickk.rdi.essentials.general.thread.player;

import cn.davickk.rdi.essentials.general.enums.EBack;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.lib.Location;
import cn.davickk.rdi.essentials.general.request.BackRequest;
import cn.davickk.rdi.essentials.general.util.PlayerUtils;
import cn.davickk.rdi.essentials.general.util.RandomUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TechRollT extends Thread {
    private final ServerPlayerEntity player;
    private final String MC="minecraft:";
    private final String AE="appliedenergistics2:";
    private final String MEK="mekanism:";
    public TechRollT(ServerPlayerEntity player) {
        this.player = player;

    }

    public void run() {
        List<Items2Give> itemList=new ArrayList<>();
        itemList.add(new Items2Give(MC+"dirt","[泥土]",0.4));
        itemList.add(new Items2Give(MEK+"fluorite_ore","[氟石]",0.1));
        itemList.add(new Items2Give(MC+"jungle_sapling","[丛林树苗]",0.1));
        itemList.add(new Items2Give(MC+"nether_quartz_ore","[地狱石英矿]",0.05));
        itemList.add(new Items2Give(MC+"ancient_debris","[远古残骸]",0.05));
        itemList.add(new Items2Give(AE+"sky_stone_block","[陨石]",0.05));
        itemList.add(new Items2Give(AE+"charged_quartz_ore","[充能石英]",0.05));
        itemList.add(new Items2Give(AE+"calculation_processor_press","[运算压印板]",0.04));
        itemList.add(new Items2Give(AE+"silicon_processor_press","[硅压印板]",0.04));
        itemList.add(new Items2Give(AE+"name_press","[名称压印板]",0.02));
        itemList.add(new Items2Give(AE+"logic_processor_press","[逻辑压印板]",0.02));
        itemList.add(new Items2Give(AE+"engineering_processor_press","[工程压印板]",0.02));
        itemList.add(new Items2Give(MC+"cow_spawn_egg","[牛]",0.02));
        itemList.add(new Items2Give(MC+"sheep_spawn_egg","[羊]",0.02));
        itemList.add(new Items2Give(MC+"slime_spawn_egg","[史莱姆]",0.02));
        /*IFormattableTextComponent text=new StringTextComponent("");
        for (int i=0;i<itemList.size();++i) {
            Items2Give itm=itemList.get(i);
            text.append(new StringTextComponent(itm.itemDescription));
            if(i%5==0)
                text.append(new StringTextComponent("\n"));
        }
        TextUtils.sendChatMessage(player,text);*/
        for(Items2Give itm:itemList){
            try {
                for(Items2Give itmm:itemList){
                    TextUtils.sendChatMessage(player,"转盘运行中，当前物品："+itmm.itemDescription);
                    Thread.sleep(100);
                }
                if(RandomUtils.randomPercentage(itm.percent)){
                        TextUtils.sendChatMessage(player,"转盘已停，恭喜您抽中了"+EColor.GOLD.code+itm.itemDescription);
                        if(itm.percent<0.1)
                            TextUtils.sendGlobalChatMessage(player.getServer().getPlayerList(), EColor.GOLD.code+player.getDisplayName().getString()+"抽中了"+itm.itemDescription+"!!!!");
                        if(itm.itemDescription.contains("泥土"))
                            PlayerUtils.givePlayerItem(player,itm.itemId,8);
                        else
                            PlayerUtils.givePlayerItem(player,itm.itemId,1);
                        return;

                }

            } catch (CommandSyntaxException | InterruptedException e) {
                    e.printStackTrace();
            }
        }
        TextUtils.sendChatMessage(player,"似乎您什么都没抽中....要再试一次吗？");
        TextUtils.clickableContent2Send(player,EColor.ORANGE.code+"[再试一次！]","/rroll","");
        //5树苗 sapling 5下界石英矿 nether_quartz_ore
        // 5陨石 appliedenergistics2:sky_stone_block  5充能赛特斯矿 charged_quartz_ore
        //压印模板 appliedenergistics2:calculation_processor_press
        //engineering logic silicon name
    }
    public class Items2Give {
        public String itemId;
        public String itemDescription;
        public double percent;
        public Items2Give(String ItemId,String ItemDescription,double percent){
            this.itemId=ItemId;
            this.itemDescription=ItemDescription;
            this.percent=percent;
        }
    }
}
