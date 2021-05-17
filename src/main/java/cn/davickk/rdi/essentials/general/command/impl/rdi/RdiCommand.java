package cn.davickk.rdi.essentials.general.command.impl.rdi;

import cn.davickk.rdi.essentials.RDIEssentials;
import cn.davickk.rdi.essentials.general.command.BaseCommand;
import cn.davickk.rdi.essentials.general.enums.EColor;
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
        String ob2lav=EColor.RED.code+       "熔化黑曜石";
        String water2ice=EColor.AQUA.code+      "水变冰";
        String clearPhantom=EColor.STRIKE.code+ "幻翼";
        String removeGround=EColor.BRIGHT_GREEN.code+
                                                "查看延迟";

        String ys=EColor.ORANGE.code+           "召唤陨石";
        String tps=EColor.AQUA.code+       "查看延迟";
        String killme=EColor.RED.code+     "自鲨";
        String onHand=EColor.ORANGE.code+   "手上物品信息";
        String save="存档";
        sendMessage(player, EColor.AQUA.code+"--RDI Utilities "+ RDIEssentials.VERSION+"--");
        IFormattableTextComponent space=
                new StringTextComponent("   ");
        IFormattableTextComponent obsTxt=
                TextUtils.getClickableContentComp(player,ob2lav,"/obsi2lava","将黑曜石熔为岩浆");
        IFormattableTextComponent water2iceTxt=
                TextUtils.getClickableContentComp(player,water2ice,"/water2ice","让水结冰。（需要经验）");
        IFormattableTextComponent clearPhanTxt=
                TextUtils.getClickableContentComp(player,clearPhantom, "/clearphantom",
                        "花费："+(int)player.experienceLevel*0.5+"经验，立刻清除幻翼。");
        IFormattableTextComponent removeGroundTxt=
                TextUtils.getClickableContentComp(player,removeGround,"/tps","查看延迟。");
        IFormattableTextComponent ysTxt=
                TextUtils.getClickableContentComp(player,ys,"/ae2yunshi","在空旷的地方尝试，召唤一颗大陨石。");
        IFormattableTextComponent tpsTxt=
                TextUtils.getClickableContentComp(player,tps,"/tps","查看服务器延迟");
        IFormattableTextComponent kilmeTxt=
                TextUtils.getClickableContentComp(player,killme,"/shalewo8","鲨了我8");
        IFormattableTextComponent onHandTxt=
                TextUtils.getClickableContentComp(player,onHand,"/onhand","我的手上都有什么？");
        IFormattableTextComponent saveTxt=
                TextUtils.getClickableContentComp(player,save,"/SAVE","强制存档");
        /*IFormattableTextComponent forcekickTxt=
                TextUtils.getClickableContentComp(player,forcekick,"/kickme","强制退出服务器");*/
        TextUtils.sendChatMessage(player,obsTxt.append(space).append(water2iceTxt).append(space).append(clearPhanTxt));
        TextUtils.sendChatMessage(player,space);
        TextUtils.sendChatMessage(player,tpsTxt.append(space).append(kilmeTxt).append(space).append(onHandTxt));
        TextUtils.sendChatMessage(player,space);
        TextUtils.sendChatMessage(player,removeGroundTxt.append(space).append(ysTxt).append(space).append(saveTxt));
        return Command.SINGLE_SUCCESS;
    }
}
