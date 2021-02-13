package cn.davickk.rdi.essentials.general.command.impl.rdi;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.command.impl.player.BackCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
import cn.davickk.rdi.essentials.general.thread.island.IslandMenuT;
import cn.davickk.rdi.essentials.general.util.ServerUtils;
import cn.davickk.rdi.essentials.general.util.TextUtils;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;

public class RdiCommand extends BaseCommand {
    public RdiCommand(String name, int permissionLevel, boolean enabled) {
        super(name, permissionLevel, enabled);
    }

    @Override
    public LiteralArgumentBuilder<CommandSource> setExecution() {
        return builder.executes((context) -> execute(context.getSource()));
    }
    private int execute(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player=source.asPlayer();
        String ob2lav=EColor.PURPLE.code+       "**�ۻ�����ʯ**";
        String water2ice=EColor.AQUA.code+      "$$$ˮ���$$$";
        String clearPhantom=EColor.STRIKE.code+ ":::::����:::::";
        String removeGround=EColor.BRIGHT_GREEN.code+
                                                "\\\\\\�Ƴ��ݵ�///";

        String ys=EColor.ORANGE.code+           "%%%�ٻ���ʯ%%%";
        String tps=EColor.DARK_AQUA.code+       "###�鿴�ӳ�###";
        String killme=EColor.DARK_RED.code+     "@@@@����@@@@";
        String onHand=EColor.DARK_GREEN.code+   "!������Ʒ��Ϣ!";
        String save="***�浵***";
        String forcekick="[ǿ���˳�������]";
        sendMessage(player, EColor.AQUA.code+"--RDI Utilities "+ RDIEssentials.VERSION+"--");
        IFormattableTextComponent space=
                new StringTextComponent("   ");
        IFormattableTextComponent obsTxt=
                TextUtils.getClickableContentComp(player,ob2lav,"/obsi2lava","������ʯ��Ϊ�ҽ�");
        IFormattableTextComponent water2iceTxt=
                TextUtils.getClickableContentComp(player,water2ice,"/water2ice","��ˮ���������Ҫ���飩");
        IFormattableTextComponent clearPhanTxt=
                TextUtils.getClickableContentComp(player,clearPhantom, "/clearphantom",
                        "���ѣ�"+(int)player.experienceLevel*0.5+"���飬�����������");
        IFormattableTextComponent removeGroundTxt=
                TextUtils.getClickableContentComp(player,removeGround,"/removeground","�Ƴ��·���½�ء�");
        IFormattableTextComponent ysTxt=
                TextUtils.getClickableContentComp(player,ys,"/ae2yunshi","�ڿտ��ĵط����ԣ��ٻ�һ�Ŵ���ʯ��");
        IFormattableTextComponent tpsTxt=
                TextUtils.getClickableContentComp(player,tps,"/tps","�鿴�������ӳ�");
        IFormattableTextComponent kilmeTxt=
                TextUtils.getClickableContentComp(player,killme,"/shalewo8","������8");
        IFormattableTextComponent onHandTxt=
                TextUtils.getClickableContentComp(player,onHand,"/onhand","�ҵ����϶���ʲô��");
        IFormattableTextComponent saveTxt=
                TextUtils.getClickableContentComp(player,save,"/SAVE","ǿ�ƴ浵");
        /*IFormattableTextComponent forcekickTxt=
                TextUtils.getClickableContentComp(player,forcekick,"/kickme","ǿ���˳�������");*/
        TextUtils.sendChatMessage(player,obsTxt.append(space).append(water2iceTxt).append(space).append(clearPhanTxt));
        TextUtils.sendChatMessage(player,tpsTxt.append(space).append(kilmeTxt).append(space).append(onHandTxt));
        TextUtils.sendChatMessage(player,removeGroundTxt.append(space).append(ysTxt).append(space).append(saveTxt));
        return Command.SINGLE_SUCCESS;
    }
}
