package cn.davickk.rdi.essentials.general.thread.player;

import cn.davickk.rdi.essentials.general.enums.EBack;
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
    public TechRollT(ServerPlayerEntity player) {
        this.player = player;

    }

    public void run() {
        List<Items2Give> itemList=new ArrayList<>();
        itemList.add(new Items2Give(MC+"dirt","[����]",0.2));
        itemList.add(new Items2Give(MC+"cobblestone","[Բʯ]",0.3));
        itemList.add(new Items2Give(MC+"jungle_sapling","[��������]",0.1));
        itemList.add(new Items2Give(MC+"nether_quartz_ore","[����ʯӢ��]",0.05));
        itemList.add(new Items2Give(MC+"netherite","[�½�Ͻ�]",0.05));
        itemList.add(new Items2Give(AE+"sky_stone_block","[��ʯ]",0.05));
        itemList.add(new Items2Give(AE+"charged_quartz_ore","[����ʯӢ]",0.05));
        itemList.add(new Items2Give(AE+"calculation_processor_press","[����ѹӡ��]",0.04));
        itemList.add(new Items2Give(AE+"silicon_processor_press","[��ѹӡ��]",0.04));
        itemList.add(new Items2Give(AE+"name_press","[����ѹӡ��]",0.02));
        itemList.add(new Items2Give(AE+"logic_processor_press","[�߼�ѹӡ��]",0.02));
        itemList.add(new Items2Give(AE+"engineering_processor_press","[����]",0.02));
        itemList.add(new Items2Give(MC+"cow_spawn_egg","[ţ]",0.02));
        itemList.add(new Items2Give(MC+"sheep_spawn_egg","[��]",0.02));
        itemList.add(new Items2Give(MC+"slime_spawn_egg","[ʷ��ķ]",0.02));
        /*IFormattableTextComponent text=new StringTextComponent("");
        for (int i=0;i<itemList.size();++i) {
            Items2Give itm=itemList.get(i);
            text.append(new StringTextComponent(itm.itemDescription));
            if(i==8)
                text.append(new StringTextComponent("\n"));
        }
        TextUtils.sendActionMessage(player,text);*/
        for(Items2Give itm:itemList){
            if(RandomUtils.randomPercentage(itm.percent)){
                try {
                    TextUtils.sendChatMessage(player,"��ϲ��������"+itm.itemDescription);
                    PlayerUtils.givePlayerItem(player,itm.itemId,1);
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                }
            }
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
        public Items2Give(String ItemId,String ItemDescription,double percent){
            this.itemId=ItemId;
            this.itemDescription=ItemDescription;
            this.percent=percent;
        }
    }
}
